package com.e7er.learn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    TextView show;
    View back, next;
    static List<String> arrShow = new ArrayList<>();
    static int position = 1;
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = findViewById(R.id.main_show);
        back = findViewById(R.id.main_back);
        next = findViewById(R.id.main_next);

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        back.setOnLongClickListener(this);
        next.setOnLongClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        arrShow = Arrays.asList(this.getResources().getStringArray(R.array.chinese));
        Collections.shuffle(arrShow);
        show(++position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_back:
                show(--position);
                break;
            case R.id.main_next:
                count++;
                show(++position);
                break;
        }
    }

    private void show(int pointer) {
        if (0 != count && 0 == count % 10) {
            show.setText("good!");
            return;
        }
        int p = (pointer + arrShow.size()) % arrShow.size();
        show.setText(arrShow.get(p));
    }

    /**
     * 长按切换显示内容
     *
     * @param view
     * @return
     */
    @Override
    public boolean onLongClick(View view) {
        switchArray();
        return false;
    }

    /**
     * 切换数组
     */
    private void switchArray() {
        count = 0;
        String[] chinese = this.getResources().getStringArray(R.array.chinese);
        String[] phonetic = this.getResources().getStringArray(R.array.phonetic);
        if (arrShow.size() == chinese.length) {
            arrShow = Arrays.asList(phonetic);
        } else {
            arrShow = Arrays.asList(chinese);
        }
        Collections.shuffle(arrShow);
    }
}