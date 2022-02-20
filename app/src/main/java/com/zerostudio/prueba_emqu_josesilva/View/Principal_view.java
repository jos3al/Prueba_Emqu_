package com.zerostudio.prueba_emqu_josesilva.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import com.squareup.picasso.Picasso;
import com.zerostudio.prueba_emqu_josesilva.Controller.Prin_controller;
import com.zerostudio.prueba_emqu_josesilva.R;
import com.zerostudio.prueba_emqu_josesilva.Util.Servicio;
import com.zerostudio.prueba_emqu_josesilva.databinding.ActivityPrincipalViewBinding;
import com.zerostudio.prueba_emqu_josesilva.Util.Data_entrada;
import com.zerostudio.prueba_emqu_josesilva.Util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class Principal_view extends AppCompatActivity {
    List<Data_entrada> data_entrada=new ArrayList<>();
    ActivityPrincipalViewBinding binding;
    ArrayList<String>labelxis=new ArrayList<>();
    Prin_controller prin_c;
    Notification noti;
    String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding=ActivityPrincipalViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar ab=getSupportActionBar();
        binding.tvFecha.setText(this.obtenerfecha());
        prin_c=new Prin_controller();
        img=prin_c.obtenerimg(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view =getSupportActionBar().getCustomView();
        ImageButton iconuser=(ImageButton)view.findViewById(R.id.action_bar_icon);
        TextView tvab=(TextView)view.findViewById(R.id.tv_ab_user);
        tvab.setText("Admin");
        Picasso.get().load(Uri.parse(img)).fit().into(iconuser);
        generar_accion_valor();
        gethora();
        seleccionar_grafica(0);
        Picasso.get().load(R.drawable.b1).into(binding.imgvAd);
        binding.btnLun.setOnClickListener(v->seleccionar_grafica(0));
        binding.btnMar.setOnClickListener(v->seleccionar_grafica(1));
        binding.btnMier.setOnClickListener(v->seleccionar_grafica(2));
        binding.btnJue.setOnClickListener(v->seleccionar_grafica(3));
        binding.btnVier.setOnClickListener(v->seleccionar_grafica(4));
        binding.btnSab.setOnClickListener(v->seleccionar_grafica(5));
        binding.btnDom.setOnClickListener(v->seleccionar_grafica(6));
        Intent ser=new Intent(this,Servicio.class);
        startService(ser);
    }


    public void generar_accion_valor(){
        for(int i=0;i<7;i++){
            Data_entrada data_entrada1=new Data_entrada();
            data_entrada.add(data_entrada1);
        }
    }

    public void seleccionar_grafica(int dia){
        int btn=0;
        for(int i=0;i<binding.clPrin.getChildCount();i++){
            try{
                Button btn_grafica=(Button) binding.clPrin.getChildAt(i);
                if(btn==dia){
                    dibujar_grafica(dia);
                    btn_grafica.setBackgroundColor(getResources().getColor(R.color.enabled,getTheme()));
                }
                else{
                    btn_grafica.setBackgroundColor(getResources().getColor(R.color.disable,getTheme()));
                }
                btn++;
            }
            catch (Exception e){
                //
            }
        }
    }

    public void dibujar_grafica(int dia){
        binding.linechart.setData(null);
        LineDataSet dataSet=new LineDataSet(data_entrada.get(dia).getEntrada(),"leyenda");
        dataSet.setColor(Color.BLUE);
        dataSet.setDrawHorizontalHighlightIndicator(false);
        dataSet.setDrawVerticalHighlightIndicator(false);
        dataSet.setDrawCircles(false);
        dataSet.setDrawHighlightIndicators(false);
        dataSet.setDrawValues(false);
        dataSet.setDrawFilled(true);
        LineData lineData=new LineData(dataSet);
        binding.linechart.setData(lineData);
        binding.linechart.setScaleEnabled(false);
        XAxis xAxis=binding.linechart.getXAxis();
        XAxis.XAxisPosition position=XAxis.XAxisPosition.BOTTOM;
        xAxis.setPosition(position);
        xAxis.setLabelCount(5);
        xAxis.setAxisMaximum(5);
        xAxis.setAxisMinimum(0);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelxis));
        YAxis yAxis=binding.linechart.getAxisRight();
        yAxis.setEnabled(false);
        binding.linechart.setDescription(null);
        binding.linechart.getLegend().setEnabled(false);
        binding.linechart.notifyDataSetChanged();
        binding.linechart.invalidate();
    }
    public void gethora(){
        labelxis.add("10:00");
        labelxis.add("11:00");
        labelxis.add("12:00");
        labelxis.add("13:00");
        labelxis.add("14:00");
    }

    public String obtenerfecha(){
        String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String dia_semana_name[]={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        Calendar calendar=Calendar.getInstance();
        int mes=calendar.get(Calendar.MONTH);
        int dia=calendar.get(Calendar.DAY_OF_MONTH);
        int year=calendar.get(Calendar.YEAR);
        int dia_semana=calendar.get(Calendar.DAY_OF_WEEK);
        return getString(R.string.mensaje_fecha,dia_semana_name[dia_semana-1],dia,meses[mes],year);
    }



}