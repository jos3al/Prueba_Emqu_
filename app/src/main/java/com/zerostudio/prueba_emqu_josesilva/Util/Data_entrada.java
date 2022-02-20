package com.zerostudio.prueba_emqu_josesilva.Util;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class Data_entrada {
    private List<Entry> entrada=new ArrayList<>();
    private final int posi;
    private Double[] valor_accion;
    public Data_entrada(){
        this.posi=50;

            for(int i=0;i<posi;i++){
              //  Random generador=new Random(System.currentTimeMillis());
               // entrada.add(new Entry(i,1+(100-1)*generador.nextInt()));
                entrada.add(new Entry(i,(int)(Math.random()*100)+1));
            }

    }

    public List<Entry> getEntrada() {
        return entrada;
    }

    public void setEntrada(List<Entry> entrada) {
        this.entrada = entrada;
    }

    public int getPosi() {
        return posi;
    }

}
