package com.zerostudio.prueba_emqu_josesilva.Util;

public class Util {
    public static final String idchannel="channelID";
    public static final int noti_id=1;
    public static final String channelname="notificacionempre";
    public static final String channeldescrip="Tiene nueva información acerca de nuestra empresa";
    public static final String COLUMNA_id="id";
    public static final String COLUMNA_image="image";
    public static final String TABLE_USER="user";
    public static final String CREATE_USER="create table "+TABLE_USER+" ("+COLUMNA_id
            +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+COLUMNA_image+" TEXT)";
    public static final String INSER_IMG="insert into "+TABLE_USER+" ("+COLUMNA_id+
            ","+COLUMNA_image+")values ("+1+",'')";


}
