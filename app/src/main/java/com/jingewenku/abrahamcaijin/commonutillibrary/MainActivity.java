package com.jingewenku.abrahamcaijin.commonutillibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.jingewenku.abrahamcaijin.commonutil.AppLogMessageMgr;
import com.jingewenku.abrahamcaijin.commonutil.AppSharePreferenceMgr;
import com.jingewenku.abrahamcaijin.commonutil.AppToastMgr;
import com.jingewenku.abrahamcaijin.commonutil.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName();
//    private String TAG = this.getClass().getSimpleName();
    private TextView showTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTxt = (TextView) findViewById(R.id.show_txt);
        showTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSharePreferenceMgr.put(MainActivity.this, "test", "显示测试用例");
                if (AppSharePreferenceMgr.contains(MainActivity.this, "test")) {
                    AppToastMgr.ToastLongCenter(MainActivity.this, AppSharePreferenceMgr.get(MainActivity.this, "test", "") + "");

                }
            }
        });

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String str = JsonUtils.list2json(list);
        AppLogMessageMgr.e("=====", str);

    }
}
