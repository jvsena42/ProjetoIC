package com.app.projetoic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaPerfilCActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_tela_perfil_c);

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
                    float medidaAlturaInterna = medidaAltura-(2*medidaEspessura);

                    if (medidaBaseInterna>0 && medidaAlturaInterna>0){

                        //Área
                        float area1 = medidaEspessura*medidaBase;
                        float area2 = medidaAlturaInterna*medidaEspessura;
                        float areaTotal = 2*area1 + area2;
                        String textArea = String.valueOf(areaTotal);
                        textViewArea.setText("Área = " +textArea);

                        //Centróide
                        float centroideX1 = medidaBase/2;
                        float centroideX2 = medidaEspessura/2;
                        float centroideX = (2*(area1*centroideX1)+area2*centroideX2)/areaTotal;

                        float centroideY1 = medidaEspessura/2;
                        float centroideY2 = medidaAltura/2;
                        float centroideY3 = medidaAltura - (medidaEspessura/2);
                        float centroideY = (area1*centroideY1+area2*centroideY2+area1*centroideY3)/areaTotal;

                        String textCentroideX = String.valueOf(centroideX);
                        String textCentroideY = String.valueOf(centroideY);
                        textViewCentroideX.setText("X' = "+textCentroideX);
                        textViewCentroideY.setText("Y' = "+textCentroideY);

                        //Perímetro
                        float perimetro = (2*medidaBase) + (2*medidaBaseInterna) + (2*medidaEspessura)+ medidaAltura + medidaAlturaInterna;
                        String textPerimetro = String.valueOf(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                        //Momento de inercia
                        float momentoInerciaX1 = (float) (medidaBase*Math.pow(medidaEspessura,3)/12 + area1*Math.pow(centroideY-centroideY1,2));
                        float momentoInerciaX2 = (float) (medidaEspessura*Math.pow(medidaAltura-2*medidaEspessura,3)/12);
                        float momentoInerciaX3 = (float) (medidaBase*Math.pow(medidaEspessura,3)/12 + area1*Math.pow(centroideY-centroideY3,2));
                        float momentoInerciaX = momentoInerciaX1 + momentoInerciaX2 + momentoInerciaX3;

                        float momentoInerciaY1 = (float) (medidaEspessura*Math.pow(medidaBase,3)/12 + area1*Math.pow(centroideX-centroideX1,2));
                        float momentoInerciaY2 = (float) ((medidaAlturaInterna)*Math.pow(medidaEspessura,3)/12 + area2*Math.pow(centroideX-centroideX2,2));
                        float momentoInerciaY3 = (float) (medidaEspessura*Math.pow(medidaBase,3)/12 + area1*Math.pow(centroideX-centroideX1,2));
                        float momentoInerciaY = momentoInerciaY1 + momentoInerciaY2 + momentoInerciaY3;

                        String textMomentoInerciaX = String.valueOf(momentoInerciaX);
                        String textMomentoInerciaY = String.valueOf(momentoInerciaY);
                        textViewIx.setText("Ix' = " + textMomentoInerciaX);
                        textViewIy.setText("Iy' = " + textMomentoInerciaY);

                        //Raio de giração
                        double raioGiracaoX = Math.sqrt(momentoInerciaX/areaTotal);
                        double raioGiracaoY = Math.sqrt(momentoInerciaY/areaTotal);
                        String textRaioGiracaoX = String.valueOf(raioGiracaoX);
                        String textRaioGiracaoY = String.valueOf(raioGiracaoY);
                        textViewix.setText("ix' = " + textRaioGiracaoX);
                        textViewiy.setText("iy' = " + textRaioGiracaoY);

                        //Módulo plastico
                        float moduloPlasticoX = (float) (medidaBase*medidaEspessura*(medidaAltura-medidaEspessura)+medidaEspessura*Math.pow(0.5*medidaAltura-medidaEspessura,2));
                        float moduloPlasticoY = (float) (medidaEspessura*Math.pow(medidaBase-centroideX,2) + medidaEspessura*(medidaAltura-2*medidaEspessura)*(centroideX-0.5*medidaEspessura)+medidaEspessura*Math.pow(centroideX,2));
                        String textModuloPlasticoX = String.valueOf(moduloPlasticoX);
                        String textModuloPlasticoY = String.valueOf(moduloPlasticoY);
                        textViewZx.setText("Zx' = "+textModuloPlasticoX);
                        textViewZy.setText("Zy' = "+textModuloPlasticoY);

                        //Módulo elástico
                        float moduloElasticoX = 2*momentoInerciaX/medidaAltura;
                        float moduloElasticoY = momentoInerciaY/(medidaBase-centroideY);
                        String textModuloElasticoX = String.valueOf(moduloElasticoX);
                        String textModuloElasticoY = String.valueOf(moduloElasticoY);
                        textViewWx.setText("Wx' = "+textModuloElasticoX);
                        textViewWy.setText("Wx' = "+textModuloElasticoY);

                        //Limpar EditText
                        editTextBase.setText("");
                        editTextAltura.setText("");
                        editTextEspessura.setText("");
                    }else {
                        Toast.makeText(TelaPerfilCActivity.this, "Erro! digite uma espessura menor ou medidas maiores para os lados", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(TelaPerfilCActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
