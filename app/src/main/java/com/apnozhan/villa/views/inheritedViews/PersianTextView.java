package com.apnozhan.villa.views.inheritedViews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.apnozhan.villa.R;
import com.apnozhan.villa.views.utils.FormatHelper;

/**
 * Created by Y50-70 on 1/22/2018.
 */
public class PersianTextView extends TextView {
    public PersianTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            setTextColor(getResources().getColor(R.color.color_text));
            setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_normal));
        }

    }

    public PersianTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            if (attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textColor") == null) {
                setTextColor(getResources().getColor(R.color.color_text));
            }
            if (attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textSize") == null) {
                setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_normal));
            }
        }
    }

    public PersianTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            if (attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textColor") == null) {
                setTextColor(getResources().getColor(R.color.color_text));
            }
            if (attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textSize") == null) {
                setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_normal));
            }
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text != null)
            text = FormatHelper.toPersianNumber(text.toString());
        super.setText(text, type);

    }
}