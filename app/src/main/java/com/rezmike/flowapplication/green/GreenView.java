package com.rezmike.flowapplication.green;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.rezmike.flowapplication.R;
import com.rezmike.flowapplication.utils.DaggerService;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenView extends RelativeLayout {

    @Inject
    GreenScreen.GreenPresenter mPresenter;

    public GreenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            DaggerService.<GreenScreen.Component>getDaggerComponent(context).inject(this);
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
