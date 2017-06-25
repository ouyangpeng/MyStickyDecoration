package com.oyp.recyclerview.sticky.decoration.utils;

import com.oyp.recyclerview.sticky.decoration.R;
import com.oyp.recyclerview.sticky.decoration.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市Util,仅仅为了初始化一下数据
 * </p>
 * created by OuyangPeng at 2017/6/25 9:45
 */
public class CityUtil {
    /**
     * 获取城市名
     *
     * @return
     */
    public static List<City> getCityList() {
        List<City> dataList = new ArrayList<>();

        final String HU_NAN = "湖南省";
        final int HU_NAN_ICOM = R.mipmap.city1;
        dataList.add(new City("长沙", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("株洲", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("衡阳", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("邵阳", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("岳阳", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("常德", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("张家界", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("益阳", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("娄底", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("郴州", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("永州", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("怀化", HU_NAN, HU_NAN_ICOM));
        dataList.add(new City("湘西土家族苗族自治州", HU_NAN, HU_NAN_ICOM));


        final String GUANG_DONG = "广东省";
        final int GUANG_DONG_ICOM = R.mipmap.city2;
        dataList.add(new City("广州", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("深圳", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("珠海", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("汕头", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("佛山", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("韶关", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("湛江", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("肇庆", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("江门", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("茂名", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("惠州", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("梅州", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("汕尾", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("河源", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("阳江", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("清远", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("东莞", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("中山", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("潮州", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("揭阳", GUANG_DONG, GUANG_DONG_ICOM));
        dataList.add(new City("云浮", GUANG_DONG, GUANG_DONG_ICOM));



        final String ZHE_JIANG = "浙江省";
        final int ZHE_JIANG_ICON = R.mipmap.city3;
        dataList.add(new City("杭州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("宁波", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("温州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("嘉兴", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("绍兴", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("金华", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("湖州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList.add(new City("舟山", ZHE_JIANG, ZHE_JIANG_ICON));

        final String JIANG_SU = "江苏省";
        final int JIANG_SU_ICOM = R.mipmap.city4;
        dataList.add(new City("南京", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("苏州", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("徐州", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("南通", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("无锡", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("盐城", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("淮安", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("泰州", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("常州", JIANG_SU, JIANG_SU_ICOM));
        dataList.add(new City("连云港", JIANG_SU, JIANG_SU_ICOM));

        final String FU_JIAN = "福建省";
        final int FU_JIAN_ICON = R.mipmap.city5;
        dataList.add(new City("福州", FU_JIAN, FU_JIAN_ICON));
        dataList.add(new City("厦门", FU_JIAN, FU_JIAN_ICON));
        dataList.add(new City("泉州", FU_JIAN, FU_JIAN_ICON));
        dataList.add(new City("宁德", FU_JIAN, FU_JIAN_ICON));
        dataList.add(new City("漳州", FU_JIAN, FU_JIAN_ICON));

        final String AN_HUI = "安徽省";
        final int AN_HUI_ICON = R.mipmap.city6;
        dataList.add(new City("合肥", AN_HUI, AN_HUI_ICON));
        dataList.add(new City("芜湖", AN_HUI, AN_HUI_ICON));
        dataList.add(new City("蚌埠", AN_HUI, AN_HUI_ICON));
        return dataList;
    }
}
