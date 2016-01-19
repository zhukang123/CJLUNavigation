package space.fhr.cjlunavigation;
import android.widget.Toast;


//CJLU数据类

public class CJLUMatrix {
    public int[][] d;         //保存两点距离  distance
    public int[][] p;         //保存路径    path
    private static CJLUMatrix cjluMatrix = null;        //唯一实例


    //构造器
    private CJLUMatrix(){
        d = new int[][] {                                           //距离  二维数组
                {0,100,9999,9999,9999,410,9999,9999,9999,9999},
                {100,0,300,270,210,9999,9999,9999,9999,9999},
                {9999,300,0,55,9999,9999,9999,9999,9999,9999},
                {9999,270,55,0,80,9999,9999,9999,45,9999},
                {9999,210,9999,80,0,300,9999,50,80,9999},
                {410,9999,9999,9999,300,0,150,210,9999,9999},
                {9999,9999,9999,9999,9999,150,0,220,9999,9999},
                {9999,9999,9999,9999,50,210,220,0,260,200},
                {9999,9999,9999,45,80,9999,9999,260,0,80},
                {9999,9999,9999,9999,9999,9999,9999,250,80,0},

        };

        p = new int[10][10];                    //路径中转点p 二维数组
        int v,w;
        for( v = 0; v < 10; v++){               //路径初始化 当前无中转点 置为终点k
            for(w = 0; w < 10; w++){
                p[v][w] = w;
            }
        }

        //floyd最短路径算法
        //讨论所有两点之间的距离 是否需要k点作为中转点

        for(int k = 0; k < 10; k++){                         //当前中转点为k
            for(v = 0 ; v < 10; v++){                        //起点v
                for(w = 0; w < 10; w++){                     //终点w
                    if(d[v][k] + d[k][w] < d[v][w]){         //如果经过中转点比不经过短
                        d[v][w] = d[v][k] + d[k][w];         //更新距离
                        p[v][w] = p[v][k];                   //更新路径 保存k 表示经过k点
                    }
                }
            }
        }
    }


    //单例模式 获取实例
    public static CJLUMatrix getInstance(){
        if(cjluMatrix == null){
            cjluMatrix = new CJLUMatrix();
        }
        return cjluMatrix;
    }



}
