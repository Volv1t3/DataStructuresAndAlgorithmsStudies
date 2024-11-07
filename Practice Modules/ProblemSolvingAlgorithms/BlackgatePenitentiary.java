package ProblemSolvingAlgorithms;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BlackgatePenitentiary {
    public static void main(String[] args) {
        /*
         * This problem involves sorting both names and heights. Since we cannot know which height we are meant to
         * put first or last, only that it should be in descending order and that the names should come out in a good
         * ascending alphabetical order; we need to find a way to both store the possible positions they could take.
         */

        Scanner scanner = new Scanner(System.in);
        Integer gangMembersArrested = scanner.nextInt();
        scanner.nextLine();
        LinkedHashMap<String, Integer> gangMemberStorage = new LinkedHashMap<>();
        for (int i = 0; i < gangMembersArrested; i++){
            String line = scanner.nextLine();

            String[] lineParsed = line.split(" ");
            gangMemberStorage.put(String.valueOf(lineParsed[0]), Integer.valueOf(lineParsed[1]));
        }

        /*
         * The main idea behind the printing is to print out the format: name1 name2 nameN indexFrom index To, where
         * in some cases (when there is only one name the indexTo and From are the same, and in other cases, the names
         * might me more than one in which case we move the index by a given amount (i.m.o., the amount of names)
         */
        //! Sort the heights and use those to determine how many names you've got for a single height
        Integer[] gangMemberHeights = gangMemberStorage.values().toArray(new Integer[0]);
        TreeSet<Integer> gangMemberHeightsUnique = new TreeSet<>(List.of(gangMemberHeights));
        Integer gangMemberIndex = 0;
        System.out.println();
        while (gangMemberIndex < gangMembersArrested){
            //! Let us take the first height of the Treeset
            Integer height = gangMemberHeightsUnique.pollFirst(); //Should return the first element i.e. lowest

            List<String> gangNames = gangMemberStorage.entrySet()
                    .stream()
                    .filter(entry -> Objects.equals(entry.getValue(), height))
                    .map(Map.Entry::getKey)
                    .toList();
            TreeSet<String> orderedgangNames = new TreeSet<>(gangNames);
                orderedgangNames.forEach((name) -> {
                    System.out.print(name + " ");
                });
                System.out.print((gangMemberIndex +1) + " ");
                gangMemberIndex += orderedgangNames.size();
                System.out.print(gangMemberIndex);
                System.out.println();
        }
    }
}
