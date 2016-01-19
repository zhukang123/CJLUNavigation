package space.fhr.cjlunavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


//引导页Activity
public class LaunchActivity extends AppCompatActivity {
    private Button button;              //点击进入
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);       //布局

        button = (Button) findViewById(R.id.button_click_in);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
                startActivity(intent);      //跳至MainActivity
                finish();                   //结束启动页Activity的生命周期
            }
        });
    }


}
