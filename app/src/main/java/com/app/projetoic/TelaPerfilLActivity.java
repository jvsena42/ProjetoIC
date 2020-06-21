package com.app.projetoic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaPerfilLActivity extends AppCompatActivity {

    private EditText editTextBase;
    private EditText editTextAltura;
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
    private TextView textViewCentroideX;
    private TextView textViewCentroideY;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_l);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextBase = findViewById(R.id.editTextBase);
        editTextAltura = findViewById(R.id.editTextAltura);
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
        textViewCentroideX = findViewById(R.id.textViewCentroideX);
        textViewCentroideY = findViewById(R.id.textViewCentroideY);
        buttonCalcular = findViewById(R.id.buttonCalcular);

        //Evento de clique
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textBase = editTextBase.getText().toString();
                String textAltura = editTextAltura.getText().toString();
                String textEspessura = editTextEspessura.getText().toString();

                if (!textBase.isEmpty() && !textAltura.isEmpty() && !textEspessura.isEmpty()){
                    float medidaBase = Float.parseFloat(textBase);
                    float medidaAltura = Float.parseFloat(textAltura);
                    float medidaEspessura = Float.parseFloat(textEspessura);
                    float medidaBaseInterna = medidaBase-(medidaEspessura);
                    float medidaAlturaInterna = medidaAltura-(medidaEspessura);

                    if (medidaBaseInterna>0 && medidaAlturaInterna>0){

                        //Área
                        float area1 = medidaBase*medidaEspessura;
                        float area2 = medidaEspessura*medidaAlturaInterna;
                        float areaTotal = area1 + area2;
                        String textArea = String.valueOf(areaTotal);
                        textViewArea.setText("Área = " +textArea);

                        //Centróide
                        float centroideX1 = medidaBase/2;
                        float centroideX2 = medidaEspessura/2;
                        float centroideX = centroideX1 + centroideX2;

                        float centroideY1 = medidaEspessura/2;
                        float centroideY2 = medidaEspessura + (medidaAlturaInterna/2);
                        float centroideY = centroideY1 + centroideY2;


                        String textCentroideX = String.valueOf(centroideX);
                        String textCentroideY = String.valueOf(centroideY);
                        textViewCentroideX.setText("Centróide X = "+textCentroideX);
                        textViewCentroideY.setText("Centróide Y = "+textCentroideY);


                        //Perímetro
                        float perimetro = medidaBase + medidaAltura + medidaBaseInterna + medidaAlturaInterna * 2*medidaEspessura;
                        String textPerimetro = String.valueOf(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);


                        //Momento de inercia
                        float momentoInerciaX1 = (float) ((medidaBase*Math.pow(medidaEspessura,3)/12)  + area1*Math.pow(centroideY-centroideY1,2));
                        float momentoInerciaX2 = (float) ((medidaEspessura*Math.pow(medidaAltura-medidaEspessura,3)/12)  + area2*Math.pow(centroideY-centroideY2,2));
                        float momentoInerciaX = momentoInerciaX1 + momentoInerciaX2;

                        float momentoInerciaY1 = (float) ((medidaEspessura*Math.pow(medidaBase,3)/12)  + area1*Math.pow(centroideX-centroideX1,2));
                        float momentoInerciaY2 = (float) (((medidaAltura-medidaEspessura)*Math.pow(medidaEspessura,3)/12)  + area2*Math.pow(centroideX-centroideX2,2));
                        float momentoInerciaY = momentoInerciaY1 + momentoInerciaY2;

                        String textMomentoInerciaX = String.valueOf(momentoInerciaX);
                        String textMomentoInerciaY = String.valueOf(momentoInerciaY);
                        textViewIx.setText("Ix = " + textMomentoInerciaX);
                        textViewIy.setText("Iy = " + textMomentoInerciaY);

                        //Raio de giração
                        float raioGiracaoX = (float) Math.sqrt((momentoInerciaY/areaTotal));
                        float raioGiracaoY = (float) Math.sqrt((momentoInerciaX/areaTotal));
                        String textRaioGiracaoX = String.valueOf(raioGiracaoX);
                        String textRaioGiracaoY = String.valueOf(raioGiracaoY);
                        textViewix.setText("ix = " + textRaioGiracaoX);
                        textViewiy.setText("iy = " + textRaioGiracaoY);

                        /*

                        //Módulo plastico
                        float moduloPlasticoX = (float) (Math.pow(medidaAltura,2)*medidaBase/4*(1-(1-2*medidaEspessura/medidaBase)*Math.pow(1-2*medidaEspessura/medidaAltura,2)));
                        float moduloPlasticoY = (float) (Math.pow(medidaBase,2)*medidaAltura/4*(1-(1-2*medidaEspessura/medidaAltura)*Math.pow(1-2*medidaEspessura/medidaBase,2)));
                        String textModuloPlasticoX = String.valueOf(moduloPlasticoX);
                        String textModuloPlasticoY = String.valueOf(moduloPlasticoY);
                        textViewZx.setText("Zx = "+textModuloPlasticoX);
                        textViewZy.setText("Zy = "+textModuloPlasticoY);

                        //Módulo elástico
                        float moduloElasticoX = (float) ((medidaBase*medidaAltura)*medidaEspessura + (Math.pow(medidaBase,2)*medidaEspessura/3));
                        float moduloElasticoY = (float) ((medidaBase*medidaAltura)*medidaEspessura + (Math.pow(medidaAltura,2)*medidaEspessura/3));
                        String textModuloElasticoX = String.valueOf(moduloElasticoX);
                        String textModuloElasticoY = String.valueOf(moduloElasticoY);
                        textViewWx.setText("Wx = "+textModuloElasticoX);
                        textViewWy.setText("Wx = "+textModuloElasticoY);
                        */
                        //Limpar EditText
                        editTextBase.setText("");
                        editTextAltura.setText("");
                        editTextEspessura.setText("");
                    }else {
                        Toast.makeText(TelaPerfilLActivity.this, "Erro! digite uma espessura menor ou medidas maiores para os lados", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(TelaPerfilLActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}