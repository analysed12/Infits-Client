package com.example.infits;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polidea.rxandroidble3.RxBleClient;
import com.polidea.rxandroidble3.RxBleDevice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

import io.reactivex.rxjava3.disposables.Disposable;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceListViewHolder> {

    ArrayList<String> deviceName;
    ArrayList<String> deviceAddress;
    Context context;
    int row_index = -1;
    GetMacInterface getMacInterface;

    DeviceListAdapter(Context context,ArrayList<String> deviceName,ArrayList<String> deviceAddress,GetMacInterface getMacInterface){
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
        this.context = context;
        this.getMacInterface = getMacInterface;
    }

    @NonNull
    @Override
    public DeviceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.bluetooth_device,parent,false);
        return new DeviceListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceListViewHolder holder, int position) {
            holder.name.setText(deviceName.get(position));
            holder.linearLayout.setOnClickListener(v->{
                row_index=position;
                notifyDataSetChanged();
            });
            if (row_index == position){
                holder.selected.setVisibility(View.VISIBLE);
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.outline_black));
                getMacInterface.getMac(deviceAddress.get(row_index));
            }
            else{
                holder.selected.setVisibility(View.GONE);
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.outline));
            }
    }

    @Override
    public int getItemCount() {
        return deviceName.size();
    }

    public class DeviceListViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView selected;
        LinearLayout linearLayout;
//        Button button;
        public DeviceListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.deviceName);
            selected = itemView.findViewById(R.id.selected);
            linearLayout = itemView.findViewById(R.id.device_pan);
//            button = itemView.findViewById(R.id.know_about);
        }
    }
}