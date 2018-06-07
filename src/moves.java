public class moves {

    private int[][] map;
    private int[][] tmpMap;

    public moves(int[][] map) {
        this.map = map;
        this.tmpMap = map;
    }

    private int checkMid(int row, int col) {
        int counter = 0;
        if (map[row][col] == 1)
            counter--;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (map[i][j] == 1)
                    counter++;
            }
        }
        return counter;
    }

    private int checkSides(int row, int col) {
        int counter = 0;
        if (map[row][col] == 1)
            counter--;
        counter = ifSides0(col, row, counter);
        counter = ifSidesSize(col, row, counter);
        counter = ifSides0(row, col, counter);
        counter = ifSidesSize(row, col, counter);
        return counter;
    }

    private int ifSidesSize(int row, int col, int counter) {
        if (col == map.length - 1) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col; j++) {
                    if (map[i][j] == 1)
                        counter++;
                }
            }
        }
        return counter;
    }

    private int ifSides0(int row, int col, int counter) {
        if (col == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col; j <= col + 1; j++) {
                    if (map[i][j] == 1)
                        counter++;
                }
            }
        }
        return counter;
    }

    private int checkCorners(int row, int col) {
        int counter = 0;
        if (map[row][col] == 1)
            counter--;
        if (row == 0 && col == 0) {
            for (int i = row; i <= row + 1; i++) {
                for (int j = col; j <= col + 1; j++) {
                    if (map[i][j] == 1)
                        counter++;
                }
            }
        }
        if (row == map.length - 1 && col == 0) {
            for (int i = row - 1; i <= row; i++) {
                for (int j = col; j <= col + 1; j++) {
                    if (map[i][j] == 1)
                        counter++;
                }
            }
        }
        if (row == 0 && col == map.length - 1) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col; j++) {
                    if (map[i][j] == 1)
                        counter++;
                }
            }
        }
        if (row == map.length - 1 && col == map.length - 1) {
            for (int i = row - 1; i <= row; i++) {
                for (int j = col - 1; j <= col; j++) {
                    if (map[i][j] == 1)
                        counter++;
                }
            }
        }
        return counter;
    }

    private void killSides(int row, int col) {
        if (checkSides(row, col) == 3 && map[row][col] == 0)
            tmpMap[row][col] = 1;
        else if ((checkSides(row, col) == 2 || checkSides() == 3) && map[row][col] == 1)
            tmpMap[row][col] = 1;
        else
            tmpMap[row][col] = 0;
    }

    public int[][] nextGen() {
    //prepare next generation in the middle part of the map
        for (int row = 1; row < map.length - 1; row++)
            for (int col = 1; col < map.length - 1; col++) {
                if (checkMid(row, col) == 3 && map[row][col] == 0)
                    tmpMap[row][col] = 1;
                else if ((checkMid(row, col) == 2 || checkMid() == 3) && map[row][col] == 1)
                    tmpMap[row][col] = 1;
                else
                    tmpMap[row][col] = 0;
            }
    //prepare next generation in left and right part of the map
        for (int col = 0; col < map.length; col = col + map.length - 1)
            for (int row = 1; row < map.length - 1; row++)
                killSides(row, col);
    //prepare next generation in bottom and top part of the map
        for (int row = 0; row < map.length; row = row + map.length - 1)
            for (int col = 1; col < map.length - 1; col++)
                killSides(row, col);
    //prepare next generation in corners of the map
        for (int row = 0; row < map.length; row = row + map.length - 1)
            for (int col = 0; col < map.length; col = col + map.length - 1) {
                if (checkCorners(row, col) == 3 && map[row][col] == 0)
                    tmpMap[row][col] = 1;
                else if ((checkCorners(row, col) == 2 || checkCorners() == 3) && map[row][col] == 1)
                    tmpMap[row][col] = 1;
                else
                    tmpMap[row][col] = 0;
            }
        return tmpMap;
    }
}
