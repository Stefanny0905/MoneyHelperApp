package com.aquino.moneyhelper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.aquino.moneyhelper.models.Operacion;
import com.aquino.moneyhelper.R;
import com.aquino.moneyhelper.repository.OperacionRepository;

public class NewOperationActivity extends AppCompatActivity {

    private EditText monto_input;
    private RadioGroup radioGroup;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_operation);

        monto_input = (EditText)findViewById(R.id.monto_input);
        spinner = (Spinner)findViewById(R.id.spinner);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
    }


    public void registrar(View view) {

        String monto1= monto_input.getText().toString();

        if(monto1.isEmpty()){
            Toast.makeText(this, "Debe ingresar un valor", Toast.LENGTH_SHORT).show();
            return;
        }

        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(radioButtonId);
        String tipo= radioButton.getText().toString();

        String tipo_cuenta = (String) spinner.getSelectedItem();

        Operacion operacion = new Operacion(Double.parseDouble(monto1),tipo,tipo_cuenta);

        OperacionRepository operacionRepository = OperacionRepository.getInstance();
        operacionRepository.agregarOperacion(operacion);

        finish();

    }
}
