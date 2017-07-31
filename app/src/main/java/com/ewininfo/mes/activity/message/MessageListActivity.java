package com.ewininfo.mes.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ewininfo.mes.R;
import com.ewininfo.mes.base.BaseActivity;

/**
 * Created by fulishuang on 2017/7/14.
 * 系统通知和消息通知列表
 */

public class MessageListActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_title,tv_set_read,tv_delete,tv_edit;
    private ImageView img_view,image_choice;
    private String mType="";
    private RelativeLayout rl_bottom;//底部状态栏
    private LinearLayout ly_choice,ly_right;
    private RelativeLayout message_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list_layout);
        Bundle bundle=getIntent().getExtras();
        mType=bundle.getString("type");
        initView();
    }
    public void initView(){
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_edit= (TextView) findViewById(R.id.tv_edit);
        tv_set_read= (TextView) findViewById(R.id.tv_set_read);
        tv_delete= (TextView) findViewById(R.id.tv_delete);
        ly_choice= (LinearLayout) findViewById(R.id.ly_choice);
        img_view= (ImageView) findViewById(R.id.img_view);
        image_choice= (ImageView) findViewById(R.id.image_choice);
        ly_right= (LinearLayout) findViewById(R.id.ly_right);
        rl_bottom= (RelativeLayout) findViewById(R.id.rl_bottom);
        rl_bottom.setVisibility(View.GONE);
        if(mType.equals("1")){
            tv_title.setText(R.string.system_notification);
        }else{
            tv_title.setText(R.string.message_notification);
        }

        tv_edit.setText(R.string.edit);

        tv_edit.setOnClickListener(this);
        img_view.setOnClickListener(this);
        tv_set_read.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        tv_edit.setOnClickListener(this);

        message_layout= (RelativeLayout) findViewById(R.id.message_layout);
        message_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(tv_edit==v){
            if(rl_bottom.getVisibility()==View.GONE){
                rl_bottom.setVisibility(View.VISIBLE);
                ly_right.setVisibility(View.GONE);
            }else{
                rl_bottom.setVisibility(View.GONE);
                ly_right.setVisibility(View.VISIBLE);
            }
        }else if(v==img_view){
            finish();
        }else if(v==tv_set_read){
            ly_choice.setVisibility(View.VISIBLE);
            image_choice.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.kai));
        }else if(v==tv_delete){
            ly_choice.setVisibility(View.VISIBLE);
            image_choice.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.guan));
        }else if(v==message_layout){
            Intent intent=new Intent(this,MessageListDetailsActivity.class);
            startActivity(intent);
        }
    }
}
