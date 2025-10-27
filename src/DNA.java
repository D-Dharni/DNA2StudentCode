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

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */

    public static final int radix = 256;
    public static final long modulo = 1000000007;

    public static int STRCount(String sequence, String STR) {
        int lengthSequence = sequence.length();
        int lengthTandem = STR.length();

        // Base case
        if (lengthSequence < lengthTandem) {
            return 0;
        }

        long strHash = 1;
        // Value for removing left most character for rolling hash (radix^m-1)
        for (int i = 1; i < lengthTandem; i++) {
            strHash = (strHash * radix) % modulo;
        }

        long tandemHash = 0;
        // Compute the hash of the tandem
        for (int i = 0; i < lengthTandem; i++) {
            // Horner's formula: h = (h * R + t.charAt(i)) % p
            tandemHash = (tandemHash * radix + STR.charAt(i)) % modulo;
        }

        // Compute hash of the first window in the sequence
        long sequenceHash = 0;
        for (int i = 0; i < lengthTandem; i++) {
            // Horner's formula
            sequenceHash = (sequenceHash * radix + sequence.charAt(i)) % modulo;
        }

        int numberRepeat = 0;

        // Loop through every window in the sequence
        for (int i = 0; i <= lengthSequence - lengthTandem; i++) {
            // Check if hashing sequence is right
            if (sequenceHash == tandemHash) {
                // Assume correct (Monte Carlo)

                // Variable to keep track of consecutive repeats
                int count = 0;

                // Marks the current position for the next repeat
                int j = i;

                // Loop through each window
                while (j + lengthTandem <= lengthSequence) {
                    // Compute hash for next chunk of same length
                    long nextHash = 0;

                    for (int g = 0; g < lengthTandem; g++) {
                        nextHash = (nextHash * radix + sequence.charAt(j + g)) % modulo;
                    }

                    // Increment if windows are the same and slide over
                    if (nextHash == tandemHash) {
                        count++;
                        j += lengthTandem;
                    }
                    else {
                        break;
                    }
                }

                // Change if it's the longest streak
                if (count > numberRepeat) {
                    numberRepeat = count;
                }
            }

            // Implement rolling hash
            if (i + lengthTandem < lengthSequence) {
                // Remove the old leftmost character
                sequenceHash = (sequenceHash + modulo - strHash * sequence.charAt(i) % modulo) % modulo;

                // Make space and add the new character
                sequenceHash = (sequenceHash * radix + sequence.charAt(i+lengthTandem)) % modulo;
            }
        }
        return numberRepeat;
    }
}