package com.liyi.multiple;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 多种状态切换的 view
 */
public class MultipleStatusView extends FrameLayout {
    // 默认的 layout 属性
    private final FrameLayout.LayoutParams DEFAULT_LAYOUT_PARAMS = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT);
    // 无效的布局资源文件 id
    private final int INVALID_LAYOUT_ID = 0;

    // 存储各种类型的 view
    private SparseArray<View> mViews;
    // 存储各种类型的 view 的资源 id
    private SparseArray<Integer> mViewRes;
    // 存储对各种类型 view 的处理类
    private SparseArray<ViewHandler> mHandlers;

    // 当前显示的视图
    private View currentView;
    private LayoutInflater mInflater;

    public MultipleStatusView(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public MultipleStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MultipleStatusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mInflater = LayoutInflater.from(context);
        mViews = new SparseArray<>();
        mViewRes = new SparseArray<>();
        mHandlers = new SparseArray<>();

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyle, 0);
        mViewRes.put(ViewType.TYPE_LOADING, a.getResourceId(R.styleable.MultipleStatusView_loading, R.layout.multiple_view_loading));
        mViewRes.put(ViewType.TYPE_NETWORK_POOR, a.getResourceId(R.styleable.MultipleStatusView_network_poor, R.layout.multiple_view_network_poor));
        mViewRes.put(ViewType.TYPE_EMPTY, a.getResourceId(R.styleable.MultipleStatusView_empty, R.layout.multiple_view_empty));
        mViewRes.put(ViewType.TYPE_ERROR, a.getResourceId(R.styleable.MultipleStatusView_error, R.layout.multiple_view_error));
        mViewRes.put(ViewType.TYPE_SPECIFIED, a.getResourceId(R.styleable.MultipleStatusView_specified, INVALID_LAYOUT_ID));
        a.recycle();
    }

    /**
     * 设置 view 的处理类
     *
     * @param viewType view 的类型
     * @param handler  view 的处理类
     */
    public void setHandler(int viewType, ViewHandler handler) {
        if (handler == null) return;
        mHandlers.put(viewType, handler);
    }

    /**
     * 显示指定的 view
     *
     * @param viewType view 的类型
     */
    public void showView(int viewType) {
        setVisibility(VISIBLE);
        hideView();
        showView(viewType, mViewRes.get(viewType), mViews.get(viewType));
    }

    /**
     * 显示指定的 view
     *
     * @param viewType view 的类型
     * @param layoutId
     * @param view
     */
    private void showView(int viewType, int layoutId, View view) {
        // 判断 view 是否为空
        if (checkNotNull(view)) {
            // 判断 view 是否被添加
            if (view.getParent() != null) {
                // 判断 view 是否正在显示
                if (view.getVisibility() != VISIBLE) {
                    view.setVisibility(VISIBLE);
                }
            } else {
                if (mHandlers.get(viewType) != null) {
                    mHandlers.get(viewType).handleView(viewType, view);
                }
                addView(view, 0, DEFAULT_LAYOUT_PARAMS);
            }
            currentView = view;
        } else {
            // 判断布局文件是否有效
            if (layoutId != 0) {
                view = inflateView(layoutId);
                if (mHandlers.get(viewType) != null) {
                    mHandlers.get(viewType).handleView(viewType, view);
                }
                addView(view, 0, DEFAULT_LAYOUT_PARAMS);
                currentView = view;
            } else {
                currentView = null;
            }
        }
    }

    /**
     * 隐藏当前正在显示的 view
     */
    private void hideView() {
        if (currentView != null && currentView.getVisibility() == VISIBLE) {
            currentView.setVisibility(GONE);
        }
    }

    /**
     * 设置 view
     *
     * @param viewType view 的类型，可添加除{@link ViewType}之外的类型
     * @param view
     */
    public void setView(int viewType, View view) {
        if (view == null) return;
        mViews.put(viewType, view);
    }

    /**
     * 设置 view
     *
     * @param viewType view 的类型，可添加除{@link ViewType}之外的类型
     * @param layoutId 布局资源 id
     */
    public void setView(int viewType, int layoutId) {
        if (layoutId == INVALID_LAYOUT_ID) return;
        mViewRes.put(viewType, layoutId);
        setView(viewType, inflateView(layoutId));
    }

    /**
     * 加载布局文件
     *
     * @param layoutId 布局文件的资源 id
     * @return
     */
    private final View inflateView(@LayoutRes int layoutId) {
        return mInflater.inflate(layoutId, null);
    }

    /**
     * 判断 view 是否为空
     *
     * @param view
     * @return
     */
    private boolean checkNotNull(View view) {
        if (view != null) return true;
        return false;
    }

    /**
     * 关闭界面
     */
    public void dimiss() {
        setVisibility(GONE);
    }

    /**
     * 清除所有的 view
     */
    private void clear() {
        if (mViews != null && mViews.size() > 0) mViews.clear();
        if (mViewRes != null && mViewRes.size() > 0) mViewRes.clear();
        if (mHandlers != null && mHandlers.size() > 0) mHandlers.clear();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear();
        mInflater = null;
        mViews = null;
        mViewRes = null;
        mHandlers = null;
    }

    /**
     * 默认的 view 的类型
     */
    public class ViewType {
        // 加载中视图
        public static final int TYPE_LOADING = 0x01;
        // 网络异常视图
        public static final int TYPE_NETWORK_POOR = 0x02;
        // 空数据视图
        public static final int TYPE_EMPTY = 0x03;
        // 页面加载失败视图
        public static final int TYPE_ERROR = 0x04;
        // 自定义视图
        public static final int TYPE_SPECIFIED = 0x05;
    }
}
