package DeberTresStringMatchingAnalysisSantiagoArellano;

/**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: La presente clase contiene los metodos de brute force y Bayes-Moore para identificar cadenas dentro de
 * un texto que coincidan con un patron dado. Su codigo fue modificado para poder registrar el numero de comparaciones que
 * cada una realiza
 ============================================**/


public class StringMatchingHelper {


    /**
     * Performs brute-force string matching to find the first occurrence of a pattern in the given text.
     * The algorithm also counts the number of comparisons made during the search.
     *
     * @param textAndPatternDTO A Data Transfer Object that contains the text to search in and the pattern to search for.
     * @return A DeberTresStringMatchingAnalysisSantiagoArellano.PatternMatcherResultsDTO object that contains the result of the search, the number of comparisons made,
     *         and the index where the pattern was found in the text.
     */
    public static PatternMatcherResultsDTO bruteForceStringMatching(TextAndPatternDTO textAndPatternDTO) {
        int countOfComparisons = 0;
        boolean isMatch = false;
        int indexFound = -1;
        int textLength = textAndPatternDTO.getM_Text().length();
        int patternLength = textAndPatternDTO.getM_Pattern().length();

        for (int i = 0; i <= textLength - patternLength; i++) {
            int j;
            for (j = 0; j < patternLength; j++) {
                countOfComparisons++;
                if (textAndPatternDTO.getM_Text().charAt(i + j) != textAndPatternDTO.getM_Pattern().charAt(j)) {
                    break;
                }
            }
            if (j == patternLength) {
                isMatch = true;
                indexFound = i;
                break;
            }
        }

        return new PatternMatcherResultsDTO(isMatch, textAndPatternDTO, countOfComparisons, indexFound);
    }

    //! Implementation of the Boyer-Moore Algorithm
    public static PatternMatcherResultsDTO BoyerMooreMatching(TextAndPatternDTO textAndPatternDTO) {
        int index = textAndPatternDTO.getM_Pattern().length() - 1;
        int comparisons = 0; // Initialize comparison counter

        while (index < textAndPatternDTO.getM_Text().length()) {
            int kindex = index;
            int jindex = textAndPatternDTO.getM_Pattern().length() - 1;

            while (jindex >= 0) {
                comparisons++; // Increment comparisons counter

                if (textAndPatternDTO.getM_Text().charAt(kindex) == textAndPatternDTO.getM_Pattern().charAt(jindex)) {
                    kindex--;
                    jindex--;
                } else {
                    break;
                }
            }

            if (jindex < 0) {
                return new PatternMatcherResultsDTO(true,
                        new TextAndPatternDTO(textAndPatternDTO.getM_Text(),
                                textAndPatternDTO.getM_Pattern(),  textAndPatternDTO.getM_willMatch()),
                        comparisons,
                        kindex + 1);
            }

            int u = findLastIndex(textAndPatternDTO.getM_Text().charAt(kindex), jindex - 1, textAndPatternDTO.getM_Pattern());
            if (u >= 0) {
                index = kindex + (textAndPatternDTO.getM_Pattern().length() - 1) - u;
            } else {
                index = kindex + textAndPatternDTO.getM_Pattern().length();
            }
        }

        return new PatternMatcherResultsDTO(false,
                new TextAndPatternDTO(textAndPatternDTO.getM_Text(), textAndPatternDTO.getM_Pattern(),
                        textAndPatternDTO.getM_willMatch()),
                comparisons,
                -1);
    }

    private static int findLastIndex(char ch, int j, String pattern) {
        for (int k = j; k >= 0; k--) {
            if (ch == pattern.charAt(k)) {
                return k;
            }
        }
        return -1;
    }

//    public static void main(String[] args) {
//        DeberTresStringMatchingAnalysisSantiagoArellano.TextAndPatternDTO textAndPatternDTO = new DeberTresStringMatchingAnalysisSantiagoArellano.TextAndPatternDTO("Hello, world!", "world", true);
//        DeberTresStringMatchingAnalysisSantiagoArellano.PatternMatcherResultsDTO result = bruteForceStringMatching(textAndPatternDTO);
//        System.out.println(result);
//
//        DeberTresStringMatchingAnalysisSantiagoArellano.PatternMatcherResultsDTO result2 = BoyerMooreMatching(textAndPatternDTO);
//        System.out.println(result2);
//    }
}
