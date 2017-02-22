package cyq.hashcode;


/**
 * A Camel Application
 */
public class PizzaApp {


    public static final String inputName = "medium";

    /**0
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {

        long startTime = System.currentTimeMillis();

        Pizza pizza = new Pizza(inputName + ".in");
        long afterLoadingFileTime = System.currentTimeMillis();
        System.out.println("File loading took " + (afterLoadingFileTime - startTime)/1000.0 + " seconds");

        printPizza(pizza);
        long afterPrintPizzaTime = System.currentTimeMillis();
        System.out.println("Printing pizza took " + (afterPrintPizzaTime - afterLoadingFileTime)/1000.0 + " seconds");


        Algo1 algo1 = new Algo1(pizza);

        algo1.generateAllPossibleSlice();

        long afterGereratAllPossibleSliceTime = System.currentTimeMillis();
        System.out.println("Generating all possible Slices took " + (afterGereratAllPossibleSliceTime - afterPrintPizzaTime)/1000.0 + " seconds");

        //algo1.getAllIndependent();
        //long afterGereratAllIndependentTime = System.currentTimeMillis();
        //System.out.println("Generating all independent list took " + (afterGereratAllIndependentTime - afterGereratAllPossibleSliceTime)/1000.0 + " seconds");


        algo1.getPossibleSlices();

        long endTime = System.currentTimeMillis();
        System.out.println("Looking for best solution took " + (endTime - afterGereratAllPossibleSliceTime)/1000.0 + " seconds");
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
        System.out.println("Total cells : " + (pizza.getMushroomAccount() + pizza.getTomateAccount()));
    }

}



