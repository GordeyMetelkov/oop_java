import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Labirint {

    public static void main(String[] args) {
        MapGenerator mg = new MapGenerator();
        Point2D pointStart = new Point2D(7, 2);
        Point2D firstExit = new Point2D(1, 3);
        Point2D secondExit = new Point2D(1, 6);
        Point2D thirdExit = new Point2D(5, 7);        
        mg.setStartPos(pointStart); 
        mg.setExit(firstExit);
        mg.setExit(secondExit);
        mg.setExit(thirdExit);
        MapPic mp = new MapPic();
        System.out.println(mp.mapPic(mg.getField()));
        WaveAlgoritm wa = new WaveAlgoritm(mg.getField());
        wa.checkPos(pointStart);
        System.out.println(mp.mapPic(mg.getField()));
        LinkedList<Point2D> exitRoad = new LinkedList<>();
        exitRoad = wa.road(firstExit);
        for (Object o : exitRoad) {
            System.out.println(o);
        }
    }
}
class MapGenerator {

    int [][] field;

    public MapGenerator() {
        
        int [][] field = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 00, 00, 00, 00, 00, 00, 00, -1},
            {-1, 00, 00, 00, -1, 00, 00, 00, -1},
            {-1, 00, 00, 00, -1, 00, 00, 00, -1},
            {-1, 00, -1, -1, -1, -1, 00, 00, -1},
            {-1, 00, 00, 00, 00, 00, -1, 00, -1},
            {-1, 00, 00, -1, 00, 00, -1, 00, -1},
            {-1, 00, 00, -1, 00, 00, 00, 00, -1},
            {-1, 00, 00, -1, 00, 00, 00, 00, -1},
            {-1, 00, 00, 00, 00, 00, 00, 00, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1}       
        };
        this.field = field;
    }
    public int [][] getField(){
        return field;
    }
    public void setStartPos(Point2D pos){
        field[pos.getX()][pos.getY()] = 1;
    }
    public void setExit(Point2D pos) {
        field[pos.getX()][pos.getY()] = -3;
    }
}
class MapPic {

    public MapPic() {
    }
    public String mapPic(int [][] field) {

        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                sb.append(String.format("%5d", field[row][col]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String mapColor(int [][] field) {

        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                switch(field[row][col]) {
                    case 0:
                        sb.append("░");
                        break;
                    case -1:
                        sb.append("█");
                        break;
                    case -2:
                        sb.append("S");
                        break;
                    case -3:
                        sb.append("E");
                        break;
                    default:
                        break;
                }
            }
            sb.append("\n");  
        }
        return sb.toString();
    }
}

class Point2D {

    private int x;
    private int y;

    public Point2D(int x, int y) {

        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    @Override
    public String toString() {
        return String.format("X: %d, Y: %d", x, y);
    }
}
class WaveAlgoritm {

    int [][] field;
    
    public WaveAlgoritm(int [][] field) {
        this.field = field;
    }
    
    public int [][] checkPos(Point2D startPoint) {
        LinkedList<Point2D> fifo = new LinkedList<>();
        fifo.add(startPoint);
        field[startPoint.getX()][startPoint.getY()] = 1;
        while (!fifo.isEmpty()) {
            startPoint = fifo.pollFirst();
            if (field[startPoint.getX()][startPoint.getY() + 1] == 0) {
                field[startPoint.getX()][startPoint.getY() + 1] = field[startPoint.getX()][startPoint.getY()] + 1;
                fifo.add(new Point2D(startPoint.getX(), startPoint.getY() + 1));
            }
            if (field[startPoint.getX() + 1][startPoint.getY()] == 0) {
                field[startPoint.getX() + 1][startPoint.getY()] = field[startPoint.getX()][startPoint.getY()] + 1;
                fifo.add(new Point2D(startPoint.getX() + 1, startPoint.getY()));
            }
            if (field[startPoint.getX()][startPoint.getY() - 1] == 0) {
                field[startPoint.getX()][startPoint.getY() - 1] = field[startPoint.getX()][startPoint.getY()] + 1;
                fifo.add(new Point2D(startPoint.getX(), startPoint.getY() - 1));
            }
            if (field[startPoint.getX() - 1][startPoint.getY()] == 0) {
                field[startPoint.getX() - 1][startPoint.getY()] = field[startPoint.getX()][startPoint.getY()] + 1;
                fifo.add(new Point2D(startPoint.getX() - 1, startPoint.getY()));
            }
        }
        return field;
    }
    public LinkedList<Point2D> road(Point2D exit) {
        LinkedList<Point2D> exitRoad = new LinkedList<>();
        Point2D curPoint = new Point2D(exit.getX() + 1, exit.getY());
        if (field[curPoint.getX()][curPoint.getY()] < field[exit.getX()][exit.getY() + 1]) {
            curPoint = new Point2D(exit.getX(), exit.getY() + 1);
        }
        if (field[curPoint.getX()][curPoint.getY()] < field[exit.getX() - 1][exit.getY()]) {
            curPoint = new Point2D(exit.getX() - 1, exit.getY());
        }
        if (field[curPoint.getX()][curPoint.getY()] < field[exit.getX()][exit.getY() - 1]) {
            curPoint = new Point2D(exit.getX(), exit.getY() - 1);
        }
        exitRoad.add(curPoint);
        while (field[curPoint.getX()][curPoint.getY()] != 1) {
            if (field[curPoint.getX() + 1][curPoint.getY()]  == field[curPoint.getX()][curPoint.getY()] - 1) {
                curPoint = new Point2D(curPoint.getX() + 1, curPoint.getY());
                exitRoad.add(curPoint);
            }
            else if (field[curPoint.getX()][curPoint.getY() + 1]  == field[curPoint.getX()][curPoint.getY()] - 1) {
                curPoint = new Point2D(curPoint.getX(), curPoint.getY() + 1);
                exitRoad.add(curPoint);
            }
            else if (field[curPoint.getX() - 1][curPoint.getY()]  == field[curPoint.getX()][curPoint.getY()] - 1) {
                curPoint = new Point2D(curPoint.getX() - 1, curPoint.getY());
                exitRoad.add(curPoint);
            }
            else if (field[curPoint.getX()][curPoint.getY() - 1]  == field[curPoint.getX()][curPoint.getY()] - 1) {
                curPoint = new Point2D(curPoint.getX(), curPoint.getY() - 1);
                exitRoad.add(curPoint);
            }
        }
        return exitRoad;
    }
}
// class ForList {

//     Point2D point;
//     int count;

//     public ForList(Point2D point, int count) {
//         this.point = point;
//         this.count = count;
//     }
//     public int getCount() {
//         return count;
//     }

//     @Override
//     public String toString() {
//         return Integer.toString(count);
//     }
// }