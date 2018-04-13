package com.example.class_card.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.class_card.R;

import java.util.Timer;
import java.util.TimerTask;

//Created by Jim on 2018/4/13.自动循环竖直滚动播放文字。
public class ScrollTextView extends ScrollView {
    public static String text;//TextView文字输入
    private int num;//初始值
    private TextView mTextView;
    private int scrollDelay = 50;//定时频率
    private int scrollDistance = 1;//滚动距离


    Timer timer = new Timer();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        initView();
    }

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private void initView() {

        /**
         @param activity_textview   新建一个自己的texView
         *
         */
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.activity_textview, this);
        mTextView = inflate.findViewById(R.id.tv_textview);
        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        mTextView.setText(getText());
        this.setVerticalScrollBarEnabled(false);//隐藏滚动条
        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //点击事件的拦截
                return true;
            }
        });
        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //点击事件的拦截
                return true;
            }
        });

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                start();
            }
        }, 0, scrollDelay);
    }


    /**
     * 开始滚动
     *
     * @param
     */
    public void start() {
        num += scrollDistance;
        int off = mTextView.getMeasuredHeight() - this.getHeight();
        if (num >= off) {
            num = 0;
            mTextView.scrollTo(0, 0);
        }
        if (off > 0) {
            mTextView.scrollTo(0, num);
        }
    }

    /*
    取消定时器，在onDestroy的时候执行
     */
    public void exit() {
        timer.cancel();

    }


}
