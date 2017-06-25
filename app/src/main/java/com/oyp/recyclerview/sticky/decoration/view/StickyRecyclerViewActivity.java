package com.oyp.recyclerview.sticky.decoration.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oyp.recyclerview.sticky.decoration.R;
import com.oyp.recyclerview.sticky.decoration.adapter.StickyRecyclerViewAdapter;
import com.oyp.recyclerview.sticky.decoration.listener.GroupListener;
import com.oyp.recyclerview.sticky.decoration.model.City;
import com.oyp.recyclerview.sticky.decoration.utils.CityUtil;
import com.oyp.recyclerview.sticky.decoration.utils.DensityUtil;
import com.oyp.recyclerview.sticky.decoration.widget.StickyDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StickyRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    StickyRecyclerViewAdapter mAdapter;
    List<City> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticy_recycler_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //模拟数据
        dataList.addAll(CityUtil.getCityList());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        StickyDecoration decoration = StickyDecoration.Builder
                .init(new GroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //组名回掉
                        if (dataList.size() > position) {
                            return dataList.get(position).getProvince();
                        }
                        return null;
                    }
                })
                .setGroupBackGroup(Color.parseColor("#48BDFF"))    //背景色
                .setGroupHeight(DensityUtil.dip2px(this, 35))       //高度
                .setGroupTextColor(Color.WHITE)                     //字体颜色
                .setGroupTextSize(DensityUtil.sp2px(this, 15))      //字体大小
                .setTextLeftMargin(DensityUtil.dip2px(this, 10))    //左边距
                .build();
        mRecyclerView.addItemDecoration(decoration);

        mAdapter = new StickyRecyclerViewAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
