package ProblemSolvingAlgorithms;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
import org.apache.commons.math3.util.CombinatoricsUtils;
public class RestaurantCypher {
    public static void main(String[] args) {
        /*
         * The goal of this algorithm is to eat lines, literally, and through these what we could do is analyze the string
         * and probably input the string and break it down into chunks. If we get a string and we can break it down we can
         * then further filter down those characters that are part of our list of cyphers. If we can find those cyphers
         * then it would be useful for us to record them.
         */
        final Character[] IMPORTANT_CHARACTERS = {'A','B','C','D','E','F','G'};
        Set<Character> importantSet = new HashSet<>(Arrays.asList(IMPORTANT_CHARACTERS));
        Scanner scanner = new Scanner(System.in);
        Integer numberOfLines = scanner.nextInt();
        scanner.nextLine();
        for (int lineRead = 0; lineRead < numberOfLines; lineRead++) {
            LinkedHashMap<Character, Integer> countOfCharacters = new LinkedHashMap<>();
            String lineAtAnalysis = scanner.nextLine().toUpperCase();
            /*
             * Since we don't need to count anything not inside the IMPORTANT_CHARACTERS array, then we can
             * ignore those and drop them. In general, what we could do is basically take the String and analyze every character
             * only appending it with a counter in the case that it belongs to the aforementioned array.
             */
            for (Character important : IMPORTANT_CHARACTERS) {
                countOfCharacters.put(important,0);
            }
            //! Review the string and append it
            for(char c : lineAtAnalysis.toCharArray()){
                if (importantSet.contains(Character.toUpperCase(c))){
                    countOfCharacters.put(Character.toUpperCase(c), countOfCharacters.get(Character.toUpperCase(c)) +1);
                }
            }
            Character max = Collections.max(countOfCharacters.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println(max);

            countOfCharacters.values().clear();
        }
    }
}
