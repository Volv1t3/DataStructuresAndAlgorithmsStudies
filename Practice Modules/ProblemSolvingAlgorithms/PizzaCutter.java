package ProblemSolvingAlgorithms;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PizzaCutter {
    public static void main(String[] args) {
        /*
         * The program states that we need to create a robot, this robot will handle the input of cutting segments that
         * will affect the amount of pizza slices outputted. For this matter, we will have to keep track of both inputs
         * and previously executed cuts to check if the cuts are different from the ones already done or have the same
         * angles. Additionally, since we can have various cuts passed into the program, we ought to also be able to
         * process, using a linked list or hashmap
        */
        LinkedList<Integer> cutAlreadyDoneLinked = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        Integer testCases = scanner.nextInt();
        scanner.nextLine();
        for (int cases = 0; cases < testCases; cases++) {
            String cutsTotalAndCuts = scanner.nextLine();
            Integer[] cutsSpread = Arrays.stream(cutsTotalAndCuts.split(" "))
                    .map(Integer::valueOf)
                    .toArray(Integer[]::new);
                //! Read cuts iteratively and apply them
//            System.out.println("cutsSpread = " + Arrays.toString(Arrays.copyOfRange(cutsSpread, 1, cutsSpread.length)));
                 var result = totalCuts(Arrays.copyOfRange(cutsSpread, 1, cutsSpread.length ));
                System.out.println(result);
        }

    }
    private static Integer totalCuts(Integer[] cutsToDo){
        if (cutsToDo.length == 1){
            return 1;
        }
        HashSet<Integer> cutsAlreadyDone = new HashSet<>();
        //! These are used to discern from values that are the same, i.e. values that in the hashmap are stored under the
        //! same reference and should not be recalculated
        TreeSet<Integer> verticalSimilars = new TreeSet<>(List.of(0,180,360));
        TreeSet<Integer> horizontalSimilars = new TreeSet<>(List.of(90,270));
        Integer totalSlices = 0;
        /* * With the range that we are given we will analyze each input, passing it into a hash set of original cuts to
        * carry out*/
        for (Integer cut: cutsToDo) {
            Integer sanitizedCut = (cut % 360);
            if (verticalSimilars.contains(Math.abs(sanitizedCut))){
                cutsAlreadyDone.add(0);
                continue;
            }
            if (horizontalSimilars.contains(Math.abs(sanitizedCut))){
                cutsAlreadyDone.add(90);
                continue;
            }
            cutsAlreadyDone.add(sanitizedCut);
        }
        AtomicInteger results = new AtomicInteger(0);
        /* * Now we work on each of those cuts to calculate the number of pieces*/
        cutsAlreadyDone.forEach(cut -> results.getAndAdd(2));

        if (results.get() == 0){
            return 1;
        }
        else {return results.get();}
    }
}
