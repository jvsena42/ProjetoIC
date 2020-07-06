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

public class TelaRetanguloActivity extends AppCompatActivity {

    private EditText editTextBase;
    private EditText editTextAltura;
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
        setContentView(R.layout.activity_tela_retangulo);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextBase = findViewById(R.id.editTextBase);
        editTextAltura = findViewById(R.id.editTextAltura);
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
                String textBase = editTextBase.getText().toString();
                String textAltura = editTextAltura.getText().toString();

                if (!textBase.isEmpty() || !textAltura.isEmpty()) {

                    double medidaBase = Float.parseFloat(textBase);
                    double medidaAltura = Float.parseFloat(textAltura);

                    if (medidaBase > 0 && medidaAltura > 0) {

                        //Área
                        double area = medidaBase*medidaAltura;
                        String textArea = Utils.arredondar(area);
                        textViewArea.setText("Área = " +textArea);

                        //Perímetro
                        double perimetro = medidaBase*2+medidaAltura*2;
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaX =  (Math.pow(medidaAltura,3)*medidaBase/12);
                        double momentoInerciaY =  (Math.pow(medidaBase,3)*medidaAltura/12);
                        String textMomentoInerciaX = Utils.arredondar(momentoInerciaX);
                        String textMomentoInerciaY = Utils.arredondar(momentoInerciaY);
                        textViewIx.setText("Ix = " + textMomentoInerciaX);
                        textViewIy.setText("Iy = " + textMomentoInerciaY);

                        //Raio de giração
                        double raioGiracaoX = Math.sqrt((momentoInerciaX/area));
                        double raioGiracaoY = Math.sqrt((momentoInerciaY/area));
                        String textRaioGiracaoX = Utils.arredondar(raioGiracaoX);
                        String textRaioGiracaoY = Utils.arredondar(raioGiracaoY);
                        textViewix.setText("ix = " + textRaioGiracaoX);
                        textViewiy.setText("iy = " + textRaioGiracaoY);

                        //Módulo Plástico
                        double moduloPlasticoX = (Math.pow(medidaAltura,2)*medidaBase/4);
                        double moduloPlasticoY = (Math.pow(medidaBase,2)*medidaAltura/4);
                        String textModuloPlasticoX = Utils.arredondar(moduloPlasticoX);
                        String textModuloPlasticoY = Utils.arredondar(moduloPlasticoY);
                        textViewZx.setText("Zx = "+textModuloPlasticoX);
                        textViewZy.setText("Zy = "+textModuloPlasticoY);

                        //Módulo Elástico
                        double moduloElasticoX = (medidaBase*Math.pow(medidaAltura,2)/6);
                        double moduloElasticoY = (medidaAltura*Math.pow(medidaBase,2)/6);
                        String textModuloElasticoX = Utils.arredondar(moduloElasticoX);
                        String textModuloElasticoY = Utils.arredondar(moduloElasticoY);
                        textViewWx.setText("Wx = "+textModuloElasticoX);
                        textViewWy.setText("Wy = "+textModuloElasticoY);

                        //Limpar EditText
                        editTextBase.setText("");
                        editTextAltura.setText("");

                    } else {
                    Toast.makeText(TelaRetanguloActivity.this, "Utilize valores válidos!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TelaRetanguloActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}