package com.ewininfo.mes.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ewininfo.mes.R;
import com.ewininfo.mes.activity.children.ChildrenActivity;
import com.ewininfo.mes.activity.login.LoginActivity;
import com.ewininfo.mes.activity.password.UpdatePasswordActivity;
import com.ewininfo.mes.db.database.UserUtilityDao;
import com.ewininfo.mes.fragment.BaseFragment;
import com.ewininfo.mes.manager.UserManager;
import com.ewininfo.mes.util.ButtonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fulishuang on 2017/6/12.
 * 我的界面
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.user)
    TextView user;

    //修改密码
    private TextView tv_update_password;
    private Button bt_log_out;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_my, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind( this , view ) ;
        initView(view);
    }
    //实例化控件
    public void initView(View view){
        tv_update_password= (TextView) view.findViewById(R.id.tv_update_password);
        bt_log_out= (Button) view.findViewById(R.id.bt_log_out);

        tv_update_password.setOnClickListener(this);
        bt_log_out.setOnClickListener(this);
        user.setText("测试BindView");
    }

    @Override
    public void onClick(View v) {
        if(tv_update_password==v){
            if (!ButtonUtils.isFastDoubleClick(R.id.tv_update_password)) {
                Intent intent = new Intent(getActivity(), UpdatePasswordActivity.class);
                startActivity(intent);
            }
        }else if(v==bt_log_out){
            if (!ButtonUtils.isFastDoubleClick(R.id.bt_log_out)) {
                com.ewininfo.mes.bean.User shop = new com.ewininfo.mes.bean.User();
                shop.setNumber(UserManager.getInstance().getUser().getNumber());
                UserUtilityDao.deleteLove(shop);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        }
    }
}
