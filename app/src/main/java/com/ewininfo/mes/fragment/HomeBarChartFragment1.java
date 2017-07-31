package com.ewininfo.mes.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ewininfo.mes.R;
import com.ewininfo.mes.activity.message.MessageListActivity;
import com.ewininfo.mes.widget.BarChart3s;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;


/**
 * Created by fulishuang on 2017/6/12.
 * 我的消息界面
 */

public class HomeBarChartFragment1 extends Fragment {
    private BarChart bar_chart;
    private BarChart3s mBarChart3s;
    private static final String ARG_POSITION = "position";

    public static HomeBarChartFragment1 newInstance(String position) {
        HomeBarChartFragment1 f = new HomeBarChartFragment1();
        Bundle b = new Bundle();
        b.putString(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_barchart_layout, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }
    public void initView(View view){
        bar_chart= (BarChart)view.findViewById(R.id.bar_chart);
        mBarChart3s = new BarChart3s(bar_chart);
        BarData data = new BarData(mBarChart3s.getDataSet2());
        data.setValueTextSize(10f);
        //  data.setValueTypeface(mTfLight);
        data.setBarWidth(0.9f);
        // 设置数据
        bar_chart.setData(data);
    }
}