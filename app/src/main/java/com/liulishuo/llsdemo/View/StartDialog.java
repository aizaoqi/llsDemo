package com.liulishuo.llsdemo.View;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.liulishuo.llsdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by twer on 2/13/15.
 */
public class StartDialog extends Dialog {

    @InjectView(R.id.splash_iv)
    ImageView splash_imageView;

    @InjectView(R.id.countdown_num_text)
    TextView num_textView;

    private int index = 0;

    public StartDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_start);
        ButterKnife.inject(this);
        splash_imageView.startAnimation(shakeAnimation(3));
    }

    public Animation shakeAnimation(int CycleTimes) {

        final Animation.AnimationListener animationListener = new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                num_textView.setText(String.valueOf(animation.getRepeatCount() - index));
                index++;
            }
        };

        Animation scaleAnimation = new ScaleAnimation(0.95f, 1.0f, 0.95f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatCount(3);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }
}
