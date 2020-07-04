package com.app.projetoic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaPerfilIActivity extends AppCompatActivity {

    private EditText editTextBase;
    private EditText editTextAltura;
    private EditText editTextEspessuraMesa;
    private EditText editTextEspessuraAlma;
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
    private TextView textViewCentroideX;
    private TextView textViewCentroideY;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_i);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextBase = findViewById(R.id.editTextBase);
        editTextAltura = findViewById(R.id.editTextAltura);
        editTextEspessuraMesa = findViewById(R.id.editTextEspessuraMesa);
        editTextEspessuraAlma = findViewById(R.id.editTextEspessuraAlma);
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
        textViewCentroideX = findViewById(R.id.textViewCentroideX);
        textViewCentroideY = findViewById(R.id.textViewCentroideY);
        buttonCalcular = findViewById(R.id.buttonCalcular);

        //Evento de clique
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textBase = editTextBase.getText().toString();
                String textAltura = editTextAltura.getText().toString();
                String textEspessuraMesa = editTextEspessuraMesa.getText().toString();
                String textEspessuraAlma = editTextEspessuraAlma.getText().toString();

                if (!textBase.isEmpty() && !textAltura.isEmpty() && !textEspessuraMesa.isEmpty() && !textEspessuraAlma.isEmpty()){
                    float medidaBase = Float.parseFloat(textBase);
                    float medidaAltura = Float.parseFloat(textAltura);
                    float medidaEspessuraMesa = Float.parseFloat(textEspessuraMesa);
                    float medidaEspessuraAlma = Float.parseFloat(textEspessuraAlma);
                    float medidaBaseInterna = medidaBase-(medidaEspessuraAlma);
                    float medidaAlturaInterna = medidaAltura-(2*medidaEspessuraMesa);

                    if (medidaBaseInterna>0 && medidaAlturaInterna>0){

                        //Área
                        float area1 = medidaEspessuraMesa*medidaBase;
                        float area2 = medidaAlturaInterna*medidaEspessuraAlma;
                        float area3 = area1;
                        float areaTotal = area1 + area2 + area3;
                        String textArea = String.valueOf(areaTotal);
                        textViewArea.setText("Área = " +textArea);

                        //Centróide
                        float centroideX1 = medidaBase/2;
                        float centroideX2 = centroideX1;
                        float centroideX3 = centroideX1;
                        float centroideX = centroideX1;

                        float centroideY1 = medidaEspessuraMesa/2;
                        float centroideY2 = medidaAltura/2;
                        float centroideY3 = medidaAltura - (medidaEspessuraMesa/2);

                        float centroideY = medidaAltura/2;

                        String textCentroideX = String.valueOf(centroideX);
                        String textCentroideY = String.valueOf(centroideY);
                        textViewCentroideX.setText("X' = "+textCentroideX);
                        textViewCentroideY.setText("Y' = "+textCentroideY);


                        //Perímetro
                        float perimetro = (2*medidaBase) + (2*medidaBaseInterna) + (4*medidaEspessuraAlma) + (2*medidaAlturaInterna);
                        String textPerimetro = String.valueOf(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                        //Momento de inercia
                        float momentoInerciaX1 = (float) (medidaBase*Math.pow(medidaEspessuraMesa,3)/12 + area1*Math.pow(centroideY-centroideY1,2));
                        float momentoInerciaX2 = (float) (medidaEspessuraAlma*Math.pow(medidaAltura-medidaAlturaInterna,3)/12);
                        float momentoInerciaX3 = (float) (medidaBase*Math.pow(medidaEspessuraMesa,3)/12 + area3*Math.pow(centroideY-centroideY3,2));
                        float momentoInerciaX = momentoInerciaX1 + momentoInerciaX2 + momentoInerciaX3;

                        float momentoInerciaY1 = (float) (medidaEspessuraMesa*Math.pow(medidaBase,3)/12);
                        float momentoInerciaY2 = (float) (medidaAlturaInterna*Math.pow(medidaEspessuraAlma,3)/12);
                        float momentoInerciaY3 = (float) (medidaEspessuraMesa*Math.pow(medidaBase,3)/12);
                        float momentoInerciaY = momentoInerciaY1 + momentoInerciaY2 + momentoInerciaY3;

                        String textMomentoInerciaX = String.valueOf(momentoInerciaX);
                        String textMomentoInerciaY = String.valueOf(momentoInerciaY);
                        textViewIx.setText("Ix' = " + textMomentoInerciaX);
                        textViewIy.setText("Iy' = " + textMomentoInerciaY);

                        //Raio de giração
                        float raioGiracaoX = (float) Math.sqrt((momentoInerciaX/areaTotal));
                        float raioGiracaoY = (float) Math.sqrt((momentoInerciaY/areaTotal));
                        String textRaioGiracaoX = String.valueOf(raioGiracaoX);
                        String textRaioGiracaoY = String.valueOf(raioGiracaoY);
                        textViewix.setText("ix' = " + textRaioGiracaoX);
                        textViewiy.setText("iy' = " + textRaioGiracaoY);


                        //Módulo plastico
                        float moduloPlasticoX = (float) (medidaBase*medidaEspessuraMesa*(medidaAltura-medidaEspessuraMesa)+0.25*medidaEspessuraAlma*Math.pow(medidaAltura-2*medidaEspessuraMesa,2));
                        float moduloPlasticoY = (float) (0.5*medidaEspessuraMesa*Math.pow(medidaBase,2)*0.25*(medidaAltura-2*medidaEspessuraMesa)*Math.pow(medidaEspessuraAlma,2));
                        String textModuloPlasticoX = String.valueOf(moduloPlasticoX);
                        String textModuloPlasticoY = String.valueOf(moduloPlasticoY);
                        textViewZx.setText("Zx' = "+textModuloPlasticoX);
                        textViewZy.setText("Zy' = "+textModuloPlasticoY);

                        //Módulo elástico
                        float moduloElasticoX = 2*momentoInerciaX/medidaAltura;
                        float moduloElasticoY = 2*momentoInerciaY/medidaBase;
                        String textModuloElasticoX = String.valueOf(moduloElasticoX);
                        String textModuloElasticoY = String.valueOf(moduloElasticoY);
                        textViewWx.setText("Wx' = "+textModuloElasticoX);
                        textViewWy.setText("Wx' = "+textModuloElasticoY);

                        //Limpar EditText
                        editTextBase.setText("");
                        editTextAltura.setText("");
                        editTextEspessuraMesa.setText("");
                        editTextEspessuraAlma.setText("");
                    }else {
                        Toast.makeText(TelaPerfilIActivity.this, "Erro! digite uma espessura menor ou medidas maiores para os lados", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(TelaPerfilIActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
