package cyq.hashcode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hydezhao on 12/02/2017.
 */
public class CellOfSlices {
    public CellOfSlices(int row, int col) {
        this.row = row;
        this.col = col;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    private int row;

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    private int col;

    public List<Slice> getListSlice() {
        return listSlice;
    }

    public void setListSlice(List<Slice> listSlice) {
        this.listSlice = listSlice;
    }

    private List<Slice> listSlice = new ArrayList<>();


    public void addSlice(Slice slice) {
        this.listSlice.add(slice);
    }

    public String getSlice() {
        String output = "";
        for (Slice slice : listSlice) {
            output += "("+slice.getFirst_column() +"," +slice.getFirst_row()+ "," + slice.getLast_column()+","+ slice.getLast_row()+") ";
        }
        return output;
    }

}
