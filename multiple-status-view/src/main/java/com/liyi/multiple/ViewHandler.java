package com.liyi.multiple;

import android.view.View;

/**
 * view 的处理接口
 */
public interface ViewHandler {

    /**
     * 可以在本方法中对 view 进行处理
     *
     * @param viewType view 的类型
     * @param view
     */
    void handleView(int viewType, View view);
}
