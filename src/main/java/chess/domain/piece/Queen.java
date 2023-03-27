package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Position;

import java.util.List;

import static chess.domain.score.Score.QUEEN_SCORE;
import static chess.view.ErrorMessage.EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;

public class Queen extends Piece {

    private static final List<Direction> direction = Direction.getQueenDirection();

    public Queen(PieceInfo pieceInfo) {
        super(pieceInfo.getName(), pieceInfo.getColor(), QUEEN_SCORE.getScore());
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = findDirection(start, end);
        checkDirection(direction);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    public void checkDirection(Direction direction) {
        if (!Queen.direction.contains(direction)) {
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if (this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(EXIST_ALLY_AT_DESTINATION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    @Override
    public int calculateKing(int count) {
        return count;
    }

    @Override
    public boolean findDirection(Direction direction, Position start, Position end, Piece piece) {
        int gapOfRank = start.findGapOfRank(end);
        int gapOfColumn = start.findGapOfColum(end);
        int absX = Math.abs(gapOfColumn);
        int absY = Math.abs(gapOfRank);

        if (isDiagonal(direction)) {
            return direction.getX() * absX == gapOfColumn && direction.getY() * absX == gapOfRank;
        }

        return direction.getX() * absX == gapOfColumn && direction.getY() * absY == gapOfRank;
    }

    @Override
    public Piece getInstance(Color pieceColor) {
        if (pieceColor.equals(Color.BLACK)) {
            return new Queen(PieceInfo.BLACK_QUEEN_INFO);
        }

        return new Queen(PieceInfo.WHITE_QUEEN_INFO);
    }
}
