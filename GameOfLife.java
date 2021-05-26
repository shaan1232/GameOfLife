import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameOfLife {

    public static void runGame(int[][] board){
        int x = board[0].length; // size of x dim
        int y = board[1].length; // size of y dim

        int[][] boardSave = new int[x][y];
        int[][] nextBoard = new int[x][y];
        for(int i = 0; i < x; i++)
        boardSave[i] = board[i].clone();
        printBoard(board);
        boolean flag = true;

        JFrame frame = new JFrame("Game1232");
        Map map = new Map(board);
        frame.add(map);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        while (flag){
            map.updateBoard(board);
            frame.add(map);
            frame.invalidate();
            frame.validate();
            frame.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }

            for (int i = 0; i < x; i++){
                for (int j = 0; j < y; j++){
                    nextBoard[i][j] = getCurrent(board, i, j);
                }
                
            }
            for (int i = 0; i < x; i++)
                for (int j = 0; j < y; j++){
                    board[i][j] = nextBoard[i][j];
                }
            printBoard(boardSave);
            if ((isEqualArray(board,boardSave))){
                flag = false;
            }
            System.out.println("Saving new state");
            for(int i = 0; i < x; i++)
            boardSave[i] = board[i].clone();


        }
        frame.dispose();
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
    }

    



    public static void main(String[] args) {

        // glitch to fix: board has to be same n_length n_width or else it defaults to lowest (4,3) because (3,3)
        int board[][] =
        {
          { 0 ,1 ,1 ,1 ,1 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,1 ,0 ,0 ,1},
          { 0 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,0},
          { 0 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,0 ,1 ,0},
          { 0 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,0 ,1 ,0},
          { 1 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,0 ,1 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,1},
          { 1 ,0 ,0 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,0 ,0},
          {  0 ,1 ,0 ,1 ,1 ,1 ,0 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,1 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,1 ,1},
          {  1 ,1 ,0 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,0 ,1 ,1 ,1},
          {  0 ,1 ,1 ,0 ,0 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,1},
          {  1 ,1 ,1 ,1 ,0 ,0 ,1 ,0 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,0},
          {  0 ,1 ,1 ,0 ,0 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,1 ,0 ,1 ,0},
          {  1 ,0 ,1 ,0 ,1 ,0 ,0 ,1 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,0},
          {  1 ,1 ,0 ,0 ,1 ,0 ,1 ,1 ,0 ,1 ,0 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,0 ,1 ,1 ,1 ,1 ,0 ,0 ,1 ,1},
          {  0 ,0 ,0 ,1 ,1 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,0 ,1 ,1 ,1},
          {  0 ,0 ,1 ,0 ,0 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0},
          {  1 ,1 ,0 ,0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,0 ,1 ,1 ,0},
          {  1 ,1 ,1 ,0 ,0 ,1 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,0 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,0 ,1 ,0 ,1 ,1},
          {  1 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,1 ,0},
          {  1 ,1 ,0 ,0 ,1 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,0},
          {  0 ,0 ,1 ,0 ,1 ,0 ,1 ,0 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,0},
          {  0 ,1 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,0},
          {  1 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,1 ,0 ,1 ,1 ,0 ,1 ,0 ,0 ,1 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,1},
          {  1 ,0 ,1 ,0 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,0 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,1},
          {  1 ,0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,0 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,1},
          {  0 ,0 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,0},
          {  1 ,1 ,1 ,0 ,0 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,1},
          {  0 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,0 ,1 ,1 ,0 ,1},
          {  0 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,0 ,1 ,0 ,0 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,0 ,1 ,1 ,0 ,0 ,0 ,1 ,1},
          {  0 ,1 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,1 ,0 ,0 ,0},
          {  0 ,0 ,1 ,0 ,0 ,1 ,1 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,0 ,1 ,1 ,0 ,0 ,1},
          {  1 ,1 ,1 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,1 ,1 ,1 ,0 ,0 ,0}
        };

        
        runGame(board);

        
    }
}