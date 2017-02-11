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

        PizzaApp pizzaApp = new PizzaApp("C:\\Users\\zjyju\\IdeaProjects\\pizza\\small.in");

        printPizza(pizzaApp.pizza);
    }


    public PizzaApp(String inputFileName){
        loadInput(inputFileName);
    }

    private void loadInput(String inputFileName){
        String line;
        try {
                InputStream fis = new FileInputStream(inputFileName);
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);

                int line_num = 0;
                while ((line = br.readLine()) != null) {
                    // Deal with the line
                    //System.out.println(line);
                    if(line_num == 0){
                        // first line
                        String[] values = line.split(SEPERATOR);
                        R = Integer.parseInt(values[0]);
                        C = Integer.parseInt(values[1]);
                        L = Integer.parseInt(values[2]);
                        H = Integer.parseInt(values[3]);
                        pizza = new char[R][C];
                    } else{
                        int current_r = line_num - 1;
                        pizza[current_r] = line.toCharArray();

                    }
                    line_num ++;
                }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error while read line : " + e.getMessage());
        }
    }

    private static void printPizza(char[][] pizza){
        for(char[] row : pizza){
            for(char cell : row){
                System.out.print(cell);
                System.out.print(' ');
            }
            System.out.println();
        }
    }



}

