import static java.util.Objects.hash;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Deven Dharni
 *</p>
 */

public class DNA {
    // Constants for use throughout the code
    public static final int radix = 256;
    public static final long modulo = 1000000007;

    public static int STRCount(String sequence, String STR) {
        int sequenceLength = sequence.length();
        int STRLength = STR.length();

        // Base case:
        if (sequenceLength < STRLength) {
            return 0;
        }

        // Get value for radix^(m-1) to subtract during rolling hash
        long rollingHash = 1;
        for (int i = 0; i < STRLength - 1; i++) {
            rollingHash = (rollingHash * radix) % modulo;
        }

        // Compute hash for sequence for first window
        long sequenceHash = calculateHash(sequence, STRLength, 0);


        // Compute hash for the STR
        long STRHash = calculateHash(STR, STRLength, 0);

        // Rabin-Karp Algorithm:

        int highestRun = 0;
        int currentRun = 0;

        // Loop from 0 to the last possible "window" for the STR
        for (int i = 0; i <= sequenceLength - STRLength;) {
            // Use Monte Carlo (assume correct)
            if (STRHash == sequenceHash) {
                // There is a match
                currentRun++;

                // Increase index to check for next one
                i += STRLength;

                // Check if you can go another tandem repeat
                if (i <= sequenceLength - STRLength) {
                    // Recalculate hash for this value
                    sequenceHash = calculateHash(sequence, STRLength, i);
                }
                // Not enough room for another STR
                else {
                    break;
                }
            }
            // No match
            else {
                // Check if new record
                if (highestRun < currentRun) {
                    highestRun = currentRun;
                }

                // Run just broke and 'i' is at the position where it did
                if (currentRun > 0) {
                    // Restart the search from the character after not a whole new window

                    // Index of where run ended
                    int startOfRunEnd = i - (currentRun * STRLength);

                    // Set to run after
                    i = startOfRunEnd + 1;

                    // Recalculate hash using standard method
                    if (i <= sequenceLength - STRLength) {
                        sequenceHash = calculateHash (sequence, STRLength, i);
                    }
                    else {
                        break;
                    }
                }
                else {
                    // Use Rabin-Karp to roll to next window
                    if (i + STRLength < sequenceLength) {
                        // Remove the original hash using the precomputed value
                        long originalHash = (rollingHash * sequence.charAt(i)) % modulo;
                        sequenceHash = (sequenceHash - originalHash + modulo) % modulo;

                        // Shift the hash
                        sequenceHash = (sequenceHash * radix) % modulo;

                        // Add the hash value of the new char
                        sequenceHash = (sequenceHash + sequence.charAt(i + STRLength)) % modulo;
                    }

                    // Move to next character's window
                    i++;
                }
                // Reset
                currentRun = 0;
            }
        }

        // Return the highest run
        if (highestRun < currentRun) {
            return currentRun;
        }
        return highestRun;
    }

    public static long calculateHash (String str, int length, int startingValue) {
        // Set the initial variable to zero
        long strHash = 0;

        // Go from the startingValue so that hash can be calculated while within the sequence
        for (int i = startingValue; i < startingValue + length; i++) {
            // Using Horner's method
            strHash = (strHash * radix + str.charAt(i)) % modulo;
        }
        return strHash;
    }
}