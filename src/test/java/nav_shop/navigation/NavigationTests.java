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
    public void routeTest() {
        int[][] points = new int[][]{
                {0, 3},
                {7, 5}
        };
        ArrayList<int[]> realRoute = new ArrayList<int[]>();

        int tmp[] = {0,3}; realRoute.add(tmp.clone());
        tmp[0]=1; realRoute.add(tmp.clone());
        tmp[0]=2; realRoute.add(tmp.clone());
        tmp[1]=4; realRoute.add(tmp.clone());
        tmp[0]=3; realRoute.add(tmp.clone());
        tmp[0]=4; realRoute.add(tmp.clone());
        tmp[1]=3; realRoute.add(tmp.clone());
        tmp[0]=5; realRoute.add(tmp.clone());
        tmp[1]=2; realRoute.add(tmp.clone());
        tmp[1]=1; realRoute.add(tmp.clone());
        tmp[0]=6; realRoute.add(tmp.clone());
        tmp[0]=7; realRoute.add(tmp.clone());
        tmp[0]=8; realRoute.add(tmp.clone());
        tmp[1]=2; realRoute.add(tmp.clone());
        tmp[1]=3; realRoute.add(tmp.clone());
        tmp[0]=9; realRoute.add(tmp.clone());
        tmp[1]=4; realRoute.add(tmp.clone());
        tmp[1]=5; realRoute.add(tmp.clone());
        tmp[1]=6; realRoute.add(tmp.clone());
        tmp[0]=8; realRoute.add(tmp.clone());
        tmp[0]=7; realRoute.add(tmp.clone());
        tmp[1]=5; realRoute.add(tmp.clone());//21

        Navigation nav = new Navigation(map, points);
        ArrayList<int[]> calRoute = nav.getRoute();
        System.out.println("ty");

        if(calRoute.size() != realRoute.size())
            Assert.assertTrue(false);

        for(int i=0;i<calRoute.size(); i++){
            System.out.println("R("+ realRoute.get(calRoute.size()-1-i)[0] +","+realRoute.get(calRoute.size()-1-i)[1]+"); CR("
                                +calRoute.get(i)[0] +","+calRoute.get(i)[1]+")");
            if(realRoute.get(calRoute.size()-1-i)[0] != calRoute.get(i)[0] || realRoute.get(calRoute.size()-1-i)[1] != calRoute.get(i)[1])
                Assert.assertTrue(false);
        }
        Assert.assertTrue(true);

    }

}