package com.example.jina.draw;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private DrawTouchView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mView = new DrawTouchView(this);
        setContentView(mView);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {}

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public class DrawTouchView extends View {
        Paint fillPaint = new Paint();

        RectF brightRectf;
        RectF volumeRectf;

        public DrawTouchView(Context context) {
            super(context);
            init();
        }

        public DrawTouchView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public DrawTouchView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        private void init() {
            Point mSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(mSize);

            float width = mSize.x;
            float height = mSize.y - convertDpToPixel(30, getApplicationContext());

            brightRectf = new RectF(0, 0, width/5, height);
            volumeRectf = new RectF(width/5*4, 0, width, height);

            // fill
            fillPaint.setStyle(Paint.Style.FILL);
            fillPaint.setColor(Color.YELLOW);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // First rectangle
            canvas.drawRect(brightRectf, fillPaint);    // fill
            canvas.drawRect(volumeRectf, fillPaint);    // fill
        }
    }
}
