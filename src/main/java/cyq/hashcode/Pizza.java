package cyq.hashcode;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by zjyju on 11/02/2017.
 */
public class Pizza {
    private int R;
    private int C;

    private char[][] cells = null;
    private int L = 0;
    private int H = 0;
    private static final String SEPERATOR = " ";

    private int tomateAccount = 0;
    private int mushroomAccount = 0;

    public Pizza(Pizza parentPizza, Slice slice){

    }

    public Pizza(String inputFilePath){
        loadInput(inputFilePath);
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    public char[][] getCells() {
        return cells;
    }

    public void setCells(char[][] cells) {
        this.cells = cells;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
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
                    cells = new char[R][C];
                } else{
                    int current_r = line_num - 1;
                    cells[current_r] = line.toCharArray();
                    for(char cell : cells[current_r]){
                        if(cell == 'T'){
                            tomateAccount ++;
                        } else{
                            mushroomAccount ++;
                        }
                    }
                }
                line_num ++;
            }
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error while read line : " + e.getMessage());
        }
    }

    public int getTomateAccount() {
        return tomateAccount;
    }

    public int getMushroomAccount() {
        return mushroomAccount;
    }
}
