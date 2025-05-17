package com.example.t5_a4_duransantillanmariana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView txtTitulo, txtIngresa;
    private RadioGroup rGroup;
    private RadioButton rBtnDecimal, rBtnBinario, rBtnOctal, rBtnHexadecimal;
    private EditText cajaNumeroI, resBinario, resOctal, resHexadecimal;
    private CheckBox cBoxBinario, cBoxOctal, cBoxHexadecimal;
    private Button btnConvertir;
    private String baseInicial = "decimal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtTitulo = findViewById(R.id.txt_Titulo);
        txtIngresa = findViewById(R.id.txt_ingresa);
        rGroup = findViewById(R.id.r_group);
        rBtnDecimal = findViewById(R.id.r_btn_decimal);
        rBtnBinario = findViewById(R.id.r_btn_binario);
        rBtnOctal = findViewById(R.id.r_btn_octal);
        rBtnHexadecimal = findViewById(R.id.r_btn_hexadecimal);
        cajaNumeroI = findViewById(R.id.caja_numero_I);
        resBinario = findViewById(R.id.res_binario);
        resOctal = findViewById(R.id.res_octal);
        resHexadecimal = findViewById(R.id.res_hexadecimal);
        cBoxBinario = findViewById(R.id.c_box_binario);
        cBoxOctal = findViewById(R.id.c_box_octal);
        cBoxHexadecimal = findViewById(R.id.c_box_hexadecimal);
        btnConvertir = findViewById(R.id.btn_convertir);


        rGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.r_btn_decimal) {
                baseInicial = "decimal";
                cajaNumeroI.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            } else if (checkedId == R.id.r_btn_binario) {
                baseInicial = "binario";
                cajaNumeroI.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            } else if (checkedId == R.id.r_btn_octal) {
                baseInicial = "octal";
                cajaNumeroI.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            } else if (checkedId == R.id.r_btn_hexadecimal) {
                baseInicial = "hexadecimal";
                cajaNumeroI.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
            }
            cajaNumeroI.setText("");
            limpiarResultados();
        });

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertirNumero();
            }
        });

        rBtnDecimal.setChecked(true);
    }


    private void limpiarResultados() {
        resBinario.setText("");
        resOctal.setText("");
        resHexadecimal.setText("");
        cBoxBinario.setChecked(false);
        cBoxOctal.setChecked(false);
        cBoxHexadecimal.setChecked(false);
    }

    private void convertirNumero() {
        String numeroStr = cajaNumeroI.getText().toString();
        if (numeroStr.isEmpty()) {
            Toast.makeText(this, "ingrese un número", Toast.LENGTH_SHORT).show();
            limpiarResultados();
            return;
        }

        long decimalValue = 0;

        try {
            if (baseInicial.equals("decimal")) {
                decimalValue = Long.parseLong(numeroStr);
            } else if (baseInicial.equals("binario")) {
                decimalValue = Long.parseLong(numeroStr, 2);
            } else if (baseInicial.equals("octal")) {
                decimalValue = Long.parseLong(numeroStr, 8);
            } else if (baseInicial.equals("hexadecimal")) {
                decimalValue = Long.parseLong(numeroStr, 16);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Formato invalido", Toast.LENGTH_SHORT).show();
            limpiarResultados();
            return;
        }

        if (cBoxBinario.isChecked()) {
            resBinario.setText(Long.toBinaryString(decimalValue));
        } else {
            resBinario.setText("");
        }

        if (cBoxOctal.isChecked()) {
            resOctal.setText(Long.toOctalString(decimalValue));
        } else {
            resOctal.setText("");
        }

        if (cBoxHexadecimal.isChecked()) {
            resHexadecimal.setText(Long.toHexString(decimalValue).toUpperCase());
        } else {
            resHexadecimal.setText("");
        }
    }

    public void iniciar(View view){
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
    }
}