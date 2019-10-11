package structures;

public class Cell {
    private int value;

    public Cell(){
        value = 0;
    }

    public Cell(int v){
        value = v;
    }


    public void setValue(int v){
        value = v;
    }
    public int getValue(){
        return value;
    }

}
