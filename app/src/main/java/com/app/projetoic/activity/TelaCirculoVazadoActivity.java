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
import com.app.projetoic.helper.Utils;

public class TelaCirculoVazadoActivity extends AppCompatActivity {

    private EditText editTextRaio;
    private EditText editTextEspessura;
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
        setContentView(R.layout.activity_tela_circulo_vazado);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configirações iniciais
        editTextRaio = findViewById(R.id.editTextRaio);
        editTextEspessura = findViewById(R.id.editTextEspessura);
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
                String textEspessura = editTextEspessura.getText().toString();

                //Cálculo
                if (!textRaio.isEmpty() && !textEspessura.isEmpty()) {

                    //Converter medidas para Float
                    Float medidaRaio = Float.parseFloat(textRaio);
                    Float medidaEspessura = Float.parseFloat(textEspessura);
                    Float medidaRaioMenor = medidaRaio-medidaEspessura;

                    if (medidaEspessura >= medidaRaio) {
                        Toast.makeText(TelaCirculoVazadoActivity.this, "Erro! digite uma espessura menor.", Toast.LENGTH_SHORT).show();
                    } else {
                        //Área
                        double areaMaior = Math.PI*Math.pow(medidaRaio,2);
                        double areaMenor = Math.PI*Math.pow(medidaRaioMenor,2);
                        double area = areaMaior - areaMenor;
                        String textArea = Utils.arredondar(area);
                        textViewArea.setText("Área = " + textArea);

                        //Perímetro
                        double perimetro = 2*Math.PI*medidaRaio;
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaMaior = Math.PI*Math.pow(medidaRaio,4) /4;
                        double momentoInerciaMenor = Math.PI*Math.pow(medidaRaioMenor,4) /4;
                        double momentoInercia = momentoInerciaMaior - momentoInerciaMenor;
                        String textMomentoInercia = Utils.arredondar(momentoInercia);
                        textViewIx.setText("Ix = " + textMomentoInercia);
                        textViewIy.setText("Iy = " + textMomentoInercia);

                        //Raio de giração
                        double raioGiracao =  Math.sqrt((momentoInercia/area));
                        String textRaioGiracao = Utils.arredondar(raioGiracao);
                        textViewix.setText("ix = "+textRaioGiracao);
                        textViewiy.setText("iy = "+textRaioGiracao);

                        //Módulo plastico
                        double moduloPlastico = 4*Math.pow(medidaRaio,3)*(1-Math.pow((1-(medidaEspessura/medidaRaio)),3))/3;
                        String textModuloPlastico = Utils.arredondar(moduloPlastico);
                        textViewZx.setText("Zx = "+textModuloPlastico);
                        textViewZy.setText("Zy = "+textModuloPlastico);

                        //Modulo Elastico
                        double moduloElastico = Math.PI*Math.pow(medidaRaio,2)*medidaEspessura;
                        String textModuloElastico = Utils.arredondar(moduloElastico);
                        textViewWx.setText("Wx = "+textModuloElastico);
                        textViewWy.setText("Wy = "+textModuloElastico);

                        //Limpar EditText
                        editTextEspessura.setText("");
                        editTextRaio.setText("");

                       /* Log.i("TESTEVALOR", "Lado maior: " + medidaLadoMaior.toString());
                        Log.i("TESTEVALOR", "Lado menor: " + medidaLadoMenor.toString());
                        Log.i("TESTEVALOR", "Espessura: " + medidaEspessura.toString());
                        Log.i("TESTEVALOR", "AreaMaior: " + areaMaior);
                        Log.i("TESTEVALOR", "AreaMenor: " + areaMenor);
                        Log.i("TESTEVALOR", "Area: " + area);*/
                    }

                } else {
                    Toast.makeText(TelaCirculoVazadoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
