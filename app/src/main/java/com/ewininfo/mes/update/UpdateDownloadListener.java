package com.ewininfo.mes.update;

/**
 * Created by fulishuang on 2017/7/27.
 * 事件的回调
 */

public interface  UpdateDownloadListener  {
    /**
     * 下载请求开始回调
     */
    public void onStarted();

    /**
     * 进度回调更新
     * @param progress
     * @param downloadUrl
     */
    public void onProgressChanged(int progress, String downloadUrl);

    /**
     * 下载完成回调
     * @param completeSize
     * @param downloadUrl
     */
    public void onFinished(float completeSize, String downloadUrl);

    /**
     * 下载失败回调
     */
    public void onFailure();
}
