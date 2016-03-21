package com.thefinestartist.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.thefinestartist.converters.Unit;
import com.thefinestartist.utils.log.L;
import com.thefinestartist.utils.ui.DisplayUtil;

/**
 * Created by TheFinestArtist on 3/21/16.
 */
public class CommentActivity3 extends AppCompatActivity implements CustomEditText.OnKeyboardListener, View.OnClickListener, SoftInputModeHelper3.SoftKeyboardListener {

    FrameLayout layout;
    ScrollView scrollView;
    CustomEditText customEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (FrameLayout) findViewById(R.id.layout);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        customEditText = (CustomEditText) findViewById(R.id.editText);

        customEditText.setOnKeyboardListener(this);
        customEditText.setOnClickListener(this);
        SoftInputModeHelper3.assistActivity(this, this);

        int scrollViewHeight = DisplayUtil.getHeight() - Unit.dpToPx(56) - Unit.dpToPx(40);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, scrollViewHeight));
    }

    @Override
    public void onStateChanged(boolean showing, int keyboardHeight) {
        L.e("onStateChanged:" + showing + ", keyboardHeight:" + keyboardHeight);
        if (showing) {
            int scrollViewHeight = DisplayUtil.getHeight() - Unit.dpToPx(56) - Unit.dpToPx(40) - keyboardHeight;
            scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, scrollViewHeight));
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
