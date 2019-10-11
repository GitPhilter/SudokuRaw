package solutionfinder;

import rules.Rules;
import structures.Board;
import structures.Field;

import java.util.ArrayList;

public class Algorithm2 {
    //returns the first solved board it finds
    public Board problem;
    //private Board temporaryBoard;
    public Board solution;
    public int numberOfRecursions = 0;
    public int recursionDepth = 0;
    public int maxRecursionDepth = 0;
    boolean terminated = false;


    public Algorithm2(Board b) {
        problem = b;
        //temporaryBoard = b;
        solution = null;
    }

    public void findSolution(){
        long millis = System.currentTimeMillis();
        java.util.Date date =new java.util.Date(millis);
        System.out.println("solutionfinder.Algorithm2 wurde um " + date + " gestartet!");
        System.out.println("problem analysis: ");
        problem.printBoard();
        System.out.println("there are " + getNumberOfGivenValues() + " out of " + (int)(Math.pow(problem.getN(), 4)) + " values given. That means there are " + (int)((Math.pow(problem.getN(), 4)) - getNumberOfGivenValues()) + " spots to be filled!");
        //
        findSolutionHelper(problem);
        //
        long millisTerminated = System.currentTimeMillis() - millis;
        int secondsTerminated = (int) (millisTerminated / 1000);
        if(solution == null) {
            System.out.println("Algorithm2 could not find a solution in " + numberOfRecursions + " recursion steps!");
        } else {
            System.out.println("Algorithm2 found a solution in " + numberOfRecursions + " recursion steps!");
            solution.printBoard();
        }
        System.out.println("solutionfinder.Algorithm2 took " + secondsTerminated + " seconds to terminate!");
    }

    //TODO: recursive function for searching the solution
    public void findSolutionHelper(Board b) {
        if (!terminated) {
            numberOfRecursions++;
            recursionDepth++;
            if (recursionDepth > maxRecursionDepth) {
                maxRecursionDepth = recursionDepth;
                System.out.println("maxRecursionDepth is " + maxRecursionDepth);
            }
            //System.out.println("recursion depth is " + recursionDepth);
            //b.printBoard();
            if (Rules.isWin(b)) {
                solution = b;
                //solution.printBoard();
                terminated = true;
            } else {
                //actual part for finding the solution by recursion:
                //
                //check first if there is a field that has 8 values in it
                //then fill it accordingly
                for(int i = 0; i < b.getFields().size(); i++){
                    if(getNumberOfGivenValuesPerField(b.getFields().get(i)) == 8){
                        int indexOfZero = getIndexOfZeroInField(b.getFields().get(i));
                        for(int value = 1; value < 9; value++){
                            b.getFields().get(i).getCells().get(indexOfZero).setValue(value);
                            if(b.getFields().get(i).isValid()){
                                findSolutionHelper(b);
                                if(terminated){
                                    value = 9;
                                }
                            }
                        }
                    }
                }
                //get 6 fieldlines á 3 fields from the board
                //and count the values in each line to get the "fullest fieldline"
                ArrayList<ArrayList<Field>> fieldlines = getFieldlines(b);
                //get the fieldlines sorted, so that the "fullest" fieldline is in place 0 (full-to-empty-sorted)
                fieldlines = getSortedFieldArrayList(fieldlines);
                //go through fieldlines and check if there is a number that is already present
                // in two of the three fields of the respective fieldline
                for(int i = 0; i < fieldlines.size(); i++){
                    ArrayList<Integer> integersThatAreTwiceInFieldline = getIntegersThatAreTwiceInFieldline(fieldlines.get(i));
                    for(int k = 0; k < integersThatAreTwiceInFieldline.size(); k++){
                        for(int field = 0; field < 3; field++){
                            //if current int is not in field, try to find a position
                            if(!fieldlines.get(i).get(field).isInField(integersThatAreTwiceInFieldline.get(k))){
                                for (int cell = 0; cell < 9; cell++){
                                    if(fieldlines.get(i).get(field).getCells().get(cell).getValue() == 0){
                                        fieldlines.get(i).get(field).getCells().get(cell).setValue(integersThatAreTwiceInFieldline.get(k));

                                    }
                                }

                            }
                        }
                    }

                }



            }
        }
        recursionDepth--;
    }

    private ArrayList<ArrayList<Field>> getSortedFieldArrayList(ArrayList<ArrayList<Field>> fieldlines){
        ArrayList<ArrayList<Field>> result = new ArrayList<ArrayList<Field>>();

        while (fieldlines.size() > 0) {
            int indexOfMostFilledFieldLine = 0;
            for (int i = 1; i < fieldlines.size(); i++) {
                if(getNumberOfGivenValuesPerFieldline(fieldlines.get(indexOfMostFilledFieldLine)) < getNumberOfGivenValuesPerFieldline(fieldlines.get(i))){
                    indexOfMostFilledFieldLine = i;
                }
            }
            result.add(fieldlines.get(indexOfMostFilledFieldLine));
            fieldlines.remove(indexOfMostFilledFieldLine);
        }
        // test output
        for (int i = 0; i < result.size(); i++){
            System.out.println("Fieldline at index " + i + " has " + getNumberOfGivenValuesPerFieldline(result.get(i)) + " values given!");
        }

        //
        return result;
    }

    private int getNumberOfGivenValuesPerFieldline(ArrayList<Field> fieldline){
        int result = 0;
        for (int i = 0; i < fieldline.size(); i++){
            result += getNumberOfGivenValuesPerField(fieldline.get(i));
        }
        return result;
    }

    public int getNumberOfGivenValuesPerField(){
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

    public int getNumberOfGivenValuesPerField(Field field){
        int result = 0;
            for(int c = 0; c < field.getCells().size(); c++){
                if(field.getCells().get(c).getValue() != 0){
                    result++;
                }
            }
        return result;
    }

    public int getIndexOfZeroInField(Field field){
        int result = 0;
        for (int i = 0; i < field.getCells().size(); i++){
            if(field.getCells().get(i).getValue() == 0){
                return i;
            }
        }
        return result;
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

    private ArrayList<ArrayList<Field>> getFieldlines(Board b){
        int currentFieldline = 0;
        ArrayList<ArrayList<Field>> fieldlines = new ArrayList<ArrayList<Field>>();
        for(int i = 0; i < 2 * b.getN(); i++){
            fieldlines.add(new ArrayList<Field>());
        }
        //get horizontal fieldlines
        for(int i = 0; i <= b.getN() * (b.getN() - 1); i = i + b.getN()){
            for(int k = i; k < i + b.getN(); k++){
                fieldlines.get(currentFieldline).add(b.getFields().get(k));
            }
            currentFieldline++;
        }
        //get vertical fieldlines
        for(int i = 0; i < b.getN(); i++){
            for(int k = i; k <= i + 2 * b.getN(); k = k + b.getN()){
                fieldlines.get(currentFieldline).add(b.getFields().get(k));
            }
            currentFieldline++;
        }
        //print fieldlines for testreasons
                /*
                for(int i = 0; i < fieldlines.size(); i++){
                    System.out.println("fieldline no." + i);
                    for(int k = 0; k < fieldlines.get(i).size(); k++){
                        fieldlines.get(i).get(k).printField();
                        System.out.println("-----");
                    }
                    System.out.println("#####");
                }
         */
        return fieldlines;
    }

    public ArrayList<Integer> getIntegersThatAreTwiceInFieldline(ArrayList<Field> fieldline){
        ArrayList<Integer> resultIntegers = new ArrayList<Integer>();
        int timesFound = 0;
        for(int integer = 1; integer <= 9; integer++){
            for(int fieldIndex = 0; fieldIndex < 3; fieldIndex++){
                for(int indexInField = 0; indexInField < 9; indexInField++){
                    if(fieldline.get(fieldIndex).getCells().get(indexInField).getValue() == integer){
                        timesFound++;
                        indexInField = 9;
                    }
                }
            }
            if(timesFound == 2){
                resultIntegers.add(new Integer(integer));
            }
            timesFound = 0;
        }
        return resultIntegers;
    }

}