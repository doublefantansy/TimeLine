package hzkj.cc.timeline2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import java.util.List;
import java.util.Map;

public class TimeLine extends View {
    float firstOutRadius;
    float firstInRadius;
    int lineHeight;
    float otherRadius;
    private Paint firstItemInPaint;
    private Paint firstItemOutPaint;
    private Paint linePaint;
    private Paint otherItemPaint;
    private TextPaint textPaint;
    int firstTextMargin = 25;
    int otherTextMargin = 50;
    int textLineSpace = 10;
    int signLocateWidth;
    int topPadding = 20;
    int textRightMargin = 60;
    List<List<String>> data;
    int sumTextHeight = 0;
    float sumBefore = 0;
    int count = 0;

    public TimeLine(Context context) {
        super(context);
        init();
    }

    public TimeLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        signLocateWidth = getMeasuredWidth() / 4;
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), getContentHeight());
    }

    int getContentHeight() {
        int sum = 0;
        int temp = 0;
        for (int i = 0; i < data.size(); i++) {
            temp = 0;
            for (int j = 0; j < data.get(i)
                    .size(); j++) {
                Rect rect = new Rect();
                textPaint.getTextBounds(data.get(i)
                        .get(j), 0, data.get(i)
                        .get(j)
                        .length(), rect);
//                temp = temp + rect.height() + textLineSpace;
                int subIndex = textPaint.breakText(data.get(i)
                        .get(j), 0, data.get(i)
                        .get(j)
                        .length(), true, getMeasuredWidth() - signLocateWidth - 2 * textRightMargin, null);
                String mytext = data.get(i)
                        .get(j)
                        .substring(0, subIndex);
                String remain = data.get(i)
                        .get(j)
                        .substring(subIndex);
                if (mytext.equals(data.get(i)
                        .get(j))) {
                    temp = temp + rect.height() + textLineSpace;
                } else {
                    temp = temp + (rect.height() + textLineSpace) * 2;
//                    canvas.drawText(mytext, signLocateWidth + textRightMargin, topPadding + temp + sumBefore + rect.height() / 2 + j * (rect.height() + textLineSpace), textPaint);
//                    canvas.drawText(remain, signLocateWidth + textRightMargin, topPadding + temp + sumBefore + rect.height() / 2 + (j + 1) * (rect.height() + textLineSpace), textPaint);
                }
//                Log.d("ccnb", getMeasuredWidth() * 3 / 4 + "");
            }
            int textMargin = i == 0 ? firstTextMargin : otherTextMargin;
            sum += textMargin + temp;
        }
        sum = (int) (sum + topPadding + 2 * firstOutRadius + 2 * otherRadius);
        return sum;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(signLocateWidth, topPadding + firstOutRadius,
                firstOutRadius, firstItemOutPaint);
        canvas.drawCircle(signLocateWidth, topPadding + firstOutRadius,
                firstInRadius, firstItemInPaint);
        sumBefore = 2 * firstOutRadius;
        int temp = 0;
        for (int i = 0; i < data.size(); i++) {
            count = 0;
            sumTextHeight = 0;
            for (int j = 0; j < data.get(i)
                    .size(); j++) {
                Rect rect = new Rect();
                textPaint.getTextBounds(data.get(i)
                        .get(j), 0, data.get(i)
                        .get(j)
                        .length(), rect);
                temp = i == 0 ? firstTextMargin : otherTextMargin;
                int subIndex = textPaint.breakText(data.get(i)
                        .get(j), 0, data.get(i)
                        .get(j)
                        .length(), true, getMeasuredWidth() - signLocateWidth - 2 * textRightMargin, null);
                String mytext = data.get(i)
                        .get(j)
                        .substring(0, subIndex);
                String remain = data.get(i)
                        .get(j)
                        .substring(subIndex);
                Log.d("ccnb", remain + "");
                if (mytext.equals(data.get(i)
                        .get(j))) {
                    sumTextHeight = sumTextHeight + rect.height() + textLineSpace;
                    canvas.drawText(data.get(i)
                            .get(j), signLocateWidth + textRightMargin, topPadding + temp + sumBefore + rect.height() / 2 + (j + count) * (rect.height() + textLineSpace), textPaint);
                } else {
                    sumTextHeight = sumTextHeight + (rect.height() + textLineSpace) * 2;
                    canvas.drawText(mytext, signLocateWidth + textRightMargin, topPadding + temp + sumBefore + rect.height() / 2 + (j + count) * (rect.height() + textLineSpace), textPaint);
                    canvas.drawText(remain, signLocateWidth + textRightMargin, topPadding + temp + sumBefore + rect.height() / 2 + (j + count + 1) * (rect.height() + textLineSpace), textPaint);
                    count++;
                }
            }
            canvas.drawLine(signLocateWidth, topPadding + sumBefore, signLocateWidth, topPadding + temp + sumBefore + sumTextHeight, linePaint);
            canvas.drawCircle(signLocateWidth, topPadding + sumBefore + sumTextHeight + temp, otherRadius, otherItemPaint);
            sumBefore += temp + sumTextHeight;
        }
    }

    private void init() {
        firstItemInPaint = new Paint();
        firstItemOutPaint = new Paint();
        linePaint = new Paint();
        textPaint = new TextPaint();
        otherItemPaint = new Paint();
        firstItemInPaint.setStyle(Paint.Style.FILL);
        firstItemInPaint.setAntiAlias(true);
        firstItemInPaint.setColor(getResources().getColor(R.color.green));
        firstItemOutPaint.setColor(getResources().getColor(R.color.outColor));
        firstItemOutPaint.setAntiAlias(true);
        linePaint.setColor(Color.GRAY);
        linePaint.setStrokeWidth(7);
        linePaint.setAntiAlias(true);
        otherItemPaint.setColor(Color.GRAY);
        otherItemPaint.setAntiAlias(true);
        textPaint.setTextSize(34);
        textPaint.setAntiAlias(true);
        firstOutRadius = 40;
        firstInRadius = 30;
        otherRadius = 20;
        lineHeight = 310;
    }
}
