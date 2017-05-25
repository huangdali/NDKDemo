package com.jwkj.ffmpeg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvReuslt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvReuslt = (TextView) findViewById(R.id.tv_result);
        tvReuslt.append(NDKUtils.getSum(4, 2) + "");
    }
}
