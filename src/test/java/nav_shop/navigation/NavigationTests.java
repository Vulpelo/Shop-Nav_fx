package nav_shop.navigation;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

// Own imports
import sample.navigation.Navigation;


public class NavigationTests {

    private boolean map[][];
    @Before
    public void setUp(){
        map = new boolean[][]{
                {true, false, true, false, false, false, true, true, false, true},
                {true, false, true, false, true, false, false, false, false, false},
                {true, false, true, true, true, false, true, true, false, true},
                {false, false, false, true, false, false, true, true, false, false},
                {false, true, false, false, false, true, true, true, true, false},
                {false, true, true, false, true, true, true, false, true, false},
                {false, true, true, false, true, true, true, false, false, false},
                {false, false, false, false, false, true, true, true, true, false},
                {true, false, true, true, true, false, true, true, false, false},
                {true, false, false, false, false, false, false, false, true, true},
        };
    }

    @Test
    public void mapMakingShow(){
        int[][] mapResult = new int[][]{
                {-1, 23, -1, 15, 14, 13, -1, -1, 10, -1},
                {-1, 22, -1, 16, -1, 12, 11, 10,  9, 10},
                {-1, 21, -1, -1, -1, 13, -1, -1,  8, -1},
                {21, 20, 19, -1, 15, 14, -1, -1,  7, 6},
                {22, -1, 18, 17, 16, -1, -1, -1, -1, 5},
                {23, -1, -1, 18, -1, -1, -1,  0, -1, 4},
                {24, -1, -1, 19, -1, -1, -1,  1,  2, 3},
                {23, 22, 21, 20, 21, -1, -1, -1, -1, 4},
                {-1, 23, -1, -1, -1, 29, -1, -1,  6, 5},
                {-1, 24, 25, 26, 27, 28, 29, 30, -1, -1},
        };
        int[][] points = new int[][]{
                {0, 3},
                {7, 5}
        };
        Navigation nav = new Navigation(map, points);
        nav.printMap();
        int [][]tmp = nav.getMap();
        for(int i =0; i<tmp.length; i++)
            for(int j=0; j<tmp[i].length; j++)
                if(tmp[i][j] != mapResult[i][j])
                    Assert.assertTrue(false);
        Assert.assertTrue(true);
    }

    @Test
    public void routeTest_1() {
        int[][] points = new int[][]{
                {0, 3},
                {7, 5}
        };
        int[][] realRoute = new int[][]{
                {0,3},
                {1,3},
                {2,3},
                {2,4},
                {3,4},
                {4,4},
                {4,3},
                {5,3},
                {5,2},
                {5,1},
                {6,1},
                {7,1},
                {8,1},
                {8,2},
                {8,3},
                {9,3},
                {9,4},
                {9,5},
                {9,6},
                {8,6},
                {7,6},
                {7,5}
        };


        Navigation nav = new Navigation(map, points);
        ArrayList<int[]> calRoute = nav.getRoute();

        if(calRoute.size() != realRoute.length)
            Assert.assertTrue(false);

        for(int i=0;i<calRoute.size(); i++){
//            System.out.println("R("+ realRoute.get(i)[0] +","+realRoute.get(i)[1]+"); CR("
//                                +calRoute.get(i)[0] +","+calRoute.get(i)[1]+")");
            if(realRoute[i][0] != calRoute.get(i)[0] || realRoute[i][1] != calRoute.get(i)[1])
                Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void routeTest_2(){
        int[][] points = new int[][]{
                {7, 9},
                {5, 8},
                {2, 7},
                {0, 3}
        };
        int[][] realRoute = new int[][]{
                {7, 9},
                {6, 9},
                {5, 9},
                {5, 8},
                {5, 9},
                {4, 9},
                {3, 9},
                {2, 9},
                {1, 9},
                {1, 8},
                {1, 7},
                {2, 7},
                {1, 7},
                {0, 7},
                {0, 6},
                {0, 5},
                {0, 4},
                {0, 3}
        };
        Navigation nav = new Navigation(map, points);
        ArrayList<int[]> calRoute = nav.getRoute();
        if(calRoute.size() != realRoute.length)
            Assert.assertTrue(false);

        for(int i=0;i<calRoute.size(); i++){
//            System.out.println("R("+ realRoute[i][0] +","+realRoute[i][1]+"); CR("
//                    +calRoute.get(i)[0] +","+calRoute.get(i)[1]+")");
            if(realRoute[i][0] != calRoute.get(i)[0] || realRoute[i][1] != calRoute.get(i)[1])
                Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

}