package com.thefinestartist.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.nineoldandroids.view.ViewHelper;
import com.thefinestartist.converters.Unit;
import com.thefinestartist.utils.log.L;
import com.thefinestartist.utils.ui.DisplayUtil;

/**
 * Created by TheFinestArtist on 3/21/16.
 */
public class CommentActivity3 extends AppCompatActivity implements CustomEditText.OnKeyboardListener, View.OnClickListener, SoftInputModeHelper3.SoftKeyboardListener {

    FrameLayout layout;
    FrameLayout mainView;
    FrameLayout editView;
    CustomEditText customEditText;
    boolean globalLayoutConsumed = false;

    int mainViewHeight = DisplayUtil.getHeight() - Unit.dpToPx(56);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (FrameLayout) findViewById(R.id.layout);
        mainView = (FrameLayout) findViewById(R.id.mainView);
        editView = (FrameLayout) findViewById(R.id.editView);
        customEditText = (CustomEditText) findViewById(R.id.editText);

        L.e("mainViewHeight: " + mainViewHeight);
        mainView.setMinimumHeight(mainViewHeight);
        mainView.setLayoutParams(new ScrollView.LayoutParams(DisplayUtil.getWidth(), mainViewHeight));
        editView.setLayoutParams(new FrameLayout.LayoutParams(DisplayUtil.getWidth(), mainViewHeight));

        customEditText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                L.showThreadInfo(true).e("onGlobalLayout");
                if (!globalLayoutConsumed) {
                    ViewHelper.setTranslationY(customEditText, mainViewHeight - customEditText.getHeight());
                    globalLayoutConsumed = !globalLayoutConsumed;
                }
            }
        });

        customEditText.setOnKeyboardListener(this);
        customEditText.setOnClickListener(this);
        SoftInputModeHelper3.assistActivity(this, this);
    }

    @Override
    public void onStateChanged(boolean showing, int keyboardHeight) {
        L.e("mainView: " + mainView.getHeight());
        L.e("customEditText: " + customEditText.getHeight());
        L.e("onStateChanged:" + showing + ", keyboardHeight:" + keyboardHeight);
        if (showing) {
            int mainViewHeight = DisplayUtil.getHeight() - Unit.dpToPx(56);
            L.e("mainViewHeight: " + mainViewHeight);
            mainView.setMinimumHeight(mainViewHeight - keyboardHeight);
            mainView.setLayoutParams(new ScrollView.LayoutParams(DisplayUtil.getWidth(), mainViewHeight - keyboardHeight));
            editView.setLayoutParams(new FrameLayout.LayoutParams(DisplayUtil.getWidth(), mainViewHeight - keyboardHeight));
            ViewHelper.setTranslationY(customEditText, mainViewHeight - customEditText.getHeight() - keyboardHeight);
        } else {
            mainView.setMinimumHeight(mainViewHeight);
            mainView.setLayoutParams(new ScrollView.LayoutParams(DisplayUtil.getWidth(), mainViewHeight));
            editView.setLayoutParams(new FrameLayout.LayoutParams(DisplayUtil.getWidth(), mainViewHeight));
            ViewHelper.setTranslationY(customEditText, mainViewHeight - customEditText.getHeight());
        }
    }

    @Override
    public void onKeyboardHidden() {
        L.e("onKeyboardHidden");
    }

    @Override
    public void onClick(View v) {
        L.e("onClick");
    }
}
