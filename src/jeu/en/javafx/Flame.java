/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.en.javafx;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Aabidi Hassan
 */
public class Flame extends Entete{

    public Flame() {
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("photos/fire.png")));
        img.setFitHeight(50);
        img.setFitWidth(50);
        super.corps = img;
        double x = 0+(800-0)*Math.random();
        ((ImageView)corps).setTranslateX(x);
        ((ImageView)corps).setTranslateY(0);
    }
    public void hbet(){
        Line path = new Line(corps.getTranslateX(), 0, corps.getTranslateX(),800);
        PathTransition trans = new PathTransition(Duration.seconds(5), path, this.corps);
        trans.setOrientation(PathTransition.OrientationType.NONE);
        trans.play(); 
    }
    
}
