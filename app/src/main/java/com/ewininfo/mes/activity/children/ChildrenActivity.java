package com.ewininfo.mes.activity.children;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ewininfo.mes.R;
import com.ewininfo.mes.adapter.ChildrenAdapter;
import com.ewininfo.mes.base.BaseActivity;
import com.ewininfo.mes.fragment.home.HomePresenter;
import com.ewininfo.mes.module.menu.Menu;
import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import java.util.List;

/**
 * Created by fulishuang on 2017/7/24.
 */

public class ChildrenActivity extends BaseActivity {
    private LRecyclerView mRecyclerView = null;

    private ChildrenAdapter mChildrenAdapter= null;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private ImageView mLoadingView;
    private HomePresenter presenter;
    private TextView tv_title;
    private List<Menu.ChildrenBean> lstBean=null;
    private String mName="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_layout);
        Intent intentGet = getIntent();

        lstBean = (List<Menu.ChildrenBean>) intentGet.getSerializableExtra("lstBean");
        mName=intentGet.getStringExtra("name");
        initView();
    }
    public void initView(){
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText(mName);
        mLoadingView= (ImageView)findViewById(R.id.loading_view);
        //启动loading动画
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
        mLoadingView.setVisibility(View.GONE);

        mRecyclerView = (LRecyclerView)findViewById(R.id.list);
        //setLayoutManager must before setAdapter
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(manager);

        mChildrenAdapter = new ChildrenAdapter(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mChildrenAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.dark ,android.R.color.white);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.dark ,android.R.color.white);
        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

        int spacing = getResources().getDimensionPixelSize(R.dimen.default_divider_height);
        mRecyclerView.addItemDecoration(SpacesItemDecoration.newInstance(spacing, spacing, manager.getSpanCount(), Color.GRAY));

        //根据需要选择使用GridItemDecoration还是SpacesItemDecoration
        GridItemDecoration divider = new GridItemDecoration.Builder(this)
                .setHorizontal(R.dimen.default_divider_height)
                .setVertical(R.dimen.default_divider_height)
                .setColorResource(R.color.color_f6f6f6)
                .build();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setHasFixedSize(true);
        //禁用下拉刷新功能
        mRecyclerView.setPullRefreshEnabled(false);
        //禁用自动加载更多功能
        mRecyclerView.setLoadMoreEnabled(false);
        mRecyclerView.refresh();
        //点击事件
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mChildrenAdapter.getDataList().size() > position) {
                    Menu.ChildrenBean item = mChildrenAdapter.getDataList().get(position);

                }
            }
        });
        mChildrenAdapter.addAll(lstBean);
        mRecyclerView.refreshComplete(lstBean.size());
        mLoadingView.setVisibility(View.GONE);
    }
}
