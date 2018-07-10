package com.codificador.stopwatch;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.codificador.stopwatch.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    String[] ListElements = new String[] {  };
    List<String> ListElementsArrayList ;
    ArrayAdapter<String> adapter ;
    StopWatchUtil stopWatchUtil;
    ActivityMainBinding binding;
    TimerValue timerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        timerValue = new TimerValue();
        binding.setEvent(this);
        binding.setData(timerValue);
        initComponents();
    }

    private void initComponents(){
        stopWatchUtil = new StopWatchUtil(timerValue);
        ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));
        adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                ListElementsArrayList
        );
        binding.listView.setAdapter(adapter);
    }

    public void start(){
        stopWatchUtil.start();
        binding.buttonReset.setEnabled(false);
    }

    public void pause(){
        stopWatchUtil.pause();
        binding.buttonReset.setEnabled(true);
    }

    public void reset(){
        stopWatchUtil.reset();
        timerValue.setValue("00:00:00:00");
        ListElementsArrayList.clear();
        adapter.notifyDataSetChanged();
    }

    public void lap(){
        ListElementsArrayList.add(timerValue.getValue());
        adapter.notifyDataSetChanged();
    }
}