package com.app.projetoic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageQuadrado;
    private ImageView imageQuadradoVazado;
    private ImageView imageRetangulo;
    private ImageView imageRetanguloVazado;
    private ImageView imageCirculo;
    private ImageView imageCirculoVazado;
    private ImageView imageTresQuartosCirculo;
    private ImageView imageSemicirculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar itens
        inicializarItens();

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageQuadrado:
                Intent intent = new Intent(MainActivity.this, TelaQuadradoActivity.class);
                startActivity(intent);
                break;
            case R.id.imageQuadradoVazado:
                Intent intent2 = new Intent(MainActivity.this, TelaQuadradoVazadoActivity.class);
                startActivity(intent2);
                break;
            case R.id.imageRetangulo:
                Intent intent3 = new Intent(MainActivity.this, TelaRetanguloActivity.class);
                startActivity(intent3);
                break;
            case R.id.imageRetanguloVazado:
                Intent intent4 = new Intent(MainActivity.this, TelaRetanguloVazadoActivity.class);
                startActivity(intent4);
                break;
            case R.id.imageCirculo:
                Intent intent5 = new Intent(MainActivity.this, TelaCirculoActivity.class);
                startActivity(intent5);
                break;

            case R.id.imageCirculoVazado:
                Intent intent6 = new Intent(MainActivity.this, TelaCirculoVazadoActivity.class);
                startActivity(intent6);
                break;

            case R.id.imageTresQuartosCirculo:
                Intent intent7 = new Intent(MainActivity.this, TresQuartosCirculoActivity.class);
                startActivity(intent7);
                break;
            case R.id.imageSemicirculo:
                /*Intent intent8 = new Intent(MainActivity.this, TelaSemicirculoActivity.class);
                startActivity(intent8);*/
                break;

        }
    }

    private void inicializarItens() {
        imageQuadrado = findViewById(R.id.imageQuadrado);
        imageQuadradoVazado = findViewById(R.id.imageQuadradoVazado);
        imageRetangulo = findViewById(R.id.imageRetangulo);
        imageRetanguloVazado = findViewById(R.id.imageRetanguloVazado);
        imageCirculo = findViewById(R.id.imageCirculo);
        imageCirculoVazado = findViewById(R.id.imageCirculoVazado);
        imageTresQuartosCirculo = findViewById(R.id.imageTresQuartosCirculo);
        imageSemicirculo = findViewById(R.id.imageSemicirculo);

        imageQuadrado.setOnClickListener(this);
        imageRetanguloVazado.setOnClickListener(this);
        imageRetangulo.setOnClickListener(this);
        imageQuadradoVazado.setOnClickListener(this);
        imageCirculo.setOnClickListener(this);
        imageCirculoVazado.setOnClickListener(this);
        imageTresQuartosCirculo.setOnClickListener(this);
        imageSemicirculo.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemConfiguracoes:
                Toast.makeText(getApplicationContext(), "Página configurações", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemSobre:
                Intent intent = new Intent(MainActivity.this, SobreActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Página sobre", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
