package com.ewininfo.mes.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.load.engine.GlideException;
import com.ewininfo.mes.R;
import com.ewininfo.mes.bean.ItemModel;
import com.ewininfo.mes.module.menu.Menu;
import com.mes.glideimageview.GlideImageView;
import com.mes.glideimageview.progress.OnProgressListener;

/**
 * Created by fulishuang on 2017/7/20.
 */

public class HomeAdapter extends ListBaseAdapter<Menu> {

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_layout;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        String url1="http://www.lw1989.com/images/nfwwcz3ffz2gsylonjuw2zlenfqs4y3pnu/uploadImages/2013/159/C206IEPYZM8R.jpg";
        Menu item = mDataList.get(position);
        GlideImageView itemImage =holder.getView(R.id.itemImage);
        TextView tv_name = holder.getView(R.id.tv_name);
        tv_name.setText(item.getName());
        itemImage.loadImage(url1, R.drawable.login_icon);
        itemImage.setClickable(false);
    }
}
