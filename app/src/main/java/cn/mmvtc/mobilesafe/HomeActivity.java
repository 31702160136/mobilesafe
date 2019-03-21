package cn.mmvtc.mobilesafe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import cn.mmvtc.mobilesafe.chapter01.adapter.HomeAdapter;
import cn.mmvtc.mobilesafe.chapter02.LostFindActivity;
import cn.mmvtc.mobilesafe.chapter02.dialog.InterPasswordDialog;
import cn.mmvtc.mobilesafe.chapter02.dialog.SetUpPasswordDialog;

public class HomeActivity extends Activity {
    private long mExitTime;
    private GridView gv_home;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        mSharedPreferences=getSharedPreferences("config",MODE_PRIVATE);
        gv_home= (GridView) findViewById(R.id.gv_home);
        gv_home.setAdapter(new HomeAdapter(HomeActivity.this));
        savePswd(null);
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0://手机防盗
                        if (isSetUpPassword()){
                            //弹出输入密码对话框
                            showInterPswdDialog();
                        }else {
                            //弹出设置密码对话框
                            showSetUpPswDialog();
                        }
                        break;
                    case 1://通讯卫士
//                        startActivity(SecurityPhone);
                }
            }
        });
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
    //保存密码
    private void savePswd(String affirmPwsd){
        SharedPreferences.Editor edit=mSharedPreferences.edit();
        //保存密码,但未加密（需要补充）
        edit.putString("PhoneAntiTheftPWD",affirmPwsd);
        edit.commit();
    }
    //读取密码
    private String getPassword(){
        String password=mSharedPreferences.getString("PhoneAntiTheftPWD",null);
        return password;
    }
    //判断用户 是否 手机防盗密码
    private boolean isSetUpPassword(){
        String password=mSharedPreferences.getString("PhoneAntiTheftPWD",null);
        if (TextUtils.isEmpty(password)){
            return false;
        }
        return true;
    }
    //弹出设置密码对话框
    private void showSetUpPswDialog(){
        final SetUpPasswordDialog setUpPasswordDialog=new SetUpPasswordDialog(HomeActivity.this);
        setUpPasswordDialog.setCallBack(new SetUpPasswordDialog.MyCallBack() {
            @Override
            public void ok() {
                String firstPwsd=setUpPasswordDialog.mFirstPWDET.getText().toString().trim();
                String affirmPwsd=setUpPasswordDialog.mAffirmET.getText().toString().trim();
                if (!TextUtils.isEmpty(firstPwsd)&&!TextUtils.isEmpty(affirmPwsd)){
                    if (firstPwsd.equals(affirmPwsd)){
                        //两次密码一致，存储密码
                        savePswd(affirmPwsd);
                        setUpPasswordDialog.dismiss();
                        //显示输入密码对话框(补充)
                    }else {
                        Toast.makeText(HomeActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void cancle() {
                setUpPasswordDialog.dismiss();
            }
        });
        setUpPasswordDialog.setCancelable(true);
        setUpPasswordDialog.show();
    }

    //弹出输入密码对话框
    private void showInterPswdDialog(){
        //获取密码
        final String password=getPassword();
        final InterPasswordDialog mInPaswdDialog=new InterPasswordDialog(HomeActivity.this);
        //密码做比对
        mInPaswdDialog.setCallBack(new InterPasswordDialog.MyCallBack() {
            @Override
            public void confirm() {
                if (TextUtils.isEmpty(mInPaswdDialog.getPassword())){
                    Toast.makeText(HomeActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(password.equals(mInPaswdDialog.getPassword())){
                    //进入防盗主界面
                    mInPaswdDialog.dismiss();
                    startActivity(LostFindActivity.class);
                }else {
                    //对话框小时，弹出土司
                    mInPaswdDialog.dismiss();
                    Toast.makeText(HomeActivity.this,"密码有误,请重新输入",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void cancle() {
                mInPaswdDialog.dismiss();
            }
        });
        mInPaswdDialog.setCancelable(true);
        mInPaswdDialog.show();
    }

}
