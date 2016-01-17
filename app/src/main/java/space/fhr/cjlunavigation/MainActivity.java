package space.fhr.cjlunavigation;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements AMap.OnMapClickListener, AMap.OnMarkerClickListener,AMap.OnInfoWindowClickListener{
    private MapView mapView;                //容器
    private AMap aMap;                      //地图
    private Button setAsStart;              //设为起点
    private Button setAsEnd;                //设为终点
    private Button showPath;                //路径规划
    private TextView currentPosition;       //当前位置
    private CJLUMatrix myMatrix;            //数据矩阵
    private  Marker currentMarker;       //当前marker
    private Marker startMarker;           //起点marker
    private Marker endMarker;           //终点marker
    private  int startNum ;              //起点编号
    private  int endNum ;                 //终点编号
    private Marker TSG,XYL,HYL,TYG,ZDM,RYH,SBL,QSL,XH,QM;       //地点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        setAsStart = (Button)findViewById(R.id.button_start);           //起点按钮
        setAsEnd = (Button)findViewById(R.id.button_end);               //终点按钮
        currentPosition = (TextView)findViewById(R.id.current_position);    //当前位置信息
        showPath = (Button)findViewById(R.id.button_show_path);         //规划路径
        myMatrix = CJLUMatrix.getInstance();                            //数据

        initAMap();                                         //初始化地图
        initMarker();                                       //初始化Marker
        initButton();                                       //初始化button


    }



    //按钮监听
    private void initButton() {

        //设为起点
        setAsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMarker = currentMarker;            //当前marker设为起点marker
                currentMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.flag_start));
                startNum = returnSpotNum(currentMarker.getTitle());
                Toast.makeText(MainActivity.this, currentMarker.getTitle()+" 设为起点成功", Toast.LENGTH_LONG).show();

            }
        });


        //设为终点
        setAsEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endMarker = currentMarker;
                currentMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.flag_end));
                endNum = returnSpotNum(currentMarker.getTitle());
                Toast.makeText(MainActivity.this, currentMarker.getTitle()+" 设为终点成功", Toast.LENGTH_LONG).show();


            }
        });


        //显示路径
        showPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "路径规划成功！", Toast.LENGTH_SHORT).show();
                int s = startNum;
                int e = endNum;
                int k = myMatrix.p[startNum][endNum];
                while(k != endNum){
                    aMap.addPolyline(new PolylineOptions().add(numToMarker(s).getPosition(),
                            numToMarker(k).getPosition()));     //s k之间划线
                            s = k;                              //s后移
                            k = myMatrix.p[k][endNum];          //更新k
                }

                aMap.addPolyline(new PolylineOptions().add(numToMarker(s).getPosition(),
                        numToMarker(k).getPosition()));     //s k之间划线


            }
        });
    }



    public Marker numToMarker(int num){
        switch (num){
            case 0:
                return SBL;
            case 1:
                return TSG;
            case 2:
                return ZDM;
            case 3:
                return XYL;
            case 4:
                return RYH;
            case 5:
                return QSL;
            case 6:
                return XH;
            case 7:
                return QM;
            case 8:
                return HYL;
            case 9:
                return TYG;
            default:
                return null;
        }

    }


    public int returnSpotNum(String spot) {
        switch (spot){
            case "赛博楼":
                return 0;
            case "逸夫图书馆":
                return 1;
            case "中国计量学院正门":
                return 2;
            case "翔宇楼":
                return 3;
            case "日月湖":
                return 4;
            case "求是楼":
                return 5;
            case "星火工训中心":
                return 6;
            case "启明广场":
                return 7;
            case "环宇楼":
                return 8;
            case "天健体育场":
                return 9;

            default:
                Toast.makeText(MainActivity.this, "未选择", Toast.LENGTH_SHORT).show();
                return -1;
        }
    }


    private void initMarker() {
        LatLng CJLU = new LatLng(30.320793,120.362663);
        LatLng LIBRARY = new LatLng(30.321296,120.360214);
        LatLng XIANGYU = new LatLng(30.319395,120.360534);
        LatLng HUANYU = new LatLng(30.31921,120.362297);
        LatLng TIYUCHANG = new LatLng(30.319679,120.365692);
        LatLng DAMEN = new LatLng(30.318683,120.360443);
        LatLng RIYUEHU = new LatLng(30.320521,120.36222);
        LatLng SAIBO = new LatLng(30.321846,120.360206);
        LatLng QIUSHI = new LatLng(30.321828,120.363151);
        LatLng XINGHUO = new LatLng(30.322565,120.364913);
        LatLng  QIMING = new LatLng(30.320701,120.364166);

         TSG = aMap.addMarker(new MarkerOptions()
                .position(LIBRARY)
                .title("逸夫图书馆"));
         XYL = aMap.addMarker(new MarkerOptions()
                .position(XIANGYU)
                  .title("翔宇楼"));
         HYL = aMap.addMarker(new MarkerOptions()
                .position(HUANYU)
                .title("环宇楼"));
         TYG = aMap.addMarker(new MarkerOptions()
                .position(TIYUCHANG)
                .title("天健体育场"));
         ZDM = aMap.addMarker(new MarkerOptions()
                .position(DAMEN)
                .title("中国计量学院正门"));
         RYH = aMap.addMarker(new MarkerOptions()
                .position(RIYUEHU)
                .title("日月湖"));
         SBL = aMap.addMarker(new MarkerOptions()
                .position(SAIBO)
                .title("赛博楼"));
         QSL = aMap.addMarker(new MarkerOptions()
                .position(QIUSHI)
                .title("求是楼"));
         XH = aMap.addMarker(new MarkerOptions()
                .position(XINGHUO)
                .title("星火工训中心"));
         QM = aMap.addMarker(new MarkerOptions()
                .position(QIMING)
                .title("启明广场"));


    }


    private void initAMap() {
        if(aMap == null){
            aMap = mapView.getMap();                                //初始化
        }

        //移动视图到CJLU
        LatLng CJLU = new LatLng(30.320793,120.362663);
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(CJLU,16,0,0)));

        aMap.setOnMapClickListener(this);                           //map点击监听
        aMap.setOnInfoWindowClickListener(this);                    //windowInfo点击监听
        aMap.setOnMarkerClickListener(this);

    }



    //marker信息窗口点击
    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(MainActivity.this,ItemDetailActivity.class);
        intent.putExtra("spot",marker.getTitle());
        startActivity(intent);                                      //跳转到detail界面
    }



    //marker标志点击
    @Override
    public boolean onMarkerClick(Marker marker) {
        currentMarker = marker;
        marker.showInfoWindow();

        double longitude = marker.getPosition().longitude;
        double latitude = marker.getPosition().latitude;
        currentPosition.setText("当前位置:\n"+marker.getTitle()+ "\n坐标(经/纬):\n" +longitude+"/"+ latitude+"\n");

        return true;
    }

    @Override
    public void onMapClick(LatLng latLng) {


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
