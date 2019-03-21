package cn.mmvtc.mobilesafe.chapter02.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.mmvtc.mobilesafe.R;


public class SetUpPasswordDialog extends Dialog implements View.OnClickListener{

    //先声明使用的控件
    private TextView mTitleTv;//标题栏
    public EditText mFirstPWDET;//第一次密码输入
    public EditText mAffirmET;//再次输入密码
    private MyCallBack myCallBack;
    public SetUpPasswordDialog(Context context) {
        super(context,R.style.dialog_custom);//引入自定义对话框的样式
    }
    public void setCallBack(MyCallBack myCallBack){
        this.myCallBack=myCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.set_up_password_dialog);
        super.onCreate(savedInstanceState);
        //初始化控件
        initView();
    }
    //初始化控件
    private void initView(){
        mTitleTv= (TextView) findViewById(R.id.tv_setuppwd_title);
        mFirstPWDET= (EditText) findViewById(R.id.et_firstpwd);
        mAffirmET= (EditText) findViewById(R.id.et_affirm_password);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_cancle).setOnClickListener(this);
    }
    public void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            mTitleTv.setText(title);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_ok:
                myCallBack.ok();
                break;
            case R.id.btn_cancle:
                myCallBack.cancle();
                break;
        }
    }
    public interface MyCallBack{
        void ok();
        void cancle();
    }
}
