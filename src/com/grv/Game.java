package com.grv;

import java.util.Scanner;

/**
 * @author Gaurav Rajput
 * Created on 20/11/19
 */
public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayer;

    Game() {
        System.out.println("Welcome to Tic tac Toe");
    }

    public void addBoard(Board board) {
        this.board = board;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void displayBoard() {
        System.out.println("'O' for : " + players[0].name);
        System.out.println("'X' for : " + players[1].name);

        board.display();
    }

    public boolean checkWinner() {
        if(board.checkWinner()) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("## Congratulations : " + players[currentPlayer].name + " You Won !!!");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Final Board Status");
            board.display();
            return true;
        }
        return false;
    }

    public boolean checkForTie() {
        if(board.checkTie()) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("## Both played well!! Game is a Tie");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------");

            return true;
        }
        return false;
    }

    public int getRow(int cellNo) {
        return (cellNo - 1) / board.getSize();
    }

    public int getCol(int cellNo) {
        return (cellNo - 1) % board.getSize();
    }

    public void start(Scanner sc) {
        currentPlayer = 0;
        char empty = '\u0000';
        while(true) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Empty Cell : " + empty);
            System.out.println(players[0].name + " : O");
            System.out.println(players[1].name + " : X");
            board.display();
            if(currentPlayer == 0) {
                System.out.println("Please play your move : " + players[currentPlayer].name);
            } else {
                System.out.println("Please play your move : " + players[currentPlayer].name);
            }

            System.out.println("Enter cell No : ");
            int cellNo = sc.nextInt();
            if(cellNo <= 0 || cellNo > board.getSize() * board.getSize()) {
                System.out.println("Enter correct cell No!!!");
            }
            else {
                int row = getRow(cellNo);
                int col = getCol(cellNo);

                if (isValid(row, col)) { //Checking for occupied and invalid indexes
                    board.put(row, col, players[currentPlayer].symbol);

                    if (checkWinner() || checkForTie()) {
                        break;
                    }
                    currentPlayer = currentPlayer == 0 ? 1 : 0;
                } else {
                    System.out.println("**********Wrong Move. Please Enter carefully!!**********");
                }
            }
        }
    }

    public boolean isValid(int row, int col) {
        if(!board.isCellEmpty(row, col))
            return false;

        if(row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize())
            return true;
        return false;
    }

    public static void main(String[] args) {
        Player[] players = new Player[2];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of First Player : ");
        String name1 = sc.next();
        System.out.println("Enter name of Second Player : ");
        String name2 = sc.next();
        players[0] = new Player('O', name1);
        players[1] = new Player('X', name2);

        System.out.println("Enter the size of board.");
        int boardSize = sc.nextInt();
        Board board = new Board(boardSize);

        Game g = new Game();
        g.addBoard(board);
        g.setPlayers(players);
        g.start(sc);
    }
}
