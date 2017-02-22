package cyq.hashcode;

/**
 * Created by zjyju on 11/02/2017.
 */
public class Slice {
    private int first_row;
    private int first_column;
    private int last_row;
    private int last_column;

    public int getFirst_row() {
        return first_row;
    }

    public void setFirst_row(int first_row) {
        this.first_row = first_row;
    }

    public int getFirst_column() {
        return first_column;
    }

    public void setFirst_column(int first_column) {
        this.first_column = first_column;
    }

    public int getLast_row() {
        return last_row;
    }

    public void setLast_row(int last_row) {
        this.last_row = last_row;
    }

    public int getLast_column() {
        return last_column;
    }

    public void setLast_column(int last_column) {
        this.last_column = last_column;
    }

    public int getSurface() { return (last_row-first_row+1)*(last_column-first_column+1); }

    public void printSlice() {
        System.out.print(first_row);
        System.out.print(' ');
        System.out.print(first_column);
        System.out.print(' ');
        System.out.print(last_row);
        System.out.print(' ');
        System.out.print(last_column);
        System.out.println();
    }

}
