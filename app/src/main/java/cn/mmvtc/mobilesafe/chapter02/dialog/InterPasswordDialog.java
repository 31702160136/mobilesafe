package cn.mmvtc.mobilesafe.chapter02.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.mmvtc.mobilesafe.R;

public class InterPasswordDialog extends Dialog implements View.OnClickListener {
    //对话框标题
    private TextView mTitleTv;
    //输入密码文本框
    private EditText mInterET;
    //确认按钮
    private Button mOKBtn;
    //取消
    private Button mCancleBtn;
    private MyCallBack myCallBack;
    private Context context;
    public InterPasswordDialog(@NonNull Context context) {
        super(context, R.style.dialog_custom);
        this.context=context;
    }
    public void setCallBack(MyCallBack myCallBack){
        this.myCallBack=myCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter_password_dialog);
        initView();
    }
    private void initView(){
        mTitleTv= (TextView) findViewById(R.id.tv_interpwd_title);
        mInterET= (EditText) findViewById(R.id.et_inter_password);
        mOKBtn= (Button) findViewById(R.id.btn_comfirm);
        mCancleBtn= (Button) findViewById(R.id.btn_dismiss);
        mOKBtn.setOnClickListener(this);
        mCancleBtn.setOnClickListener(this);
    }

    public void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            mTitleTv.setText(title);
        }
    }
    public String getPassword(){
        return mInterET.getText().toString();
    }
    @Override
    public void onClick(View v) {
        Log.d("sss","ssss");
        switch (v.getId()){
            case R.id.btn_comfirm:
                myCallBack.confirm();
                break;
            case R.id.btn_dismiss:
                myCallBack.cancle();
                break;
        }
    }
    public interface MyCallBack{
        void confirm();
        void cancle();
    }
}
