package com.ewininfo.mes.update;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.widget.Toast;


import com.ewininfo.mes.R;

import java.io.File;

/**
 * Created by fulishuang on 2017/7/27.
 * app跟新下载后台服务
 */

public class UpdateService  extends Service {
    private String apkURL;
    private String filePath;
    private NotificationManager notificationManager;
    private Notification mNotification;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        filePath= Environment.getExternalStorageDirectory()+"/imooc/QjFund.apk";

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null){
            notifyUser(getString(R.string.update_download_failed),
                    getString(R.string.update_download_failed_msg),0);
            stopSelf();
        }else{
            apkURL=intent.getStringExtra("apkUrl");
          //  notifyUser(getString(R.string.update_download_start), getString(R.string.update_download_start_msg),0);
            startDownload();
        }

        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void startDownload(){
        UpdateManager.getInstance().startDownloads(apkURL, filePath, new UpdateDownloadListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgressChanged(int progress, String downloadUrl) {
                notifyUser(getString(R.string.update_download_processing),
                        getString(R.string.update_download_processing),progress);
            }

            @Override
            public void onFinished(float completeSize, String downloadUrl) {
                notifyUser(getString(R.string.update_download_finish),
                        getString(R.string.update_download_finish),100);
                stopSelf();
            }

            @Override
            public void onFailure() {
                notifyUser(getString(R.string.update_download_failed),
                        getString(R.string.update_download_failed),0);
                stopSelf();

            }
        });
    }

    /**
     * 更新我们的 Notification 来告知用户当前的下载进度
     * @param retult
     * @param reason
     * @param progress
     */
    private void notifyUser(String retult,String reason,int progress){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.icon_login)
                .setContentTitle("报表报表不不不");
        if(progress>0&&progress<100){
            builder.setContentText("下载中..."+(progress+1)+"%");
            builder.setProgress(100,progress,false);
        }else{
            builder.setProgress(100,100,false);
            builder.setContentText("下载完成，点击安装");
        }
        //设置为true，点击通知栏 ，移除通知
        builder.setAutoCancel(true);
        //设置当前时间
        builder.setWhen(System.currentTimeMillis());
        //设置的是通知时在状态栏显示的通知内容
        builder.setTicker(retult);
        builder.setContentIntent(progress>=100?getContentIntent():PendingIntent
                .getActivity(this,0,new Intent(),PendingIntent.FLAG_UPDATE_CURRENT));
        mNotification=builder.build();
        notificationManager.notify(0,mNotification);
    }
    private PendingIntent getContentIntent(){
        //创建好我们下载好的文件
        File apkFile=new File(filePath);

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) { //判读版本是否在7.0以上
            Uri apkUri =
                    FileProvider.getUriForFile(this, "com.com.yll520wcf.test.fileprovider", apkFile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.parse("file://"+apkFile.getAbsolutePath())
                    ,"application/vnd.android.package-archive");
        }

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

}
