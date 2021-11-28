/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.en.javafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Aabidi Hassan
 */
public class Blood extends Entete{
    private Rectangle rect1;
    private Rectangle rect2;
    public Blood() {
        rect1 = new Rectangle(100, 10);
        rect1.setStroke(Color.BLACK);
        rect1.setFill(Color.TRANSPARENT);
        rect1.setTranslateX(690);
        rect1.setTranslateY(580);
        
        rect2 = new Rectangle(98, 8);
        rect2.setFill(Color.RED);
        rect2.setTranslateX(691);
        rect2.setTranslateY(581);
    }
    public void MonstreBlood(){
        rect1.setTranslateX(690);
        rect1.setTranslateY(20);
        rect2.setTranslateX(691);
        rect2.setTranslateY(21);
    }
    
    public void bdel(){
        rect1.setTranslateX(110);
        rect1.setTranslateY(20);
        rect2.setTranslateX(111);
        rect2.setTranslateY(21);
    }

    public Rectangle getRect1() {
        return rect1;
    }

    public Rectangle getRect2() {
        return rect2;
    }
    
    public void n9es(int i){
        if(i%25==0){
            this.rect2.setWidth(rect2.getWidth()-10);
        }
    }
    
    public boolean ba9i(){
        return rect2.getWidth()<=0;
    }
    
    public void again(){
        this.rect2.setWidth(98);
    }
}
