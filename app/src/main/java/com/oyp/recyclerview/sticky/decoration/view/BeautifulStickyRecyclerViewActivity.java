package com.oyp.recyclerview.sticky.decoration.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oyp.recyclerview.sticky.decoration.R;
import com.oyp.recyclerview.sticky.decoration.adapter.BeautifulAdapter;
import com.oyp.recyclerview.sticky.decoration.adapter.StickyRecyclerViewAdapter;
import com.oyp.recyclerview.sticky.decoration.listener.PowerfulGroupListener;
import com.oyp.recyclerview.sticky.decoration.model.City;
import com.oyp.recyclerview.sticky.decoration.utils.CityUtil;
import com.oyp.recyclerview.sticky.decoration.utils.DensityUtil;
import com.oyp.recyclerview.sticky.decoration.widget.PowerfulStickyDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeautifulStickyRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    BeautifulAdapter mAdapter;
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

        PowerfulStickyDecoration decoration = PowerfulStickyDecoration.Builder
                .init(new PowerfulGroupListener() {
                    @Override
                    public String getGroupName(int position) {
                        //组名回掉
                        if (dataList.size() > position) {
                            return dataList.get(position).getProvince();
                        }
                        return null;
                    }

                    @Override
                    public View getGroupView(int position) {
                        //获取自定定义的组View回掉
                        if (dataList.size() > position) {
                            View view = getLayoutInflater().inflate(R.layout.city_group, null, false);
                            ((TextView) view.findViewById(R.id.tv)).setText(dataList.get(position).getProvince());
                            ((ImageView) view.findViewById(R.id.iv)).setImageResource(dataList.get(position).getIcon());
                            return view;
                        }
                        return null;
                    }
                })
                .setGroupHeight(DensityUtil.dip2px(this, 40))       //高度
                .build();
        mRecyclerView.addItemDecoration(decoration);

        mAdapter = new BeautifulAdapter(this, dataList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
