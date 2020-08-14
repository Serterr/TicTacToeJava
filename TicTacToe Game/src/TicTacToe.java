import javafx.application.Application;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class TicTacToe{
    char[] row1 = new char[3];
    char[] row2 = new char[3];
    char[] row3 = new char[3];
    Deque<String> replayQueue = new ArrayDeque<>();

    public void printBoard(){
        System.out.println(this.row1);
        System.out.println(this.row2);
        System.out.println(this.row3);
    }
    public void setup(){
        for(int i = 0; i < 3; i++) {
            this.row1[i] = '-';
        }
        for(int i = 0; i < 3; i++) {
            this.row2[i] = '-';
        }
        for(int i = 0; i < 3; i++) {
            this.row3[i] = '-';
        }
    }
    public void move(String[] coords, char symbol){
        if(coords[1].equals("0")){
            if(coords[0].equals("0")){
                row1[0] = symbol;
            }
            else if(coords[0].equals("1")){
                row1[1] = symbol;
            }
            else if(coords[0].equals("2")){
                row1[2] = symbol;
            }
        }
        else if(coords[1].equals("1")){
            if(coords[0].equals("0")){
                row2[0] = symbol;
            }
            else if(coords[0].equals("1")){
                row2[1] = symbol;
            }
            else if(coords[0].equals("2")){
                row2[2] = symbol;
            }

        }
        else if(coords[1].equals("2")){
            if(coords[0].equals("0")){
                row3[0] = symbol;
            }
            else if(coords[0].equals("1")){
                row3[1] = symbol;
            }
            else if(coords[0].equals("2")){
                row3[2] = symbol;
            }
        }
    }
    public void graphicalmove(int pos, char symbol){
        if(pos < 3){
            row1[pos] = symbol;
        }
        else if((pos >= 3)&&(pos<6)){
            pos = pos -3;
            row2[pos] = symbol;
        }
        else{
            pos = pos -6;
            row3[pos] = symbol;
        }
    }
    public void replayGame(){
        System.out.println("Replaying the game");
        TicTacToe board = new TicTacToe();
        board.setup();
        board.printBoard();
        System.out.println();
        while(!(this.replayQueue.isEmpty())){
            String[] location = this.replayQueue.removeFirst().split(" ");
            board.move(location, 'X');
            board.printBoard();
            System.out.println();
            if(!(this.replayQueue.isEmpty())) {
                location = this.replayQueue.removeFirst().split(" ");
                board.move(location, 'O');
                board.printBoard();
                System.out.println();
            }

        }
    }
    public void graphicReplayGame(){
        this.setup();
        System.out.println("Replaying the GUI game in text");
        while(!(this.replayQueue.isEmpty())){
            String location = this.replayQueue.removeFirst();
            int intlocation = Integer.parseInt(location);
            graphicalmove(intlocation, 'X');
            this.printBoard();
            System.out.println();
            if(!(this.replayQueue.isEmpty())){
                location = this.replayQueue.removeFirst();
                intlocation = Integer.parseInt(location);
                graphicalmove(intlocation, 'O');
                this.printBoard();
                System.out.println();
            }
        }
    }
    //isDone() returns 0 when X player wins, returns 1 when O player wins, returns 2 when a stalemate occurs, and returns 3 if the game is still on!
    public int isDone(){
        if((row1[0] == 'X' && row1[1] == 'X' && row1[2] == 'X') || (row2[0] == 'X' && row2[1] == 'X' && row2[2] == 'X') || (row3[0] == 'X' && row3[1] == 'X' && row3[2] == 'X') ){
            return 0;
        }
        else if((row1[0] == 'X' && row2[0] == 'X' && row3[0] == 'X') || (row1[1] == 'X' && row2[1] == 'X' && row3[1] == 'X') || (row1[2] == 'X' && row2[2] == 'X' && row3[2] == 'X')){
            return 0;
        }
        else if((row1[0] == 'X' && row2[1] == 'X' && row3[2] == 'X') || (row1[2] == 'X' && row2[1] == 'X' && row3[0] == 'X')){
            return 0;
        }
        else if((row1[0] == 'O' && row1[1] == 'O' && row1[2] == 'O') || (row2[0] == 'O' && row2[1] == 'O' && row2[2] == 'O') || (row3[0] == 'O' && row3[1] == 'O' && row3[2] == 'O') ){
            return 1;
        }
        else if((row1[0] == 'O' && row2[0] == 'O' && row3[0] == 'O') || (row1[1] == 'O' && row2[1] == 'O' && row3[1] == 'O') || (row1[2] == 'O' && row2[2] == 'O' && row3[2] == 'O')){
            return 1;
        }
        else if((row1[0] == 'O' && row2[1] == 'O' && row3[2] == 'O') || (row1[2] == 'O' && row2[1] == 'O' && row3[0] == 'O')){
            return 1;
        }
        else if((row1[0] != '-' && row1[1] != '-' && row1[2] != '-' && row2[0] != '-' && row2[1] != '-' && row2[2] != '-' && row3[0] != '-' && row3[1] != '-' && row3[2] != '-')){
            return 2;
        }
        else return 3;
    }
}
