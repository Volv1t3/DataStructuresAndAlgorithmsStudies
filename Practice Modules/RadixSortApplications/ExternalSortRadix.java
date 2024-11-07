package RadixSortApplications;

import DeberCincoSortingComparisonSantiagoArellano.AlgorithmComparisonDriver;
import DeberSeisRadixSortSantiagoArellano.RadixSortDriver;
import SortingAlgoPractice.AdvancedSortingAlgorithms;
import org.apache.commons.math3.random.MersenneTwister;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Santiago Arellano.
 * @Date: October 19th, 2024.
 * @Description: The present file will show my attempt at creating a self-contained, demonstrative program which
 * generates a listing of 1_000_000 integers, shuffles them and proceeds to write them out to a file ordered such that
 * it simulates a database file of thousands of lines. The idea is to use the notions of External Sorting to sort the
 * data in chunks of 100_000 items, and then merging those runs iteratively to produce a sorted file which can be
 * easily checked by opening the file.
 * @implNote : For this implementation we will be using two merging algorithms (rather one actual and another idealistic
 * versions), merge sort and its notions will be used when working to merge the corrected output from each file. Whilst
 * radix sort will be used internally to try and get a better run time on large files. For a <b>baseline, we have gotten
 * run times of 1245 ms on an iterative simple radix sort application over a 1_000_000 integers</b>, the goal for this
 * would be to break down that complexity through divide and conquer merging.
 */
public class ExternalSortRadix {

    /**
     * The first method to be created is one that creates the larger output file locally into memory.
     * @param fileName : Name of the File to the created with the 1_000_000 integers to sort through
     */
    public static boolean generateLargeFile(String fileName){
        //? 1st._ create a large IntStream and store it as an array
        List<Integer> integers = IntStream.rangeClosed(1,1_000).boxed().collect(Collectors.toList());
        //? 2nd._ shuffle the array
        Collections.shuffle(integers);
        //? 3rd._ write the shuffled array to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Integer integer : integers) {
                writer.println(integer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * The second step for sorting this larger file is to break it down into chunks of data which can be then sorted with
     * a quicker in memory method, rather than a slower 1_000_000 O(n) analysis
     * @param inputFileName : Name of the input file where the larger data is stored
     * @param tempFileName : Name of the output files where the data will be written to in the format of tempFileName[Integer]
     */
    public static Integer recursivelyDivideLargeFile(String inputFileName, String tempFileName){
      //? 1st._ Load the input file into the computer and prepare to write files per every 100_000 items
        Path pathOfInputFile = Paths.get(inputFileName);
        final int MAX_NUMBERS = 10_000;
        int countOfFilesCreated = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(pathOfInputFile.toFile()))){
            //? 2nd._ Define our constants and our buffer sizes for the writing of the new files
            Integer[] buffer;
            String line;
            while ((line = reader.readLine()) != null){
                buffer = new Integer[MAX_NUMBERS];
                int index = 0;
                while (index < MAX_NUMBERS && line != null){
                    buffer[index++] = Integer.parseInt(line);
                    line = reader.readLine();
                }
                if (index < buffer.length){
                    buffer = Arrays.copyOfRange(buffer, 0, index);
                }
                RadixSortDriver.radixSort(buffer, buffer.length);

                Path pathOfTempFile = Paths.get(tempFileName + countOfFilesCreated + ".txt");
                try (PrintWriter writer = new PrintWriter(new FileWriter(pathOfTempFile.toFile()))) {
                    for (Integer integer : buffer) {
                        writer.println(integer);
                    }
                    countOfFilesCreated++;
                }
            }

            System.out.println("Total files created: " + countOfFilesCreated);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countOfFilesCreated;
    }

    /**
     * An intermediate step is needed to merge the files, what we are going to do is recursively merge two files together,
     * the idea is that we would have a number N of files to merge and what we could do is randomly choose two files per iteration
     * and merge them to sort them, this would inevitably remove some files and create others. For example, what we could do
     * is rewrite over the name of the first file a new file with the two files merged.
     * @param temporaryFileName : Name of the temporary files created, which can be used to track them
     * @param numberOfFilesCreated : Number of files created so far, which can be used to track the number of files to merge
     */
    public static void mergeTwoFiles(String temporaryFileName,Integer numberOfFilesCreated) throws IOException {
        //! We know that we have created a total of numberOfFilesCreated files, they are sorted to some extent and the
        //! idea now is to make it so that we merge files. We could merge consecutive files, starting from 0 and
        //! going to the left, so let us attempt that first
        //?Base Step: Add every combination of files into an ArrayList<>()
        ArrayList<String> files = new ArrayList<>();
        for(int i = 0; i < numberOfFilesCreated; i++){
            files.add(temporaryFileName + i + ".txt");
        }
        int mergeRound = 0;
        while (files.size() > 1) {
            ArrayList<String> newFiles = new ArrayList<>();
            for (int i = 0; i < files.size(); i+=2) {
                if (i + 1 < files.size()) {//? 1st._ Get the two files needed to apply the method on
                    String leftFile = (files.get(i)); //Would begin at zero and end at 98
                    String rightFile = (files.get(i + 1)); //Would begin at one and end at 99
                    //? 2nd._ Using these two files, send them over to the main process for merging into one file
                    String mergedFileName = "mergedResult" + mergeRound + "_" + (i / 2) + ".txt";
                    mergeTwoSortedFiles(leftFile, rightFile, mergedFileName);
                    Files.deleteIfExists(Paths.get(leftFile));
                    Files.deleteIfExists(Paths.get((rightFile)));
                    newFiles.add(mergedFileName);
                }
                else {
                    newFiles.add(files.get(i));
                }
            }
            files = newFiles;
            mergeRound++;

        }
        if (!files.isEmpty()) {
            Path finalOutput = Paths.get(files.get(0));
            Path finalDestination = Paths.get(temporaryFileName + "FinalSorted.txt");
            Files.move(finalOutput, finalDestination, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

    }

    public static void mergeTwoSortedFiles(String firstFile, String secondFile, String outputFile){
        Path pathOfFirstFile = Paths.get(firstFile);
        Path pathOfSecondFile = Paths.get(secondFile);
        Path pathOfOutputFile = Paths.get(outputFile);

        try (BufferedReader reader1 = new BufferedReader(new FileReader(pathOfFirstFile.toFile()));
             BufferedReader reader2 = new BufferedReader(new FileReader(pathOfSecondFile.toFile()));
             PrintWriter writer = new PrintWriter(new FileWriter(pathOfOutputFile.toFile()))) {

            String line1 = reader1.readLine();
            String line2 = reader2.readLine();

            while (line1 != null && line2 != null) {
                if (Integer.parseInt(line1) <= Integer.parseInt(line2)) {
                    writer.println(line1);
                    line1 = reader1.readLine();
                } else {
                    writer.println(line2);
                    line2 = reader2.readLine();
                }
            }

            while (line1 != null) {
                writer.println(line1);
                line1 = reader1.readLine();
            }

            while (line2 != null) {
                writer.println(line2);
                line2 = reader2.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        boolean fileGenerated = generateLargeFile("LargeFile.txt");
        boolean fileTwo = generateLargeFile("LargeFileTwo.txt");
        mergeTwoSortedFiles("LargeFile.txt","LargeFileTwo.txt","evenLargerFile.txt");
        //? 4th._ read the file back in
        if (fileGenerated){
            Path path = Paths.get("evenLargerFile.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
                String line;
                int counter = 0;
                while((line = reader.readLine()) != null && counter < 10){
                    System.out.println(line);
                    counter++;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



        var countOfFIles = recursivelyDivideLargeFile("evenLargerFile.txt","SortedSegment");
        mergeTwoFiles("SortedSegment", countOfFIles);
        //? 5th._ read the sorted file back in and print the first 10 lines to verify the sorting
    }
}
