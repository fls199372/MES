package com.ewininfo.mes.fragment.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ewininfo.mes.R;
import com.ewininfo.mes.adapter.MFragmentPagerAdapter;
import com.ewininfo.mes.view.CirclePageIndicator;
import com.ewininfo.mes.widget.BarChart3s;
import com.ewininfo.mes.widget.UPMarqueeView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


/**
 * Created by fulishuang on 2017/7/20.
 */

public class HomeHeaderLayout extends LinearLayout {

    private Context mContext;
    private LinearLayout mRootView;
    private UPMarqueeView upview1;
    List<String> data = new ArrayList<>();
    List<View> views = new ArrayList<>();
    private CirclePageIndicator mPagerIndictor;
    private AutoScrollViewPager mViewPager;
    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    //管理Fragment
    private FragmentManager fragmentManager;

    public HomeHeaderLayout(Context context, String string,ArrayList<Fragment> fragmentArrayList,FragmentManager fragmentManager) {
        this(context, null, string,fragmentArrayList,fragmentManager);
    }

    public HomeHeaderLayout(Context context, AttributeSet attrs, String string,ArrayList<Fragment> fragmentArrayList,FragmentManager fragmentManager) {
        super(context, attrs);
        mContext = context;
        this.fragmentArrayList=fragmentArrayList;
        this.fragmentManager=fragmentManager;
        initdata();
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = (LinearLayout) inflater.inflate(R.layout.listview_home_head_layout, this);
        upview1= (UPMarqueeView)findViewById(R.id.upview1);
        mViewPager = (AutoScrollViewPager) mRootView.findViewById(R.id.pager);
        mPagerIndictor = (CirclePageIndicator) mRootView.findViewById(R.id.pager_indictor_view);

        setView();
        upview1.setViews(views);


        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);
        mViewPager.startAutoScroll(3000);

        mPagerIndictor.setViewPager(mViewPager);

    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext.getApplicationContext(), position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
    }

    /**
     * 初始化数据
     */
    private void initdata() {
        data = new ArrayList<>();
        data.add("家人给2岁孩子喝这个，孩子智力倒退10岁!!!");
        data.add("iPhone8最感人变化成真，必须买买买买!!!!");
        data.add("简直是白菜价！日本玩家33万甩卖15万张游戏王卡");
        data.add("iPhone7价格曝光了！看完感觉我的腰子有点疼...");
        data.add("主人内疚逃命时没带够，回废墟狂挖30小时！");
        data.add("竟不是小米乐视！看水抢了骁龙821首发了！！！");
    }

    public LinearLayout getmRootView() {
        return mRootView;
    }

    public void setmRootView(LinearLayout mRootView) {
        this.mRootView = mRootView;
    }

}