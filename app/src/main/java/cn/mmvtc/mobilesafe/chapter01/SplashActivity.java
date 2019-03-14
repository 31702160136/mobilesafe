package cn.mmvtc.mobilesafe.chapter01;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import cn.mmvtc.mobilesafe.R;
import cn.mmvtc.mobilesafe.chapter01.utils.MyUtils;
import cn.mmvtc.mobilesafe.chapter01.utils.VersionUpdateUtils;

public class SplashActivity extends Activity {

    private TextView mVersionTv;
    private String mVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mVersion= MyUtils.getVersion(getApplicationContext());
        initView();
        final VersionUpdateUtils versionUpdateUtils=new VersionUpdateUtils(mVersion,SplashActivity.this);
        new Thread(){
            @Override
            public void run() {
                versionUpdateUtils.getCloudVersion();
            }
        }.start();
    }

    private void initView(){
        mVersionTv= (TextView) findViewById(R.id.splash_tv);
        mVersionTv.setText("版本号 "+mVersion);
    }
}
