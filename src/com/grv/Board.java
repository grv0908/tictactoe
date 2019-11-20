package com.grv;

/**
 * @author Gaurav Rajput
 * Created on 20/11/19
 */
public class Board {
    private int size;
    private char[][] matrix;

    Board(int size) {
        this.size = size;
        this.matrix = new char[size][size];
    }

    public int getSize() {
        return this.size;
    }

    public void put(int row, int col, char c) {
        matrix[row][col] = c;
    }

    public boolean isCellEmpty(int row, int col) {
        return matrix[row][col] != 'O' && matrix[row][col] != 'X';
    }

    public boolean checkWinner() {
        for(int i = 0; i < size; i++) {
            if(checkRow(i)) {
                return true;
            }
            if(checkColumn(i)) {
                return true;
            }
        }

        if(checkFirstDiagonal())
            return true;

        if(checkSecondDiagonal())
            return true;
        //Also check for Diagonal
        return false;
    }

    public boolean checkFirstDiagonal() {
        char c = matrix[0][0];
        if(isCellEmpty(0,0))
            return false;

        for(int i = 0; i < size; i++) {
            if(c != matrix[i][i])
                return false;
        }
        return true;
    }

    public boolean checkSecondDiagonal() {
        if(isCellEmpty(0, size - 1))
            return false;

        char c = matrix[0][size - 1];
        int k = 0;
        for(int i = size - 1; i >= 0; i--) {
            if(c != matrix[k++][i])
                return false;
        }
        return true;
    }

    public boolean checkRow(int row) {
        char c = matrix[row][0];
        if(isCellEmpty(row, 0)) {
            return false; // if cell is empty return false;
        }

        // Checking if whole row is same as first character
        for(int i = 1; i < size; i++) {
            if(c != matrix[row][i] )
                return false;
        }
        return true;
    }

    public boolean checkColumn(int col) {
        char c = matrix[0][col];
        if(isCellEmpty(0, col)) {
            return false; // if cell is empty return false;
        }

        // Checking if whole col is same as first character
        for(int i = 1; i < size; i++) {
            if(c != matrix[i][col])
                return false;
        }
        return true;
    }

    public boolean checkTie() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(isCellEmpty(i, j))
                    return false; // if any cell is empty then chances are left
            }
        }
        return true; // if all cell are filled hence tie
    }

    public void display() {
        int k = 1;
        System.out.println("----------------------------------------------------------------------------");

        System.out.println("Current Matrix Status : " + "\t\t\t\t" + "Cell No. for Reference");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j<size; j++) {
                System.out.print(matrix[i][j] + "\t");
            }

            System.out.print("\t\t\t\t\t\t\t");
            for(int j = 0; j<size; j++) {
                System.out.print(k++ + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
    }
}
