package com.ewininfo.mes.activity.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ewininfo.mes.R;
import com.ewininfo.mes.activity.login.LoginActivity;
import com.ewininfo.mes.base.BaseActivity;
import com.ewininfo.mes.util.LattePreference;

import java.util.ArrayList;

/**
 * Created by fulishuang on 2017/7/27.
 */

public class StartGuideActivity extends BaseActivity implements OnItemClickListener {
    private ConvenientBanner convenientBanner;
    private ArrayList<Integer> integers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_guide_layout);
        initBanner();
        initView();
    }
    public void initBanner(){
        integers.add(R.drawable.page_loading_01);
        integers.add(R.drawable.page_loading_03);
        integers.add(R.drawable.page_loading_04);
        integers.add(R.drawable.page_loading_05);
        integers.add(R.drawable.page_loading_06);
    }
    public void initView(){
        convenientBanner= (ConvenientBanner) findViewById(R.id.convenientBanner);
        convenientBanner.setPages(new LauncherHolderCreator(), integers)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == integers.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            Intent intent = new Intent(StartGuideActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
