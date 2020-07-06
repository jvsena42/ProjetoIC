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
                    double medidaBase = Float.parseFloat(textBase);
                    double medidaAltura = Float.parseFloat(textAltura);
                    double medidaEspessura = Float.parseFloat(textEspessura);
                    double medidaBaseInterna = medidaBase-(medidaEspessura);
                    double medidaAlturaInterna = medidaAltura-(2*medidaEspessura);

                    if (medidaBaseInterna>0 && medidaAlturaInterna>0){

                        //Área
                        double area1 = medidaEspessura*medidaBase;
                        double area2 = medidaAlturaInterna*medidaEspessura;
                        double areaTotal = 2*area1 + area2;
                        String textArea = Utils.arredondar(areaTotal);
                        textViewArea.setText("Área = " +textArea);

                        //Centróide
                        double centroideX1 = medidaBase/2;
                        double centroideX2 = medidaEspessura/2;
                        double centroideX = (2*(area1*centroideX1)+area2*centroideX2)/areaTotal;

                        double centroideY1 = medidaEspessura/2;
                        double centroideY2 = medidaAltura/2;
                        double centroideY3 = medidaAltura - (medidaEspessura/2);
                        double centroideY = (area1*centroideY1+area2*centroideY2+area1*centroideY3)/areaTotal;

                        String textCentroideX = Utils.arredondar(centroideX);
                        String textCentroideY = Utils.arredondar(centroideY);
                        textViewCentroideX.setText("X' = "+textCentroideX);
                        textViewCentroideY.setText("Y' = "+textCentroideY);

                        //Perímetro
                        double perimetro = (2*medidaBase) + (2*medidaBaseInterna) + (2*medidaEspessura)+ medidaAltura + medidaAlturaInterna;
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaX1 = (medidaBase*Math.pow(medidaEspessura,3)/12 + area1*Math.pow(centroideY-centroideY1,2));
                        double momentoInerciaX2 = (medidaEspessura*Math.pow(medidaAltura-2*medidaEspessura,3)/12);
                        double momentoInerciaX3 = (medidaBase*Math.pow(medidaEspessura,3)/12 + area1*Math.pow(centroideY-centroideY3,2));
                        double momentoInerciaX = momentoInerciaX1 + momentoInerciaX2 + momentoInerciaX3;

                        double momentoInerciaY1 = (medidaEspessura*Math.pow(medidaBase,3)/12 + area1*Math.pow(centroideX-centroideX1,2));
                        double momentoInerciaY2 = ((medidaAlturaInterna)*Math.pow(medidaEspessura,3)/12 + area2*Math.pow(centroideX-centroideX2,2));
                        double momentoInerciaY3 = (medidaEspessura*Math.pow(medidaBase,3)/12 + area1*Math.pow(centroideX-centroideX1,2));
                        double momentoInerciaY = momentoInerciaY1 + momentoInerciaY2 + momentoInerciaY3;

                        String textMomentoInerciaX = Utils.arredondar(momentoInerciaX);
                        String textMomentoInerciaY = Utils.arredondar(momentoInerciaY);
                        textViewIx.setText("Ix' = " + textMomentoInerciaX);
                        textViewIy.setText("Iy' = " + textMomentoInerciaY);

                        //Raio de giração
                        double raioGiracaoX = Math.sqrt(momentoInerciaX/areaTotal);
                        double raioGiracaoY = Math.sqrt(momentoInerciaY/areaTotal);
                        String textRaioGiracaoX = Utils.arredondar(raioGiracaoX);
                        String textRaioGiracaoY = Utils.arredondar(raioGiracaoY);
                        textViewix.setText("ix' = " + textRaioGiracaoX);
                        textViewiy.setText("iy' = " + textRaioGiracaoY);

                        //Módulo plastico
                        double moduloPlasticoX = (medidaBase*medidaEspessura*(medidaAltura-medidaEspessura)+medidaEspessura*Math.pow(0.5*medidaAltura-medidaEspessura,2));
                        double moduloPlasticoY = (medidaEspessura*Math.pow(medidaBase-centroideX,2) + medidaEspessura*(medidaAltura-2*medidaEspessura)*(centroideX-0.5*medidaEspessura)+medidaEspessura*Math.pow(centroideX,2));
                        String textModuloPlasticoX = Utils.arredondar(moduloPlasticoX);
                        String textModuloPlasticoY = Utils.arredondar(moduloPlasticoY);
                        textViewZx.setText("Zx' = "+textModuloPlasticoX);
                        textViewZy.setText("Zy' = "+textModuloPlasticoY);

                        //Módulo elástico
                        double moduloElasticoX = 2*momentoInerciaX/medidaAltura;
                        double moduloElasticoY = momentoInerciaY/(medidaBase-centroideY);
                        String textModuloElasticoX = Utils.arredondar(moduloElasticoX);
                        String textModuloElasticoY = Utils.arredondar(moduloElasticoY);
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