package com.example.sensors.content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sensors.R;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapterRecylerView extends RecyclerView.Adapter<ContactAdapterRecylerView.ContectViewHolder> {

    List<ContactData> contactDataList;
    ItemClick context;

    public ContactAdapterRecylerView(List<ContactData> contactDataList, ItemClick context) {
        this.contactDataList = contactDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_recylerview, parent, false);
        return new ContectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContectViewHolder holder, int position) {
        ContactData contact = contactDataList.get(position);
        holder.name.setText(contact.getName());
        holder.number.setText(contact.getNumber());

        // Load image if photoUri is not null
        if (contact.getImg() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(contact.getImg())
                    .circleCrop()
                    .into(holder.img); // Ensure you have an ImageView for photo
        } else {
            holder.img.setImageResource(R.drawable.baseline_notifications_active_24); // Fallback image
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                contactDataList.remove(position);
                notifyItemRemoved(position);

                return true;
            }
        });

        //making call
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num = contactDataList.get(position).getNumber();
                context.makeCall(num);

            }
        });

    }

    @Override
    public int getItemCount() {
        return contactDataList.size();
    }


    public void filterList(List<ContactData> filterList){
        contactDataList=filterList;
        notifyDataSetChanged();
    }

    public class ContectViewHolder extends RecyclerView.ViewHolder {
        TextView number, name;
        ImageView img;

        public ContectViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.contactNumber);
            name = itemView.findViewById(R.id.contactName);
            img = itemView.findViewById(R.id.img);
        }
    }
}
