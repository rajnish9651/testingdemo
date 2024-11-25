package com.example.sensors.content;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sensors.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContentProviderDemo extends AppCompatActivity implements ItemClick {

    RecyclerView recyclerView;
    ContactAdapterRecylerView adapter;
    List<ContactData> contactDataList;
    EditText searchBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_provider);
        recyclerView = findViewById(R.id.recylerView);
        searchBar = findViewById(R.id.searchBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactDataList = new ArrayList<>();
        adapter = new ContactAdapterRecylerView(contactDataList, ContentProviderDemo.this);
        recyclerView.setAdapter(adapter);


        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // HashSet to store unique contact names
        HashSet<String> uniqueContacts = new HashSet<>();

        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String photo = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
//                Log.i("mainActiviy", "name : " + name + " number: " + number + " PHOTO: " + photo);

                if (uniqueContacts.add(name)) {
                    contactDataList.add(new ContactData(photo, name, number));
                }

            }
        }

        cursor.close();


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });


    }


    //search for
    private void filter(String query) {
        List<ContactData> filteredList = new ArrayList<>();
        for (ContactData data : contactDataList) {
            if (data.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(data);
            }
        }
        adapter.filterList(filteredList);
    }


    //for delete contact
    private void deleteContact(String number) {

    }

    //making call
    @Override
    public void makeCall(String number) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Confirm Action")
                .setMessage("Do you want to make Call?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + number));
                startActivity(call);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ContentProviderDemo.this, "Dismiss", Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}