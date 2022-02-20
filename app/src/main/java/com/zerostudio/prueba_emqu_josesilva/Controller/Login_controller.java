package com.zerostudio.prueba_emqu_josesilva.Controller;

import android.content.Context;

import com.zerostudio.prueba_emqu_josesilva.Model.Model_user;

public class Login_controller {

    public void guardarimagen(Context context,String image){
        Model_user.actualizar_imagen(context,image);
    }
    public String obtenerimg(Context context){
        return Model_user.leerimagen(context);
    }
    public void crearimg(Context context,String image){
        Model_user.escribir_image(context,image);
    }
}
