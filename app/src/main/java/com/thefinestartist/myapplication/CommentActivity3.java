package com.thefinestartist.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.nineoldandroids.view.ViewHelper;
import com.thefinestartist.converters.Unit;
import com.thefinestartist.utils.log.L;
import com.thefinestartist.utils.ui.DisplayUtil;

/**
 * Created by TheFinestArtist on 3/21/16.
 */
public class CommentActivity3 extends AppCompatActivity implements SoftInputModeHelper3.SoftKeyboardListener {

    FrameLayout layout;
    FrameLayout mainView;
    EditText editText;
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
        editText = (EditText) findViewById(R.id.editText);

        mainView.setMinimumHeight(mainViewHeight);
        mainView.setLayoutParams(new ScrollView.LayoutParams(DisplayUtil.getWidth(), mainViewHeight));

        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!globalLayoutConsumed) {
                    ViewHelper.setTranslationY(editText, mainViewHeight - editText.getHeight());
                    globalLayoutConsumed = !globalLayoutConsumed;
                }
            }
        });
        SoftInputModeHelper3.assistActivity(this, this);
    }

    @Override
    public void onStateChanged(boolean showing, int keyboardHeight) {
        if (showing) {
            mainView.setMinimumHeight(mainViewHeight - keyboardHeight);
            mainView.setLayoutParams(new ScrollView.LayoutParams(DisplayUtil.getWidth(), mainViewHeight - keyboardHeight));
            ViewHelper.setTranslationY(editText, mainViewHeight - editText.getHeight() - keyboardHeight);
        } else {
            mainView.setMinimumHeight(mainViewHeight);
            mainView.setLayoutParams(new ScrollView.LayoutParams(DisplayUtil.getWidth(), mainViewHeight));
            ViewHelper.setTranslationY(editText, mainViewHeight - editText.getHeight());
        }
    }
}
