package com.ewininfo.mes.activity.start;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by fulishuang on 2017/7/27.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LocalImageHolderView> {

    @Override
    public LocalImageHolderView createHolder() {
        return new LocalImageHolderView();
    }
}