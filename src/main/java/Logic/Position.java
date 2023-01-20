package Logic;

public class Position {
    public int row;
    public int column;
    public Direction direction;

    public Position() {
    }

    public Position(int row, int column, Direction direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                ", direction=" + direction +
                '}';
    }
}
