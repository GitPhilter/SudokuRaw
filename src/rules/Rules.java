package rules;

import structures.*;
import java.util.*;
import java.lang.Math;

public abstract class Rules {

    public static boolean isWin(Board board){
        ArrayList<Field> fields = board.getFields();
        //check if fields are valid for themselves
        for (int i = 0; i < fields.size(); i++){
            //System.out.println("checking field no." + fields.get(i).getID());
            if (!fields.get(i).isWin()){
                //System.out.println("Rules.isWin(): Fields No." + i + " is not Valid!");
                return false;
            }
            //System.out.println("Rules.isWin(): Fields No." + i + " is Valid!");
        }
        //check column
        //System.out.println("Rules.isWin(): Check columns.");
        //board.printBoard();
        for(int i = 0; i < board.getN() * board.getN(); i++){
            if(isWin(board.getColumn(i))){
                //printLine(board.getColumn(i));
                //System.out.println();
                //System.out.println("Column no." + i + " is valid! #####################");
            } else{
                //printLine(board.getColumn(i));
                //System.out.println("Column no." + i + " is not valid!");
                return false;
            }
        }
        //check lines
        //System.out.println("Rules.isWin(): Check lines.");
        for(int i = 0; i < board.getN() * board.getN(); i++){
            if(isValidLine(board.getLine(i))){
                //printLine(board.getLine(i));
                //System.out.println();
                //System.out.println("Line no." + i + " is valid! #####################");
            } else{
                //printLine(board.getLine(i));
                //System.out.println("Line no." + i + " is not valid!");
                return false;
            }
        }

        return true;
    }

    public static boolean isValid(Board board){
        ArrayList<Field> fields = board.getFields();
        //check if fields are valid for themselves
        for (int i = 0; i < fields.size(); i++){
            //System.out.println("checking field no." + fields.get(i).getID());
            if (!fields.get(i).isValid()){
                //System.out.println("Rules.isValid(): Fields No." + i + " is not Valid!");
                return false;
            }
            //System.out.println("Rules.isValid(): Fields No." + i + " is Valid!");
        }
        //check column
        //System.out.println("Rules.isValid(): Check columns.");
        //board.printBoard();
        for(int i = 0; i < board.getN() * board.getN(); i++){
            if(isValidLine(board.getColumn(i))){
                //printLine(board.getColumn(i));
                //System.out.println();
                //System.out.println("Column no." + i + " is valid! #####################");
            } else{
                //printLine(board.getColumn(i));
                //System.out.println("Column no." + i + " is not valid!");
                return false;
            }
        }
        //check lines
        //System.out.println("Rules.isValid(): Check lines.");
        for(int i = 0; i < board.getN() * board.getN(); i++){
            if(isValidLine(board.getLine(i))){
                //printLine(board.getLine(i));
                //System.out.println();
                //System.out.println("Line no." + i + " is valid! #####################");
            } else{
                //printLine(board.getLine(i));
                //System.out.println("Line no." + i + " is not valid!");
                return false;
            }
        }

        return true;
    }

    public static int getValidInt(int n){
        return ((int)(Math.random() * (n * n) + 1));
    }


    public static boolean isWin(ArrayList<Cell> cells){
        for (int i = 0; i < cells.size() - 1; i++){
            if(cells.get(i).getValue() == 0) {
                return false;
            }
                for (int k = i + 1; k < cells.size(); k++) {
                    if (cells.get(i).getValue() == cells.get(k).getValue()) {
                        //System.out.println();
                        return false;
                    }
                }

        }
        //System.out.println("Line is valid!");

        return true;
    }

    public static boolean isValidLine(ArrayList<Cell> cells){
        for (int i = 0; i < cells.size() - 1; i++){
            if(cells.get(i).getValue() != 0) {
                for (int k = i + 1; k < cells.size(); k++) {
                    if (cells.get(i).getValue() == cells.get(k).getValue()) {
                        //printLine(cells);
                        //System.out.println(" is not valid!");
                        //System.out.println();
                        return false;
                    }
                }
            }
        }
        //printLine(cells);
        //System.out.println(" is valid!");

        return true;
    }

    public static void printLine(ArrayList<Cell> cells){
        for(int i = 0; i < cells.size(); i++){
            if (i < cells.size() - 1) {
                System.out.print(cells.get(i).getValue() + ",");
            } else {
                System.out.print(cells.get(i).getValue() + ": ");
            }
        }
    }


}
