package com.oyp.recyclerview.sticky.decoration.model;

/**
 * 城市PoJO
 * </p>
 * created by OuyangPeng at 2017/6/25 9:44
 */
public class City {
    /**
     * 城市名
     */
    private String name;
    /**
     * 所属省份
     */
    private String province;
    /**
     * 所属省份
     */
    private int icon;

    public City(String name, String province, int icon) {
        this.name = name;
        this.province = province;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", icon=" + icon +
                '}';
    }
}