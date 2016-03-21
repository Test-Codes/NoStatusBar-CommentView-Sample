package com.thefinestartist.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.thefinestartist.utils.log.L;

/**
 * Created by TheFinestArtist on 3/21/16.
 */
public class CommentActivity2 extends AppCompatActivity implements CustomEditText.OnKeyboardListener, View.OnClickListener, SoftInputModeHelper2.SoftKeyboardListener {

    FrameLayout layout;
    CustomEditText customEditText;
    View keyboardBelow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (FrameLayout) findViewById(R.id.layout);
        customEditText = (CustomEditText) findViewById(R.id.editText);
        keyboardBelow = findViewById(R.id.keyboardBelow);

        customEditText.setOnKeyboardListener(this);
        customEditText.setOnClickListener(this);
        SoftInputModeHelper2.assistActivity(this, this);
    }

    @Override
    public void onStateChanged(boolean showing, int keyboardHeight) {
        L.e("onStateChanged:" + showing + ", keyboardHeight:" + keyboardHeight);
        if (showing) {
            keyboardBelow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, keyboardHeight));
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
    }

    @Override
    public void onKeyboardHidden() {
        L.e("onKeyboardHidden");
        keyboardBelow.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        L.e("onClick");
        keyboardBelow.setVisibility(View.VISIBLE);
    }
}
