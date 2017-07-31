package com.ewininfo.mes.server;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.allenliu.versionchecklib.AVersionService;

/**
 * Created by fulishuang on 2017/7/28.
 */

public class UpgradeServer extends AVersionService {
    public UpgradeServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onResponses(AVersionService service, String response) {
        service.showVersionDialog("http://www.apk3.com/uploads/soft/guiguangbao/UCllq.apk","检测到新版本","");
    }
}
