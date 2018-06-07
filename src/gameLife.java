public class gameLife {
    public static void main(String[] args) {
        int n = 5;
        int[][] map = new int[n][n];
        for (int row = 0; row < n; row++)
            for (int col = 0; col < n; col++)
                map[row][col] = (int) (Math.random() * 2);
        for (int row = 0; row < n; row++){
            for (int col = 0; col < n; col++) {
                System.out.print(map[row][col]);
            }
            System.out.println();
        }
        moves move = new moves(map);
        map = move.nextGen();
        for (int row = 0; row < n; row++){
            for (int col = 0; col < n; col++) {
                System.out.print(map[row][col]);
            }
            System.out.println();
        }
    }
}
