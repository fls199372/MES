package com.ewininfo.mes.adapter;

import android.content.Context;
import android.widget.TextView;

import com.ewininfo.mes.R;
import com.ewininfo.mes.module.menu.Menu;
import com.mes.glideimageview.GlideImageView;

/**
 * Created by fulishuang on 2017/7/20.
 */

public class ChildrenAdapter extends ListBaseAdapter<Menu.ChildrenBean> {

    public ChildrenAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_home_layout;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        String url1="http://v1.qzone.cc/skin/201407/29/21/38/53d7a3d5ae50b606.jpg!600x600.jpg";
        Menu.ChildrenBean item = mDataList.get(position);
        GlideImageView itemImage =holder.getView(R.id.itemImage);
        TextView tv_name = holder.getView(R.id.tv_name);
        tv_name.setText(item.getName());
        itemImage.loadImage(url1, R.color.color_4bacff);
        itemImage.setClickable(false);
    }
}
