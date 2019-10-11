import rules.Rules;
import solutionfinder.Algorithm1;
import solutionfinder.Algorithm2;
import structures.*;

public class Main {

    public static void main(String[] args){

        Board board = new Board();
        board.printBoard();


/*
        int[] plain =  {1,2,0,5,0,0,0,0,0,
                        0,0,4,0,0,9,0,3,0,
                        0,8,6,0,0,4,2,0,0,
                        2,0,6,0,0,0,0,7,0,
                        0,1,0,7,0,6,0,8,0,
                        4,0,3,0,0,0,9,0,1,
                        0,0,0,3,0,7,0,4,1,
                        0,0,0,8,0,2,0,0,7,
                        8,3,0,1,0,0,0,9,0};
        int[][] ints = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                ints[i][j] = plain[j + i * 9];
            }
        }
        board.setBoardByArray(ints);
        Algorithm1 bf = new Algorithm1(board);
        bf.findSolution();
*/
/*
        int[] problem =    {0,0,0,5,0,8,7,6,4,
                            0,7,4,0,2,9,0,3,8,
                            3,0,0,7,0,4,2,5,9,
                            2,8,6,9,1,3,0,7,5,
                            9,0,0,7,4,0,2,0,3,
                            0,7,3,0,2,8,0,0,1,
                            6,5,0,0,9,0,8,0,1,
                            0,0,0,8,0,0,0,5,0,
                            8,3,7,1,4,0,6,9,0};
        int[][] ints = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                ints[i][j] = problem[j + i * 9];
            }
        }
        board.setBoardByArray(ints);
        Algorithm1 bf = new BruteForce(board);
        bf.findSolution();
*/
        /*
        System.out.println("Board is WinningBoard: " + Rules.isWin(board));

        int[] winningBoard =   {1,2,9,5,3,8,7,6,4,
                                5,7,4,6,2,9,1,3,8,
                                3,8,6,7,1,4,2,5,9,
                                2,8,6,9,1,3,4,7,5,
                                9,1,5,7,4,6,2,8,3,
                                4,7,3,5,2,8,9,6,1,
                                6,5,2,3,9,7,8,4,1,
                                4,9,1,8,6,2,3,5,7,
                                8,3,0,1,4,5,6,9,0};
        int[][] ints = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                ints[i][j] = winningBoard[j + i * 9];
            }
        }
        board.setBoardByArray(ints);
        Algorithm2 at = new Algorithm2(board);
        at.findSolution();
        System.out.println("Board is WinningBoard: " + Rules.isWin(board));
        */
        /*

        int steps = 0;
        boolean isWin = false;
        while(!isWin){
            steps++;
            //System.out.println(steps);
            //board.setRandomValues();
            board.setValidFields();
            //board.printBoard();
            isWin = Rules.isWin(board);
        }
        board.printBoard();
        for(int i = 0; i < board.getN() * board.getN(); i++){
            board.getLine(i);
        }
        */

    }
}
