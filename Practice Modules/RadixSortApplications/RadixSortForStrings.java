package RadixSortApplications;


import java.util.*;


/**
 * @author : Santiago Arellano
 * @Date: October 17th, 2024
 * @Description: The present file contains a hybrid, full-lexicographical support, radix, and merge sorting for Strings
 * given that <b>begin with at least one alphabetic character</b>. The algorithm works by linearly sorting each string into
 * their corresponding bins <b>(26 bins of strings each of them meant to hold as many strings as there are ones with a given
 * alphabet initial character)</b>. Once these are sorted, the program iteratively selects those who have a length of more
 * than two <b>(meaning there were more than one instance of letter 'x' as the initial character for different inputs)</b>.
 * <br><br>
 * Once it selects those that have duplicates, it applies a variant of merge sort through which <b>lexicographical</b> checks
 * are applied to determine whether they are lexicographically higher or lower than the other.
 * <br><br>
 * Finally, if the buckets are not empty we can append them to the bigger list which now is sorted.
 * @apiNote : The program excepts an instance of <code>String[]</code>, this means that it expects your input to be
 * compatible with <code>String and its underlying interfaces</code>. Moreover, the format for the strings tolerable by
 * this process are the following <code>"Santiago" or "santiago" or "sANTIAGO"</code>, since all of these would be first
 * distributed by the lowercase of their first character and ultimately sorted by our merge sort implementation.
 * @implNote : The program is <b>case-insensitive</b> and <b>ignores spaces</b> in the input strings. However, improvements
 * can be made in the way the merge sort algorithm works and the number of buckets (which could go from 26 to 36 to allow
 * for letter number too)
 */
public class RadixSortForStrings {

    /**
     * Main radix Sort method for strings, it takes care of null checking and of the internal radix sort logic. The program
     * creates various buckets and allocates each string into them depending of their corresponding initial character. If
     * there are multiple strings in a bucket, then it calls the external method mergeSort() to sort internally, based on
     * lexicographical concerns.
     * @param externalSource : The external source array to be sorted.
     * @param sourceSize : The size of the external source array.
     * @return : void
     * @throws NullPointerException : If the external source array is null.
     * @throws IllegalArgumentException : If the external source array is empty or the source size is zero.
     * @see #mergeSort(ArrayList)
     */
    public static void radixSortForStrings(String[] externalSource, int sourceSize)
            throws NullPointerException, IllegalArgumentException{

        if (externalSource == null){
            throw new NullPointerException("Input" + " externalSource" +
                    " cannot be null");
        }
        if (externalSource.length == 0 || externalSource.length != sourceSize){
            throw new IllegalArgumentException("Input" + (externalSource.length == 0 ? "externalSource":"sourceSize") +
                    " is invalid");
        }

        //! Character buckets
        final int characters = 26;

        //! Create the buckets
        LinkedHashMap<Character, ArrayList<String>> buckets = new LinkedHashMap<>();
        final String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < characters; i++) {
            buckets.put(chars.charAt(i), new ArrayList<>());
        }
        //! Using the first character in each input, sort them into buckets
        for(int inputIndex = 0; inputIndex < sourceSize; inputIndex++){
            char firstCharacter = externalSource[inputIndex].toLowerCase().charAt(0);
            int totalInputs = buckets.get(firstCharacter).size();
            buckets.get(firstCharacter).add(externalSource[inputIndex]);
        }


        //! We look at each array, and those who have more than two elements get internally sorted
        for (int i = 0; i < characters; i ++)
        {
            if(buckets.get(chars.charAt(i)).size() >= 2){
                //! Sort by Subdividing into two arrays and merging each one in place
                mergeSort(buckets.get(chars.charAt(i)));
            }
        }

        //! Append each value to the source again
        int placeOutside = 0;
        for(int i = 0; i < characters; i ++){
            ArrayList<String> bucket = buckets.get(chars.charAt(i));
            if (!bucket.isEmpty()){
                for(int j = 0; j < bucket.size(); j++){
                    externalSource[placeOutside++] = bucket.get(j);
                }
            }
        }

    }


    public static void mergeSort(ArrayList<String> externalData)
    {
        String[] copyOfValues = externalData.toArray(new String[0]);
        mergeSortHelper(copyOfValues);
        externalData.clear();
        Arrays.stream(copyOfValues).forEach(externalData::add);
    }

    public static void mergeSortHelper(String[] externalData){
        //! The main concept remains: partition into arrays on one element, sort based on their second element. Keep going
        if (externalData.length > 1){
            int mid = externalData.length / 2; //* this should be the precise half of the array, i.e numerical half, treat it
            //* as if it always needed a substraction of one when working with indices
            String[] leftHalf = new String[mid];
            System.arraycopy(externalData, 0, leftHalf, 0, mid);
            mergeSortHelper(leftHalf);

            //! Right Half
            // Merge sort the second half
            int secondHalfLength = externalData.length - mid;
            String[] rightHalf = new String[secondHalfLength];
            System.arraycopy(externalData, mid, rightHalf, 0, secondHalfLength);
            mergeSortHelper(rightHalf);


            //! Merge
            String[] temp = mergeTwoArrays(leftHalf, rightHalf);
            System.arraycopy(temp, 0, externalData, 0, temp.length);
        }
    }

    public static String[] mergeTwoArrays(String[] left, String[] right)
    {
        //! The bulk of the work happens here:
        /**
         * I was thinking of working like this:
         * In any given moment with this array we might have strings of equal length or mismatche length. So our first
         * priority is to optimize for both cases
         * (1) Check for string length.
         * Moreover, when we are working with strings, we do not know when two will stop being equal with their characters.
         * So the idea would be to check until they stop being equal and once they stop we revise.
         * (2) Check for inequality of characters
         * Lastly, we need to consider that these left and right arrays might not be of the same length either, since
         * the division is not all the time accurate, we ought to consider these running out of space.
         * (3) Check indeces on arrays
         */

        String[] tempArray = new String[left.length + right.length];
        int currentLeft = 0;
        int currentRight = 0;
        int currentTemp = 0;

        while (currentLeft < left.length && currentRight < right.length){ //*This satisfies condition three
            String leftInput = left[currentLeft], rightInput = right[currentRight];
            //! We proceed to check while both have inputs
            int min = Math.min(leftInput.length(), rightInput.length());
            int result = 0;
            for(int i = 0; i < min; i++){
                if (leftInput.charAt(i) != rightInput.charAt(i)){
                    result = leftInput.charAt(i) - rightInput.charAt(i);
                    break;
                }
            }
            if (result == 0){
                result = leftInput.length() - rightInput.length();
            }
            if (result < 0){
                tempArray[currentTemp++] = leftInput;
                currentLeft++;
            } else {
                tempArray[currentTemp++] = rightInput;
                currentRight++;
            }
        }

        //! This will inevitably leave data out so we use our normal logic to pass them in
        while (currentLeft < left.length){
            tempArray[currentTemp++] = left[currentLeft++];
        }

        while (currentRight < right.length){
            tempArray[currentTemp++] = right[currentRight++];
        }

        return tempArray;
    }

    public static void main(String[] args) {
        String[] arr = {"Santiago","Sen","Maria","Juan","Sofia", "Sebastian","Carlos","Carlota"};
        radixSortForStrings(arr, arr.length);
        //Arrays.sort(arr);
        System.out.println(Arrays.asList(arr));
    }

}
