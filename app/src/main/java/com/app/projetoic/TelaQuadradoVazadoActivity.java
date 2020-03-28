package com.app.projetoic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaQuadradoVazadoActivity extends AppCompatActivity {

    private EditText editTextLado;
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
        setContentView(R.layout.activity_tela_quadrado_vazado);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configirações iniciais
        editTextLado = findViewById(R.id.editTextLadoQVazado);
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
                String textLado = editTextLado.getText().toString();
                String textEspessura = editTextEspessura.getText().toString();

                //Cálculo
                if (!textLado.isEmpty() && !textEspessura.isEmpty()) {

                    //Converter medidas para Float
                    Float medidaLadoMaior = Float.parseFloat(textLado);
                    Float medidaEspessura = Float.parseFloat(textEspessura);
                    Float medidaLadoMenor = medidaLadoMaior - (2f * medidaEspessura);

                    if (medidaLadoMenor <0) {
                        Toast.makeText(TelaQuadradoVazadoActivity.this, "Erro! digite uma espessura menor.", Toast.LENGTH_SHORT).show();
                    } else {
                        //Área
                        float areaMaior = (medidaLadoMaior * medidaLadoMaior);
                        float areaMenor = (medidaLadoMenor * medidaLadoMenor);
                        float area = areaMaior - areaMenor;
                        String textArea = String.valueOf(area);
                        textViewArea.setText("Área = " + textArea);

                        //Perímetro
                        float perimetro = medidaLadoMaior * 4;
                        String textPerimetro = String.valueOf(perimetro);
                        textViewPerimetro.setText("Perímetro = " + textPerimetro);

                        //Momento de inercia
                        float momentoInercia = (float) ((Math.pow(medidaLadoMaior,4)/12)-(Math.pow(medidaLadoMenor,4)/12));
                        String textMomentoInercia = String.valueOf(momentoInercia);
                        textViewIx.setText("Ix = " + textMomentoInercia);
                        textViewIy.setText("Iy = " + textMomentoInercia);

                        //Raio de giração
                        float raioGiracao = (float) Math.sqrt((momentoInercia/area));
                        String textRaioGiracao = String.valueOf(raioGiracao);
                        textViewix.setText("ix = "+textRaioGiracao);
                        textViewiy.setText("iy = "+textRaioGiracao);

                        //Módulo plastico
                        float moduloPlastico = (float) (Math.pow(medidaLadoMaior,3)/4*(1-Math.pow(1-2*medidaEspessura/medidaLadoMaior,3)));
                        String textModuloPlastico = String.valueOf(moduloPlastico);
                        textViewZx.setText("Zx = "+textModuloPlastico);
                        textViewZy.setText("Zy = "+textModuloPlastico);

                        editTextEspessura.setText("");
                        editTextLado.setText("");

                       /* Log.i("TESTEVALOR", "Lado maior: " + medidaLadoMaior.toString());
                        Log.i("TESTEVALOR", "Lado menor: " + medidaLadoMenor.toString());
                        Log.i("TESTEVALOR", "Espessura: " + medidaEspessura.toString());
                        Log.i("TESTEVALOR", "AreaMaior: " + areaMaior);
                        Log.i("TESTEVALOR", "AreaMenor: " + areaMenor);
                        Log.i("TESTEVALOR", "Area: " + area);*/
                    }

                } else {
                    Toast.makeText(TelaQuadradoVazadoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
