package hzkj.cc.timeline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import hzkj.cc.timeline2.TimeLine;
import hzkj.cc.timeline2.TimeLineLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimeLineLayout timeLine = findViewById(R.id.time);
        List<List<String>> datas = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings.add("申请时间:2019-03-01");
        strings.add("审批人:刘翔");
        strings.add("审批意见:通过");
        strings.add("备注:拒绝");
//        strings.add("备注:无敌盛大举行违警行为京二胡我和我哈");
        List<String> strings1 = new ArrayList<>();
        strings1.add("申请时间:2019-01-01");
        strings1.add("审批人:陈诚");
        strings1.add("审批意见:流转");
        strings1.add("备注:允许");
        datas.add(strings);
        datas.add(strings1);
        datas.add(strings1);
        datas.add(strings1);
        timeLine.setData(datas);
    }
}
