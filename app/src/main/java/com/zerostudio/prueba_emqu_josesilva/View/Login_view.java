package com.zerostudio.prueba_emqu_josesilva.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;
import com.zerostudio.prueba_emqu_josesilva.Controller.Login_controller;
import com.zerostudio.prueba_emqu_josesilva.R;
import com.zerostudio.prueba_emqu_josesilva.databinding.ActivityLogInBinding;
import java.util.Objects;

public class Login_view extends AppCompatActivity {
    ActivityLogInBinding binding_login;
    String user="Admin";
    String password="Admin";
    final int permiso_storage=100;
    String uri_image="";
    boolean permiso_otorgado=false;
    ActivityResultLauncher<String> getpic=registerForActivityResult(new ActivityResultContracts.GetContent(),
            result -> {
                if(result!=null){
                    uri_image=result.toString();
                    Picasso.get().load(result).fit().into(binding_login.imgbAvatar);
                }
            });
    Login_controller login_c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding_login=ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding_login.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding_login.imgbAvatar.setOnClickListener(v->{verificar_permiso();
        obtener_imagen();});
        binding_login.imgbCamera.setOnClickListener(v->{verificar_permiso();
        obtener_imagen();});
        binding_login.btnNext.setOnClickListener(v->next());
        login_c=new Login_controller();
        uri_image=login_c.obtenerimg(this);
        if(uri_image.equals("")){
            login_c.crearimg(this,"");
        }
    }

    public void obtener_imagen(){
        if(permiso_otorgado)
             getpic.launch("image/*");
    }
    public void verificar_permiso(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED){
            permiso_otorgado=true;
        }
        else if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
            obtener_permiso();
        }
        else{
            obtener_permiso();
        }
    }
    public void obtener_permiso(){
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},this.permiso_storage);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==permiso_storage){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                permiso_otorgado=true;
            }
            else{
                this.Mensaje_explicacion();
            }
        }
    }


    public void Mensaje_explicacion(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(R.string.msj_permiso);
        builder.setTitle(R.string.title_dialog);
        builder.setNeutralButton("Ok",null);
        builder.create().show();
    }

    public void next(){
       // Intent prin=new Intent(this,Principal_view.class);
       // startActivity(prin);
        int pass=0;
        for(int i=0;i<binding_login.clLog.getChildCount();i++){
            try{
                TextInputEditText textInputEditText=(TextInputEditText) binding_login.clLog.getChildAt(i);
                if(Objects.requireNonNull(textInputEditText.getText()).toString().equals("")){
                pass++;
                }
            }
            catch (Exception e){
                //
            }
        }
        if(pass==0 && !uri_image.equals("")){
            if(Objects.requireNonNull(binding_login.tieUser.getText()).toString().equals(user)&&
            Objects.requireNonNull(binding_login.tiePassword.getText()).toString().equals(password)){
                login_c.guardarimagen(this,uri_image);
                Intent prin=new Intent(this,Principal_view.class);
                startActivity(prin);
            }
            else{
                Toast.makeText(this,getString(R.string.toast_dat_invalidos),Toast.LENGTH_SHORT).show();
            }
        }
        else if(uri_image.equals("")){
            Toast.makeText(this,getString(R.string.toast_avatarvacio),Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,getString(R.string.toast_vacio),Toast.LENGTH_SHORT).show();
        }
    }
}