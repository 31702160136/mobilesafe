package cn.mmvtc.mobilesafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.GridView;
import android.widget.Toast;

import cn.mmvtc.mobilesafe.chapter01.adapter.HomeAdapter;

public class HomeActivity extends Activity {
    private long mExitTime;
    private GridView gv_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        gv_home= (GridView) findViewById(R.id.gv_home);
        gv_home.setAdapter(new HomeAdapter(HomeActivity.this));
//        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch (i){
//                    case 0://手机防盗
//                        if (isSetUpSassword()){
//                            //弹出输入密码对话框
//                            showInterPswdDialog();
//                        }else {
//                            //弹出设置密码对话框
//                            showSetUpPswdDialog();
//                        }
//                        break;
//                    case 1://通讯卫士
//                        startActivity(SecurityPhone);
//                }
//            }
//        });
    }




    public void startActivity(Class<?> cls){
        Intent intent=new Intent(HomeActivity.this,cls);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if ((System.currentTimeMillis()-mExitTime)<2000){
                System.exit(0);
            }else {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime=System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
