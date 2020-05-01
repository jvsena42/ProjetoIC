package com.app.projetoic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaQuadradoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_tela_quadrado);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextLado = findViewById(R.id.editTextLado);
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
                if (!textLado.isEmpty()){
                    float medidaLado = Float.parseFloat(textLado);

                    //Área
                    float area = medidaLado*medidaLado;
                    String textArea = String.valueOf(area);
                    textViewArea.setText("Área = " +textArea);

                    //Perímetro
                    float perimetro = medidaLado*4;
                    String textPerimetro = String.valueOf(perimetro);
                    textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                    //Momento de inercia
                    float momentoInercia = (float) (Math.pow(medidaLado,4)/12);
                    String textMomentoInercia = String.valueOf(momentoInercia);
                    textViewIx.setText("Ix = " + textMomentoInercia);
                    textViewIy.setText("Iy = " + textMomentoInercia);

                    //Raio de giração
                    float raioGiracao = (float) (medidaLado/Math.sqrt(12));
                    String textRaioGiracao = String.valueOf(raioGiracao);
                    textViewix.setText("ix = " + textRaioGiracao);
                    textViewiy.setText("iy = " + textRaioGiracao);

                    //Módulo Plástico
                    float moduloPlastico = (float) (Math.pow(medidaLado,3)/4);
                    String textModuloPlastico = String.valueOf(moduloPlastico);
                    textViewZx.setText("Zx = "+textModuloPlastico);
                    textViewZy.setText("Zy = "+textModuloPlastico);

                    //Módulo Elástico
                    float moduloElastico = (float) (Math.pow(medidaLado,3)/6);
                    String textModuloElastico = String.valueOf(moduloElastico);
                    textViewWx.setText("Wx = "+textModuloElastico);
                    textViewWy.setText("Wy = "+textModuloElastico);

                    //Limpar EditText
                    editTextLado.setText("");

                }else {
                    Toast.makeText(TelaQuadradoActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
