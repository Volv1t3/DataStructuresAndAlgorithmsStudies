package ProblemSolvingAlgorithms;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CaesarRedux {
    public static void main(String[] args) {
        /*
         * The basis of this problem is again a sort of cipher, the cipher, however, will be told to use. Since we are using
         * the Caesar Redux Cipher, we are basically going to shift letters around. We are
         * looking at an algorithm where we should generally calculate a linkedHashMap where we can hold the mapping
         * for each original lowercase letter and go back and forth in here.
         *
         * The problem doesn't lie here, however; it lies in the way are told to identify a string that is a cipher and
         * one that isn't. This is the issue. If we know that the ASCII codes, then maybe we can try to do some kind of
         * modulo operation to decipher the puzzle.
         *
         * It works by shifting the letters in the plaintext message by a certain number of positions.
         *  Decryption is performed by shifting in the opposite direction by the same number of positions.
         *
         * In one of the examples that we are given we are told that the radix for switching is 19, and that a letter is
         * w and the resulting encoded one is d. Through observation, we notice that w had three spots left until it went
         * up to 26, then we wrapped around and we completed four more moves from the beginning of the list. This leads
         * me to believe that we are using (as suggested by Jetbrains AI too) a modulo operator, and we are moving
         * how many radix % 26 letters forward from each location
         *
         * Also, note that the problem says that we should find the word "the" inside the inputs to determine if its
         * plain text or if its cyphertext. Therefore, our approach should be:
         *
         * 1. Break down the line into segments, push all of those segments into lowercase through mapping,
         * 2. Iterate over the split words to find the keyword "the" if it is there we branch to step 2.1
         *      2.1 We apply the cypher algorithm to transform it into a ciphertext,
         *    If the result was no 'the' in the array, then we do 2.2
         *      2.2 We apply the cypher directly and print to the standard output the transformation
         */

        Scanner scanner = new Scanner(System.in);
        Integer totalCases = scanner.nextInt();
        scanner.nextLine();

        for (int stringInput = 0; stringInput < totalCases; stringInput++) {
            Integer radixVariation = scanner.nextInt();
            scanner.nextLine();
            String input = scanner.nextLine();
            //! Open first case, some method to determine if the thing is plaintext.
            String[] splitInput = Arrays.stream(input.split(" "))
                    .map(String::toLowerCase)
                    .toArray(String[]::new);
            boolean isThePresent = Arrays.stream(splitInput).map(String::toLowerCase)
                    .anyMatch(word -> word.equals("the"));
            if (isThePresent){
                //! Apply the cypher to transform the text into cypher text
                var name = caesarRadixModification(input, radixVariation, true);
                System.out.println(name);
            }
            else{
                //! Apply the cypher to transform ciphertext into plaintext
                var name = caesarRadixModification(input, radixVariation, false);
                System.out.println(name);
            }

        }
    }
    private static String caesarRadixModification(String input, Integer radix, boolean toCypher) {
        /*
         * The logic behind this is that if we go around the end of the 26 letters, i.e., 26%26 then we can simply restart
         * from zero until the count is done. So this is what we are going to do.
         */
        Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        StringBuilder result = new StringBuilder();
        if (toCypher) {
            for(int i = 0; i < input.length(); i++) {
                //! Check the letter in here.
                char letter = input.charAt(i);
                if (Character.isSpaceChar(letter)){
                    result.append(" ");
                    continue;
                }
                int indexOfLetter = Arrays.asList(letters).indexOf(letter);
                int newRadix = 26 - radix;
                if (indexOfLetter + newRadix < 26){
                    result.append(letters[indexOfLetter + newRadix]);
                }
                else {
                    int remainder = (indexOfLetter + newRadix) % 26;
                    result.append(letters[remainder]);
                }
            }
        }
        else{
            for(int i = 0; i < input.length(); i++) {
                //! Check the letter in here.
                char letter = input.charAt(i);
                if (Character.isSpaceChar(letter)){
                    result.append(" ");
                    continue;
                }
                int indexOfLetter = Arrays.asList(letters).indexOf(letter);
                int newRadix = 26 - radix;
                if (indexOfLetter - newRadix >= 0){
                    result.append(letters[indexOfLetter - newRadix]);
                }
                else {
                    int remainder = (indexOfLetter - newRadix) + 26;
                    result.append(letters[remainder]);
                }
            }
        }

        return result.toString();
    }
}
