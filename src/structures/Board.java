package structures;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.Math;

//Standard sudoku-board with numbers from 1-9
public class Board {
    ArrayList<Field> fields;
    ArrayList<Cell> cells;
    int n = 3;


    public Board(){
        //
    fields = new ArrayList<Field>();
    for(int i = 0; i < 9; i++){
        fields.add(new Field(i));
    }
    cells = new ArrayList<Cell>();
        for(int i = 0; i < 81; i++){
            cells.add(new Cell());
        }

    }

    private void fillCellsFromFields(){

    }

    public void setRandomValues(){
        for (int i = 0; i < fields.size(); i++){
            for(int k = 0; k < 9; k++){
                fields.get(i).getCells().get(k).setValue((int)(Math.random() * 9 + 1));
            }
        }
    }

    public void printBoard(){
        ArrayList<Cell> currentLine = new ArrayList<Cell>();
        ArrayList<Cell> temp = new ArrayList<Cell>();
        //print upper boundary
        System.out.print("");
        for(int i = 0; i < 2 + (n * n); i++){
            System.out.print("--");
        }
        System.out.println();
        //
        for(int startingField = 0; startingField < fields.size(); startingField = startingField + n){
            for(int lines = 0; lines < n; lines++){
                System.out.print("|");
                for(int field = startingField; field < n + startingField; field++){
                    currentLine = fields.get(field).getLine(lines);
                    for(int print = 0; print < currentLine.size(); print++){
                        System.out.print(currentLine.get(print).getValue() + " ");
                    }
                    System.out.print("|");
                }System.out.println();
            }
            System.out.println("----------------------");
        }


        //

    }

    public ArrayList<Field> getFields(){
        return fields;
    }

    public void setValidFields(){
        for(int i = 0; i < fields.size(); i++){
            fields.get(i).setValidField();
        }
        //printBoard();
    }

    public ArrayList<Cell> getLine(int l){
        ArrayList<Cell> result = new ArrayList<Cell>();
        //
        int startingField = n * (int)(l / n);
        //System.out.println("Trying to get line " + l + ", startingField = " + startingField);
        for(int i = startingField; i < startingField + n; i++){
            ArrayList<Cell> fieldLine = fields.get(i).getLine(l % n);
            for (int k = 0; k < fieldLine.size(); k++) {
                result.add(fieldLine.get(k));
            }
        }
        /*
        for(int j = 0; j < result.size(); j++ ) {
            System.out.print(result.get(j).getValue());
        }
        System.out.println();
        */
        return result;
    }

    public ArrayList<Cell> getColumn(int c){
        ArrayList<Cell> result = new ArrayList<Cell>();
        //
        int startingField = (int)(c / n);
        //System.out.println("Trying to get line " + l + ", startingField = " + startingField);
        for(int i = startingField; i <= startingField + 2 * n; i = i + n){
            ArrayList<Cell> fieldColumn = fields.get(i).getColumn(c % n);
            for (int k = 0; k < fieldColumn.size(); k++) {
                result.add(fieldColumn.get(k));
            }
        }
        /*
        for(int j = 0; j < result.size(); j++ ) {
            System.out.print(result.get(j).getValue());
        }
        System.out.println();
        */
        return result;
    }
    public int getN(){
        return n;
    }

    public void setBoardByArray(int[][] ints){
        for(int field = 0; field < 9; field++){
            fields.get(field).setField(ints[field]);
        }
    }

}
