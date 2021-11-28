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
public class Monstre extends Entete{
    static private int nombres = 0;
    private Blood blood;
    
    
    public Monstre(Zone z){
        corps = new ImageView(new Image(getClass().getResourceAsStream("photos/Monstre.png")));
        ((ImageView)corps).setX(0);
        ((ImageView)corps).setY(0);
        double x = z.getX()+(z.getX1()-z.getX())*Math.random();
        double y = z.getY()+(z.getY1()-z.getY())*Math.random();
        ((ImageView)corps).setTranslateX(x);
        ((ImageView)corps).setTranslateY(y);
        increment();
    }
    public Monstre(){
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("photos/bigmonstre.png")));
        img.setFitHeight(100);
        img.setFitWidth(100);
        corps = img;
        double x = 0+(800-0)*Math.random();
        double y = 0+(200-0)*Math.random();
        ((ImageView)corps).setTranslateX(x);
        ((ImageView)corps).setTranslateY(y);
        blood = new Blood();
        blood.MonstreBlood();
    }
    public Blood getBlood(){
        return this.blood;
    }
    
    static public int getNombre(){
        return nombres;
    }
    static public void setNombre(int i){
        nombres=i;
    }
    public void increment(){
        nombres++;
    }
//    public void killingHero(Arme arme, Pane root){
//        AnimationTimer animation = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                if(Math.random()<0.02){
//                    Balle balle = new Balle(arme);
//                    root.getChildren().add(balle.getCorps());
//                }
//            }
//        };
//    }
//    public void move(){
//        Line path = new Line(100, 25, 725,25);
//	PathTransition trans = new PathTransition(Duration.seconds(2), path, corps);
//	trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//	trans.setCycleCount(FadeTransition.INDEFINITE);
//	trans.setAutoReverse(true);
//	trans.play();
//    }
}

