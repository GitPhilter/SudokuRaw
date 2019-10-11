package structures;

import java.util.ArrayList;
import rules.*;

public class Field {
    ArrayList<Cell> cells;
    final int ID;
    int n = 3;

    public Field(int id){
        ID = id;
        cells = new ArrayList<Cell>();
        for (int i = 0;i < 9; i++){
            cells.add(new Cell());
        }
    }

    public ArrayList<Cell> getCells(){
        return cells;
    }

    public ArrayList<Cell> getLine(int l){
        ArrayList<Cell> temp = new ArrayList<Cell>();
        for (int i = l * n; i < (l * n)+ n; i++){
            temp.add(cells.get(i));
        }
        return temp;
    }

    public ArrayList<Cell> getColumn(int c){
        ArrayList<Cell> temp = new ArrayList<Cell>();
        for (int i = c; i <= c + 2 * n; i = i + n){
            temp.add(cells.get(i));
        }
        return temp;
    }

    public boolean isWin() {
        //check all values
        for (int i = 0; i < cells.size() ; i++) {
            if(cells.get(i).getValue() == 0){
                return false;
            }
            for (int k = i + 1; k < cells.size(); k++) {
                if (cells.get(i).getValue() == cells.get(k).getValue()) {
                    //System.out.println(i + ":" + cells.get(i).getValue() + "==" + k + ":" + cells.get(k).getValue());
                    return false;
                }
            }
        }
        return true;
    }
        public boolean isValid(){
            //check all values
            for (int i = 0; i < cells.size(); i++){
                if(cells.get(i).getValue() != 0) {
                    for (int k = i + 1; k < cells.size(); k++) {
                        if (cells.get(i).getValue() == cells.get(k).getValue()) {
                            //System.out.println(i + ":" + cells.get(i).getValue() + "==" + k + ":" + cells.get(k).getValue());
                            //System.out.println("Field " + ID + " is not valid!");
                            //printField();
                            return false;
                        }
                    }
                }
            }
        //System.out.println("Field " + ID + " is valid!");
        //printField();
        return true;
    }

    public void printField(){
        for (int i = 0; i < n * n; i = i + n){
            for (int k = i; k < n + i; k++){
                System.out.print(cells.get(k).getValue() + " ");
            }
            System.out.println();
        }
    }

    public int getID(){
        return ID;
    }

    public void setValidField(){
        cells = new ArrayList<Cell>();
        for(int i = 0; i < n * n; i++){
            cells.add(new Cell(Rules.getValidInt(n)));
            for(int k = 0; k < i; k++){
                if (cells.get(k).getValue() == cells.get(i).getValue()){
                    cells.remove(i);
                    i--;
                }
            }
        }
        //printField();
    }

    public void setField(int[] ints){
        cells = new ArrayList<Cell>();
        for(int i = 0; i < 9; i++){
            cells.add(new Cell(ints[i]));
        }
    }

    public ArrayList<Integer> getMissingNumbers(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        boolean found = false;
        for(int i = 1; i <= 9; i++){
            found = false;
            for(int k = 0; k < cells.size(); k++) {
                if (i == cells.get(k).getValue()){
                    found = true;
                }
            }
            if (!found) {
                result.add(new Integer(i));
            }
        }
        return result;
    }

    public boolean isInField(int integer){
        for(int i = 0; i < this.getCells().size(); i++){
            if(this.getCells().get(i).getValue() == integer){
                return true;
            }
        }
        return false;
    }

}
