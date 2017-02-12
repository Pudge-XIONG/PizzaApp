package cyq.hashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjyju on 11/02/2017.
 */
public class Algo1 {

    protected int R;
    protected int C;
    protected int L;
    protected int H;

    protected int least;
    protected int most;

    protected char[][] cells;

    List<Slice> allPossibleSliceList = new ArrayList<>();


    List<List<Integer>> independentList = new ArrayList<>();
    int[][] independentArray;


    public Algo1(Pizza pizza){
        R = pizza.getR();
        C = pizza.getC();
        L = pizza.getL();
        H = pizza.getH();
        cells = pizza.getCells();
        least = 2*L;
        most = H;

    }

    public void generateAllPossibleSlice(){

        for(int r_num = 1; r_num <= H; r_num ++){
            int max = H/r_num;
            max = C<max ? C:max;
            int min = 2*L/r_num;
            min = min > 0 ? min:1;
            for(int c_num = min; c_num <= max; c_num++){

                int current_c = 0;
                while(current_c + c_num <= C ){
                    int current_r = 0;
                    while(current_r + r_num <= R){
                        Slice slice = new Slice();
                        slice.setFirst_row(current_r);
                        slice.setFirst_column(current_c);
                        slice.setLast_column(current_c + c_num - 1);
                        slice.setLast_row(current_r + r_num - 1);


                        if(isSliceValide(cells, slice)){
                            allPossibleSliceList.add(slice);
                        }
                        current_r ++;
                    }

                    current_c ++;
                }
            }
        }

        System.out.println("Total available slices account : " + allPossibleSliceList.size());
    }

    protected boolean isSliceValide(char[][] cells, Slice slice){
        int t_cell_num = 0;
        int m_cell_num = 0;
        for(int r = slice.getFirst_row(); r <= slice.getLast_row(); r ++){
            for(int c = slice.getFirst_column() ; c <= slice.getLast_column(); c ++){
                char cell = cells[r][c];
                if(cell == 'T'){
                    t_cell_num++;
                }else{
                    m_cell_num ++;
                }
            }
        }
        if(t_cell_num < L || m_cell_num < L){
            return false;
        }

        return true;
    }

    public void getAllIndependent(){

        int size = allPossibleSliceList.size();
        independentArray = new int[size][size];

        for(int index = 0; index < size; index ++){
            Slice slice = allPossibleSliceList.get(index);

            List<Integer> indexList = new ArrayList<>();
            for(int index1 = 0; index1 < size; index1 ++){
                if(!isOverLay(slice, allPossibleSliceList.get(index1))){
                    independentArray[index][index1] = 1;
                    indexList.add(index1);
                } else{
                    independentArray[index][index1] = 0;
                }
            }
            independentList.add(indexList);
        }
    }


    public void getPossibleSlices(){

    }

    private boolean isOverLay(Slice slice1, Slice slice2){
        if(slice1.getFirst_row() > slice2.getLast_row() || slice1.getFirst_column() > slice2.getLast_column()
                || slice1.getLast_row() < slice2.getFirst_row() || slice1.getLast_column() < slice2.getFirst_column()){
            return false;
        }
        return true;
    }

}
