package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.projetoic.R;
import com.app.projetoic.helper.Utils;

public class TelaSemicirculoActivity extends AppCompatActivity {

    private EditText editTextRaio;
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
        setContentView(R.layout.activity_tela_semicirculo);

        //Configurar Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextRaio = findViewById(R.id.editTextRaio);
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
                String textRaio = editTextRaio.getText().toString();
                if (!textRaio.isEmpty()){
                    double medidaRaio = Float.parseFloat(textRaio);

                    //Área
                    double area = (Math.PI*Math.pow(medidaRaio,2)/2);
                    String textArea = Utils.arredondar(area);
                    textViewArea.setText("Área = " +textArea);

                    //Perímetro
                    double perimetro = ((Math.PI*2*medidaRaio)/2)+(2*medidaRaio);
                    String textPerimetro = Utils.arredondar(perimetro);
                    textViewPerimetro.setText("P. Ext.= " + textPerimetro);

                    //Momento de inercia
                    double momentoInercia = (Math.PI*Math.pow(medidaRaio,4)/8);
                    String textMomentoInercia = Utils.arredondar(momentoInercia);
                    textViewIx.setText("Ix = " + textMomentoInercia);
                    textViewIy.setText("Iy = " + textMomentoInercia);

                    //Raio de giração
                    double raioGiracao = Math.sqrt(momentoInercia/area);
                    String textRaioGiracao = Utils.arredondar(raioGiracao);
                    textViewix.setText("ix = " + textRaioGiracao);
                    textViewiy.setText("iy = " + textRaioGiracao);

                    //Módulo Plástico
                    double moduloPlastico = (2*Math.pow(medidaRaio,3)/3);
                    String textModuloPlastico = Utils.arredondar(moduloPlastico);
                    textViewZx.setText("Zx' = "+textModuloPlastico);
                    textViewZy.setText("Zy' = "+textModuloPlastico);

                    //Módulo Elástico
                    double moduloElastico = (0.3927*Math.pow(medidaRaio,3));
                    String textModuloElastico = Utils.arredondar(moduloElastico);
                    textViewWx.setText("Wx = "+textModuloElastico);
                    textViewWy.setText("Wy = "+textModuloElastico);

                    //Limpar EditText
                    editTextRaio.setText("");

                }else {
                    Toast.makeText(TelaSemicirculoActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
