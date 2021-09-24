package com.nikitha.ferofly_task;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

class TimeLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TimeLineModel> timeLineModelList;
    private Context context;

    TimeLineAdapter(Context context, List<TimeLineModel> timeLineModelList) {
        this.timeLineModelList = timeLineModelList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline_horizontal, parent, false);
        return new ViewHolder(view, viewType);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).textViewDescription.setText(timeLineModelList.get(position).getDescription());

        if (timeLineModelList.get(position).getStatus().equals("inactive"))
            ((ViewHolder) holder).timelineView.setMarker(AppCompatResources.getDrawable(context, R.drawable.ic_baseline_remove_circle_24));
        else {
            ((ViewHolder) holder).timelineView.setMarker(AppCompatResources.getDrawable(context, R.drawable.ic_baseline_check_circle_24));
            ((ViewHolder) holder).timelineView.setEndLineColor(R.color.purple_200, 0);
            ((ViewHolder) holder).timelineView.setStartLineColor(R.color.purple_200, 0);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return timeLineModelList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TimelineView timelineView;
        TextView textViewDescription;

        ViewHolder(View itemView, int viewType) {
            super(itemView);
            timelineView = itemView.findViewById(R.id.timeline);
            textViewDescription = itemView.findViewById(R.id.text_timeline_title);
            timelineView.initLine(viewType);
        }
    }}