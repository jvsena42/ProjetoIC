package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TelaCirculoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_tela_circulo);

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
                    double area = (Math.PI*Math.pow(medidaRaio,2));
                    String textArea = String.valueOf(area);
                    textViewArea.setText("Área = " +textArea);

                    //Perímetro
                    double perimetro = (Math.PI*2*medidaRaio);
                    String textPerimetro = String.valueOf(perimetro);
                    textViewPerimetro.setText("P. Ext.= " + textPerimetro);

                    //Momento de inercia
                    double momentoInercia = (Math.PI*Math.pow(medidaRaio,4)/4);
                    String textMomentoInercia = String.valueOf(momentoInercia);
                    textViewIx.setText("Ix = " + textMomentoInercia);
                    textViewIy.setText("Iy = " + textMomentoInercia);

                    //Raio de giração
                    double raioGiracao = medidaRaio/2;
                    String textRaioGiracao = String.valueOf(raioGiracao);
                    textViewix.setText("ix = " + textRaioGiracao);
                    textViewiy.setText("iy = " + textRaioGiracao);

                    //Módulo Plástico
                    double moduloPlastico = (4*Math.pow(medidaRaio,3)/3);
                    String textModuloPlastico = String.valueOf(moduloPlastico);
                    textViewZx.setText("Zx = "+textModuloPlastico);
                    textViewZy.setText("Zy = "+textModuloPlastico);

                    //Módulo Elástico
                    double moduloElastico = (Math.PI*Math.pow(medidaRaio,3)/4);
                    String textModuloElastico = String.valueOf(moduloElastico);
                    textViewWx.setText("Wx = "+textModuloElastico);
                    textViewWy.setText("Wy = "+textModuloElastico);

                }else {
                    Toast.makeText(TelaCirculoActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
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
                editTextRaio.setText("");
                break;
            case R.id.idNotacao:
                Intent intent2 = new Intent(this, NotacoesActivity.class);
                startActivity(intent2);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
