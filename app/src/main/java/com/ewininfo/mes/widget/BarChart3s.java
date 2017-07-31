package com.ewininfo.mes.widget;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * Created by fulishuang on 2017/7/24.
 */

public class BarChart3s {
    public BarChart3s(BarChart mChart) {
        //设置表格上的点，被点击的时候，的回调函数

        // mChart.setOnChartValueSelectedListener(this);
        /**
         * 将其设置为true，使高亮操作以全面方向为准，使其突出显示单个值（仅与堆叠相关）。
         * 如果启用，突出显示操作将突出显示整个条，即使只有一个堆栈条目被点击。
         */
        mChart.setHighlightFullBarEnabled(false);
        /**
         * 设置为true说明有背景 false没有背景
         */
        mChart.setDrawBarShadow(false);
        /**
         * 可以点击滑动事件 true表示可以  false表示不可以
         */
        mChart.setTouchEnabled(true);
        // 是否可以拖拽
        mChart.setDragEnabled(true);
        /**
         * 是否可以缩放  true 可以缩放 false禁止缩放
         */
        mChart.animateY(1500);

        mChart.setScaleEnabled(false);
        /**
         * 设置为true表示显示的值显示在柱状图上方  false的话显示在柱状图内的顶部
         */
        mChart.setDrawValueAboveBar(true);
        /**
         * 这里设置成true表示右下角有标志  false表示有标志
         */
        mChart.getDescription().setEnabled(false);

        /**
         * 设置柱状图的数量  这里是60  表示的就是可以超过60  但是超过60过后  壮壮图上方就不在显示具体参数
         */
        mChart.setMaxVisibleValueCount(60);
        /**
         * 如果设置为true，则可以使用2个手指同时缩放x轴和y轴，如果为false，
                x和y轴可以单独缩放。 默认值：false
         */
        mChart.setPinchZoom(false);
        /**
         * 将其设置为true以绘制网格背景，否则为false
         */
        mChart.setDrawGridBackground(false);

       // IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

        XAxis xAxis = mChart.getXAxis();
        /**
         * 枚举x标签相对于图表的位置

         */
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        /**
         * 为标签设置一个特定的字体
         */
        //   xAxis.setTypeface(mTfLight);
        /**
         * 将其设置为true可以绘制此轴的网格线。
         */
        xAxis.setDrawGridLines(false);
        /**
         * 在放大时设置轴的最小间隔。轴不允许进入下方
           那个限制。 这可以用于在放大时避免标签复制。
         */
        xAxis.setGranularity(1f); // only intervals of 1 day
        /**
         * 计算应显示的y轴标签数量
         */
        xAxis.setLabelCount(5);
        /**
         * 强制设置标签数
         */
        //mChart.getAxisLeft().setLabelCount(5,true);

        /**
         * 设置用于格式化轴标签的格式化程
         */
       //xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();
        /**
         * 返回左侧的y轴对象。 在水平条形图中，这是顶轴。
         */
        YAxis leftAxis = mChart.getAxisLeft();
        //  leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);
        mChart.getAxisRight().setEnabled(false); //隐藏Y轴右边轴线，此时标签数字也隐藏

        /**
         * 返回右侧的y轴对象。 在水平条形图中，这是底轴。


         YAxis rightAxis = mChart.getAxisRight();

         //将此设置为true，以便绘制此轴的网格线。

         rightAxis.setDrawGridLines(false);

         rightAxis.setTypeface(mTfLight);
         rightAxis.setLabelCount(8, false);
         rightAxis.setValueFormatter(custom);
         //将顶轴空间设置为全量程的百分比。默认10f

         rightAxis.setSpaceTop(20f);

         //为该轴设置自定义最小值。如果设置，则不计算该值。自动根据提供的数据

         rightAxis.setAxisMinimum(0f);*/


        Legend l = mChart.getLegend();
        /**
         * 设置图例的垂直对齐方式。 TOP  顶部  CENTER  中心  BOTTOM  底部
         */
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        /**
         * 设置图例的水平对齐方式。 LEFT 左侧  RIGHT 右侧  CENTER 中心
         */
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        /**
         * 确定方向 HORIZONTAL 水平 VERTICAL 垂直
         */
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        /**
         * 设置图例窗体的窗体/形状。 NONE 隐藏控件 不留空间  EMPTY 隐藏空间但要留出空间。
         * DEFAULT 使用默认（数据集的表单到图例的形式 圆形）SQUARE 正方形 CIRCLE 圆 LINE 横线
         */
        l.setForm(Legend.LegendForm.SQUARE);
        //字体
        l.setFormSize(9f);
        //大小
        l.setTextSize(11f);

        l.setXEntrySpace(4f);
        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });

        //条形图点击事件
        /** XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
         //边界控制
         mv.setChartView(mChart);
         //将标记设置为图表
         mChart.setMarker(mv); /*/

    }

    public BarDataSet getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
        for (int i = 1; i < 6; i++) {
            float value = (float) (Math.random() * 6/*100以内的随机数*/);
            valueSet1.add(new BarEntry(i, value));
        }


        ArrayList<BarEntry> valueSet2 = new ArrayList<BarEntry>();
        for (int j = 0; j < 31; j++) {
            float value1 = (float) (Math.random() * 100/*100以内的随机数*/) + 3;
            valueSet2.add(new BarEntry(value1, j));
        }
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "目标");
        barDataSet1.setColor(Color.parseColor("#45a2ff"));

        barDataSet1.setBarShadowColor(Color.parseColor("#01000000"));


        dataSets = new ArrayList<BarDataSet>();
        dataSets.add(barDataSet1);
        System.out.println("第一个页面");

        return barDataSet1;
    }
    public BarDataSet getDataSet2() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet2 = new ArrayList<BarEntry>();
        for (int j = 0; j < 31; j++) {
            float value1 = (float) (Math.random() * 100/*100以内的随机数*/) + 3;
            valueSet2.add(new BarEntry(value1, j));
        }


        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "实际");
        barDataSet2.setColor(Color.parseColor("#6faae7"));
        barDataSet2.setBarShadowColor(Color.parseColor("#01000000"));

        dataSets = new ArrayList<BarDataSet>();
        dataSets.add(barDataSet2);

        System.out.println("第二个页面");

        return barDataSet2;
    }
    public ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        for (int j = 0; j < 31; j++) {
            xAxis.add("8-" + (j + 1));
        }
        return xAxis;
    }
}

