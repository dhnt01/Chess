package boardview;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import model.Position;
import model.Piece;
import model.chess.ChessBoard;
import gamecontrol.ChessController;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;

/**
 * View class for a tile on a chess board
 * A tile should be able to display a chess piece
 * as well as highlight itself during the game.
 *
 * @author <Yourname here>
 */
public class TileView implements Tile {
    private Position p;
    private Label labsymbol;
    private String symbol;
    private Node stackPane;
    private Rectangle rect;


    /**
     * Creates a TileView with a specified position
     * @param p
     */
    public TileView(Position p) {
        this.p = p;
        this.labsymbol = new Label();
        ChessBoard board = new ChessBoard();
        if (board.getPieceAt(p) == null) {
            this.symbol = "";
        } else {
            Piece piece = board.getPieceAt(p);
            this.symbol = piece.getType().getSymbol(piece.getSide());
        }

        Canvas canvas = new Canvas(70, 70);
        labsymbol = new Label();
        labsymbol.setText(symbol);
        labsymbol.setFont(new Font(45));
        labsymbol.setWrapText(true);
        stackPane = new StackPane(canvas, labsymbol);
        if ((p.getRow() % 2) == (p.getCol() % 2)) {
            stackPane.setStyle("-fx-background-color: WHITE");
        } else {
            stackPane.setStyle("-fx-background-color: #CD5C5C");
        }

    }


    @Override
    public Position getPosition() {
        // tODO
        return p;
    }


    @Override
    public Node getRootNode() {
        // TODO
        // Canvas canvas = new Canvas(70, 70);
        // labsymbol = new Label();
        // labsymbol.setText(symbol);
        // labsymbol.setFont(new Font(45));
        // labsymbol.setWrapText(true);
        // stackPane = new StackPane(canvas, labsymbol);
        // if ((p.getRow() % 2) == (p.getCol() % 2)) {
        //     stackPane.setStyle("-fx-background-color: WHITE");
        // } else {
        //     stackPane.setStyle("-fx-background-color: red");
        // }
        return stackPane;
    }

    @Override
    public void setSymbol(String symbol) {
        // TODO
        this.symbol = symbol;
        labsymbol = new Label();
        labsymbol.setText(symbol);
        labsymbol.setFont(new Font(45));
        labsymbol.setWrapText(true);
        // Canvas canvas = new Canvas(70, 70);
        // labsymbol = new Label();
        // labsymbol.setText(symbol);
        // labsymbol.setFont(new Font(45));
        // labsymbol.setWrapText(true);
        // stackPane = new StackPane(canvas, labsymbol);
        if ((p.getRow() % 2) == (p.getCol() % 2)) {
            stackPane.setStyle("-fx-background-color: WHITE");
        } else {
            stackPane.setStyle("-fx-background-color: #CD5C5C");
        }
        getRootNode();
    }


    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void highlight(Color color) {
        // TODO
        // Canvas canvas = new Canvas(70, 70);
        // labsymbol = new Label();
        // labsymbol.setText(symbol);
        // labsymbol.setFont(new Font(45));
        // labsymbol.setWrapText(true);
        // stackPane = new StackPane(canvas, labsymbol);
        if ((p.getRow() % 2) == (p.getCol() % 2)) {
            stackPane.setStyle("-fx-background-color: #FFDEAD");
        } else {
            stackPane.setStyle("-fx-background-color: #90EE90");
        }

    }

    @Override
    public void clear() {
        if ((p.getRow() % 2) == (p.getCol() % 2)) {
            stackPane.setStyle("-fx-background-color: white");
        } else {
            stackPane.setStyle("-fx-background-color: #CD5C5C");
        }
    }
}
