package com.schneewittchen.rosandroid.widgets.buttonSubscriber;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.views.widgets.SubscriberWidgetView;
import com.schneewittchen.rosandroid.widgets.button.ButtonEntity;

public class ButtonSubscriberView extends SubscriberWidgetView{
    public static final String TAG = ButtonSubscriberView.class.getSimpleName();

    Paint buttonPaint;
    TextPaint textPaint;
    StaticLayout staticLayout;
    private float rotation;
    private CharSequence text;

    public ButtonSubscriberView(Context context) {
        super(context);
        init();
    }


    public ButtonSubscriberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        buttonPaint = new Paint();
        buttonPaint.setColor(getResources().getColor(R.color.colorPrimary));
        buttonPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(26 * getResources().getDisplayMetrics().density);
    }

    private void changeState(boolean pressed) {
        //this.publishViewData(new ButtonData(pressed));
        //invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.editMode) {
            return super.onTouchEvent(event);
        }
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                // Si el valor de la fuerza en Z es seguro:
                buttonPaint.setColor(getResources().getColor(R.color.ok_green));
                changeState(false);
                // Y si el valor de la z está en un rango de valores peligroso:
                //buttonPaint.setColor(getResources().getColor(R.color.color_attention));
                // Y si el valor de z es dañino para el cuerpo:
                //buttonPaint.setColor(getResources().getColor(R.color.delete_red));
                break;

            case MotionEvent.ACTION_DOWN:
                //buttonPaint.setColor(getResources().getColor(R.color.color_attention));
                changeState(true);
                break;

            default:
                return false;
        }

        return true;
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float textLayoutWidth = width;

        ButtonSubscriberEntity entity = (ButtonSubscriberEntity) widgetEntity;


        if (entity.rotation == 90 || entity.rotation == 270) {
            textLayoutWidth = height;
        }

        canvas.drawRect(new Rect(0, 0, (int) width, (int) height), buttonPaint);

        staticLayout = new StaticLayout(entity.text,
                textPaint,
                (int) textLayoutWidth,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0,
                false);
        canvas.save();
        canvas.rotate(entity.rotation, width / 2, height / 2);
        canvas.translate(((width / 2) - staticLayout.getWidth() / 2), height / 2 - staticLayout.getHeight() / 2);
        staticLayout.draw(canvas);
        canvas.restore();
    }


}
