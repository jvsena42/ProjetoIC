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

public class TelaTrianguloEquilateroActivity extends AppCompatActivity {

    private EditText editTextLado;
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
        setContentView(R.layout.activity_tela_triangulo_equilatero);


        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextLado = findViewById(R.id.editTextC);
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
                String textLado = editTextLado.getText().toString();
                if (!textLado.isEmpty()) {
                    float medidaLado = Float.parseFloat(textLado);
                    float altura = (float) Math.sqrt(Math.pow(medidaLado,2)+Math.pow(medidaLado/2,2));

                    //Área
                    float area = (medidaLado/2) * altura/2;
                    String textArea = String.valueOf(area);
                    textViewArea.setText("Área = " + textArea);

                    //Perímetro
                    float perimetro = medidaLado * 3;
                    String textPerimetro = String.valueOf(perimetro);
                    textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                    //Momento de inercia
                    float momentoInerciaX = (float) (medidaLado*Math.pow(altura,3)/36);
                    float momentoInerciaY = (float) (altura*Math.pow(medidaLado,3)/36);
                    String textMomentoInerciaX = String.valueOf(momentoInerciaX);
                    String textMomentoInerciaY = String.valueOf(momentoInerciaY);
                    textViewIx.setText("Ix = " + textMomentoInerciaX);
                    textViewIy.setText("Iy = " + textMomentoInerciaY);

                    //Raio de giração
                    float raioGiracaoX = (float) Math.sqrt(momentoInerciaX/area);
                    float raioGiracaoY = (float) Math.sqrt(momentoInerciaY/area);
                    String textRaioGiracaoX = String.valueOf(raioGiracaoX);
                    String textRaioGiracaoY = String.valueOf(raioGiracaoY);
                    textViewix.setText("ix = " + textRaioGiracaoX);
                    textViewiy.setText("iy = " + textRaioGiracaoY);

                    //Módulo Plástico
                    float moduloPlasticoX = (float) ((medidaLado*Math.pow(altura, 2)*(2-Math.sqrt(2))) / 6);
                    float moduloPlasticoY = (float) (altura*Math.pow(medidaLado, 2) / 12);
                    String textModuloPlasticoX = String.valueOf(moduloPlasticoX);
                    String textModuloPlasticoY = String.valueOf(moduloPlasticoY);
                    textViewZx.setText("Zx = " + textModuloPlasticoX);
                    textViewZy.setText("Zy = " + textModuloPlasticoY);

                    //Módulo Elástico
                    float moduloElasticoX = (float) (medidaLado*Math.pow(altura, 2) / 24);
                    float moduloElasticoY = (float) (altura*Math.pow(medidaLado, 2) / 18);
                    String textModuloElasticoX = String.valueOf(moduloElasticoX);
                    String textModuloElasticoY = String.valueOf(moduloElasticoY);
                    textViewWx.setText("Wx = " + textModuloElasticoX);
                    textViewWy.setText("Wy = " + textModuloElasticoY);

                    //Limpar EditText
                    editTextLado.setText("");

                } else {
                    Toast.makeText(TelaTrianguloEquilateroActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
