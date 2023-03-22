package chess.domain;

import chess.direction.Direction;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final Column column;

    public Position(String column, String rank) {
        this(Column.findColumnByValue(column), Rank.findRankByValue(rank));
    }

    public Position(Column column, Rank rank) {
        this.column = column;
        this.rank = rank;
    }

    public int findGapOfRank(Position destination) {
        return destination.rank.getIndex() - rank.getIndex();
    }

    public int findGapOfColum(Position destination) {
        return destination.column.getIndex() - column.getIndex();
    }

    public Position moveDirection(Direction direction) {
        Column newColumn = Column.findColumnByIndex(column.getIndex() + direction.getX());
        Rank newRank = Rank.findRankByIndex(rank.getIndex() + direction.getY());

        return new Position(newColumn, newRank);
    }

    @Override
    public String toString() {
        return column.name() + rank.name();
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, column);
    }

    public boolean isSameColumn(Column column) {
        return this.column.equals(column);
    }
}
