package hzkj.cc.timeline2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;

public class TimeLineLayout extends ScrollView {
    TimeLine timeLine;

    public TimeLineLayout(Context context) {
        super(context);
    }

    public TimeLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        timeLine = new TimeLine(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        timeLine.setLayoutParams(params);
        linearLayout.addView(timeLine);
        this.addView(linearLayout);
    }

    public void setData(List<List<String>> datas) {
        timeLine.setData(datas);
    }
}
