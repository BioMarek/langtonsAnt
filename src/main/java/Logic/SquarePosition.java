package Logic;

public class SquarePosition {
    public int row;
    public int column;
    public Direction direction;

    public SquarePosition() {
    }

    public SquarePosition(int row, int column, Direction direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public SquarePosition(int row, int column) {
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
