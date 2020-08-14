import java.util.Scanner;



public class TextUI{
    public static void main(String[] args) {
        int done = 0;
        String[] coords;
        TicTacToe board = new TicTacToe();
        board.setup();
        board.printBoard();
        while (done == 0) {
            System.out.println("Player X <row col>: ");
            Scanner input = new Scanner(System.in);
            String location = input.nextLine();
            coords = location.split(" ");
            board.replayQueue.addLast(location);
            board.move(coords, 'X');
            board.printBoard();
            if(board.isDone() == 0){
                System.out.println("X wins!");
                done=1;
            }
            else if(board.isDone() == 1){
                System.out.println("O wins!");
                done=1;
            }
            if(board.isDone() == 2){
                System.out.println("Stalemate! Everyone Loses!");
                done=1;
            }
            if(done == 0) {
                System.out.println("Player O <row col>: ");
                input = new Scanner(System.in);
                location = input.nextLine();
                coords = location.split(" ");
                board.replayQueue.addLast(location);
                board.move(coords, 'O');
                board.printBoard();
                if(board.isDone() == 0){
                    System.out.println("X wins!");
                    done=1;
                }
                else if(board.isDone() == 1){
                    System.out.println("O wins!");
                    done=1;
                }
                if(board.isDone() == 2){
                    System.out.println("Stalemate! Everyone Loses!");
                    done=1;
                }
            }

        }
        board.replayGame();
    }
}
