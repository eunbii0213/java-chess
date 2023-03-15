package chess.domain.game;

import chess.domain.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.practiceMove.Direction;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

    public boolean hasPiece(Position start) {
        if (chessBoard.get(start) instanceof EmptyPiece) {
            return false;
        }
        return true;
    }



    public void move(Position start, Position end) {
        if (!hasPiece(start)) {
            throw new IllegalArgumentException("이동할 수 있는 기물이 없습니다.");
        }
        Piece piece = chessBoard.get(start);

        //gap구하기
        int gapOfRank = start.findGapOfRank(end);
        int gapOfColumn = start.findGapOfColum(end);

        //기물이 한번에 이동할 수 있는 거리인지 확인한다
        if (!piece.isMovableAtOnce(Math.abs(gapOfColumn), Math.abs(gapOfRank))) {
            throw new IllegalArgumentException("기물이 이동할 수 없습니다");
        }

        // todo :네이밍 고민하기
        // gap에 해당하는 Direction 구하기
        //근데 이러면 0,-1을 찾더라구?? 절댓값해서 생긴 문제일까? 흠
        Direction direction = Direction.findDirection(piece, gapOfColumn, gapOfRank);
        System.out.println("direction = " + direction.getX() + "," + direction.getY());
        //기물이 해당 direction으로 이동할 수 있는 지 구하기기
        if (!piece.isMovableDirection(direction)) {
            throw new IllegalArgumentException("기물이 이동할 수 없습니다1");
        }

        //현재 위치가 end가 될 떄 까지, move 하면서 해당포지션에 기물이 존재하는지 확인한다. 존재하면
        Position currentPosition = start;
        while (currentPosition.toString().equals(end.toString())) {
            System.out.println(currentPosition.toString());
            currentPosition = currentPosition.moveDirection(direction);
            System.out.println(currentPosition.toString());

            if (currentPosition == end && piece.isSameColor(piece)) {
                throw new IllegalArgumentException("기물이 이동할 수 없습니다2");
            }

            if (hasPiece(currentPosition)) {
                throw new IllegalArgumentException("기물이 이동할 수 없습니다3");
            }

        }

        chessBoard.replace(start, new EmptyPiece());
        chessBoard.replace(end, piece);


    }
}
