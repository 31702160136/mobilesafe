package cn.mmvtc.mobilesafe.chapter02;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import cn.mmvtc.mobilesafe.R;


public class SetUpPasswordDialog extends Dialog implements View.OnClickListener{

    public SetUpPasswordDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_up_password_dialog);
    }

    @Override
    public void onClick(View view) {

    }
}
