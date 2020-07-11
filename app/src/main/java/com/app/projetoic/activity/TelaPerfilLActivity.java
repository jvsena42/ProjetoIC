package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.projetoic.R;
import com.app.projetoic.helper.Utils;

public class TelaPerfilLActivity extends AppCompatActivity {

    private EditText editTextBase;
    private EditText editTextEspessura;
    private TextView textViewArea;
    private TextView textViewPerimetro;
    private TextView textViewIx;
    private TextView textViewIy;
    private TextView textViewIZ;
    private TextView textViewIMin;
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
        editTextEspessura = findViewById(R.id.editTextEspessura);
        textViewArea = findViewById(R.id.textViewArea);
        textViewPerimetro = findViewById(R.id.textViewPerimetro);
        textViewIx = findViewById(R.id.textViewIx);
        textViewIy = findViewById(R.id.textViewIy);
        textViewIZ = findViewById(R.id.textViewIz);
        textViewIMin = findViewById(R.id.textViewImin);
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
                String textEspessura = editTextEspessura.getText().toString();

                if (!textBase.isEmpty() && !textEspessura.isEmpty()) {
                    double medidaBase = Float.parseFloat(textBase);
                    double medidaAltura = medidaBase;
                    double medidaEspessura = Float.parseFloat(textEspessura);
                    double medidaBaseInterna = medidaBase - (medidaEspessura);
                    double medidaAlturaInterna = medidaAltura - (medidaEspessura);

                    if (medidaBaseInterna > 0 && medidaAlturaInterna > 0) {

                        //Área
                        double area1 = medidaBase * medidaEspessura;
                        double area2 = medidaEspessura * medidaAlturaInterna;
                        double areaTotal = area1 + area2;
                        String textArea = Utils.arredondar(areaTotal);
                        textViewArea.setText("Área = " + textArea);

                        //Centróide
                        double centroideX1 = medidaBase / 2;
                        double centroideX2 = medidaEspessura / 2;
                        double centroideX = (centroideX1 * area1 + centroideX2 * area2) / areaTotal;

                        double centroideY1 = medidaEspessura / 2;
                        double centroideY2 = medidaEspessura + (medidaAlturaInterna / 2);
                        double centroideY = (centroideY1 * area1 + centroideY2 * area2) / areaTotal;

                        String textCentroideX = Utils.arredondar(centroideX);
                        String textCentroideY = Utils.arredondar(centroideY);
                        textViewCentroideX.setText("X' = " + textCentroideX);
                        textViewCentroideY.setText("Y' = " + textCentroideY);

                        //Perímetro
                        double perimetro = medidaBase + medidaAltura + medidaBaseInterna + medidaAlturaInterna + (2 * medidaEspessura);
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaX1 = ((medidaBase * Math.pow(medidaEspessura, 3) / 12) + area1 * Math.pow(centroideY - centroideY1, 2));
                        double momentoInerciaX2 = ((medidaEspessura * Math.pow(medidaAltura - medidaEspessura, 3) / 12) + area2 * Math.pow(centroideY - centroideY2, 2));
                        double momentoInerciaX = momentoInerciaX1 + momentoInerciaX2;

                        double momentoInerciaY1 = ((medidaEspessura * Math.pow(medidaBase, 3) / 12) + area1 * Math.pow(centroideX - centroideX1, 2));
                        double momentoInerciaY2 = (((medidaAltura - medidaEspessura) * Math.pow(medidaEspessura, 3) / 12) + area2 * Math.pow(centroideX - centroideX2, 2));
                        double momentoInerciaY = momentoInerciaY1 + momentoInerciaY2;

                        double Ixy = area1 * (centroideX1 - centroideX) * (centroideY1 - centroideY) + area2 * (centroideX2 - centroideX) * (centroideY2 - centroideY);
                        double Imin = momentoInerciaX - Math.abs(Ixy);
                        double IZ = Imin / areaTotal;

                        String textMomentoInerciaX = Utils.arredondar(momentoInerciaX);
                        String textMomentoInerciaY = Utils.arredondar(momentoInerciaY);
                        String textMomentoInerciaMin = Utils.arredondar(Imin);
                        String textMomentoInerciaZ = Utils.arredondar(IZ);
                        textViewIx.setText("Ix' = " + textMomentoInerciaX);
                        textViewIy.setText("Iy' = " + textMomentoInerciaY);
                        textViewIMin.setText("Imin' = " + textMomentoInerciaMin);
                        textViewIZ.setText("Iz = " + textMomentoInerciaZ);

                        //Raio de giração
                        double raioGiracaoX = Math.sqrt((momentoInerciaX / areaTotal));
                        //float raioGiracaoY = (float) Math.sqrt((momentoInerciaY/areaTotal));
                        double raioGiracaoZ = Math.sqrt((Imin / areaTotal));
                        String textRaioGiracaoX = Utils.arredondar(raioGiracaoX);
                        //String textRaioGiracaoY = String.valueOf(raioGiracaoY);
                        String textRaioGiracaoZ = Utils.arredondar(raioGiracaoZ);
                        textViewix.setText("ix'=iy'= " + textRaioGiracaoX);
                        textViewiy.setText("iz' = " + textRaioGiracaoZ);
                        //textViewiz.setText("iy' = " + textRaioGiracaoZ);

                        //Módulo plastico
                        double moduloPlasticoX = (0.5 * medidaEspessura * Math.pow(medidaAltura - centroideY, 2) + 0.5 * medidaEspessura * Math.pow(centroideY, 2) + medidaEspessura * (medidaBase - medidaEspessura) * (centroideY - 0.5 * medidaEspessura));
                        double moduloPlasticoY = (0.5 * medidaEspessura * Math.pow(medidaBase - centroideX, 2) + 0.5 * medidaEspessura * Math.pow(centroideX, 2) + medidaEspessura * (medidaAltura - medidaEspessura) * (centroideX - 0.5 * medidaEspessura));
                        String textModuloPlasticoX = Utils.arredondar(moduloPlasticoX);
                        String textModuloPlasticoY = Utils.arredondar(moduloPlasticoY);
                        textViewZx.setText("Zx' = " + textModuloPlasticoX);
                        textViewZy.setText("Zy' = " + textModuloPlasticoY);

                        //Módulo elástico
                        double moduloElasticoX = momentoInerciaX / (medidaAltura - centroideY);
                        double moduloElasticoY = momentoInerciaY / (medidaBase - centroideX);
                        String textModuloElasticoX = Utils.arredondar(moduloElasticoX);
                        String textModuloElasticoY = Utils.arredondar(moduloElasticoY);
                        textViewWx.setText("Wx' = " + textModuloElasticoX);
                        textViewWy.setText("Wx' = " + textModuloElasticoY);

                    } else {
                        Toast.makeText(TelaPerfilLActivity.this, "Erro! digite uma espessura menor ou medidas maiores para os lados", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(TelaPerfilLActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_secundario, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_limpar:

                //Limpar EditText
                editTextBase.setText("");
                editTextEspessura.setText("");
                break;
            case R.id.idNotacao:
                Intent intent2 = new Intent(this, NotacoesActivity.class);
                startActivity(intent2);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
