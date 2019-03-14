package cn.mmvtc.mobilesafe.chapter01.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.mmvtc.mobilesafe.R;

/**
 * Created by Administrator on 2019/3/14.
 */

public class HomeAdapter extends BaseAdapter {
    int[] imageId={R.drawable.safe,R.drawable.callmsgsafe,
            R.drawable.app,R.drawable.trojan,R.drawable.sysoptimize,
            R.drawable.taskmanager,R.drawable.netmanager,
            R.drawable.icon,R.drawable.settings};
    String[] names={"手机防盗","通讯卫士","软件管家","手机杀毒","缓存清理",
                "进程管理","流量统计","高级工具","设置中心"};
    private Context context;
    public HomeAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View conView, ViewGroup viewGroup) {
        View view1=View.inflate(context,R.layout.item_home,null);
        ImageView iv_icon= (ImageView) view1.findViewById(R.id.iv_icon);
        TextView tv_name= (TextView) view1.findViewById(R.id.tv_name);
        iv_icon.setImageResource(imageId[i]);
        tv_name.setText(names[i]);
        return view1;
    }
}
