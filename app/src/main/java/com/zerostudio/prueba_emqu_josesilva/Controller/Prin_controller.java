package com.zerostudio.prueba_emqu_josesilva.Controller;

import android.content.Context;

import com.zerostudio.prueba_emqu_josesilva.Model.Model_user;

public class Prin_controller {
    public String obtenerimg(Context context){
        return Model_user.leerimagen(context);
    }
}
