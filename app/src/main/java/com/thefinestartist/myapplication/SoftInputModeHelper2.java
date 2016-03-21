package com.thefinestartist.myapplication;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.thefinestartist.utils.log.L;
import com.thefinestartist.utils.ui.DisplayUtil;

/**
 * Created by TheFinestArtist on 9/24/15.
 */
public class SoftInputModeHelper2 {
    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistActivity(Activity activity, SoftKeyboardListener listener) {
        new SoftInputModeHelper2(activity, listener);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams layoutParams;

    private SoftInputModeHelper2(final Activity activity, SoftKeyboardListener listener) {
        FrameLayout content = (FrameLayout) activity.findViewById(R.id.layout);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        layoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();

        this.listener = listener;
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = DisplayUtil.getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                if (listener != null)
                    listener.onStateChanged(true, heightDifference);
            } else {
                // keyboard probably just became hidden
                if (listener != null)
                    listener.onStateChanged(false, heightDifference);
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }

    private SoftKeyboardListener listener;

    public interface SoftKeyboardListener {
        void onStateChanged(boolean showing, int keyboardHeight);
    }

}