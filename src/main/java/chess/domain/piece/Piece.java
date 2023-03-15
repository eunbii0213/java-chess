package chess.domain.piece;

import chess.domain.Color;
import chess.practiceMove.Direction;

import java.util.List;

public abstract class Piece {

    private final String name;
    private final Color color;

    public Piece(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String findName() {
        if(color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name.toUpperCase();
    }

    abstract public boolean isMovableAtOnce(int abs, int abs1);

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    abstract public boolean isMovableDirection(Direction direction);
}
