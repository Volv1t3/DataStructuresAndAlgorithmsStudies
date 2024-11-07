package DeberTresStringMatchingAnalysisSantiagoArellano;

/**=============================================
 * ?                Data
 * 1. Nombre: Santiago Arellano [00328370]
 * 2. Clase: Data Structures And Algorithms
 * 3. Descripcion: Programa cliente para probar la estadistica del sistema
 ============================================**/


public class PatternMatchingDriver {

    public static void main(String[] args) {
        System.out.printf("%120s%n", "Resultados Estadistica Descriptiva Para Corridas de Algoritmos de Pattern Matching");
        System.out.println();
        System.out.printf("%120s%n","Resultados Para Algoritmo de Fuerza Bruta [2000 Datos - 1000 con Match, 1000 sin Match]");
        System.out.println();
        StatisticalAnalysisInterface.StatisticalAnalysisDTO[]
                resultsForBruteForce = StatisticalAnalysisInterface.performBruteForceAnalysis();
        System.out.println("Resultados para 1000 datos con Match");
        System.out.printf(
                "Promedio:\t%20s%nMediana:\t%20s%nModa:\t%20s%nDesviacion Estandar:%20s%nVarianza:\t%20s%n",resultsForBruteForce[0].getM_MeanComparison(),
                resultsForBruteForce[0].getM_MedianComparison(),
                resultsForBruteForce[0].getM_ModeComparison(),
                resultsForBruteForce[0].getM_StandardDeviation(),
                resultsForBruteForce[0].getM_Variance()
        );
        System.out.println();
        System.out.println("Resultados para 1000 datos sin Match");
        System.out.printf(
                "Promedio:\t%20s%nMediana:\t%20s%nModa:\t%20s%nDesviacion Estandar:%20s%nVarianza:\t%20s%n",resultsForBruteForce[1].getM_MeanComparison(),
                resultsForBruteForce[1].getM_MedianComparison(),
                resultsForBruteForce[1].getM_ModeComparison(),
                resultsForBruteForce[1].getM_StandardDeviation(),
                resultsForBruteForce[1].getM_Variance()
        );
        System.out.printf("%n%120s%n", "Resultados Para Algoritmo de Boyer-Moore [2000 Datos - 1000 con Match, 1000 sin Match]");
        StatisticalAnalysisInterface.StatisticalAnalysisDTO[] resultsForBoyerMoore = StatisticalAnalysisInterface.performBoyerMooreAnalysis();
        System.out.println("Resultados para 1000 datos con Match");
        System.out.printf(
                "Promedio:\t%20s%nMediana:\t%20s%nModa:\t%20s%nDesviacion Estandar:%20s%nVarianza:\t%20s%n",resultsForBoyerMoore[0].getM_MeanComparison(),
                resultsForBoyerMoore[0].getM_MedianComparison(),
                resultsForBoyerMoore[0].getM_ModeComparison(),
                resultsForBoyerMoore[0].getM_StandardDeviation(),
                resultsForBoyerMoore[0].getM_Variance()
        );
        System.out.println();
        System.out.println("Resultados para 1000 datos sin Match");
        System.out.printf(
                "Promedio:\t%20s%nMediana:\t%20s%nModa:\t%20s%nDesviacion Estandar:%20s%nVarianza:\t%20s%n",resultsForBoyerMoore[1].getM_MeanComparison(),
                resultsForBoyerMoore[1].getM_MedianComparison(),
                resultsForBoyerMoore[1].getM_ModeComparison(),
                resultsForBoyerMoore[1].getM_StandardDeviation(),
                resultsForBoyerMoore[1].getM_Variance()
        );
    }
}
