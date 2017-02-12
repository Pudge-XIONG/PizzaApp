package cyq.hashcode;

import java.io.*;
import java.nio.charset.Charset;

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


    /**0
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {

        Pizza pizza = new Pizza("C:\\Users\\zjyju\\IdeaProjects\\pizza\\small.in");

        printPizza(pizza);

        Algo1 algo1 = new Algo1(pizza);

        algo1.generateAllPossibleSlice();
        algo1.getAllIndependent();
        algo1.getPossibleSlices();

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



