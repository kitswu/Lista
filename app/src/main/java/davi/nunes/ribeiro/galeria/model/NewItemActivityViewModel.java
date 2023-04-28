package davi.nunes.ribeiro.galeria.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    Uri selectedPhotoLocation = null;

    public Uri getSelectedPhotoLocation(){
        return selectedPhotoLocation; // retorna o endereço uri da foto escolhida
    }

    public void setSelectedPhotoLocation(Uri selectedPhotoLocation){
        this.selectedPhotoLocation = selectedPhotoLocation; // seta o endereço uri da foto escolhida
    }
}
