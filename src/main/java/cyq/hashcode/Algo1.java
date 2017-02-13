package cyq.hashcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjyju on 11/02/2017.
 */
public class Algo1 {

    private int R;
    private int C;
    private int L;
    private int H;

    private int tomatAccount;
    private int mushroomAccount;

    private int least;
    private int most;

    private int totalCellsAccount;

    private int currentBestCompositionTotalCellsAccount = 0;
    private int[] currentBestComposition;

    private char[][] cells;

    private int smallestSliceSize = 0;

    private int compositionAccount = 1;

    List<Slice> allPossibleSliceList = new ArrayList<>();

    List<List<Integer>> independentList = new ArrayList<>();
    int[][] independentArray;

    List<List<Integer>> possibleSlicesList = new ArrayList<>();


    public List<Slice> getAllPossibleSliceList() {
        return allPossibleSliceList;
    }

    public Algo1(Pizza pizza){
        R = pizza.getR();
        C = pizza.getC();
        L = pizza.getL();
        H = pizza.getH();
        tomatAccount = pizza.getTomateAccount();
        mushroomAccount = pizza.getMushroomAccount();
        cells = pizza.getCells();
        least = 2*L;
        most = H;
        totalCellsAccount = R * C;

    }

    public int getSmallestSliceSize() {
        return smallestSliceSize;
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
                            int sliceSize = slice.getCellsAccount();
                            if(smallestSliceSize == 0) smallestSliceSize = sliceSize;
                            else smallestSliceSize = smallestSliceSize>sliceSize?sliceSize:smallestSliceSize;
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

    private boolean isSliceValide(char[][] cells, Slice slice){
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
        slice.setTomatAccount(t_cell_num);
        slice.setMushroomAccount(m_cell_num);

        return true;
    }



    public void getBestComposition(){
        int size = allPossibleSliceList.size();
        int current_compositionSize = 0;


        for(int index = 0; index < size; index ++){

        }
    }



    public void getAllIndependent(){

        int size = allPossibleSliceList.size();
        //independentArray = new int[size][size];

        for(int index = 0; index < size; index ++){
            Slice slice = allPossibleSliceList.get(index);

            List<Integer> indexList = new ArrayList<>();
            for(int index1 = index + 1; index1 < size; index1 ++){
                if(!isOverLay(slice, allPossibleSliceList.get(index1))){
                    //independentArray[index][index1] = 1;
                    indexList.add(index1);
                } else{
                    //independentArray[index][index1] = 0;
                }
            }
            //independentList.add(indexList);

            int[] indexArray = new int[indexList.size() + 1];
            indexArray[0] = index;
            for(int i = 0; i < indexList.size(); i ++){
                indexArray[i+1] = indexList.get(i);
            }
            int smallestSliceSize = getSmallestSliceSize(indexArray);
            System.out.println("Smallest slice has " + smallestSliceSize + " cells!");

            int smallerAccount = tomatAccount > mushroomAccount?mushroomAccount:tomatAccount;

            int _size = smallerAccount/L;
            double yu = smallerAccount%L;
            if(yu > 0) _size += 1;
            System.out.println("begin index : " + index + " | " + indexArray.length + "//////////////////////////////////////////////");

            getAllCompositions(0,0, 0, _size, indexArray);
        }

        //System.out.println("All indipendentList done!!");
    }


    public int getSmallestSliceSize(int[] indexArray){
        int size = tomatAccount;
        for(int index : indexArray){
            Slice slice = allPossibleSliceList.get(index);
            int sliceSize = slice.getCellsAccount();
            if(size > sliceSize) size = sliceSize;
        }
        return size;
    }



    public void generateAllPossibleComposition(){
        int size = independentList.size();

        for(int index = 0; index < size; index ++ ){
            List<Integer> compoistionList = new ArrayList<>();
            int currentCompositionListSize = 1;
            while(currentCompositionListSize <= size){

            }
        }
    }


    public List<int[]> getAllCompositions(int prevSize, int cosumedTomatAccount, int cosumedMushroomAccount,int size, int[] indexArray){

        List<int[]> compositionList = new ArrayList<>();

        if(size < 1 ){
            //System.out.println("Error : composition size can not be smaller than 1!");
        } else if(size == 1){
            for(int index : indexArray){
                int[] composition = new int[size];
                composition[0] = index;
                //compositionList.add(composition);
                Slice slice = allPossibleSliceList.get(index);
                int restTomatAccount = tomatAccount - cosumedTomatAccount - slice.getMushroomAccount();
                int restMushroomAccount = mushroomAccount - cosumedMushroomAccount - slice.getMushroomAccount();
                if(restMushroomAccount >=0 && restTomatAccount >=0) {
                    compositionList.add(composition);
                }

                // composition ready need to test if available
            }
        } else {
            for(int i = 0; i < indexArray.length; i++){
                int[] composition = new int[size];
                composition[0] = indexArray[i];
                Slice slice = allPossibleSliceList.get(composition[0]);
                int restTomatAccount = tomatAccount - cosumedTomatAccount - slice.getMushroomAccount();
                int restMushroomAccount = mushroomAccount - cosumedMushroomAccount - slice.getMushroomAccount();
                if(restMushroomAccount >=0 && restTomatAccount >= 0) {

                    int smaller = restMushroomAccount>restTomatAccount?restTomatAccount:restMushroomAccount;
                    int _size = smaller/L;
                    double yu = smaller%L;
                    if(yu > 0) _size += 1;
                    if(prevSize + 1 + _size >= totalCellsAccount/H){
                        List<int[]> allSubCompositionList = getAllCompositions(prevSize + 1,cosumedTomatAccount + slice.getTomatAccount(), cosumedMushroomAccount + slice.getMushroomAccount(),_size, getRestIndexArray(i, indexArray));
                        for(int[] subComposition : allSubCompositionList){
                            int compositionIndex = 1;
                            for(int value : subComposition){
                                if(compositionIndex >= size){
                                    System.out.println();
                                }
                                composition[compositionIndex] = value;
                                compositionIndex ++;
                            }
                            int[] compositionCopy = composition.clone();
                            // composition ready need to test if available`
                            if(checkComposition(compositionCopy)){

                                compositionList.add(compositionCopy);
                            }

                        }
                    } else{
                        System.out.println("dont need recursive call");
                    }

                }

            }
        }

        return compositionList;
    }


    public boolean checkComposition(int[] composition){
        List<Slice> sliceComposition = new ArrayList<>();

        //System.out.println("validating composition : ");


        int cellAccount = 0;
        for(int i = 0; i < composition.length; i++){
            Slice slice = allPossibleSliceList.get(composition[i]);
            cellAccount += slice.getCellsAccount();
            if(cellAccount > totalCellsAccount){
                //System.out.println("This composition is not valid");
                //System.out.println("-------------------------------------------------------");
                return false;
            }
            for(int j = 0; j < i; j++){
                Slice slice1 = sliceComposition.get(j);
                if(isOverLay(slice1, slice)){
                    //System.out.println("This composition is NOT valid");
                    //System.out.println("-------------------------------------------------------");
                    return false;
                }
            }
            sliceComposition.add(slice);
        }

        //printComposition(composition);

        //System.out.println("This composition is valid");
        //System.out.println("-------------------------------------------------------");

        if(cellAccount == totalCellsAccount){
            System.out.println("we found the best solution which cover all the cells !!!!");
            currentBestComposition = composition;
            printComposition(currentBestComposition);

            long endTime = System.currentTimeMillis();
            System.out.println("execution time : " + (endTime - PizzaApp.startTime)/1000.0 + "s");
            System.exit(1);

        } else if(cellAccount > currentBestCompositionTotalCellsAccount){
            currentBestCompositionTotalCellsAccount = cellAccount;
            currentBestComposition = composition;
            System.out.println("Found better solution : " + currentBestCompositionTotalCellsAccount);
        }

        //System.out.println("Best composition covers cells account : " + currentBestCompositionTotalCellsAccount);

        //System.out.println("valid composition : " + compositionAccount + "\n");
        compositionAccount++;
        return true;

    }



    public void printComposition(int[] composition){
        System.out.println("#########");
        System.out.println("#" + composition.length);
        for(int index : composition){
            Slice slice = allPossibleSliceList.get(index);
            System.out.println("#" + slice.getFirst_row() + " " + slice.getFirst_column() + " "
                    + slice.getLast_row() + " " + slice.getLast_column() + "#");
        }
        System.out.println("#########");

    }


    public int[] getRestIndexArray(int removeIndex, int[] indexArray){
        int restSize = indexArray.length - 1;
        int[] restIndexArray = new int[restSize];
        int restIndex = 0;
        for(int i = 0; i < indexArray.length; i ++){
            int indexValue = indexArray[i];
            if(i != removeIndex){
                restIndexArray[restIndex] = indexValue;
                restIndex ++;
            }
        }

        return restIndexArray;

    }

    public void getPossibleSlicesList(){

        int size = independentList.size();

        for(int index = 0; index < size; index ++ ){

            List<Integer> possibleSlices = new ArrayList<>();
            possibleSlices.add(index);
            List<Integer> intList1 = independentList.get(index);

            while(possibleSlices.size() > 0){
                Integer filterSliceIndex = possibleSlices.get(0);
                List<Integer> indexList = independentList.get(possibleSlices.get(0));
                indexList.add(filterSliceIndex);

                List<Integer> commonIntList = getCommonIntList(possibleSlices);
            }
        }

    }


    private boolean isIntListContainsInt(List<Integer> intList, Integer i){
        for(Integer value : intList){
            if(value.intValue() == i.intValue()){
                return true;
            }
        }
        return false;
    }

    private List<Integer> getCommonIntList(List<Integer> intList1){
        List<Integer> integerList = new ArrayList<>();
        for(Integer i : intList1){


        }
        return integerList;
    }


    private boolean isOverLay(Slice slice1, Slice slice2){
        if(slice1.getFirst_row() > slice2.getLast_row() || slice1.getFirst_column() > slice2.getLast_column()
                || slice1.getLast_row() < slice2.getFirst_row() || slice1.getLast_column() < slice2.getFirst_column()){
            return false;
        }
        return true;
    }

    public int[] getCurrentBestComposition() {
        return currentBestComposition;
    }
}
