package com.srkakadesir.calendarteachers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srkakadesir.calendarteachers.R;
import com.srkakadesir.calendarteachers.RegionalDataProvider;
import com.srkakadesir.calendarteachers.model.DaysModel;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {
    private Context context;
    private List<DaysModel> dList;

    public HolidayAdapter(Context context, List<DaysModel> dList) {
        this.context = context;
        this.dList = dList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_holiday_item, parent, false);
        return new HolidayAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RegionalDataProvider provider = new RegionalDataProvider(dList.get(position).getDate(),context);
        RegionalDataProvider id_provider = new RegionalDataProvider(position+1,context);
        holder.sr_no.setText(id_provider.getDate());
        holder.date.setText(" "+ provider.getDate() + " / "+ getMonthNumber(dList.get(position).getMonth())+ " / " + context.getResources().getString(R.string.yyyy));
        holder.occassion.setText(dList.get(position).getOccassion());
        holder.day.setText(setDay(position));
    }


    private String getMonthNumber(int id) {
        String required_id = " ";
        switch (id){
            case 1:
                required_id = context.getResources().getString(R.string.mar_1);
                break;
            case 2:
                required_id = context.getResources().getString(R.string.mar_2);
                break;
            case 3:
                required_id = context.getResources().getString(R.string.mar_3);
                break;
            case 4:
                required_id = context.getResources().getString(R.string.mar_4);
                break;
            case 5:
                required_id = context.getResources().getString(R.string.mar_5);
                break;
            case 6:
                required_id = context.getResources().getString(R.string.mar_6);
                break;
            case 7:
                required_id = context.getResources().getString(R.string.mar_7);
                break;
            case 8:
                required_id = context.getResources().getString(R.string.mar_8);
                break;
            case 9:
                required_id = context.getResources().getString(R.string.mar_9);
                break;
            case 10:
                required_id = context.getResources().getString(R.string.mar_10);
                break;
            case 11:
                required_id = context.getResources().getString(R.string.mar_11);
                break;
            case 12:
                required_id = context.getResources().getString(R.string.mar_12);
                break;
        }
        return required_id;
    }

    private String setDay(int position) {
        int mod = (dList.get(position).getId()) % 7;
        String day = "null";
        switch (mod){
            case 0:
                return context.getResources().getString(R.string.monday);

            case 1:
                return context.getResources().getString(R.string.tuesday);

            case 2:
                return context.getResources().getString(R.string.wednesday);

            case 3:
                return context.getResources().getString(R.string.thursday);

            case 4:
                return context.getResources().getString(R.string.friday);

            case 5:
                return context.getResources().getString(R.string.saturday);

            case 6:
                return context.getResources().getString(R.string.sunday);

        }
        return  day;
    }

    @Override
    public int getItemCount() {
        return dList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sr_no,date,day,occassion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sr_no = itemView.findViewById(R.id.sr_no_single_holiday);
            day = itemView.findViewById(R.id.tv_day_single_holiday);
            date = itemView.findViewById(R.id.date_single_holiday);
            occassion = itemView.findViewById(R.id.occassion_single_holiday);


        }
    }
}
