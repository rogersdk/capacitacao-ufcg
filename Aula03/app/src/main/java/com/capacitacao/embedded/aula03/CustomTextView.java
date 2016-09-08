
package com.capacitacao.embedded.aula03;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by rogerio on 08/09/16.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/roboto/Roboto-Thin.ttf"));
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/roboto/Roboto-Thin.ttf"));
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/roboto/Roboto-Thin.ttf"));
    }
}
