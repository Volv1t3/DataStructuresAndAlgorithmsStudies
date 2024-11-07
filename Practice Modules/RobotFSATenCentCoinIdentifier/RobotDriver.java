package RobotFSATenCentCoinIdentifier;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RobotDriver {
    private static RobotController controller = new RobotController();


    public static void main(String[] args) {
        controller.addRobotInstance(new RobotEntity("R2D2"));

        /*El Presente archivo muestra varias pruebas realizadas a la clase para identificar succesos y fracasos en
        * diversas situaciones.
        * Como dato adicional, la clase TenCentCoinFinder contiene 4 diferentes metodos de analisis que pueden ser
        * utilizados*/
        //! Print a formatted message, in 120 characters which presents the message "Theory of Computation - FSA For Coin Analysis Testing"
        System.out.printf("%120s%n","Theory of Computation - FSA For Coin Analysis Testing");
        System.out.println("\n1._ Basic Single Robot Single Input Analysis\n");
        testingSingleInputString();
        System.out.println("\n2._ Complex Single Robot Multiple Input Analysis\n");
        testingMultipleStringsOnSingleRobots();
        System.out.println("\n3._ Complex Multiple Robot Multiple Input Analysis Continuous\n");
        testingSingleRobotContinuousData();
    }

    public static void testingSingleInputString()
    {
        AnalysisResultDTO results = null;
        System.out.println("\nTesting control character #\n");
        results = RobotDriver.controller.singleRobotSingleStringAnalysis("#");
        System.out.println(results.toString());
        System.out.println("\nTesting String 10, 1010 as Bin String or Not\n");
        results = RobotDriver.controller.singleRobotSingleStringAnalysis("1010");
        System.out.println(results.toString());
        System.out.println("\nTesting String 11, 1011 as Bin String or Not\n");
        results = RobotDriver.controller.singleRobotSingleStringAnalysis("1011");
        System.out.println(results.toString());
        System.out.println("\nTesting control character *\n");
        results = RobotDriver.controller.singleRobotSingleStringAnalysis("*");
        System.out.println(results.toString());
    }

    public static void testingMultipleStringsOnSingleRobots()
    {
        AnalysisResultDTO results = null;

        //! Testing Entry Arrays
        System.out.println("\nTesting Entry Arrays\n");
        results = RobotDriver.controller.singleRobotMultipleStringAnalysis(new String[] {"#", "1010","1011","0111","*"});
        System.out.println(results.toString());  //! The results here expect to show two distinct values: control = true and halt = true
        //! Testing Entry List
        System.out.println("\nTesting Entry List\n");
        results = RobotDriver.controller.singleRobotMultipleStringAnalysis(new String[]
                {"#","1101101","1101100","111111","0001001","*"});
        System.out.println(results.toString());
        //! Testing Entry List with mixed results
        System.out.println("\nTesting Entry List with mixed results\n");
        results = RobotDriver.controller.singleRobotMultipleStringAnalysis(new String[]
                {"#","1101101","1101100","111111","0001001","1010","*"}); //! For this set of data, nothing will be
        //! returned nor announced about all data points since they have been analyzed, however the halt operator will
        //! throw back a result for program coherence.

        //! Las Check with 10 random data values
        System.out.println("\nTesting Entry List with 10 random data values\n");
        results = RobotDriver.controller.singleRobotMultipleStringAnalysis(new String[]
                {"#","00000110","00010001","00000101","00001101","00010011","00010100",
                        "00010101","00010111","00011011","00100101","*"});
        System.out.println(results.toString());
    }

    public static void testingSingleRobotContinuousData()
    {
        SecureRandom random = new SecureRandom();
        int[] stream = IntStream.range(0, 101).toArray();

        List<String> streamConverted = new java.util.ArrayList<>(Arrays.stream(stream).mapToObj(new IntFunction<String>() {
            @Override
            public String apply(int value) {
                return Integer.toBinaryString(value);
            }
        }).toList());

        streamConverted.addFirst("#"); streamConverted.add(random.nextInt(0,101),"*");

        AnalysisResultDTO resultDTO= new AnalysisResultDTO(false,false,null,false);
        while (!Objects.equals(Objects.requireNonNull(resultDTO).getBinaryString(), "*")) {
            resultDTO = RobotDriver.controller.singleRobotMultipleStringAnalysis(streamConverted.toArray(new String[0]));
        }


    }
}
