package com.wcx.filterView.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wcx.filterView.R;
import com.wcx.filterView.adapter.FilterListViewAdapter;
import com.wcx.filterView.bean.FiltrateBean;

import java.util.ArrayList;
import java.util.List;

public class FilterGridView extends LinearLayout {
    private Context context;
    private List<FiltrateBean> dictList = new ArrayList<>();
    private FilterListViewAdapter adapter;
    private TextView tvTitle;
    private View topView, bottomView;
    private ListView mListView;
    private TextView tvReset, tvConfirm;
    private OnConfirmClickListener onConfirmClickListener;
    private OnRadioClickListener onRadioClickListener;
    private OnCheckedTextChangeClickListener onCheckedTextChangeClickListener;

    public FilterGridView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }


    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_filter, null);
        tvTitle = view.findViewById(R.id.popupTitle);
        topView = view.findViewById(R.id.topView);
        bottomView = view.findViewById(R.id.bottomView);
        mListView = view.findViewById(R.id.listview);
        tvReset = view.findViewById(R.id.tv_reset);
        tvConfirm = view.findViewById(R.id.tv_confirm);
        adapter = new FilterListViewAdapter(context, dictList);
        mListView.setAdapter(adapter);
        initListeners();
        addView(view);
    }

    public void setDatas(List<FiltrateBean> datas) {
        dictList.addAll(datas);
        Log.d("================", "数据：" + datas);
        adapter.notifyDataSetChanged();
    }

    private void initListeners() {
        adapter.setCheckedTextChangeListener(new FilterListViewAdapter.OnCheckedTextChangeListener() {
            @Override
            public void onTextChange(String text, boolean checked) {
                onCheckedTextChangeClickListener.onCheckedClick(text, checked);
            }
        });
        adapter.setArrowListener(new FilterListViewAdapter.ArrowClickListener() {
            @Override
            public void onArrowClick(String content) {
                onRadioClickListener.onRadioClick(content);
            }
        });
        //重置
        tvReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int x = 0; x < dictList.size(); x++) {
                    List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
                    for (int y = 0; y < childrenBeen.size(); y++) {
                        if (childrenBeen.get(y).isSelected())
                            childrenBeen.get(y).setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        //确定
        tvConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = new ArrayList<>();
                List<Integer> ids = new ArrayList<>();
                for (FiltrateBean fb : dictList) {
                    List<FiltrateBean.Children> cdList = fb.getChildren();
                    for (int x = 0; x < cdList.size(); x++) {
                        FiltrateBean.Children children = cdList.get(x);
                        if (children.isSelected()) {
                            list.add(children.getValue());
                            ids.add(children.getId());
                        }
                    }
                }
                onConfirmClickListener.onConfirmClick(list, ids);
            }
        });
    }

    //设置筛选的标题
    public FilterGridView setTitle(String title, int textColor, int size) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        tvTitle.setTextColor(textColor);
        tvTitle.setTextSize(size);
        return this;
    }

    //设置顶部分割线是否显示，以及颜色。默认true,#f3f3f3
    public FilterGridView setTopView(Boolean isShow, int color) {
        if (isShow) {
            topView.setVisibility(View.VISIBLE);
        } else {
            topView.setVisibility(View.GONE);
        }
        topView.setBackgroundColor(color);
        return this;
    }

    //设置底部分割线是否显示，以及颜色。默认true,#f3f3f3
    public FilterGridView setBottomView(Boolean isShow, int color) {
        if (isShow) {
            bottomView.setVisibility(View.VISIBLE);
        } else {
            bottomView.setVisibility(View.GONE);
        }
        bottomView.setBackgroundColor(color);
        return this;
    }

    //设置确定按钮的文字，字体大小，字体颜色，背景颜色。默认“确定”，14，#ffffff，#0aa666，
    public FilterGridView setConfirm(String text, int size, int textColor, int color) {
        tvConfirm.setText(text);
        tvConfirm.setTextSize(size);
        tvConfirm.setTextColor(textColor);
        tvConfirm.setBackgroundColor(color);
        return this;
    }

    //设置重置按钮的文字，字体大小，字体颜色，背景颜色。默认“重置”，#000000，#ffffff
    public FilterGridView setReset(String text, int size, int textColor, int color) {
        tvReset.setText(text);
        tvReset.setTextSize(size);
        tvReset.setTextColor(textColor);
        tvReset.setBackgroundColor(color);
        return this;
    }

    //设置分类title是否显示
    public FilterGridView hideTitle(boolean isShow) {
        adapter.setHideTitle(isShow);
        return this;
    }

    //设置分类title的字体颜色,默认#000000
    public FilterGridView setTitleColor(int color) {
        adapter.setTitleColor(color);
        return this;
    }

    //设置分类title的字体大小,默认14
    public FilterGridView setTitleSize(int size) {
        adapter.setTitleSize(size);
        return this;
    }

    //设置item圆角大小，默认12
    public FilterGridView setRadius(int radius) {
        adapter.setRadius(radius);
        return this;
    }

    //设置item边框粗细，默认2
    public FilterGridView setStrokeWidth(int width) {
        adapter.setStrokeWidth(width);
        return this;
    }

    //设置item边框颜色，默认#0aa666
    public FilterGridView setStrokeColor(int color) {
        adapter.setStrokeColor(color);
        return this;
    }

    //设置item宽度，默认是200dp
    public FilterGridView setBoxWidth(int width) {
        adapter.setBoxWidth(width);
        return this;
    }

    //设置item高度，默认是WRAP_CONTENT
    public FilterGridView setBoxHeight(int height) {
        adapter.setBoxHeight(height);
        return this;
    }

    //设置item选中时的颜色，默认#0aa666
    public FilterGridView setChecked(String color) {
        adapter.setChecked(color);
        return this;
    }

    //设置item未选中时的颜色，默认#000000
    public FilterGridView setEnabled(String color) {
        adapter.setEnabled(color);
        return this;
    }

    //设置item字体大小，默认13
    public FilterGridView setBoxSize(int size) {
        adapter.setBoxSize(size);
        return this;
    }

    //设置是否开启单选，默认单选
    public FilterGridView setSingle(boolean isSingle) {
        adapter.setSingle(isSingle);
        return this;
    }

    //设置单选GNN模式
    public FilterGridView hideRadioButton(boolean isGnn) {
        if (isGnn) {
            tvConfirm.setVisibility(View.GONE);
            tvReset.setVisibility(View.GONE);
        } else {
            tvConfirm.setVisibility(View.VISIBLE);
            tvReset.setVisibility(View.VISIBLE);
        }
        adapter.setHideRadio(isGnn);
        return this;
    }

    //显示控件时数据重置
    public FilterGridView reset() {
        for (int x = 0; x < dictList.size(); x++) {
            List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
            for (int y = 0; y < childrenBeen.size(); y++) {
                if (childrenBeen.get(y).isSelected())
                    childrenBeen.get(y).setSelected(false);
            }
        }
        return this;
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    public interface OnConfirmClickListener {
        void onConfirmClick(List<String> textList, List<Integer> ids);
    }

    public void setOnRadioClickListener(OnRadioClickListener onRadioClickListener) {
        this.onRadioClickListener = onRadioClickListener;
    }

    public interface OnRadioClickListener {
        void onRadioClick(String text);
    }

    public void setOnCheckedTextChangeClickListener(OnCheckedTextChangeClickListener onCheckedTextChangeClickListener) {
        this.onCheckedTextChangeClickListener = onCheckedTextChangeClickListener;
    }

    public interface OnCheckedTextChangeClickListener {
        void onCheckedClick(String text, boolean checked);
    }
}
