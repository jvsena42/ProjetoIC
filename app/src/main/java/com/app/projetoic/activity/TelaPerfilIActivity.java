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
                    double medidaBase = Float.parseFloat(textBase);
                    double medidaAltura = Float.parseFloat(textAltura);
                    double medidaEspessuraMesa = Float.parseFloat(textEspessuraMesa);
                    double medidaEspessuraAlma = Float.parseFloat(textEspessuraAlma);
                    double medidaBaseInterna = medidaBase-(medidaEspessuraAlma);
                    double medidaAlturaInterna = medidaAltura-(2*medidaEspessuraMesa);

                    if (medidaBaseInterna>0 && medidaAlturaInterna>0){

                        //Área
                        double area1 = medidaEspessuraMesa*medidaBase;
                        double area2 = medidaAlturaInterna*medidaEspessuraAlma;
                        double area3 = area1;
                        double areaTotal = area1 + area2 + area3;
                        String textArea = Utils.arredondar(areaTotal);
                        textViewArea.setText("Área = " +textArea);

                        //Centróide
                        double centroideX1 = medidaBase/2;
                        double centroideX2 = centroideX1;
                        double centroideX3 = centroideX1;
                        double centroideX = centroideX1;

                        double centroideY1 = medidaEspessuraMesa/2;
                        double centroideY2 = medidaAltura/2;
                        double centroideY3 = medidaAltura - (medidaEspessuraMesa/2);

                        double centroideY = medidaAltura/2;

                        String textCentroideX = Utils.arredondar(centroideX);
                        String textCentroideY = Utils.arredondar(centroideY);
                        textViewCentroideX.setText("X' = "+textCentroideX);
                        textViewCentroideY.setText("Y' = "+textCentroideY);


                        //Perímetro
                        double perimetro = (2*medidaBase) + (2*medidaBaseInterna) + (4*medidaEspessuraAlma) + (2*medidaAlturaInterna);
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaX1 = (medidaBase*Math.pow(medidaEspessuraMesa,3)/12 + area1*Math.pow(centroideY-centroideY1,2));
                        double momentoInerciaX2 = (medidaEspessuraAlma*Math.pow(medidaAlturaInterna,3)/12);
                        double momentoInerciaX3 = (medidaBase*Math.pow(medidaEspessuraMesa,3)/12 + area3*Math.pow(centroideY-centroideY3,2));
                        double momentoInerciaX = momentoInerciaX1 + momentoInerciaX2 + momentoInerciaX3;

                        double momentoInerciaY1 = (medidaEspessuraMesa*Math.pow(medidaBase,3)/12);
                        double momentoInerciaY2 = (medidaAlturaInterna*Math.pow(medidaEspessuraAlma,3)/12);
                        double momentoInerciaY3 = (medidaEspessuraMesa*Math.pow(medidaBase,3)/12);
                        double momentoInerciaY = momentoInerciaY1 + momentoInerciaY2 + momentoInerciaY3;

                        String textMomentoInerciaX = Utils.arredondar(momentoInerciaX);
                        String textMomentoInerciaY = Utils.arredondar(momentoInerciaY);
                        textViewIx.setText("Ix' = " + textMomentoInerciaX);
                        textViewIy.setText("Iy' = " + textMomentoInerciaY);

                        //Raio de giração
                        double raioGiracaoX = Math.sqrt((momentoInerciaX/areaTotal));
                        double raioGiracaoY = Math.sqrt((momentoInerciaY/areaTotal));
                        String textRaioGiracaoX = Utils.arredondar(raioGiracaoX);
                        String textRaioGiracaoY = Utils.arredondar(raioGiracaoY);
                        textViewix.setText("ix' = " + textRaioGiracaoX);
                        textViewiy.setText("iy' = " + textRaioGiracaoY);


                        //Módulo plastico
                        double moduloPlasticoX = (medidaBase*medidaEspessuraMesa*(medidaAltura-medidaEspessuraMesa)+0.25*medidaEspessuraAlma*Math.pow(medidaAltura-2*medidaEspessuraMesa,2));
                        double moduloPlasticoY = (0.5*medidaEspessuraMesa*Math.pow(medidaBase,2)*0.25*(medidaAltura-2*medidaEspessuraMesa)*Math.pow(medidaEspessuraAlma,2));
                        String textModuloPlasticoX = Utils.arredondar(moduloPlasticoX);
                        String textModuloPlasticoY = Utils.arredondar(moduloPlasticoY);
                        textViewZx.setText("Zx' = "+textModuloPlasticoX);
                        textViewZy.setText("Zy' = "+textModuloPlasticoY);

                        //Módulo elástico
                        double moduloElasticoX = moduloPlasticoX/1.12;
                        double moduloElasticoY = moduloPlasticoY/1.55;
                        String textModuloElasticoX = Utils.arredondar(moduloElasticoX);
                        String textModuloElasticoY = Utils.arredondar(moduloElasticoY);
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
