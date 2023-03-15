package chess.practiceMove;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;

import java.util.Arrays;

public enum Direction {
    TOP(0,1),
    BOTTOM(0,-1),
    LEFT(-1,0),
    RIGHT(1,0),
    TOP_LEFT(-1,1),
    TOP_RIGHT(1,1),
    BOTTOM_LEFT(-1,-1),
    BOTTOM_RIGHT(1,-1),
    KNIGHT_TOP_LEFT(-1,2),
    KNIGHT_TOP_RIGHT(1,2),
    KNIGHT_LEFT_TOP(-2,1),
    KNIGHT_LEFT_BOTTOM(-2,-1),
    KNIGHT_RIGHT_TOP(2,1),
    KNIGHT_RIGHT_BOTTOM(2,-1),
    KNIGHT_BOTTOM_LEFT(-1,-2),
    KNIGHT_BOTTOM_RIGHT(1,-2);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
// 4 -8  absX absY
    public static Direction findDirection(Piece piece, int x, int y) {
        int absX = Math.abs(x);
        int absY = Math.abs(y);
        return Arrays.stream(Direction.values())
                .filter(direction -> {
                    if(piece instanceof Knight) {
                        return direction.x == x && direction.y == y;
                    }
                    return direction.x * absX == x && direction.y * absY == y;
                })
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("일치하는 Direction 값이 없습니다"));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
