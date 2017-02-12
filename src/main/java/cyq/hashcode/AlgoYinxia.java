package cyq.hashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hydezhao on 12/02/2017.
 */
public class AlgoYinxia extends Algo1 {
    public AlgoYinxia(Pizza pizza) {
        super(pizza);
    }

    private CellOfSlices [][] listSliceForEachCell = new CellOfSlices[R][C];

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

                            if (listSliceForEachCell[current_r][current_c] == null) {
                                listSliceForEachCell[current_r][current_c] = new CellOfSlices(current_r, current_c);
                            }
                            listSliceForEachCell[current_r][current_c].addSlice(slice);

                        }
                        current_r ++;
                    }

                    current_c ++;
                }
            }
        }

        System.out.println("Total available slices account : " + allPossibleSliceList.size());
    }

    public void printListSliceForEachCell() {

        int count = 0;

        for(int r_num = 0; r_num <= R-1; r_num ++){
            for (int c_num =0; c_num <= C-1; c_num ++) {
                if (listSliceForEachCell[r_num][c_num] != null){
                    System.out.println("[" + r_num + "," + c_num + "]:" + listSliceForEachCell[r_num][c_num].getSlice());
                }
            }
        }


    }

}
