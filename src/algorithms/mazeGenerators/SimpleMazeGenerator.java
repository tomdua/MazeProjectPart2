package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {

    private Maze MyMaze;
   /**
    * generate a maze and randomly fill with 0's and 1's
    * creates a maze and makes sure it has a solution
    *
    */
   public Maze generate(int rows, int columns) {
       MyMaze = new Maze(rows, columns);
       int MazeValue;
       for (int i = 0; i < rows; i++) {
           for (int j = 0; j < columns; j++) {
               if (i == 0 || i == rows - 1 || j == 0 || j == columns - 1)
                   MyMaze.changeCellValue(i, j, 1);
               else {
                   MazeValue = (Math.random() < 0.5) ? 0 : 1;
                   MyMaze.changeCellValue(i, j, MazeValue); //fill maze randomly with 0's and 1's
               }
           }
       }
       //set a random start point
       int MazeStartPoint = (int) (Math.random() * rows);
       Position start = new Position(MazeStartPoint, 0);
       MyMaze.setStartPosition(start);
       Position end = new Position(rows-1, columns-1);
       MyMaze.setGoalPosition(end);
       int i = MazeStartPoint, j = 0;
       int Direction;
       //make sure maze has a solution
       while (!(i == rows - 1 && j == columns - 1)) {
           if (i == rows - 1) {
               MyMaze.changeCellValue(i, j + 1, 0);
               j++;
           } else if (j == columns - 1) {
               MyMaze.changeCellValue(i + 1, j, 0);
               i++;
           } else {
               Direction = (Math.random() < 0.5) ? 0 : 1;
               if (Direction == 1) {
                   MyMaze.changeCellValue(i + 1, j, 0);
                   i++;
               } else {
                   MyMaze.changeCellValue(i, j + 1, 0);
                   j++;
               }
           }
       }
       return MyMaze;
   }
}