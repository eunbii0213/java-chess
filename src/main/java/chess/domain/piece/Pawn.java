package chess.domain.piece;

import chess.domain.Color;
import chess.practiceMove.Direction;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final String name = "p";
    private static final int MovableDistance = 1;
    private final List<Direction> movableDirection;

    public Pawn(Color color) {
        super(name, color);
        this.movableDirection = createMovableDirectionByColor(color);
    }

    private List<Direction> createMovableDirectionByColor(Color color) {
        if(color == Color.BLACK){
            return List.of(Direction.BOTTOM, Direction.LEFT, Direction.RIGHT, Direction.BOTTOM_LEFT,
                    Direction.BOTTOM_RIGHT);
        }
        return List.of(Direction.TOP, Direction.LEFT, Direction.RIGHT, Direction.TOP_LEFT,
                Direction.TOP_RIGHT);
    }

    @Override
    public boolean isMovableAtOnce(int absGapOfColum, int absGapOfRank) {
        return absGapOfColum <= MovableDistance && absGapOfRank <= MovableDistance;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return movableDirection.contains(direction);
    }
}
