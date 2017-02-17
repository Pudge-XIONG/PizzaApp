package cyq.hashcode;

import java.util.*;

/**
 * Created by zjyju on 11/02/2017.
 *
 * version qxiong 20170213 We don't save independent list of all possible slices anymore
 * because it causes an OutOfMemoryException when there are too many possible slices
 */
public class Algo1 {

    protected int R;
    protected int C;
    protected int L;
    protected int H;

    protected int least;
    protected int most;

    protected char[][] cells;

    protected List<Slice> allPossibleSliceList = new ArrayList<>();


    protected List<List<Integer>> independentList = new ArrayList<>();
    protected int[][] independentArray;

    protected List<Integer> combinaision = new ArrayList<>();

    protected int max_surface;
    protected int current_surface;
    protected long max_try;

    public Algo1(Pizza pizza){
        R = pizza.getR();
        C = pizza.getC();
        L = pizza.getL();
        H = pizza.getH();
        cells = pizza.getCells();
        least = 2*L;
        most = H;

        max_surface = 0;
        current_surface=0;
        max_try=1000;
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

        System.out.println("Now we try to refine the possible slices and remove those who can be composed by others");

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


    public List<Integer> getIndependentListOf(int index){

        int size = allPossibleSliceList.size();
        Slice slice = allPossibleSliceList.get(index);

        List<Integer> indexList = new ArrayList<>();
        for(int index1 = 0; index1 < size; index1 ++){
            if(!isOverLay(slice, allPossibleSliceList.get(index1))){
                indexList.add(index1);
            }
        }

        return indexList;
    }


    /**
     * @version qxiong 20170213 replace independentList.get(i) by using getIndependentListOf(i) which returns the independent list of slices of slice i
     */
    public void getPossibleSlices(){
        int size = allPossibleSliceList.size();

        int i=0;
        int n=0;    // loop index

        // sort allPossibleSliceList
        // allPossibleSliceList.sort( (o1, o2) -> Integer.compare(o2.getSurface(), o1.getSurface()) );

        while (max_surface<R*C && n<max_try) {
            // allPossibleSliceList.sort((o1, o2) -> o1.getSurface() > o2.getSurface() ? + 1 : o1.getSurface() < o2.getSurface() ? -1 : 0 );
            Slice slice = allPossibleSliceList.get(i);
            List<Integer> comb = new ArrayList<>();
            comb.add(i);

            /*
            if (independentList.get(i).size()>0) {
                current_surface = slice.getSurface() + addFirstValideSlice(independentList.get(i), comb);

                if (max_surface < current_surface) {
                    combinaision = comb;
                    max_surface = current_surface;
                }
            }
            */
            List<Integer> potentialSlices = getIndependentListOf(i);

            long startTime = System.currentTimeMillis();
            if (potentialSlices.size()>0) {
                current_surface = slice.getSurface() + addFirstValideSlice(potentialSlices, comb);

                if (max_surface < current_surface) {
                    combinaision = comb;
                    max_surface = current_surface;
                    System.out.println("current max surface is : " + max_surface  );
                    long endTime = System.currentTimeMillis();
                    System.out.println("Looking current max surface took " + (endTime - startTime)/1000.0 + " seconds");
                }
            }

            n++;
            i++;
            i=i%size ;

        }

        System.out.println("Covered pieces : " + max_surface);
        System.out.println(combinaision.size());

        for(int s:combinaision) {
            allPossibleSliceList.get(s).printSlice();
        }


    }


    /**
     * @version qxiong 20170213 replace independentArray[i] by using isOverLay(slice1, slice2) which returns detect if two slices are independent
     */
    public Integer addFirstValideSlice(List<Integer> potentialSlices, List<Integer> addedSlices)  {
        if (potentialSlices.size()>0) {
            Random rr = new Random();
            int s=rr.nextInt() % potentialSlices.size();
            s= s<0 ? -s : s;
            int newSlice = potentialSlices.get(s);

            addedSlices.add(newSlice);
            Slice slice = allPossibleSliceList.get(newSlice);

            if (potentialSlices.size() > 1) {
                List<Integer> remainSlices = new ArrayList<>();

                for (int j = 0; j < potentialSlices.size(); j++) {

                    /*
                    if (independentArray[newSlice][potentialSlices.get(j)] == 1) {
                        remainSlices.add(potentialSlices.get(j));
                    }
                    */

                    if (!isOverLay(allPossibleSliceList.get(newSlice), allPossibleSliceList.get(potentialSlices.get(j)))) {
                        remainSlices.add(potentialSlices.get(j));
                    }

                }
                return slice.getSurface() + addFirstValideSlice(remainSlices, addedSlices);
            }
            return slice.getSurface();
        }

        return 0;
    }

    private boolean isOverLay(Slice slice1, Slice slice2){
        if(slice1.getFirst_row() > slice2.getLast_row() || slice1.getFirst_column() > slice2.getLast_column()
                || slice1.getLast_row() < slice2.getFirst_row() || slice1.getLast_column() < slice2.getFirst_column()){
            return false;
        }
        return true;
    }

}
