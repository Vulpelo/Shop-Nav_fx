package sample.navigation;
import static java.lang.Math.*;
import java.util.ArrayList;

public class Navigation {
    // Variables
    private boolean[][]wallMap;
    private int[][] map;
    private int[][] points;

    // Functions

    public Navigation(boolean[][]wallMap, int[][]points) {
        this.points = points;
        this.wallMap = wallMap;

        resetMap();
        // nastepny punkt (czyli w A do B, to będzie punkt końcowy B)
        setSquaresDistance(this.points[1][0], this.points[1][1]);
    }

    private void resetMap() {
        map = new int[wallMap.length][];
        for(int i=0; i<this.map.length; i++) {
            map[i] = new int[wallMap[i].length];
            for (int j = 0; j < this.map[i].length; j++)
                this.map[i][j] = -1;
        }
    }

    private void setSquaresDistance(int eX, int eY){
        int distance=0;
        boolean anyPointSetted;
        this.map[eY][eX]=0;

        int width = 1;
        int height = 1;

        do {
            width += 2;
            height += 2;
            anyPointSetted = false;
            for (int i = eY - height; i < eY+height; i++) {
                if(!(i>=0 && i < this.map.length))
                    continue;
                for (int j = eX-width; j < eX+width; j++) {
                    if(!(j>=0 && j < this.map[0].length))
                        continue;
                    if (this.map[i][j] == distance) {
                        if (i+1<this.map.length && !this.wallMap[i + 1][j] && this.map[i + 1][j] == -1) {
                            this.map[i + 1][j] = distance + 1;
                            anyPointSetted = true;
                        }
                        if (i-1 >=0 && !this.wallMap[i - 1][j] && this.map[i - 1][j] == -1) {
                            this.map[i - 1][j] = distance + 1;
                            anyPointSetted = true;
                        }
                        if (j+1<this.map[0].length && !this.wallMap[i][j + 1] && this.map[i][j + 1] == -1) {
                            this.map[i][j + 1] = distance + 1;
                            anyPointSetted = true;
                        }
                        if (j-1 >=0 && !this.wallMap[i][j - 1] && this.map[i][j - 1] == -1) {
                            this.map[i][j - 1] = distance + 1;
                            anyPointSetted = true;
                        }
                    }

                }
            }
            distance++;
        }while(anyPointSetted);
    }

    private int[][] getRouteAB(int startX, int startY){
        // array has length of distance beetween A and B point
        int actualX = startX;
        int actualY = startY;
        int distLeft=this.map[actualY][actualX];
        int[][] routePoints=new int[distLeft+1][2];

        routePoints[0][0] = actualX;
        routePoints[0][1] = actualY;

        for(int i=0; i<this.map[ startY ][ startX ]; i++){
            distLeft--;
            if(actualY+1 < this.map.length && this.map[actualY+1][actualX] == distLeft){
                actualY++;
            }
            else if(actualY-1 >= 0 && this.map[actualY-1][actualX] == distLeft){
                actualY--;
            }
            else if(actualX+1 < this.map[actualY].length && this.map[actualY][actualX+1] == distLeft){
                actualX++;
            }
            else if(actualX-1 >= 0 && this.map[actualY][actualX-1] == distLeft){
                actualX--;
            }
            routePoints[i+1][0] = actualX;
            routePoints[i+1][1] = actualY;
        }
        return routePoints;
    }

    // Wyznacza trasę między wprowadzonymi punktami.
    // Pierwszy punkt to punkt startowy, a ostatni punkt na liście jest końcowy/ostatni.
    public ArrayList<int[]> getRoute() {
        int len = this.map.length;
        int nrOfPoints = this.points.length;
        ArrayList<int[]> allRoute = new ArrayList<int[]>();
        boolean used[] = new boolean[nrOfPoints];
        int point=0;
        used[0] = true;
        used[nrOfPoints-1] = true;
        for(int i=1; i<nrOfPoints-1; i++)
            used[i]=false;

        // Main loop, for each element
        for(int i=1; i<nrOfPoints-1; i++){
            resetMap();
            // tworze mapę odległości do każdego miejsca na mapie z punktu startowego
            setSquaresDistance(this.points[point][0], this.points[point][1]);
            used[point] = true;

            int j;
            int nextPoint=0; //Następny, najbliższy punkt do którego można dojść
            int nextPointDistance= 1000000;

            // Pierwszy wolny, nieużyty punkt
            for(j=1; j<nrOfPoints; j++)
                if(!used[j]) {
                    nextPoint = j;
                    nextPointDistance = map[this.points[j][1]][this.points[j][0]];
                    break;
                }
                // przeszukiwanie reszty punktów który może być bliżej
            for(j=j+1; j<nrOfPoints; j++)
                if(!used[j])
                    if(map[this.points[j][1]][this.points[j][0]] < nextPointDistance){
                        nextPoint = j;
                        nextPointDistance = map[this.points[j][1]][this.points[j][0]];
                    }
            resetMap();
            setSquaresDistance(this.points[nextPoint][0], this.points[nextPoint][1]);
            // Pobieranie trasy z point to nextPoint
            int[][]tmp = getRouteAB(this.points[point][0], this.points[point][1]);
            for(int k=0; k<tmp.length-1; k++)
                allRoute.add(tmp[k]);

            // przygotowanie do następnej pętli
            used[nextPoint] = true;
            point=nextPoint;
        }
        resetMap();
        setSquaresDistance(this.points[nrOfPoints-1][0], this.points[nrOfPoints-1][1]);
        int[][]tmp = getRouteAB(this.points[point][0], this.points[point][1]);
        for(int k=0; k < tmp.length; k++)
            allRoute.add(tmp[k]);
        return allRoute;
    }

    public int[][] getMap(){
        return this.map;
    }

    public void printMap(){
        for (int[] aMap : this.map) {
            for (int anAMap : aMap) {
                System.out.print(anAMap + " ");
            }
            System.out.println("");
        }
    }
}
