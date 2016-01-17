package space.fhr.cjlunavigation;

import android.content.Intent;
import android.os.Bundle;
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
    private TextView textView;
    private View toolBarBg;
    private Intent getIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        //浮动按钮
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
        getIntent = getIntent();

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();                                     //view初始化

    }


    private void initView() {
       String spot = getIntent.getStringExtra("spot");
        switch (spot){
            case "逸夫图书馆":
                toolBarBg.setBackgroundResource(R.drawable.library);
                textView.setText(R.string.lib);
                break;
            case "翔宇楼":
                toolBarBg.setBackgroundResource(R.drawable.xyl);
                break;
            case "环宇楼":
                toolBarBg.setBackgroundResource(R.drawable.hyl);
                textView.setText("环宇楼，啪啪啪");
                 break;
            case "天健体育场":
                toolBarBg.setBackgroundResource(R.drawable.tyg);
                break;
            case "中国计量学院正门":
                toolBarBg.setBackgroundResource(R.drawable.cjluzdm);
                break;
            case "日月湖":
                toolBarBg.setBackgroundResource(R.drawable.ryh);
                textView.setText(R.string.ryh);
                break;
            case "赛博楼":
                toolBarBg.setBackgroundResource(R.drawable.sbl);
                break;
            case "求是楼":
                toolBarBg.setBackgroundResource(R.drawable.qsl);
                break;
            case "星火工训中心":
                toolBarBg.setBackgroundResource(R.drawable.xh);
                break;
            case "启明广场":
                toolBarBg.setBackgroundResource(R.drawable.qm);
                break;

        }

    }


}
