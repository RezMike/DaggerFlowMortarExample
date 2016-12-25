package com.rezmike.flowapplication.red;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.rezmike.flowapplication.R;
import com.rezmike.flowapplication.utils.DaggerService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;

import static android.content.ContentValues.TAG;

public class RedView extends RelativeLayout {

    @Inject
    RedScreen.RedPresenter mPresenter;

    @BindView(R.id.button)
    Button mButton;

    public RedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            DaggerService.<RedScreen.Component>getDaggerComponent(context).inject(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            mPresenter.takeView(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!isInEditMode()) {
            mPresenter.dropView(this);
        }
    }

    //region ======================== Events ========================

    @OnClick(R.id.button)
    void onButtonClick() {
        mPresenter.onButtonClick();
    }

    //endregion
}
