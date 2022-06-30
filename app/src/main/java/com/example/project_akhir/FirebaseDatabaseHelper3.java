package com.example.project_akhir;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class FirebaseDatabaseHelper3 {
    private DatabaseReference mReferenceRequests;
    private FirebaseDatabase mDatabase;
    private List<Requests> requests = new ArrayList<>();

    public interface DataStatus {
        void dataIsLoad(List<Requests> requests, List<String> keys);

        void dataIsUpdate();

        void dataIsDeleted();
    }

    public FirebaseDatabaseHelper3() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceRequests = mDatabase.getReference("conver");
    }

    public void readRequests(final DataStatus dataStatus) {
        mReferenceRequests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                requests.clear();
                ArrayList<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Requests request = keyNode.getValue(Requests.class);
                    requests.add(request);
                }
                dataStatus.dataIsLoad(requests, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void updateBook(String key, Requests req, final DataStatus dataStatus) {
        mReferenceRequests.child(key).setValue(req)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.dataIsUpdate();
                    }
                });
    }

    public void deleteBook(String key, final DataStatus dataStatus) {
        mReferenceRequests.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsDeleted();
            }
        });
    }
}