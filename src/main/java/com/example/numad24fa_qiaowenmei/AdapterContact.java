package com.example.numad24fa_qiaowenmei;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ContactViewHolder> {

    private Context context;
    private ArrayList<ModelContact> contactList;
    private DatabaseHelper db;

    public AdapterContact(Context context, ArrayList<ModelContact> contactList) {
        this.context = context;
        this.contactList = contactList;
        db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ModelContact modelContact = contactList.get(position);

        // Get data
        String id = modelContact.getId();
        String name = modelContact.getName();
        String phone = modelContact.getPhone();

        // Set data in view
        holder.contactName.setText(name);
        holder.contactPhone.setText(phone);

        // Handle edit button click
        holder.contactEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditContact.class);
            intent.putExtra("ID", id);
            intent.putExtra("NAME", name);
            intent.putExtra("PHONE", phone);
            intent.putExtra("isEditMode", true);
            context.startActivity(intent);
        });

        // Handle delete button click
        holder.contactDelete.setOnClickListener(v -> {
            db.deleteContact(id);
            contactList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Contact deleted", Toast.LENGTH_SHORT).show();
        });

        //make phone call
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView contactName, contactPhone;
        Button contactEdit, contactDelete;
        RelativeLayout relativeLayout;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contact_name);
            contactPhone = itemView.findViewById(R.id.contact_phone);
            contactEdit = itemView.findViewById(R.id.contact_edit);
            contactDelete = itemView.findViewById(R.id.contact_delete);
        }
    }
}
