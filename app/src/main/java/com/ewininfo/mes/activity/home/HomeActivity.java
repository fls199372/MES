package com.ewininfo.mes.activity.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ewininfo.mes.R;
import com.ewininfo.mes.base.BaseActivity;
import com.ewininfo.mes.base.BaseApplication;
import com.ewininfo.mes.bean.User;
import com.ewininfo.mes.fragment.home.HomeFragment;
import com.ewininfo.mes.fragment.message.MessageFragment;
import com.ewininfo.mes.fragment.mine.MineFragment;
import java.util.List;

public class HomeActivity extends BaseActivity implements OnClickListener{
    private FragmentManager fm;
    private HomeFragment mHomeFragment;
    private Fragment mCommonFragmentOne;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private Fragment mCurrent;
    private TextView nav_tv_dot;
    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private LinearLayout mMessageLayout;
    private RelativeLayout mMineLayout;
    private TextView mHomeView,tv_home,tv_message,tv_mine;
    private TextView mMessageView;
    private TextView mMineView;
    private List<User> shops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        /**
         * 初始化所有控件
         */
        initView();

        mHomeFragment = new HomeFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, mHomeFragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        mHomeLayout = (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);

        mMessageLayout = (LinearLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout = (RelativeLayout) findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);

        mHomeView = (TextView) findViewById(R.id.home_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mMineView = (TextView) findViewById(R.id.mine_image_view);
        tv_home= (TextView) findViewById(R.id.tv_home);
        tv_message= (TextView) findViewById(R.id.tv_message);
        tv_mine= (TextView) findViewById(R.id.tv_mine);


        nav_tv_dot= (TextView) findViewById(R.id.nav_tv_dot);
        nav_tv_dot.setVisibility(View.VISIBLE);
        nav_tv_dot.setText("1");
    }
    private long mExitTime;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(),"再按一次退出MES", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                BaseApplication.GetInstance().exit();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.home_layout_view:
                //changeStatusBarColor(R.color.color_fed952);
                /**
                 * 修改图片
                 */
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                tv_home.setTextColor(ContextCompat.getColor(this,R.color.color_ff7231));
                tv_message.setTextColor(ContextCompat.getColor(this,R.color.color_333333));
                tv_mine.setTextColor(ContextCompat.getColor(this,R.color.color_333333));

                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                hideFragment(mMineFragment, fragmentTransaction);
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout, mHomeFragment);
                } else {
                    mCurrent = mHomeFragment;
                    fragmentTransaction.show(mHomeFragment);
                }
                break;
            case R.id.message_layout_view:
                //changeStatusBarColor(R.color.color_e3e3e3);
                /**
                 * 修改图片
                 */
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                tv_message.setTextColor(ContextCompat.getColor(this,R.color.color_ff7231));
                tv_home.setTextColor(ContextCompat.getColor(this,R.color.color_333333));
                tv_mine.setTextColor(ContextCompat.getColor(this,R.color.color_333333));

                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMineFragment, fragmentTransaction);
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout, mMessageFragment);
                } else {
                    mCurrent = mMessageFragment;
                    fragmentTransaction.show(mMessageFragment);
                }
                break;
            case R.id.mine_layout_view:
               // changeStatusBarColor(R.color.white);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);

                tv_mine.setTextColor(ContextCompat.getColor(this,R.color.color_ff7231));
                tv_home.setTextColor(ContextCompat.getColor(this,R.color.color_333333));
                tv_message.setTextColor(ContextCompat.getColor(this,R.color.color_333333));

                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content_layout, mMineFragment);
                } else {
                    mCurrent = mMineFragment;
                    fragmentTransaction.show(mMineFragment);
                }
                break;
        }

        fragmentTransaction.commit();
    }
    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

}
