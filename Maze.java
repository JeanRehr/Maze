import java.util.Random;

public class Maze {
	private static int[][] maze2D;

	public Maze(int row, int col) {
		maze2D = new int[row][col];
	}

	// 1 are walls, 0 are possible paths
	// wallDensity used to determine the number of walls to randomly place within the maze
	public void fillMazeRand(int wallDensity) {
		for(int row = 0; row < maze2D.length; row++) {
			for(int col = 0; col < maze2D.length; col++) {
				maze2D[row][col] = 0;
			}
		}

		Random rand = new Random();
		for(int i = 0; i < maze2D.length * wallDensity; i++)
			maze2D[rand.nextInt(maze2D.length)][rand.nextInt(maze2D.length)] = 1;

		maze2D[0][0] = 0; // starting pos always a valid path
	}

	//assumes 10x10
	public void fillMaze() {
		int[][] matrix2D = {
			{0, 0, 1, 1, 1, 1, 0, 0, 1, 0},
			{1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
			{1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
			{1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
			{1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
			{0, 0, 0, 0, 1, 0, 0, 1, 1, 1},
			{1, 1, 1, 0, 1, 1, 0, 0, 0, 1},
			{0, 0, 1, 0, 1, 1, 1, 1, 0, 0}
		};
		maze2D = matrix2D;
	}

	public void showMaze() {
		for(int row = 0; row < maze2D.length; row++) {
			for(int col = 0; col < maze2D.length; col++) {
				System.out.print(maze2D[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// will only fill the zeroes where it's possible to go, row and col here are starting pos
	public void traverseMaze(int[][] maze2D, int row, int col) {
		if(maze2D[row][col] == 1)
			return;

		if(maze2D[row][col] == 0) {
			maze2D[row][col] = 2;
			traverseMaze(maze2D, row, col);
		}

		if(row-1 >= 0 && maze2D[row-1][col] == 0) // up
			traverseMaze(maze2D, --row, col);

		if(col+1 < maze2D.length && maze2D[row][col+1] == 0) // right
			traverseMaze(maze2D, row, ++col);

		if(row+1 < maze2D.length && maze2D[row+1][col] == 0) // down
			traverseMaze(maze2D, ++row, col);

		if(col-1 >= 0 && maze2D[row][col-1] == 0) // left
			traverseMaze(maze2D, row, --col);
	}

	public static void main(String[] args) {
		Maze maze = new Maze(10, 10);

		maze.fillMaze();
		//maze.fillMazeRand((Math.max(maze2D.length, maze2D[0].length) / 3));
		maze.showMaze();
		System.out.println();
		maze.traverseMaze(maze2D, 0, 0);
		maze.showMaze();
	}
}
