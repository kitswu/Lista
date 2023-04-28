package davi.nunes.ribeiro.galeria.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import davi.nunes.ribeiro.galeria.R;
import davi.nunes.ribeiro.galeria.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class); // obtem o view model de new item activity
        
        Uri selectedPhotoLocation = vm.getSelectedPhotoLocation(); // obtem o URI do view model
        if (selectedPhotoLocation != null){
            ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview); //obtem o image view
            imvPhotoPreview.setImageURI(selectedPhotoLocation); // seta a imagem no image view
        }

        ImageButton imbCl = findViewById(R.id.imbCI);
        imbCl.setOnClickListener(new View.OnClickListener() { // detecta o click no botão
            @Override
            public void onClick(View view) { // método que roda quando o botão é clicado
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); // cria intent com ação de abrir documento
                photoPickerIntent.setType("image/*"); // define o tipo de documento como imagens
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST); // executa o intent
            }
        });

        Button btnAddItem = findViewById(R.id.btnAddItem); // obtem o button de adicionar item
        EditText etTitle = findViewById(R.id.etTitle); // obtem o titulo
        EditText etDesc = findViewById(R.id.etDesc); // obtem a descrição

        btnAddItem.setOnClickListener(new View.OnClickListener() { // detecta o click no botão
            @Override
            public void onClick(View view) { // método que roda quando o botão é clicado
                String title = etTitle.getText().toString(); // converte o titulo para string
                //System.out.println(title);
                String desc = etDesc.getText().toString(); // converte a descrição para string
                //System.out.println(desc);
                NewItemActivityViewModel vm = new ViewModelProvider(NewItemActivity.this).get(NewItemActivityViewModel.class); // obtem o view model de new item activity

                Uri selectedPhotoLocation = vm.getSelectedPhotoLocation(); // obtem o URI do view model

                if (selectedPhotoLocation == null){ // se nenhuma foto tiver sido escolhida
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (title.isEmpty()){ // se nenhum titulo for inserido
                    Toast.makeText(NewItemActivity.this, "É necessário adicionar um título", Toast.LENGTH_LONG).show();
                    return;
                }
                if (desc.isEmpty()){ // se nenhuma descrição for inserida
                    Toast.makeText(NewItemActivity.this, "É necessário adicionar uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(); // cria nova intent que guarda os dados que vao voltar para a main activity
                i.setData(selectedPhotoLocation); // coloca a uri da foto escolhida na intent
                i.putExtra("title", title); // coloca o título na intent
                i.putExtra("desc", desc); // coloca a descrição na intent
                setResult(Activity.RESULT_OK, i); // seta o resultado da intent como o resultado de sucesso
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST){ // verifica se o request code é igual ao enviado na startActivityForResult
            if (resultCode == Activity.RESULT_OK){ // verifica se o result code é igual ao código de operação bem sucedida
                Uri photoSelected = data.getData(); // obtemos o uri da imagem escolhida
                ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview); // pega a image view

                imvPhotoPreview.setImageURI(photoSelected); // seta a imagem da image view
                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                vm.setSelectedPhotoLocation(photoSelected); // guarda a foto selecionada no view model
            }
        }
    }

    ;
}