package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Column;
import chess.domain.Position;

import java.util.List;

import static chess.direction.Direction.getBlackPawnDirection;
import static chess.direction.Direction.getWhitePawnDirection;
import static chess.domain.score.Score.PAWN_DEFAULT_SCORE;
import static chess.view.ErrorMessage.MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DIRECTION_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_DISTANCE_ERROR_GUIDE_MESSAGE;
import static chess.view.ErrorMessage.MOVE_FORWARD_ERROR_GUIDE_MESSAGE;

public class Pawn extends Piece {

    private final List<Direction> direction;
    private boolean isFirstMove = true;

    public Pawn(PieceInfo pieceInfo) {
        super(pieceInfo.getName(), pieceInfo.getColor(), PAWN_DEFAULT_SCORE.getScore());
        this.direction = createDirectionByColor(pieceInfo.getColor());
    }

    private List<Direction> createDirectionByColor(Color color) {
        if (color == Color.BLACK) {
            return getBlackPawnDirection();
        }
        return getWhitePawnDirection();
    }

    @Override
    public boolean isMovable(Position start, Position end, Color destinationColor) {
        Direction direction = findDirection(start, end);
        checkDirection(direction);
        checkDistance(start, end);
        checkMovableToDestination(destinationColor, direction);
        return true;
    }

    private void checkDirection(Direction direction) {
        if (!this.direction.contains(direction)) {
            throw new IllegalArgumentException(MOVE_DIRECTION_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkDistance(Position start, Position end) {
        int absGapOfColumn = Math.abs(start.findGapOfColum(end));
        int absGapOfRank = Math.abs(start.findGapOfRank(end));
        int distance = 1;

        if (isFirstMove) {
            distance = 2;
        }

        if (absGapOfColumn > distance || absGapOfRank > distance) {
            throw new IllegalArgumentException(MOVE_DISTANCE_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private void checkMovableToDestination(Color colorOfDestination, Direction direction) {
        if (isForwardDirection(direction)) {
            checkMoveForward(colorOfDestination);
        }

        if (isDiagonalDirection(direction)) {
            checkMoveDiagonal(colorOfDestination);
        }
    }

    private void checkMoveForward(Color colorOfDestination) {
        if (colorOfDestination != Color.NONE) {
            throw new IllegalArgumentException(MOVE_FORWARD_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isForwardDirection(Direction direction) {
        return direction == Direction.TOP || direction == Direction.BOTTOM;
    }

    private void checkMoveDiagonal(Color colorOfDestination) {
        if (colorOfDestination == Color.NONE || this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(MOVE_DIAGONAL_ERROR_GUIDE_MESSAGE.getErrorMessage());
        }
    }

    private boolean isDiagonalDirection(Direction direction) {
        return direction != Direction.TOP && direction != Direction.BOTTOM;
    }

    @Override
    public int calculateKing(int count) {
        return count;
    }

    @Override
    public int calculatePawn(int count, Color color) {
        if (this.isSameColor((color))) {
            return count + 1;
        }
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
            return new Pawn(PieceInfo.BLACK_PAWN_INFO);
        }

        return new Pawn(PieceInfo.WHITE_PAWN_INFO);
    }

    public void setIsFirstMove(){
        isFirstMove = false;
    }

}
