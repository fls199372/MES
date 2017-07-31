package com.ewininfo.mes.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ewininfo.mes.R;
import com.ewininfo.mes.activity.children.ChildrenActivity;
import com.ewininfo.mes.adapter.HomeAdapter;
import com.ewininfo.mes.fragment.BaseFragment;
import com.ewininfo.mes.fragment.HomeBarChartFragment1;
import com.ewininfo.mes.fragment.HomeBarChartFragment;
import com.ewininfo.mes.fragment.view.HomeHeaderLayout;

import com.ewininfo.mes.manager.UserManager;
import com.ewininfo.mes.module.menu.Menu;
import com.ewininfo.mes.module.menu.MenuList;
import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fulishuang on 2017/6/12.
 * 首页功能
 */

public class HomeFragment extends BaseFragment implements HomeView{


    private LRecyclerView mRecyclerView = null;

    private HomeAdapter mHomeAdapter= null;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private ImageView mLoadingView;
    private HomePresenter presenter;
    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    //管理Fragment
    private FragmentManager fragmentManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_layout, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenterImpl(HomeFragment.this);
        InitFragment();
        initView(view);
    }
    public void initView(View view){

        mLoadingView= (ImageView) view.findViewById(R.id.loading_view);
        //启动loading动画
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
        mLoadingView.setVisibility(View.GONE);

        mRecyclerView = (LRecyclerView)view. findViewById(R.id.list);
        //setLayoutManager must before setAdapter
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(manager);

        mHomeAdapter = new HomeAdapter(getActivity());

        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mHomeAdapter);
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
        GridItemDecoration divider = new GridItemDecoration.Builder(getActivity())
                .setHorizontal(R.dimen.default_divider_height)
                .setVertical(R.dimen.default_divider_height)
                .setColorResource(R.color.color_f6f6f6)
                .build();
        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.setHasFixedSize(true);

        /**
         *设置顶部
         */
        mLRecyclerViewAdapter.addHeaderView(new HomeHeaderLayout(getActivity(),"",fragmentArrayList,fragmentManager));


        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                presenter.validateCredentials("","", UserManager.getInstance().getUser().getToken(),getActivity());

               // requestData();
            }
        });
        //禁用自动加载更多功能
        mRecyclerView.setLoadMoreEnabled(false);
        mRecyclerView.refresh();
        //点击事件
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mHomeAdapter.getDataList().size() > position) {
                    Menu item = mHomeAdapter.getDataList().get(position);
                    if(item.getChildren()==null){
                        Intent intent = new Intent(getActivity(), ChildrenActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getActivity(), ChildrenActivity.class);
                        intent.putExtra("lstBean", (Serializable) item.getChildren());
                        intent.putExtra("name",item.getName());

                        startActivity(intent);
                    }

                }
            }
        });
    }
    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment(){
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new HomeBarChartFragment());
        fragmentArrayList.add(new HomeBarChartFragment1());

        fragmentManager = getChildFragmentManager();

    }
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void setNetworkUtils() {
        Toast.makeText(getActivity().getApplicationContext(), R.string.network , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoginFail(String error) {
        Toast.makeText(getActivity().getApplicationContext(), ""+error , Toast.LENGTH_SHORT).show();
    }
    ArrayList<Menu> newList = new ArrayList<>();


    @Override
    public void navigateToHome(MenuList menuList) {
        mHomeAdapter.clear();
        newList.clear();
        for (int i = 0; i < menuList.results.size(); i++) {

            Menu item = new Menu();
            item=menuList.results.get(i);
            newList.add(item);
        }
        mHomeAdapter.addAll(newList);

        mRecyclerView.refreshComplete(menuList.results.size());
        mLoadingView.setVisibility(View.GONE);
    }



}
