package DeberTresStringMatchingAnalysisSantiagoArellano;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: La presente clase contiene metodos de interlace entre el programa cliente y el resto de las clases
 * helper del programa. De esta manera facilita el analisis estadistico de los resultados
 ============================================**/

public class StatisticalAnalysisInterface {

    public static int TOTALDATAANALYSIS = 1000;
    public static int TEXTLENGTH = 50;
    public static int PATTERNLENGTH = 5;
    //! Implementacion de un metodo para realizar un analisis de 2000 strings,1000 con posible match 1000 sin match
    public static StatisticalAnalysisDTO[] performBruteForceAnalysis() {

        //! Generate an 1000 value long ArrayListSA of DTOs where their pattern should match
        ArrayList<TextAndPatternDTO> m_TextAndPatternDTOs = new ArrayList<>();
        IntStream.rangeClosed(1, TOTALDATAANALYSIS)
                .parallel()
                .forEach(i -> m_TextAndPatternDTOs
                        .add(StringGeneratorHelper
                                .generateStringDTO(TEXTLENGTH, PATTERNLENGTH, true)));
        //! Perform one set of analysis
        ArrayList<PatternMatcherResultsDTO> shouldMatchComparisonStats = m_TextAndPatternDTOs
                .parallelStream()
                        .map(StringMatchingHelper::bruteForceStringMatching)
                                .collect(Collectors.toCollection(ArrayList::new));
        StatisticalAnalysisDTO bruteForceMatching =  performStatisticalAnalysis(
                shouldMatchComparisonStats.stream().map(
                        PatternMatcherResultsDTO::getM_CountOfComparisons)
                        .collect(Collectors.toCollection(ArrayList::new)));

        //! Generate a 1000 value long Arraylist of DTOS where their pattern should not match
        IntStream.rangeClosed(1, TOTALDATAANALYSIS)
                .parallel()
                .forEach(i -> m_TextAndPatternDTOs
                        .add(StringGeneratorHelper
                                .generateStringDTO(TEXTLENGTH, PATTERNLENGTH, false)));

        //! Perform the Statistical Analysis for Each Group in Parallel
        ArrayList<PatternMatcherResultsDTO> shouldNotMatchComparisonStats = m_TextAndPatternDTOs
                .parallelStream()
                .map(StringMatchingHelper::bruteForceStringMatching)
                .collect(Collectors.toCollection(ArrayList::new));
        StatisticalAnalysisDTO bruteForceNotMatching = performStatisticalAnalysis(
                shouldNotMatchComparisonStats.stream().map(
                        PatternMatcherResultsDTO::getM_CountOfComparisons)
                        .collect(Collectors.toCollection(ArrayList::new)));

        return new StatisticalAnalysisDTO[]{bruteForceMatching, bruteForceNotMatching};
    }
    //! Esqueleto de un metodo para realizar un analisis de 2000 strings, 1000 con posible match 1000 sin match Boyer-Moore
    public static StatisticalAnalysisDTO[] performBoyerMooreAnalysis() {
        //! Generate an 1000 value long ArrayListSA of DTOs where their pattern should match
        ArrayList<TextAndPatternDTO> m_TextAndPatternDTOs = new ArrayList<>();
        IntStream.rangeClosed(1, TOTALDATAANALYSIS)
                .parallel()
                .forEach(i -> m_TextAndPatternDTOs
                        .add(StringGeneratorHelper
                                .generateStringDTO(TEXTLENGTH, PATTERNLENGTH, true)));
        //! Perform one set of analysis
        ArrayList<PatternMatcherResultsDTO> shouldMatchComparisonStats = m_TextAndPatternDTOs
                .parallelStream()
                .map(StringMatchingHelper::BoyerMooreMatching)
                .collect(Collectors.toCollection(ArrayList::new));
        StatisticalAnalysisDTO boyerMooreMatchStatistics =  performStatisticalAnalysis(
                shouldMatchComparisonStats.stream().map(
                                PatternMatcherResultsDTO::getM_CountOfComparisons)
                        .collect(Collectors.toCollection(ArrayList::new)));

        //! Generate a 1000 value long Arraylist of DTOS where their pattern should not match
        IntStream.rangeClosed(1, TOTALDATAANALYSIS)
                .parallel()
                .forEach(i -> m_TextAndPatternDTOs
                        .add(StringGeneratorHelper
                                .generateStringDTO(TEXTLENGTH, PATTERNLENGTH, false)));

        //! Perform the Statistical Analysis for Each Group in Parallel
        ArrayList<PatternMatcherResultsDTO> shouldNotMatchComparisonStats = m_TextAndPatternDTOs
                .parallelStream()
                .map(StringMatchingHelper::BoyerMooreMatching)
                .collect(Collectors.toCollection(ArrayList::new));
        StatisticalAnalysisDTO boyerMooreNotMatchStatistics = performStatisticalAnalysis(
                shouldNotMatchComparisonStats.stream().map(
                                PatternMatcherResultsDTO::getM_CountOfComparisons)
                        .collect(Collectors.toCollection(ArrayList::new)));

        return new StatisticalAnalysisDTO[]{boyerMooreMatchStatistics, boyerMooreNotMatchStatistics};
    }

    //! Esqueleto de un metodo para realizar un analisis estadistico basado en una lista de valores de comparasiones
    public static StatisticalAnalysisDTO performStatisticalAnalysis(ArrayList<Integer> m_ComparisonTotals) {

        BigDecimal m_MeanComparison = BigDecimal.valueOf(m_ComparisonTotals
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0));
        List<Integer> sortedList = m_ComparisonTotals.stream()
                .sorted()
                .toList();

        BigDecimal m_MedianComparison;
        if (sortedList.size() % 2 == 0) {
            m_MedianComparison = BigDecimal.valueOf(IntStream.of(
                            sortedList.get(sortedList.size() / 2 - 1),
                            sortedList.get(sortedList.size() / 2))
                    .average()
                    .orElse(0.0));
        } else {
            m_MedianComparison = BigDecimal.valueOf(sortedList.get(sortedList.size() / 2));
        }
        BigInteger m_ModeComparison = BigInteger.valueOf(m_ComparisonTotals
                .stream() /*Transforming ArrayListSA into functional stream*/
                .collect(Collectors.groupingBy(Function.identity() /*Function that always returns its input argument*/,
                        Collectors.counting() /*Function that allows the counting of inputs, as if frequency*/))
                .entrySet() /*Adquires the entry set, i.e the values present in m_ComparisonStream and their counts*/
                .stream() /*Transform the set into a stream of elements*/
                .max(Comparator.comparingLong(Map.Entry::getValue))/*Attempts to compare the max frequency based
                on the value stored in the Entry.value() of the pair. Uses a ComparingLong to compare values as long and a
                method reference to use the getValue() method from Entry*/
                .map(Map.Entry::getKey)/*maps the max value of the previous max operator stream*/
                .orElse(0));

        BigDecimal m_StandardDeviation = BigDecimal.valueOf(Math.sqrt(m_ComparisonTotals
                .stream()
                .mapToDouble(num -> Math.pow(num - m_MeanComparison.doubleValue(), 2))
                .average()
                .orElse(0.0)));
        BigDecimal m_Variance =  m_StandardDeviation.multiply(m_StandardDeviation);

        return new StatisticalAnalysisDTO(m_ComparisonTotals,
                m_MeanComparison.setScale(4, RoundingMode.HALF_DOWN),
                m_MedianComparison.setScale(4, RoundingMode.HALF_DOWN),
                m_ModeComparison,
                m_StandardDeviation.setScale(4, RoundingMode.HALF_DOWN),
                m_Variance.setScale(4, RoundingMode.HALF_DOWN));
    }



    public  record StatisticalAnalysisDTO(ArrayList<Integer> m_ComparisonTotals /*Used to represent the list of received comparisons.*/
                                          , BigDecimal m_MeanComparison /*Used to represent the mean of received comparisons*/
                                          , BigDecimal m_MedianComparison /*Used to represent the median of received comparisons*/
                                          , BigInteger m_ModeComparison /*Used to represent the mode of received comparisons*/
                                          , BigDecimal m_StandardDeviation /*Used to represent the standard deviation of received comparisons*/
                                          , BigDecimal m_Variance /*Used to represent the variance of received comparisons*/
    )
    {

        public StatisticalAnalysisDTO(ArrayList<Integer> m_ComparisonTotals,
                                      BigDecimal m_MeanComparison, BigDecimal m_MedianComparison,
                                      BigInteger m_ModeComparison, BigDecimal m_StandardDeviation, BigDecimal m_Variance) {
            this.m_ComparisonTotals = m_ComparisonTotals;
            this.m_MeanComparison = m_MeanComparison;
            this.m_MedianComparison = m_MedianComparison;
            this.m_ModeComparison = m_ModeComparison;
            this.m_StandardDeviation = m_StandardDeviation;
            this.m_Variance = m_Variance;
        }

        //! Getters para cada variable
        public ArrayList<Integer> getM_ComparisonTotals() {
            return m_ComparisonTotals;
        }
        public BigDecimal getM_MeanComparison() {
            return m_MeanComparison;
        }
        public BigDecimal getM_MedianComparison() {
            return m_MedianComparison;
        }
        public BigInteger getM_ModeComparison() {
            return m_ModeComparison;
        }
        public BigDecimal getM_StandardDeviation() {
            return m_StandardDeviation;
        }
        public BigDecimal getM_Variance() {
            return m_Variance;
        }

        //! To String override
        @Override
        public String toString() {
            return "StatisticalAnalysisDTO{" +
                    ", m_MeanComparison=" + m_MeanComparison +
                    ", m_MedianComparison=" + m_MedianComparison +
                    ", m_ModeComparison=" + m_ModeComparison +
                    ", m_StandardDeviation=" + m_StandardDeviation +
                    ", m_Variance=" + m_Variance +
                    '}';
        }

        public static void main(String[] args) {
            ArrayList<Integer> m_ComparisonTotals = new ArrayList<>(Arrays.asList(10,50, 20, 30, 40, 50));
            StatisticalAnalysisDTO result = performStatisticalAnalysis(m_ComparisonTotals);
            System.out.println(result);
        }
    }
}
