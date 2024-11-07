package ProblemSolvingAlgorithms;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.function.BinaryOperator;


public class MosaicDecorationOne {
    public static void main(String[] args) {
        /*
         * The problem statement is straightforward as it is a simple optimization with an overshot of the number of tiles needed
         * to the nearest amount that would produce a whole amount for purchases. We are told that the user can only buy
         * packs that inherently can hold 10 pieces of tile, then we need to figure out the distance to the nearest whole
         * figure in the case we are not in a number that is whole like 40 or 50, if we are in between these we can make a range

         */


        Scanner scanner = new Scanner(System.in);
        int nBathroomsInt = scanner.nextInt();
        int costBlackTile = scanner.nextInt();
        int costPurpleTile = scanner.nextInt();
        Integer[] nBathroomBlackTilesNeeded = new Integer[nBathroomsInt];
        Integer[] nBathroomPurpleTileNeeded = new Integer[nBathroomsInt];
        String lineRead = "";
        for (int bathroomCount = 0; bathroomCount < nBathroomsInt; bathroomCount++) {

            nBathroomBlackTilesNeeded[bathroomCount] = scanner.nextInt();
            nBathroomPurpleTileNeeded[bathroomCount] = scanner.nextInt();
        }
        scanner.close();
        /*
         * With the values that we read into the program we can find the total amount of tiles that we need by multiplying
         * each item in both previous arrays. With those values we can optimize for a whole purchase
         */
        Integer totalBlackTileNeeded = addAllItems(nBathroomBlackTilesNeeded).orElse(0);
        Integer totalPurpleTileNeeded = addAllItems(nBathroomPurpleTileNeeded).orElse(0);
        /*
         * With the values we have we need to performa check, if the modulo is exactly 0 when done to a 0 then we know
         * the number is already a perfect number to be processed, however if it isn't we need to adjust it
         */
        Integer priceBlackTileNeeded = calculateTotal(totalBlackTileNeeded, costBlackTile);
        Integer pricePurpleTileNeeded = calculateTotal(totalPurpleTileNeeded, costPurpleTile);

        System.out.println(priceBlackTileNeeded + pricePurpleTileNeeded);


    }
    private static Optional<Integer> addAllItems(Integer[] array){
        return Arrays.stream(array).reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
    }
    private static Integer calculateTotal(Integer subtotal, Integer priceOfPart){
        final Integer packageSize = 10;
        if (subtotal % 10 == 0){
            var wholeAmount = subtotal / 10;
            return wholeAmount*priceOfPart;
        }
        else {
            var distanceToWhole = subtotal % 10;
            var distanceFromTen = packageSize - distanceToWhole;
            var highTailValue = subtotal + distanceFromTen;
            return (highTailValue /10) * priceOfPart;
        }
    }
}

