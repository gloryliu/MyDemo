package com.liu.demo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liu.glory.rollview.R;

/**
 * Created by liu.zhenrong on 2016/7/28.
 */
public class RollView extends RelativeLayout {
    private final String TAG = RollView.class.getName();
    private final static int SHOW = 12;
    private final static int HIDE = 13;
    private final static int SHOWTIME = 2000;
    private final static int HIDETIME = 400;
    private BaseAdapter adapter;
    private int position = 0;//当前位置
    private Context mContext;
    private int parentheight;//高度
    private float childY;
    private float parentY;
    private float oldChildY;
    private View contextView;
    private TextView tv_text1, tv_text2;
    private LinearLayout ll_item;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HIDE:
                    hideViewAnimation(ll_item);
                    handler.sendEmptyMessageDelayed(SHOW, HIDETIME);
                    break;
                case SHOW:
                    showViewAnimation(ll_item);
                    handler.sendEmptyMessageDelayed(HIDE, SHOWTIME);
                    break;
            }
        }
    };

    public RollView(Context context) {
        super(context);
        init(context, null);
    }

    public RollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public RollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        contextView = View.inflate(context, R.layout.rollview, null);
        tv_text1 = (TextView) contextView.findViewById(R.id.tv_text1);
        tv_text2 = (TextView) contextView.findViewById(R.id.tv_text2);
        ll_item = (LinearLayout) contextView.findViewById(R.id.ll_item);
        addView(contextView);
    }


    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        if(adapter!=null){
            if(adapter.getCount()>0){
                handler.sendEmptyMessageAtTime(HIDE, SHOWTIME);
            }
        }else{
            throw new IllegalArgumentException("adapter is null");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        parentheight = h;
        parentY = getY();
        Log.e(TAG, "height=" + parentheight);
        Log.e(TAG, "parentY=" + parentY);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        if (childCount > 0) {
            childY = getChildAt(0).getY();
            Log.e(TAG, "viewtop=" + getChildAt(0).getTop());
        }
        Log.e(TAG, "paddingtop=" + getPaddingTop());
    }

    private void showViewAnimation(final View view) {
        if (view != null) {
            if (adapter != null) {
                LinearLayout layout = (LinearLayout) view;
                layout.removeAllViews();
                if (position >= adapter.getCount()) {
                    position = 0;
                }
                layout.addView(adapter.getView(position, null, null));
                position++;
            } else {
                throw new IllegalArgumentException("adapter is null");
            }
        }
        childY = parentY + (parentheight - view.getMeasuredHeight()) / 2;
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", getY() + parentheight, oldChildY);
        animator.setDuration(500);
        animator.start();

    }

    private void hideViewAnimation(final View view) {
        childY = parentY + (parentheight - view.getMeasuredHeight()) / 2;
        oldChildY = tv_text1.getY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", view.getY(), -(getY() + view.getMeasuredHeight()));
        animator.setDuration(500);
        animator.start();
    }

}
