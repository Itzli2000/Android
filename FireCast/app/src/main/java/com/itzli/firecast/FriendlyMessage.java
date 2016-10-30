package com.itzli.firecast;

/**
 * Created by itzli on 27/10/2016.
 * Obtenemos el nombre, imagen y texto del usuario.
 * Se  envia a recycler view
 */

public class FriendlyMessage {

    private String text;
    private String name;
    private String photoUrl;

    public FriendlyMessage(String s, String mUsername, String mPhotoUrl){

        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getName (){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }
    public String getPhotoUrl(){
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
}
