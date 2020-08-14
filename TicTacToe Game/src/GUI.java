import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//Note: The result of the game is only displayed when the box containing the result is moused over

public class GUI extends Application {
    public int gamelock = 0;
    private boolean Xturn = true;
    TicTacToe board = new TicTacToe();


    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(600,700);
        Bottomtile Btile = new Bottomtile();
        Btile.setTranslateX(0);
        Btile.setTranslateY(600);
        root.getChildren().add(Btile);
        for(int i = 0; i <3; i++){
            for(int j = 0; j < 3; j++){
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                tile.pos = (j + (i *3));

                root.getChildren().add(tile);
            }
        }
        return root;
    }

    public void start(Stage primaryStage) throws Exception{
        board.setup();
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(gamelock==1){

                }
            }
        });
        primaryStage.show();

    }

    private class Bottomtile extends StackPane {
        private Text text = new Text();
        public Bottomtile(){
            Rectangle border = new Rectangle(600, 100);
            border.setFill(null);
            border.setStroke(Color.PURPLE);
            setAlignment(Pos.CENTER);
            getChildren().addAll(border,text);

            this.text.setText("Game In Progress!");

            setOnMouseMoved(event -> {
                if (board.isDone() == 0) {
                    this.text.setText("Player X wins!");
                } else if (board.isDone() == 1) {
                    this.text.setText("Player O wins!");
                } else if (board.isDone() == 2) {
                    this.text.setText("Stalemate! Everyone loses!");
                } else if(board.isDone() ==3) {
                    this.text.setText("Game In Progress!");
                }
            });
        }

    }

    private class Tile extends StackPane {
        private Text text = new Text();
        private int pos;
        public Tile(){
            Rectangle border = new Rectangle(200,200);
            border.setFill(null);
            border.setStroke(Color.DARKCYAN);
            text.setFont(Font.font(108));
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event ->{
                if((event.getButton() == MouseButton.PRIMARY || event.getButton() == MouseButton.SECONDARY) && Xturn == true && gamelock==0){
                    drawX();
                    board.graphicalmove(pos, 'X');
                    Xturn = false;
                    board.replayQueue.addLast(Integer.toString(pos));
                    if(board.isDone()!= 3){
                        gamelock=1;
                        board.graphicReplayGame();
                    }
                }
                else if ((event.getButton() == MouseButton.SECONDARY || event.getButton() == MouseButton.PRIMARY) && Xturn == false && gamelock ==0){
                    drawO();
                    board.graphicalmove(pos, 'O');
                    Xturn = true;
                    board.replayQueue.addLast(Integer.toString(pos));
                    if(board.isDone()!=3){
                        gamelock=1;
                        board.graphicReplayGame();
                    }
                }
            });

        }
        private void drawX(){
            text.setText("X");
        }
        private void drawO(){
            text.setText("O");
        }
        private void drawblank(){
            text.setText("");
        }
    }
    public static void main(String[] args){
        launch(args);
    }


}


