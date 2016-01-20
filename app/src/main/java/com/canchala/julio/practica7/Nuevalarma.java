package com.canchala.julio.practica7;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * A simple {@link Fragment} subclass.
 */
public class Nuevalarma extends Fragment {

    TimePicker tiempo;
    ToggleButton lunes;
    ToggleButton martes;
    ToggleButton miercoles;
    ToggleButton jueves;
    ToggleButton viernes;
    ToggleButton sabado;
    ToggleButton domingo;
    CheckBox todosdias;
    Button guardar;
    EditText actividad;
    Button lugar;

    private Firebase mRef;


    public Nuevalarma() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_nuevalarma, container, false);

        tiempo=(TimePicker) rootView.findViewById(R.id.timePicker);
        lunes=(ToggleButton) rootView.findViewById(R.id.toggleButtonlunes);
        martes=(ToggleButton) rootView.findViewById(R.id.toggleButtonmartes);
        miercoles=(ToggleButton) rootView.findViewById(R.id.toggleButtonmiercoles);
        jueves=(ToggleButton) rootView.findViewById(R.id.toggleButtonjueves);
        viernes=(ToggleButton) rootView.findViewById(R.id.toggleButtonviernes);
        sabado=(ToggleButton) rootView.findViewById(R.id.toggleButtonsabado);
        domingo=(ToggleButton) rootView.findViewById(R.id.toggleButtondomingo);
        todosdias=(CheckBox) rootView.findViewById(R.id.checkBox);
        guardar=(Button) rootView.findViewById(R.id.guardarnue);
        actividad=(EditText) rootView.findViewById(R.id.txt);
        lugar=(Button) rootView.findViewById(R.id.lugar);

        Firebase.setAndroidContext(getContext());
        mRef=new Firebase("https://fatto.firebaseio.com/");

        todosdias.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lunes.setChecked(true);
                    martes.setChecked(true);
                    miercoles.setChecked(true);
                    jueves.setChecked(true);
                    viernes.setChecked(true);
                    sabado.setChecked(true);
                    domingo.setChecked(true);
                } else {

                    lunes.setChecked(false);
                    martes.setChecked(false);
                    miercoles.setChecked(false);
                    jueves.setChecked(false);
                    viernes.setChecked(false);
                    sabado.setChecked(false);
                    domingo.setChecked(false);

                }
            }
        });

        lugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour=tiempo.getCurrentHour();
                int min=tiempo.getCurrentMinute();

                String acti=actividad.getText().toString();


                Bundle bundle=getArguments();
                String name=bundle.getString("nam");

                Firebase alarmas=mRef.child("Usuarios").child(name).child("Alarmas").child(acti);

                String l = "n";
                String m = "n";
                String w = "n";
                String j = "n";
                String vi = "n";
                String s = "n";
                String d = "n";

                if(lunes.isChecked()) { l = "s"; }
                if(martes.isChecked()) { m = "s"; }
                if(miercoles.isChecked()) { w = "s"; }
                if(jueves.isChecked()) { j = "s"; }
                if(viernes.isChecked()) { vi = "s"; }
                if(sabado.isChecked()) { s = "s"; }
                if(domingo.isChecked()) { d = "s"; }

                alarm alarmnew=new alarm(acti,hour,min,l,m,w,j,vi,s,d);
                alarmas.setValue(alarmnew, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {

                            Toast.makeText(getContext(), "Meta no guardada" + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(getContext(), "Meta Aceptada", Toast.LENGTH_SHORT).show();

                            actividad.setText("");
                            lunes.setChecked(false);
                            martes.setChecked(false);
                            miercoles.setChecked(false);
                            jueves.setChecked(false);
                            viernes.setChecked(false);
                            sabado.setChecked(false);
                            domingo.setChecked(false);
                            todosdias.setChecked(false);
                        }
                    }
                });
            }
        });

        return rootView;
    }

}
