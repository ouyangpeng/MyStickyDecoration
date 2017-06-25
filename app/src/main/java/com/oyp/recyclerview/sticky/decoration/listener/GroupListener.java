package com.oyp.recyclerview.sticky.decoration.listener;

/**
 * Created by OuyangPeng on 2017/6/25.
 * 监听接口，根据position通过接口方法getGroupName获取当前组名
 */
public interface GroupListener {
    /**
     * 根据position通过接口方法getGroupName获取当前组名
     * @param position
     * @return
     */
    String getGroupName(int position);
}
