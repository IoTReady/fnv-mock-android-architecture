package com.example.fnvMock.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fnvMock.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<String> crateList;

    public RecyclerViewAdapter(List<String> crateList) {
        this.crateList = crateList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text_view);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_view_item, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(crateList.get(position));
    }

    @Override
    public int getItemCount() {
        return crateList.size();
    }

    public void setData(List<String> crateList) {
        this.crateList = crateList;
    }

    public void addData(String newData) {
        List<String> tempList = new ArrayList<>(crateList);
        tempList.add(newData);
        crateList = tempList;
        notifyDataSetChanged();
    }
}
