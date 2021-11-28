/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.en.javafx;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Aabidi Hassan
 */
public class Main extends Application implements Runnable{
    
    double width=800,height=600;
    ArrayList<Monstre>monstres = new ArrayList<>();
    ArrayList<Balle>balles = new ArrayList<>();
    int nbmonstrerest = 5;
    Text nbmonstre = new Text();
    Pane root = new Pane();
    Scene scene = new Scene(root,width,height);
    Line line = new Line(0, 200, width, 200);
    Zone zone1 = new Zone(line.getStartX(), 0, line.getEndX()-50, line.getEndY()-50);
    Zone zone2 = new Zone(0, 0, 750, 550);
    Hero hero = new Hero(zone2);
    Rectangle r = new Rectangle(200, 30);
    Arme arme = new Arme(hero);
    int nbBalles = 100;
    Rectangle r2 = new Rectangle(110, 30);
    Text nbBallesRest = new Text();
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void run(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        r.setFill(Color.WHITE);
        r.setTranslateX(8);
        r.setTranslateY(562);
        nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
        nbmonstre.setTranslateX(10);
        nbmonstre.setTranslateY(580);
        
        nbBallesRest.setText("Balles restant: "+nbBalles);
        r2.setFill(Color.WHITE);
        r2.setTranslateX(355);
        r2.setTranslateY(562);
        nbBallesRest.setTranslateX(360);
        nbBallesRest.setTranslateY(580);
        
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                theBalle(root);
                    if(Math.random()<0.02 && Monstre.getNombre()<5){
                    Monstre monstre = new Monstre(zone1);
                    root.getChildren().add(monstre.getCorps());
                    monstres.add(monstre);
                }
            }
        };
        
        animation.start();
        
        root.getChildren().addAll(r,nbmonstre,r2,nbBallesRest,hero.getCorps(),line,arme.getCorps());
        
        
        root.setId("pane");
        scene.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());
        stage.setTitle("Jeu de guerre");
        stage.setScene(scene);
        stage.show();
        
        action(stage);
    }
    public void action(Stage stage){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                switch (t.getCode()){
                    case UP:hero.replace(0,-5);
                            arme.replace(0, -5);
                            arme.replaceCircle();
                            break;
                    case LEFT:hero.replace(-5,0);
                            arme.replace(-5, 0);
                            arme.replaceCircle();
                            break;
                    case RIGHT:hero.replace(5,0);
                            arme.replace(5, 0);
                            arme.replaceCircle();
                            break;
                    case DOWN:hero.replace(0,5);
                            arme.replace(0, 5);
                            arme.replaceCircle();
                            break;
                    case W:arme.retate();arme.rotate(5);break;
                    case X:arme.retate();arme.rotate(-5);break;
                    case SPACE: if(nbBalles<=0){lost(stage);}
                                Balle balle= new Balle(arme);
                                balles.add(balle);
                                root.getChildren().add(balle.getCorps());
                                nbBalles--;
                                nbBallesRest.setText("Balles restant: "+nbBalles);
                                break;
                }
            }
        });
    }
    
    public void theBalle(Pane root){
        for(Balle balle : balles){
            balle.update();
        }
        for(Balle balle : balles){
            for(Monstre monstre : monstres){
                if(balle.boom(monstre)){
                    root.getChildren().removeAll(monstre.getCorps(),balle.getCorps());
                    balle.setAlive(false);
                    monstre.setAlive(false);
                    nbmonstrerest--;
                    nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
                }
            }
        }
        monstres.removeIf(Entete::ifalive);
        balles.removeIf(Entete::ifalive);
        
    }
    
    public void lost(Stage stage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jeu de guere");
        alert.setContentText("You lost !! Tape ok to retry");
        alert.showAndWait();
        stage.close();
    }
}
