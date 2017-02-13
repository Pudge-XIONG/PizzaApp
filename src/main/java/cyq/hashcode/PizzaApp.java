package cyq.hashcode;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A Camel Application
 */
public class PizzaApp {

    private int R;
    private int C;

    private char[][] pizza = null;
    private int L = 0;
    private int H = 0;
    private static final String SEPERATOR = " ";

    static public long startTime;


    /**0
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {

        Pizza pizza = new Pizza("small.in");

        printPizza(pizza);

        Algo1 algo1 = new Algo1(pizza);

        algo1.generateAllPossibleSlice();


        startTime = System.currentTimeMillis();
        algo1.getAllIndependent();



/*
        List<Slice> allPossibleSlices = algo1.getAllPossibleSliceList();

        int[] indexArray = new int[allPossibleSlices.size()];
        for(int i = 0; i < allPossibleSlices.size(); i ++){
            indexArray[i] = i;
        }
        int smallestSliceSize = algo1.getSmallestSliceSize();
        System.out.println("Smallest slice has " + smallestSliceSize + " cells!");
        int tomateAccount = pizza.getTomateAccount();
        int mushroomAccount = pizza.getMushroomAccount();
        int smallerAccount = tomateAccount > mushroomAccount?mushroomAccount:tomateAccount;

        int size = smallerAccount/(pizza.getL());
        double yu = smallerAccount%(pizza.getL());
        if(yu > 0) size += 1;
        algo1.getAllCompositions(0, 0, size, indexArray);
*/
        int[] bestComposition = algo1.getCurrentBestComposition();


        System.out.println("We have found the best solutions :");
        if(bestComposition != null)
            algo1.printComposition(bestComposition);
        else System.out.println("Sorry but we didn't find any solution");

        long endTime = System.currentTimeMillis();

        System.out.println("execution time : " + (endTime - startTime)/1000.0 + "s");

    }



    private static void printPizza(Pizza pizza){

        for(char[] row : pizza.getCells()){
            for(char cell : row){
                System.out.print(cell);
                System.out.print(' ');
            }
            System.out.println();
        }

        System.out.println("tomate : " + pizza.getTomateAccount());
        System.out.println("mushroom : " + pizza.getMushroomAccount());
    }

}



