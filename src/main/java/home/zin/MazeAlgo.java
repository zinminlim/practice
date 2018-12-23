package home.zin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by zinlim on 12/19/18.
 *
 * | | | | |
 * | |X| |E|
 * | |X| | |
 * |S|X| | |
 */
public class MazeAlgo {

    private Maze maze;
    public List<List<Grid>> shortestPath = new ArrayList<>();

    public MazeAlgo(Maze maze){
        this.maze = maze;
    }


    public void getShortestPath(Queue<GridAndPath> queue) {
        while (!queue.isEmpty()) {
            GridAndPath polledGrid = queue.poll();
            polledGrid.grid.visited = true;
            if (maze.isEndingBlock(polledGrid.grid)) {
                this.shortestPath.add(polledGrid.path);
            } else {
                List<Grid> adjacents = this.maze.getAdjacent(polledGrid.grid);
                if (adjacents != null && !adjacents.isEmpty()) {
                    for (Grid g : adjacents) {
                        if (!g.visited && !g.blocked) {
                            GridAndPath gridAndPath = new GridAndPath();
                            gridAndPath.grid = g;
                            gridAndPath.path.addAll(polledGrid.path);
                            gridAndPath.path.add(g);
                            queue.add(gridAndPath);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args){
       // setup maze
        Maze maze = new Maze(4,4);
        maze.setStartingPoint(0,0);
        maze.setEndingPoint(3,2);
        maze.setBlockerGrid(1,0);
        maze.setBlockerGrid(1,1);
        maze.setBlockerGrid(1,2);

        MazeAlgo mazeAlgo = new MazeAlgo(maze);

        GridAndPath gridAndPath = new GridAndPath();
        gridAndPath.grid = maze.startingPoint;
        gridAndPath.path.add(maze.startingPoint);
        Queue<GridAndPath> queue = new LinkedList<>();
        queue.add(gridAndPath);

        mazeAlgo.getShortestPath(queue);
        for (List<Grid> path : mazeAlgo.shortestPath){
            for(Grid g: path){
                g.print();
                System.out.println();
            }
            System.out.println("==================");
        }

    }


    private static class GridAndPath{
        Grid grid;
        List<Grid> path = new ArrayList<>();
    }

    private static class Grid {
        boolean blocked = false;
        boolean visited = false;
        int x,y;
        public Grid(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void print(){
            System.out.print("("+x+","+y+")");
        }

        @Override
        public boolean equals(Object grid){
           if (grid instanceof Grid){
               Grid g = (Grid)grid;
               return this.x == g.x && this.y == g.y;
           }
            return false;
        }
    }

    private static class Maze{
        int numRows = 0;
        int numColumns = 0;
        Grid[][] maze = null;
        Grid startingPoint = null;
        Grid endingPoint = null;

        public Maze(int numRows, int numColumns){
            this.numRows = numRows;
            this.numColumns = numColumns;
            this.maze = new Grid[numRows][numColumns];
            for (int x = 0; x < numRows; x++){
                for (int y=0; y < numColumns; y++){
                    this.maze[x][y] = new Grid(x,y);
                }
            }
        }

        public boolean isEndingBlock(Grid grid){
            return grid.equals(endingPoint);
        }

        public void setBlockerGrid(int x, int y){
            this.maze[x][y].blocked = true;
        }

        public void setStartingPoint(int x, int y){
            this.startingPoint = maze[x][y];
            this.startingPoint.blocked = true;
        }

        public void setEndingPoint(int x, int y){
            this.endingPoint = maze[x][y];
            //this.endingPoint.blocked = true;
        }

        public List<Grid> getAdjacent(Grid grid){
            List<Grid> adjacents = new ArrayList<>();
            // top
            if (grid.y < numRows -1){
                adjacents.add(maze[grid.x][grid.y+1]);
            }
            // right
            if (grid.x < numColumns -1){
                adjacents.add(maze[grid.x+1][grid.y]);
            }
            // bottom
            if (grid.y > 0){
                adjacents.add(maze[grid.x][grid.y-1]);
            }
            // left
            if (grid.x > 0){
                adjacents.add(maze[grid.x-1][grid.y]);
            }
            return adjacents;
        }
    }


}
