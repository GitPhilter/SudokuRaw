package solutionfinder;
import java.util.*;
import structures.*;
import rules.*;


public class Algorithm1 {
    //returns the first solved board it finds
    public Board problem;
    //private Board temporaryBoard;
    public Board solution;
    public int numberOfRecursions = 0;
    public int recursionDepth = 0;
    public int maxRecursionDepth = 0;
    boolean terminated = false;


    public Algorithm1(Board b){
        problem = b;
        //temporaryBoard = b;
        solution = null;
    }

    public void findSolution(){
        long millis = System.currentTimeMillis();
        java.util.Date date =new java.util.Date(millis);
        System.out.println("solutionfinder.Algorithm1 wurde um " + date + " gestartet!");
        System.out.println("problem analysis: ");
        System.out.println("there are " + getNumberOfGivenValues() + " out of " + (int)(Math.pow(problem.getN(), 4)) + " values given. That means there are " + (int)((Math.pow(problem.getN(), 4)) - getNumberOfGivenValues()) + " spots to be filled!");
        //
        findSolutionHelper(problem);
        //
        long millisTerminated = System.currentTimeMillis() - millis;
        int secondsTerminated = (int) (millisTerminated / 1000);
        if(solution == null) {
            System.out.println("Algorithm1 could not find a solution in " + numberOfRecursions + " recursion steps!");
        } else {
            System.out.println("Algorithm1 found a solution in " + numberOfRecursions + " recursion steps!");
            solution.printBoard();
        }
        System.out.println("solutionfinder.Algorithm1 took " + secondsTerminated + " seconds to terminate!");
    }

    //TODO: recursive function for searching the solution
    public void findSolutionHelper(Board b){
        if(!terminated) {
            numberOfRecursions++;
            recursionDepth++;
            if (recursionDepth > maxRecursionDepth){
                maxRecursionDepth = recursionDepth;
                System.out.println("maxRecursionDepth is " + maxRecursionDepth);
            }
            //System.out.println("recursion depth is " + recursionDepth);
            //b.printBoard();
            if (Rules.isWin(b)) {
                solution = b;
                solution.printBoard();
                terminated = true;
            } else {

                //set first 0 that is found to a random int
                int fieldsLength = b.getFields().size();
                int cellsLength = 0;
                for (int field = 0; field < fieldsLength; field++) {
                    //search for first 0 in field
                    for (int cell = 0; cell < b.getFields().get(field).getCells().size(); cell++) {
                        //Define possible "fillers" for the given field
                        cellsLength = b.getFields().get(field).getCells().size();
                        if (b.getFields().get(field).getCells().get(cell).getValue() == 0) {
                            ArrayList<Integer> possibleIntegers = b.getFields().get(field).getMissingNumbers();
                            //b.getFields().get(field).printField();
                            //System.out.print("possible Integers:");
                            //for(int p = 0; p < possibleIntegers.size(); p++){
                                //System.out.print(possibleIntegers.get(p).intValue() + " ");
                            //}
                            //System.out.println();
                            for (int i = 0; i < possibleIntegers.size(); i++) {
                                b.getFields().get(field).getCells().get(cell).setValue(possibleIntegers.get(i).intValue());
                                if (Rules.isValid(b)) {
                                    findSolutionHelper(b);
                                    if(terminated){

                                        i = possibleIntegers.size();
                                        cell = b.getFields().get(field).getCells().size();
                                    }
                                } else {
                                    b.getFields().get(field).getCells().get(cell).setValue(0);
                                    possibleIntegers.remove(i);
                                    i--;
                                }
                            }
                        }
                    }
                    if(terminated){
                        field = b.getFields().size();
                    }
                }
            }
            recursionDepth--;
        }
    }

    public int getNumberOfGivenValues(){
        int result = 0;
        for(int f = 0; f < problem.getFields().size(); f++){
            for(int c = 0; c < problem.getFields().get(f).getCells().size(); c++){
                if(problem.getFields().get(f).getCells().get(c).getValue() != 0){
                    result++;
                }
            }
        }
        return result;
    }
}
