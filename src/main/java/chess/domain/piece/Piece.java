package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Color;
import chess.domain.Position;

public abstract class Piece {

    private final String name;
    private final Color color;
    private double score;

    public Piece(String name, Color color, double score) {
        this.name = name;
        this.color = color;
        this.score = score;
    }

    public String findName() {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name.toUpperCase();
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public Direction findDirection(Position start, Position end) {
        System.out.println(Direction.findDirectionByGap(start, end, this).name());
        return Direction.findDirectionByGap(start, end, this);
    }

    abstract public boolean isMovable(Position start, Position end, Color colorOfDestination);

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
