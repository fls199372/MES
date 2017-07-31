package com.ewininfo.mes.fragment.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ewininfo.mes.R;
import com.ewininfo.mes.activity.message.MessageListActivity;
import com.ewininfo.mes.fragment.BaseFragment;


/**
 * Created by fulishuang on 2017/6/12.
 * 我的消息界面
 */

public class MessageFragment extends BaseFragment implements View.OnClickListener{

    RelativeLayout notification_layout,message_layout;
    TextView nav_tv_dot,tv_notification_num;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message_layout, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }
    public void initView(View view){
        //在Fragment中使用Activity中控件的方式
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        nav_tv_dot = (TextView) activity.findViewById(R.id.nav_tv_dot);
       // nav_tv_dot.setVisibility(View.GONE);
        notification_layout= (RelativeLayout) view.findViewById(R.id.notification_layout);
        message_layout= (RelativeLayout) view.findViewById(R.id.message_layout);
        tv_notification_num= (TextView) view.findViewById(R.id.tv_notification_num);
        tv_notification_num.setVisibility(View.VISIBLE);
        tv_notification_num.setText("1");

        notification_layout.setOnClickListener(this);
        message_layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==notification_layout){
            Intent intent=new Intent(getActivity(),MessageListActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("type","1");
            intent.putExtras(bundle);
            startActivityForResult(intent,1);

        }else  if(v==message_layout){
            Intent intent=new Intent(getActivity(),MessageListActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("type","2");
            intent.putExtras(bundle);
            startActivityForResult(intent,1);
        }

    }
}
