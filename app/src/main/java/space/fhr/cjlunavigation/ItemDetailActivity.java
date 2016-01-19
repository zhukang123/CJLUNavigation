package space.fhr.cjlunavigation;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;
import org.w3c.dom.Text;
import javax.xml.transform.Source;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a .
 */
public class ItemDetailActivity extends AppCompatActivity {
    private TextView textView;          //地点描述TextView
    private View toolBarBg;             //背景图view
    private Intent getIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);


        //浮动按钮  返回MainActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        toolBarBg = findViewById(R.id.toolbar_layout);
        textView = (TextView) findViewById(R.id.text_view);
        getIntent = getIntent();        //拿到Intent


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();                                     //view初始化

    }



    private void initView() {
       String spot = getIntent.getStringExtra("spot");          //地点
        switch (spot){
            case "逸夫图书馆":
                toolBarBg.setBackgroundResource(R.drawable.library);        //添加相应的背景图片 文字说明
                textView.setText(R.string.lib);
                break;
            case "翔宇楼":
                toolBarBg.setBackgroundResource(R.drawable.xyl);
                textView.setText(R.string.xyl);
                break;
            case "环宇楼":
                toolBarBg.setBackgroundResource(R.drawable.hyl);
                textView.setText(R.string.hyl);
                 break;
            case "天健体育场":
                toolBarBg.setBackgroundResource(R.drawable.tyg);
                textView.setText(R.string.tyg);
                break;
            case "中国计量学院正门":
                toolBarBg.setBackgroundResource(R.drawable.cjluzdm);
                textView.setText(R.string.zdm);
                break;
            case "日月湖":
                toolBarBg.setBackgroundResource(R.drawable.ryh);
                textView.setText(R.string.ryh);
                break;
            case "赛博楼":
                toolBarBg.setBackgroundResource(R.drawable.sbl);
                textView.setText(R.string.sbl);
                break;
            case "求是楼":
                toolBarBg.setBackgroundResource(R.drawable.qsl);
                textView.setText(R.string.qsl);
                break;
            case "星火工训中心":
                toolBarBg.setBackgroundResource(R.drawable.xh);
                textView.setText(R.string.xh);
                break;
            case "启明广场":
                toolBarBg.setBackgroundResource(R.drawable.qm);
                textView.setText(R.string.qm);
                break;

        }

    }


}
