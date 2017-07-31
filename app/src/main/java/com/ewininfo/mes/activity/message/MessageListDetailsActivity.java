package com.ewininfo.mes.activity.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ewininfo.mes.R;
import com.ewininfo.mes.base.BaseActivity;

/**
 * Created by fulishuang on 2017/7/14.
 * 信息明细
 */

public class MessageListDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_edit,tv_title;
    private ImageView img_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details_layout);
        initView();

    }
    public void initView(){
        tv_edit= (TextView) findViewById(R.id.tv_edit);
        tv_title= (TextView) findViewById(R.id.tv_title);
        img_view= (ImageView) findViewById(R.id.img_view);
        tv_title.setText(R.string.notification_details);
        tv_title.setVisibility(View.GONE);
        img_view.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(img_view==v){
            finish();
        }
    }
}
