import java.util.Arrays;

public class Life {

    private  int rowCount;
    private  int columnCount;

    private int [][] grid;
    public final static int DeadCell = 0;
    public final static int LivingCell = 1;

    public Life(int rowCount, int columnCount){
        grid = new int[rowCount][columnCount];
        this.rowCount = grid.length;
        this.columnCount = grid[0].length;
        GridWithDeadCells();

    }

    public void GridWithDeadCells(){
        for (int y = 0; y < this.rowCount ; y++) {
            Arrays.fill(grid[y], DeadCell);
        }
    }
    public void setLivingCell(int row, int column){
        grid[row][column] = LivingCell;
    }

    public boolean isInGrid(int row, int col){
        return row >= 0 && col >= 0 && row < rowCount && col < columnCount;
    }

    public boolean isAlive(int row, int column){
        return grid[row][column] == LivingCell;
    }
    public boolean isDead(int row, int column){
        return grid[row][column] == DeadCell;
    }
    public  boolean cellIsAliveAndHasLessThanTwoLivingNeighbours(int row, int column){
        int neighboursCount = countLivingNeighbours(row, column);
        return isAlive(row, column) && neighboursCount < 2;
    }
    public boolean cellIsAliveAndHasTwoOrThreeLivingNeighbours(int row, int column){
        int livingNeighbours = countLivingNeighbours(row, column);
        return isAlive(row, column) && (livingNeighbours == 2 || livingNeighbours == 3);
    }
    public  boolean cellIsAliveAndHasMoreThanThreeLivingNeighbours(int row, int column){
        int livingNeighbours = countLivingNeighbours(row, column);
        return isAlive(row,column) && livingNeighbours > 3;
    }

    public boolean cellIsDeadAndHasThreeLivingNeighbours(int row, int column){
        int livingNeighbours = countLivingNeighbours(row, column);
        return isAlive(row, column) && livingNeighbours == 3;
    }

    public int countLivingNeighbours(int row, int column){
        int[][] cellsToCheck = {
                {row - 1, column - 1},
                {row - 1, column},
                {row - 1, column + 1},
                {row, column + 1},
                {row + 1, column + 1},
                {row + 1, column},
                {row + 1, column - 1},
                {row, column - 1},
        };
        int livingNeighbours = 0;
        for (int i = 0; i < cellsToCheck.length; i++) {
            int rowToCheck = cellsToCheck[i][0];
            int colToCheck = cellsToCheck[i][1];

            if (isInGrid(rowToCheck,colToCheck) && isAlive(rowToCheck, colToCheck)){
                livingNeighbours++;
            }
        }
        return livingNeighbours;
    }
    public void NextGeneration(){
        int[][] nextGenerationGrid = new int[rowCount][columnCount];

        for (int y = 0; y < rowCount ; y++) {
            for (int x = 0; x < columnCount ; x++) {
                if (cellIsAliveAndHasLessThanTwoLivingNeighbours(y, x)){
                    nextGenerationGrid[y][x] = DeadCell;
                }
                else if (cellIsAliveAndHasTwoOrThreeLivingNeighbours(y,x)){
                    nextGenerationGrid[y][x] = LivingCell;
                }else if (cellIsAliveAndHasMoreThanThreeLivingNeighbours(y,x)){
                    nextGenerationGrid[y][x] = DeadCell;
                }else if (cellIsDeadAndHasThreeLivingNeighbours(y,x)){
                    nextGenerationGrid[y][x] = LivingCell;
                }else{
                    nextGenerationGrid[y][x] = grid[y][x];
                }
            }
        }
        grid = nextGenerationGrid.clone();
    }
}
