package com.jingewenku.abrahamcaijin.commonutillibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.jingewenku.abrahamcaijin.commonutil.AppSharePreferenceMgr;
import com.jingewenku.abrahamcaijin.commonutil.AppToastMgr;

public class MainActivity extends AppCompatActivity {
    private TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtShow = (TextView) findViewById(R.id.txtshow);
        txtShow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSharePreferenceMgr.put(MainActivity.this, "test", "显示测试用例");
                if (AppSharePreferenceMgr.contains(MainActivity.this, "test")) {
                    AppToastMgr.shortToast(MainActivity.this, AppSharePreferenceMgr.get(MainActivity.this, "test", "") + "");
                }
            }
        });
    }
}
