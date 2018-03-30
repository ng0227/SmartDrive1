package com.techhive.smartdrive.Problems;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techhive.smartdrive.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrackProblemActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    List<ProbemReportinfo> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_problem);

        databaseReference = FirebaseDatabase.getInstance().getReference("All_Post_Uploads_Database");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ProbemReportinfo imageUploadInfo = postSnapshot.getValue(ProbemReportinfo.class);
                    list.add(imageUploadInfo);
                }
                Collections.reverse(list);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //progressDialog.dismiss();
            }
        });
    }
}
