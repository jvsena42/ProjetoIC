package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.projetoic.R;

public class TelaRetanguloActivity extends AppCompatActivity {

    private EditText editTextBase;
    private EditText editTextAltura;
    private TextView textViewArea;
    private TextView textViewPerimetro;
    private TextView textViewIx;
    private TextView textViewIy;
    private TextView textViewix;
    private TextView textViewiy;
    private TextView textViewZx;
    private TextView textViewZy;
    private TextView textViewWx;
    private TextView textViewWy;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_retangulo);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextBase = findViewById(R.id.editTextBase);
        editTextAltura = findViewById(R.id.editTextAltura);
        textViewArea = findViewById(R.id.textViewArea);
        textViewPerimetro = findViewById(R.id.textViewPerimetro);
        textViewIx = findViewById(R.id.textViewIx);
        textViewIy = findViewById(R.id.textViewIy);
        textViewix = findViewById(R.id.textViewix);
        textViewiy = findViewById(R.id.textViewiy);
        textViewZx = findViewById(R.id.textViewZx);
        textViewZy = findViewById(R.id.textViewZy);
        textViewWx = findViewById(R.id.textViewWx);
        textViewWy = findViewById(R.id.textViewWy);
        buttonCalcular = findViewById(R.id.buttonCalcular);

        //Evento de clique
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recuperar dados
                String textBase = editTextBase.getText().toString();
                String textAltura = editTextAltura.getText().toString();

                if (!textBase.isEmpty() || !textAltura.isEmpty()) {

                    float medidaBase = Float.parseFloat(textBase);
                    float medidaAltura = Float.parseFloat(textAltura);

                    if (medidaBase > 0 && medidaAltura > 0) {

                        //Área
                        float area = medidaBase*medidaAltura;
                        String textArea = String.valueOf(area);
                        textViewArea.setText("Área = " +textArea);

                        //Perímetro
                        float perimetro = medidaBase*2+medidaAltura*2;
                        String textPerimetro = String.valueOf(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                        //Momento de inercia
                        float momentoInerciaX = (float) (Math.pow(medidaAltura,3)*medidaBase/12);
                        float momentoInerciaY = (float) (Math.pow(medidaBase,3)*medidaAltura/12);
                        String textMomentoInerciaX = String.valueOf(momentoInerciaX);
                        String textMomentoInerciaY = String.valueOf(momentoInerciaY);
                        textViewIx.setText("Ix = " + textMomentoInerciaX);
                        textViewIy.setText("Iy = " + textMomentoInerciaY);

                        //Raio de giração
                        float raioGiracaoX = (float) Math.sqrt((momentoInerciaX/area));
                        float raioGiracaoY = (float) Math.sqrt((momentoInerciaY/area));
                        String textRaioGiracaoX = String.valueOf(raioGiracaoX);
                        String textRaioGiracaoY = String.valueOf(raioGiracaoY);
                        textViewix.setText("ix = " + textRaioGiracaoX);
                        textViewiy.setText("iy = " + textRaioGiracaoY);

                        //Módulo Plástico
                        float moduloPlasticoX = (float) (Math.pow(medidaAltura,2)*medidaBase/4);
                        float moduloPlasticoY = (float) (Math.pow(medidaBase,2)*medidaAltura/4);
                        String textModuloPlasticoX = String.valueOf(moduloPlasticoX);
                        String textModuloPlasticoY = String.valueOf(moduloPlasticoY);
                        textViewZx.setText("Zx = "+textModuloPlasticoX);
                        textViewZy.setText("Zy = "+textModuloPlasticoY);

                        //Módulo Elástico
                        float moduloElasticoX = (float) (medidaBase*Math.pow(medidaAltura,2)/6);
                        float moduloElasticoY = (float) (medidaAltura*Math.pow(medidaBase,2)/6);
                        String textModuloElasticoX = String.valueOf(moduloElasticoX);
                        String textModuloElasticoY = String.valueOf(moduloElasticoY);
                        textViewWx.setText("Wx = "+textModuloElasticoX);
                        textViewWy.setText("Wy = "+textModuloElasticoY);

                        //Limpar EditText
                        editTextBase.setText("");
                        editTextAltura.setText("");

                    } else {
                    Toast.makeText(TelaRetanguloActivity.this, "Utilize valores válidos!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TelaRetanguloActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
