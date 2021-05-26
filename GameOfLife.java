import java.util.*;

public class GameOfLife {

    public static void run(int[][] board){
        int x = board[0].length; // size of x dim
        int y = board[1].length; // size of y dim

        int[][] boardSave = new int[x][y];
        int[][] nextBoard = new int[x][y];
        for(int i = 0; i < x; i++)
        boardSave[i] = board[i].clone();

 

       // Arrays.copyOf(board, board.length);
        printBoard(board);
        boolean flag = true;

        while (flag){
            for (int i = 0; i < x; i++){
                for (int j = 0; j < y; j++){
                    nextBoard[i][j] = getCurrent(board, i, j);
        //            System.out.println("current board");
        //            printBoard(board);
        //            System.out.println("board Save");
          //          System.out.println("x");
                }
                
            }
            for (int i = 0; i < x; i++)
                for (int j = 0; j < y; j++){
                    board[i][j] = nextBoard[i][j];
                }
            printBoard(boardSave);
            if ((isEqualArray(board,boardSave))){
                System.out.println("OH NO!");
                flag = false;
            }
            System.out.println("Saving new state");
            for(int i = 0; i < x; i++)
            boardSave[i] = board[i].clone();


        }
    }

    private static int[][] deepCopy2DArray(int[][] board){
        return board;
    }

    // issue is we individually change each type. We should just map all changes
    // one by one then assign them altogether.

    private static boolean isEqualArray(int[][] board1, int[][]board2){
        int x = board1[0].length; // size of x dim
        int y = board1[1].length; // size of y dim
        boolean flag = true;
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (board1[i][j] != board2[i][j]){
                    System.out.println("The board's are not equal");
                    return false;
                }
            }
        }
        System.out.println("THE BOARD IS EQUAL!");
        return true;
    }

    private static void printBoard(int[][] board){
        int x = board[0].length; // size of x dim
        int y = board[1].length; // size of y dim
        for (int j = 0; j < y; j++){
            for (int i = 0; i < x; i++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("&&&&&&&&&&&&");
    }

    private static int getCurrent(int[][] board, int _x, int _y){
        // 4 corners
        // 4 sides
        // and middle
        int x = board[0].length; // size of x dim
        int y = board[1].length; // size of y dim
        int nei = 0; // neighbours

        if (_x == 0 && _y == 0){
            if (board[0][1] == 1) nei++;
            if (board[1][0] == 1) nei++;
            if (board[1][1] == 1) nei++;
        }

        else if(_x == x-1 && _y == 0){
            if (board[x-1][1] == 1) nei++;
            if (board[x-2][0] == 1) nei++;
            if (board[x-2][1] == 1) nei++;
        }

        else if (_x == 0 && _y == y-1){
            if (board[0][y-1] == 1) nei++;
            if (board[1][y-1] == 1) nei++;
            if (board[1][y-2] == 1) nei++;
        }

        else if (_x == x-1 && _y == y-1){
            if (board[x-1][y-2] == 1) nei++;
            if (board[x-2][y-2] == 1) nei++;
            if (board[x-2][y-1] == 1) nei++;
        }

        else if (_x == 0){
            if (board[0][_y-1] == 1) nei++;
            if (board[0][_y+1] == 1) nei++;
            if (board[1][_y] == 1) nei++;
            if (board[1][_y-1] == 1) nei++;
            if (board[1][_y+1] == 1) nei++;

        }

        else if (_x == x-1){
            if (board[x-1][_y-1] == 1) nei++;
            if (board[x-1][_y+1] == 1) nei++;
            if (board[x-2][_y] == 1) nei++;
            if (board[x-2][_y-1] == 1) nei++;
            if (board[x-2][_y+1] == 1) nei++;

        }

        else if (_y == 0){
            if (board[_x-1][0] == 1) nei++;
            if (board[_x+1][0] == 1) nei++;
            if (board[_x][1] == 1) nei++;
            if (board[_x-1][1] == 1) nei++;
            if (board[_x+1][1] == 1) nei++;

        }

        else if (_y == y-1){
            if (board[_x-1][y-1] == 1) nei++;
            if (board[_x+1][y-1] == 1) nei++;
            if (board[_x][y-2] == 1) nei++;
            if (board[_x-1][y-2] == 1) nei++;
            if (board[_x+1][y-2] == 1) nei++;

        }
        else{
            if (board[_x-1][_y] == 1) nei++;
            if (board[_x][_y-1] == 1) nei++;
            if (board[_x-1][_y-1] == 1) nei++;
            if (board[_x+1][_y] == 1) nei++;
            if (board[_x][_y+1] == 1) nei++;
            if (board[_x+1][_y+1] == 1) nei++;
            if (board[_x+1][_y-1] == 1) nei++;
            if (board[_x-1][_y+1] == 1) nei++;

        }
        boolean flag = false;
        if (board[_x][_y] == 1){
            flag = true;
        }

        if (nei == 3){
            return 1;
        }
        if (nei == 2 && flag){
            return 1;
        }
        return 0;

        

      //  return 0;
    }

    



    public static void main(String[] args) {
        int board[][] ={{0,1,1,0,0,0,0,0,0,0},
                        {1,1,0,0,0,0,0,0,0,0},
                        {0,0,1,0,0,0,1,0,0,0},
                        {0,0,1,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,1,0,0,0,0,0},
                        {0,1,1,1,0,0,0,1,0,0},
                        {0,0,0,0,0,1,1,1,0,0},
                        {0,1,0,0,0,0,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0}};
        run(board);
    }
}