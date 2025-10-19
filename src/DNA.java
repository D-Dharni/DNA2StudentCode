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
    public static int STRCount(String sequence, String STR) {
        // Create an array to store the numbers for each letter in sequence

        // Make variable to keep track of the maximum number of counts

        // Go through the sequence from the back to the front

            // Check if the substring at that position matches the STR

            // If it does match:

                // Look ahead to see if another repeat is after

                // If there is then set the index equal to 1 + the number after

                // Otherwise set it to 1

                // Update the maximum count if the value is bigger

            // If no match set it equal to 0 at the index

        // Return whatever the maximum count was
        return 0;
    }
}
