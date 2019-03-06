package cn.mmvtc.mobilesafe.chapter01.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

/**
 * Created by Administrator on 2019/2/28.
 */

public class DownLoadUtils {
    /**
     *
     * @param url           下载文件的路径
     * @param targerFile    下载文件本地路径
     * @param myCallBack    监听下载状态
     */
    public void downapk(String url,String targerFile,final MyCallBack myCallBack){
        //创建HttpUtils对象
        HttpUtils httpUtils = new HttpUtils();
        //调用HttpUtils 下载的方法下载指定文件
        httpUtils.download(url, targerFile, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> arg0) {
                myCallBack.onSuccess(arg0);
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                myCallBack.onFailure(arg0,arg1);
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                myCallBack.onLoadding(total,current,isUploading);
            }
        });
    }

}
interface MyCallBack{
    //下载成功调用
    void onSuccess(ResponseInfo<File> arg0);
    //下载失败调用
    void onFailure(HttpException arg0, String arg1);
    //下载中调用
    void onLoadding(long total, long current, boolean isUploading);
}