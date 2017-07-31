package com.ewininfo.mes.activity.upgrade;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.VersionDialogActivity;
import com.allenliu.versionchecklib.callback.CancelClickListener;
import com.allenliu.versionchecklib.callback.CommitClickListener;
import com.allenliu.versionchecklib.callback.DownloadSuccessListener;
import com.allenliu.versionchecklib.callback.DownloadingListener;
import com.ewininfo.mes.R;
import com.ewininfo.mes.util.StatusBarUtil;

import java.io.File;

/**
 * @author allenliu
 * @email alexliusheng@163.com
 * @link :http://github.com/alexliusheng
 * 注意为了展示本库的所有功能
 * 所以代码看上去会比较多，不过都是重写方法和监听回调
 * 如果不想自定义界面和一些自定义功能不用设置
 * versionParams.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
 * 使用库默认自带的就行了
 */
public class CustomVersionDialogActivity extends VersionDialogActivity implements CommitClickListener, CancelClickListener, DownloadingListener, DownloadSuccessListener {
    public static int customVersionDialogIndex = 3;
    public static boolean isForceUpdate = false;
    public static boolean isCustomDownloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this);
        //这里是几个回调
        setOnDownloadSuccessListener(this);
        setOnDownloadingListener(this);
        setCommitClickListener(this);
        setCancelClickListener(this);
    }

    @Override
    public void onDownloadSuccess(File file) {
        Log.e("CustomVersionDialogActi", "文件下载成功回调");
    }

    @Override
    public void onDownloading(float progress) {
        Log.e("CustomVersionDialogActi", "正在下载中回调...");
    }

    /**
     * 注意本方法只有《使用默认界面》
     * 点击取消之后，如果要强制更新 这里就可以强制退出app
     * 建议用一个ActivityManger记录每个Activity出入堆栈
     * 最后全部关闭activity 实现app exit
     * ActivityTaskManger.finishAllActivity();
     */

    @Override
    public void onCancelClick() {
        try{
            finish();
            if (isForceUpdate) {
                //我这里为了简便直接finish 就行了
            }
        }catch (Exception e){

        }
    }

    @Override
    public void onCommitClick() {
        Log.e("CustomVersionDialogActi", "确认按钮点击回调");
    }


    /**
     * 自定义更新展示界面 直接重写此方法就好
     */
    @Override
    public void showVersionDialog() {
        //使用默认的提示框直接调用父类的方法,如果需要自定义的对话框，那么直接重写此方法
        // super.showVersionDialog();
        if (customVersionDialogIndex == 1) {
            customVersionDialogOne();
        } else {
            super.showVersionDialog();
        }
    }

    /**
     * 自定义dialog one
     */
    private void customVersionDialogOne() {
        final BaseDialog baseDialog = new BaseDialog(this, R.style.BaseDialog, R.layout.activity_upgrade_dialog_layout);
        TextView tvCancel = (TextView) baseDialog.findViewById(R.id.tv_cancel);
        TextView tvUpdate = (TextView) baseDialog.findViewById(R.id.tv_update);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelClick();
                baseDialog.dismiss();
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseDialog.dismiss();
                downloadFile("http://www.apk3.com/uploads/soft/guiguangbao/UCllq.apk");
            }
        });
        baseDialog.show();
    }


    @Override
    public void showFailDialog() {
        // super.showFailDialog();
    }


    View loadingView;
    AlertDialog dialog;

    /**
     * 要更改下载中界面 只需要重写此方法即可
     * 因为下载的时候会不断回调此方法
     * dialog使用全局 只初始化一次
     *
     * @param currentProgress
     */
    @Override
    public void showLoadingDialog(int currentProgress) {

//        Toast.makeText(this, "显示自定义的下载加载框", Toast.LENGTH_SHORT).show();
    }

    private void forceCloseAPP() {

    }

}
