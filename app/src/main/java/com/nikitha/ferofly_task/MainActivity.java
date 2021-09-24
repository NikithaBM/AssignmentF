package com.nikitha.ferofly_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String[] status = {"active", "inactive", "inactive", "inactive", "inactive"};
    String[] description = {"Cooking","Picked","On the way", "Delivered", "Done"};

    List<TimeLineModel> timeLineModelList;
    TimeLineModel[] timeLineModel;
    Context context;
    LinearLayoutManager linearLayoutManager;
    TimeLineAdapter adapter;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnReport = findViewById(R.id.btnReport);
        btnReport.setEnabled(false);

        timeLineModelList = new ArrayList<>();
        timeLineModel = new TimeLineModel[5];
        context = MainActivity.this;
        linearLayoutManager = new LinearLayoutManager(this);

        for (int i = 0; i < 5; i++) {
            timeLineModel[i] = new TimeLineModel();
            timeLineModel[i].setStatus(status[i]);
            timeLineModel[i].setDescription(description[i]);
            timeLineModelList.add(timeLineModel[i]);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);  //for divider
        adapter = new TimeLineAdapter(context, timeLineModelList);
        recyclerView.setAdapter(adapter);

        new CountDownTimer(25000, 5000) {
            public void onFinish() {

            }
            public void onTick(long millisUntilFinished){
                changetimeline();
                counter++;
            }
        }.start();

    }

    public void trackOrder(View view){
        Uri gmmIntentUri = Uri.parse("geo:28.67522781142378,77.13424556285861");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void changetimeline()
    {
            timeLineModel[counter].setStatus("active");
            adapter.notifyDataSetChanged();
    }
}