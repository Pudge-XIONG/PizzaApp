package cyq.hashcode;

/**
 * Created by zjyju on 11/02/2017.
 */
public class Slice {
    private int first_row;
    private int first_column;
    private int last_row;
    private int last_column;
    private int cells_num = 0;
    private int tomatAccount;
    private int mushroomAccount;

    public int getTomatAccount() {
        return tomatAccount;
    }

    public void setTomatAccount(int tomatAccount) {
        this.tomatAccount = tomatAccount;
    }

    public int getMushroomAccount() {
        return mushroomAccount;
    }

    public void setMushroomAccount(int mushroomAccount) {
        this.mushroomAccount = mushroomAccount;
    }

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

    public int getCellsAccount(){
        if(cells_num == 0) cells_num = (last_row - first_row + 1) * (last_column - first_column + 1);
        return cells_num;
    }
}
