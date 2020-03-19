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
                break;

        }
    }

    private void inicializarItens() {
        imageQuadrado = findViewById(R.id.imageQuadrado);
        imageQuadradoVazado = findViewById(R.id.imageQuadradoVazado);
        imageRetangulo = findViewById(R.id.imageRetangulo);
        imageRetanguloVazado = findViewById(R.id.imageRetanguloVazado);

        imageQuadrado.setOnClickListener(this);
        imageRetanguloVazado.setOnClickListener(this);
        imageRetangulo.setOnClickListener(this);
        imageQuadradoVazado.setOnClickListener(this);
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
