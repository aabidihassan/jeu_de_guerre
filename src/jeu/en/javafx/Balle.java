/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.en.javafx;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Aabidi Hassan
 */
public class Balle extends Entete{
 private Circle circle = new Circle(0, 0, 5, Color.RED);
 private Point2D dot = new Point2D(0, 0);
    public Balle(Arme arme) {
        corps = circle;
        corps.setTranslateX(arme.getCircle().getTranslateX());
        corps.setTranslateY(arme.getCircle().getTranslateY());
        direction(arme.getCorps().getRotate()-90);
    }
    
    public Balle(){
        corps = circle;
        this.circle.setFill(Color.WHITE);
        direction(0);
    }
    
    private void direction(double retate){
        double x = Math.cos(Math.toRadians(retate));
        double y = Math.sin(Math.toRadians(retate));
        Point2D p = new Point2D(x, y);
        this.dot = p.normalize().multiply(5);
    }
    
    public void update(){
        corps.setTranslateX(corps.getTranslateX()+dot.getX());
        corps.setTranslateY(corps.getTranslateY()+dot.getY());
    }
    
    
}
