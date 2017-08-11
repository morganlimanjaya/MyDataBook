package sg.edu.rp.c347.mydatabook;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class VaccinationFragment extends Fragment {

    Button btnEdit;
    TextView tvVac;
    DatabaseReference dbRef;
    
    //this one will be from firebase database
    public VaccinationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.vaccinationfragment, container, false);

        btnEdit = (Button) view.findViewById(R.id.btnFragVaccination);
        tvVac = (TextView) view.findViewById(R.id.textViewVaccination);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Vac");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);
                tvVac.setText(val);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View mView = inflater.inflate(R.layout.edit_bio, null);

                builder.setTitle("Edit Vaccination")
                        // Set text for the positive button and the corresponding
                        //  OnClickListener when it is clicked
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                EditText etBio = (EditText) mView.findViewById(R.id.editTextBio);
                                String content = etBio.getText().toString();
                                dbRef.setValue(content);
                            }
                        })
                        // Set text for the negative button and the corresponding
                        //  OnClickListener when it is clicked
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getContext(), "You clicked no", Toast.LENGTH_LONG).show();
                            }
                        });
                // Create the AlertDialog object and return it
                builder.setView(mView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        return view;


    }

}
