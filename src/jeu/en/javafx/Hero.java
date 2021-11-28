/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.en.javafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Aabidi Hassan
 */
public class Hero extends Entete{
    private Blood blood;
    public Hero(Zone z){
        corps = new ImageView(new Image(getClass().getResourceAsStream("photos/Hero.png")));
//        ((ImageView)corps).setX(0);
//        ((ImageView)corps).setY(0);
//        double x = z.getX()+(z.getX1()-z.getX())*Math.random();
//        double y = z.getY()+(z.getY1()-z.getY())*Math.random();
//        ((ImageView)corps).setTranslateX(x);
//        ((ImageView)corps).setTranslateY(y);
        position(z);
        this.blood = new Blood();
    }
    public Blood getBlood() {
        return blood;
    }
   
}
