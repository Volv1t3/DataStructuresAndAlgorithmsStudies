package ProblemSolvingAlgorithms;


import java.util.*;
import java.math.*;
import java.lang.*;

public class GaiusLetters {
    public static void main(String[] args) {
    /*
    * This problem involves cryptography, there is a single input which we can use to kind of find a pattern between the letters. I think
    * our best bet is to try and approximate with the letters we know the letters that we do not. It probably wants you to, given an input
    * string that is coded, return the normal string using the letters that you know.
    *
    * Now if we were to use a stack to store the information about the letters in the input, we would reverse them. Maybe
    * what we could do is to approach this through a hashmap, and then take a look at the letters we are missing, these
    * letters can be added manually into the system like this
    */
        final String inputTest = "U iuxx nq mf ftq eqzmfq fapmk fa tqmd m bqfufuaz rday Fuxxuge. Omeeuge mzp Ndgfge tmhq nqqz mofuzs efdmzsq. Etagxp nq nmow uz fuyq rad puzzqd";
        final String outputTest = "I will be at the senate today to hear a petition from Tillius. Cassius and Brutus have been acting strange. Should be back in time for dinner";
        final String inputTest2 = "Ftue ime ftq xmef xqffqd ragzp uz ftq mdotuhq. Ftq agfbgf ue ftq pqodkbfqp hqdeuaz ar ftq qzodkbfqp uzbgf.";
        // Preload the characters into a hashmap
        LinkedHashMap<Character, Character> conversionNormalWithChyper = new LinkedHashMap<>();
        for (int i = 0; i < inputTest.length(); i++) {
            char inChar = inputTest.charAt(i);
            char outChar = outputTest.charAt(i);
            if (!conversionNormalWithChyper.containsKey(inChar)) {
                conversionNormalWithChyper.put(inChar, outChar);
            }
        }
        for (int i = 0; i < inputTest2.length(); i++) {
            char inChar = inputTest2.charAt(i);
            if (!conversionNormalWithChyper.containsKey(inChar)) {
                conversionNormalWithChyper.put(inChar, ' ');
            }
        }

        // Print the mapped characters and their ASCII values
        for (Map.Entry<Character, Character> entry : conversionNormalWithChyper.entrySet()) {
            System.out.println(entry.getKey() + " (" + (int) entry.getKey() + ") -> " + entry.getValue() + " (" + (int) entry.getValue() + ")");
        }

    }
}
