/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.en.javafx;

import java.io.File;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Aabidi Hassan
 */
public class JeuEnJavaFX extends Application{
private double width=800,height=600;
private ArrayList<Monstre>monstres = new ArrayList<>();
private ArrayList<Balle>balles = new ArrayList<>();
private int nbmonstrerest = 5;
private Text nbmonstre = new Text();
Pane root = new Pane();
Scene scene = new Scene(root,width,height);
Line line = new Line(0, 200, width, 200);
Zone zone1 = new Zone(line.getStartX(), 0, line.getEndX()-50, line.getEndY()-50);
Zone zone2 = new Zone(0, 0, 750, 550);
Hero hero = new Hero(zone2);
Rectangle r = new Rectangle(200, 30);
Arme arme = new Arme(hero);
int nbBalles = 100;
Rectangle r2 = new Rectangle(180, 30);
Text nbBallesRest = new Text();
File file1 = new File("C:\\Users\\hassa\\Documents\\NetBeansProjects\\Jeu En JavaFX\\src\\jeu\\en\\javafx\\Sounds\\kill.mp3");
Media mm = new Media(file1.toURI().toString());
boolean stage2 = false;
boolean alertt = false;
boolean fin = false;
int wach = 25;
ArrayList<Balle> nari = new ArrayList<>();
boolean stage3 = false;
boolean allt = false;
AnimationTimer animation,animation2,animm;
Monstre bigMonstre;
ArrayList<Flame> flames = new ArrayList<>();
int wacch=25;
Monstre m1,m2;

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setResizable(false);
        
        root.setId("pane");
        
        r.setFill(Color.WHITE);
        r.setTranslateX(8);
        r.setTranslateY(562);
        nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
        nbmonstre.setTranslateX(10);
        nbmonstre.setTranslateY(580);
        
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Jeu de guere");
//	alert.setContentText("Stage 1!!");
//        alert.showAndWait();
        
        this.animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                theBalle(root);
                    if(Math.random()<0.02 && Monstre.getNombre()<5){
                        Monstre monstre = new Monstre(zone1);
                        root.getChildren().add(monstre.getCorps());
                        monstres.add(monstre);
                    }
                    if(stage2){
                        //root.setId("root ");
                        for(Balle balle : nari){
                            if(balle.boom(hero)){
                                hero.getBlood().n9es(wach); 
                                wach++;
                            }
                        }
                        
                        if(alertt){
                            stage2(stage);
                            
                            alertt = false;
                            nbmonstrerest = 10;
                        }
                    
                        if(Math.random()<0.02 && Monstre.getNombre()<15){

                            Monstre monstre = new Monstre(zone1);
                            root.getChildren().add(monstre.getCorps());
                            monstres.add(monstre);
                            
                            double x = 70*Math.random();
                            double y = (150*Math.random())+30;
                            
                            double x1 = (700*Math.random())+150;
                            double y1 = (150*Math.random())+30;
                            
                            Line path = new Line(x, y, x1,y1);
                            // Set up a Path Transition for the Rectangle
                            PathTransition trans = new PathTransition(Duration.seconds(2), path, monstre.getCorps());
                            trans.setOrientation(PathTransition.OrientationType.NONE);
                            // Let the animation run forever
                            trans.setCycleCount(FadeTransition.INDEFINITE);
                            // Reverse direction on alternating cycles
                            trans.setAutoReverse(true);
                            // Play the Animation
                            trans.play(); 
                        }
                        
                        AnimationTimer anim = new AnimationTimer() {
                            @Override
                            public void handle(long l) {
                                if(nbmonstrerest==0){
                                    //stage.close();
                                    stage3 = true;
                                    allt = true;
                                }
                            }
                        };
                        anim.start();
                    }
            }
        };
        
        animation2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(nbmonstrerest==0){
                    alertt = true;
                    stage2 = true;
                }
                if(nbBalles!=100) {alertt=false;}
                if(nbBalles<=0 || hero.getBlood().ba9i()) {hero.getBlood().again();nbBalles=100; stage2(stage); }
                
                
                if(stage3){
                    animation.stop();
                    stage3(stage, this);
                }
            }
        };
        
        animation2.start();
        
        
        root.getChildren().addAll(line,hero.getCorps(),arme.getCorps(),arme.getCircle(),r,nbmonstre);
        animation.start();
        
        scene.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());
        action(scene, arme, hero, root, stage);
        stage.setScene(scene);

        stage.setTitle("Jeu de Guere");
        stage.show();
        
    }
    
    public void stage3(Stage stage, AnimationTimer anim){
        animation.stop();
        animation2.stop();
        root.setId("stage3");
        nbBalles = 150;
        nbBallesRest.setText("Nombre des Balles restant: "+nbBalles);
        hero.getBlood().again();
        
        for(Balle balle : balles){
            root.getChildren().remove(balle.getCorps());
        }
        
        balles.clear();
        
        root.getChildren().removeAll(nbmonstre,r);
        
        for(Balle balle : nari){
            root.getChildren().remove(balle.getCorps());
        }
        for(Monstre monstre : monstres){
            root.getChildren().remove(monstre.getCorps());
        }
        
        anim.stop();
        stage.close();
        final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                Pane dialogVbox = new Pane();
                Text t = new Text("Vous avez bien passez le stage 2");
                t.setLayoutX(65);
                t.setLayoutY(80);
                            Button btn = new Button("Tappez sur le button pour lancer le troisieme stage !");
                            btn.setLayoutX(5);
                            btn.setLayoutY(95);
                            btn.setStyle("-fx-background-color: TRANSPARENT;");
                            dialogVbox.getChildren().addAll(btn,t);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                
                
                dialog.setScene(dialogScene);
                dialog.setTitle("Stage 3");
                dialog.show();
                
                btn.setOnAction((eh)->{
                    dialog.close();
                    stage.show();
                });
        
        m1 = new Monstre();
        m2 = new Monstre();
        
        Line path = new Line(-10, 50, 810,50);
        PathTransition trans = new PathTransition(Duration.seconds(2), path, m1.getCorps());
        trans.setOrientation(PathTransition.OrientationType.NONE);
        trans.setCycleCount(FadeTransition.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play(); 
        
        Line path1 = new Line(-10, 150, 810,150);
        PathTransition trans1 = new PathTransition(Duration.seconds(3), path1, m2.getCorps());
        trans1.setOrientation(PathTransition.OrientationType.NONE);
        trans1.setCycleCount(FadeTransition.INDEFINITE);
        trans1.setAutoReverse(true);
        trans1.play(); 
        
        m2.getBlood().bdel();
        
        root.getChildren().addAll(m1.getCorps(),m2.getCorps(),m1.getBlood().getRect1(),m2.getBlood().getRect1(),m1.getBlood().getRect2(),m2.getBlood().getRect2());
        
        anim = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(Balle balle : balles){
                    balle.update();
                }
                
                for(Flame flame : flames){
                    if(flame.boom(hero)){
                        hero.getBlood().n9es(wach);
                        wach++;
                    }
                }
                
                if(m1.getBlood().ba9i()) root.getChildren().removeAll(m1.getBlood().getRect1(),m1.getBlood().getRect2(),m1.getCorps());
                if(m2.getBlood().ba9i()) root.getChildren().removeAll(m2.getBlood().getRect1(),m2.getBlood().getRect2(),m2.getCorps());
                if(m1.getBlood().ba9i() && m2.getBlood().ba9i()) fin(stage, this);
                
                if(Math.random()<0.004){
                    Flame flame = new Flame();
                    flame.hbet();
                    flames.add(flame);
                    root.getChildren().add(flame.getCorps());
                }
                
                for(Balle balle : balles){
                    if(balle.boom(m1)){
                        root.getChildren().remove(balle.getCorps());
                        balle.setAlive(false);
                        m1.getBlood().n9es(wacch);
                        wacch++;
                   }
                   if(balle.boom(m2)){
                        root.getChildren().remove(balle.getCorps());
                        balle.setAlive(false);
                        m2.getBlood().n9es(wach);
                        wach++;
                   }
                }
           }
        };
        anim.start();
                
    }
    
    public void inStage3(Stage stage,AnimationTimer animation){
        
        animation.stop();
        
        balles.clear();
        
        root.getChildren().remove(bigMonstre.getCorps());
        
        m1 = new Monstre();
        m2 = new Monstre();
        
        Line path = new Line(-10, 50, 810,50);
        PathTransition trans = new PathTransition(Duration.seconds(2), path, m1.getCorps());
        trans.setOrientation(PathTransition.OrientationType.NONE);
        trans.setCycleCount(FadeTransition.INDEFINITE);
        trans.setAutoReverse(true);
        trans.play(); 
        
        Line path1 = new Line(-10, 150, 810,150);
        PathTransition trans1 = new PathTransition(Duration.seconds(3), path1, m2.getCorps());
        trans1.setOrientation(PathTransition.OrientationType.NONE);
        trans1.setCycleCount(FadeTransition.INDEFINITE);
        trans1.setAutoReverse(true);
        trans1.play(); 
        
        m2.getBlood().bdel();
        
        root.getChildren().addAll(m1.getCorps(),m2.getCorps(),m1.getBlood().getRect1(),m2.getBlood().getRect1(),m1.getBlood().getRect2(),m2.getBlood().getRect2());
        
        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(Balle balle : balles){
                    if(balle.boom(m1)){
                        root.getChildren().remove(balle.getCorps());
                        balle.setAlive(false);
                        m1.getBlood().n9es(wacch);
                        wacch++;
                   }
                   if(balle.boom(m2)){
                        root.getChildren().remove(balle.getCorps());
                        balle.setAlive(false);
                        m2.getBlood().n9es(wach);
                        wach++;
                   }
                }
           }
        };
        anim.start();
        
        AnimationTimer aniii = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(m1.getBlood().ba9i()) root.getChildren().removeAll(m1.getBlood().getRect1(),m1.getBlood().getRect2(),m1.getCorps());
                if(m2.getBlood().ba9i()) root.getChildren().removeAll(m2.getBlood().getRect1(),m2.getBlood().getRect2(),m2.getCorps());
                if(m1.getBlood().ba9i() && m2.getBlood().ba9i()) fin(stage, this);
            }
        };
        aniii.start();
        
        
    }
    
    public void fin(Stage stage, AnimationTimer anim){
        anim.stop();
        stage.close();
        final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                Pane dialogVbox = new Pane();
                Text t = new Text("Bravoooo!!!! Vous avez bien fini le jeu");
                t.setLayoutX(65);
                t.setLayoutY(80);
                            Button btn = new Button("Tappez sur le button pour fermer !");
                            btn.setLayoutX(5);
                            btn.setLayoutY(95);
                            btn.setStyle("-fx-background-color: TRANSPARENT;");
                            dialogVbox.getChildren().addAll(btn,t);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                
                
                dialog.setScene(dialogScene);
                dialog.setTitle("Stage 2");
                dialog.show();
                
                btn.setOnAction((eh)->{
                    dialog.close();
                });
    }
    
    public void stage2(Stage stage){
        stage.close();
        root.setId("root");
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                Pane dialogVbox = new Pane();
                Text t = new Text("Vous avez bien passez le stage 1");
                t.setLayoutX(65);
                t.setLayoutY(80);
                            Button btn = new Button("Tappez sur le button pour lancer le deuxieme stage !");
                            btn.setLayoutX(5);
                            btn.setLayoutY(95);
                            btn.setStyle("-fx-background-color: TRANSPARENT;");
                            dialogVbox.getChildren().addAll(btn,t);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                
                
                dialog.setScene(dialogScene);
                dialog.setTitle("Stage 2");
                dialog.show();
                
                btn.setOnAction((eh)->{
                    dialog.close();
                    stage.show();
                });
                
                r2.setFill(Color.WHITE);
                r2.setTranslateX(310);
                r2.setTranslateY(562);
                nbBallesRest.setTranslateX(322);
                nbBallesRest.setTranslateY(580);
                nbBallesRest.setText("Nombre des balles restant: "+nbBalles);
                root.getChildren().addAll(r2,nbBallesRest,hero.getBlood().getRect1(),hero.getBlood().getRect2());
                
                
                Balle balle = new Balle();
                nari.add(balle);
                
                Line path = new Line(0, 280, 800,280);
                            // Set up a Path Transition for the Rectangle
                            PathTransition trans = new PathTransition(Duration.seconds(5), path, balle.getCorps());
                            trans.setOrientation(PathTransition.OrientationType.NONE);
                            // Let the animation run forever
                            trans.setCycleCount(FadeTransition.INDEFINITE);
                            // Reverse direction on alternating cycles
                            trans.setAutoReverse(true);
                            // Play the Animation
                            trans.play(); 
                            
                Balle balle2 = new Balle();
                nari.add(balle2);
                
                Line path2 = new Line(800, 380, 0,380);
                            // Set up a Path Transition for the Rectangle
                            PathTransition trans2 = new PathTransition(Duration.seconds(6), path2, balle2.getCorps());
                            trans2.setOrientation(PathTransition.OrientationType.NONE);
                            // Let the animation run forever
                            trans2.setCycleCount(FadeTransition.INDEFINITE);
                            // Reverse direction on alternating cycles
                            trans2.setAutoReverse(true);
                            // Play the Animation
                            trans2.play(); 
                            
                            
                Balle balle3 = new Balle();
                nari.add(balle3);
                
                Line path3 = new Line(-100, 520, 900,520);
                            // Set up a Path Transition for the Rectangle
                            PathTransition trans3 = new PathTransition(Duration.seconds(6), path3, balle3.getCorps());
                            trans3.setOrientation(PathTransition.OrientationType.NONE);
                            // Let the animation run forever
                            trans3.setCycleCount(FadeTransition.INDEFINITE);
                            // Reverse direction on alternating cycles
                            trans3.setAutoReverse(true);
                            // Play the Animation
                            trans3.play(); 
                            
                root.getChildren().addAll(balle.getCorps(),balle2.getCorps(),balle3.getCorps());
    }
    
    
    
    public void action(Scene scene, Arme arme, Hero hero, Pane root, Stage stage){
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
                    case SPACE: /*if(nbmonstrerest==0){ /*stage2(stage); stage2=true; alertt=true;}*/
                                
                                Balle balle= new Balle(arme);
                                balles.add(balle);
                                if(stage2){ nbBalles--; nbBallesRest.setText("Nombre des Balles restant: "+nbBalles);}
                                root.getChildren().add(balle.getCorps());
                                
                                break;
                }
            }
        });
    }
    public void theBalle(Pane group){
        for(Balle balle : balles){
            balle.update();
        }
        for(Balle balle : balles){
            for(Monstre monstre : monstres){
                if(balle.boom(monstre)){
                    group.getChildren().removeAll(monstre.getCorps(),balle.getCorps());
                    balle.setAlive(false);
                    monstre.setAlive(false);
                    nbmonstrerest--;
                    nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
//                    String uriString = new File("kill.mp3").toURI().toString();
//                    MediaPlayer player = new MediaPlayer( new Media(uriString));
//                    player.play();
                    MediaPlayer kill = new MediaPlayer(mm);
                    kill.play();
                }
            }
        }
        monstres.removeIf(Entete::ifalive);
        balles.removeIf(Entete::ifalive);
        
    }
    
//    public void theBalleStage2(Pane group){
//        for(Balle balle : balles){
//            balle.update();
//        }
//        for(Balle balle : balles){
//            for(Monstre monstre : monstres){
//                if(balle.boom(monstre)){
//                    
//                    group.getChildren().removeAll(monstre.getCorps(),balle.getCorps());
//                    balle.setAlive(false);
//                    monstre.setAlive(false);
//                    nbmonstrerest--;
//                    nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
//                }
//            }
//        }
//        monstres.removeIf(Entete::ifalive);
//        balles.removeIf(Entete::ifalive);
//        
//    }
    
//    public void stage2(Stage stage){
//        
//        monstres.removeIf(Entete::ifalive);
//        balles.removeIf(Entete::ifalive);
//        hero.getBlood().again();
//       stage.close();
//       Stage s=new Stage();
//       monstres.clear();
//       balles.clear();
//        System.out.println(monstres.size());
//        System.out.println(balles.size());
//        
//        nbBalles=100;
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Jeu de guere");
//            alert.setContentText("Stage 2!!");
//            alert.showAndWait();
//            Monstre.setNombre(0);
//            nbmonstrerest=5;
//            nbBallesRest.setText("Balles restant: "+nbBalles);
//            r2.setFill(Color.WHITE);
//            r2.setTranslateX(355);
//            r2.setTranslateY(562);
//            nbBallesRest.setTranslateX(360);
//            nbBallesRest.setTranslateY(580);
//            nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
//            Pane group = new Pane();
//            group.setId("root2");
//            Scene newScene = new Scene(group,width,height);
//            newScene.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());
//            s.setScene(newScene);
//            
//            s.show();
//            
//            stage2Action(newScene, group, s);
//            
////            Monstre m1 = new Monstre(zone1);
////            Monstre m2 = new Monstre(zone1);
////            Monstre m3 = new Monstre(zone1);
////            Monstre m4 = new Monstre(zone1);
////            monstres.add(m1);
////            monstres.add(m2);
////            monstres.add(m3);
////            monstres.add(m4);
//            group.getChildren().addAll(line,r,hero.getCorps()/*,m4.getCorps(),m1.getCorps(),m2.getCorps(),m3.getCorps()*/,r2,nbBallesRest,arme.getCircle(),nbmonstre,hero.getBlood().getRect1(),hero.getBlood().getRect2(),arme.getCorps());
////            m1.move();m2.move();m3.move();
//            
//            // Create the Path
////			Line path = new Line(200, 35, 725,25);
////			// Set up a Path Transition for the Rectangle
////			PathTransition trans = new PathTransition(Duration.seconds(2), path, m1.getCorps());
////			trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
////			// Let the animation run forever
////			trans.setCycleCount(FadeTransition.INDEFINITE);
////			// Reverse direction on alternating cycles
////			trans.setAutoReverse(true);
////			// Play the Animation
////			trans.play();
////			Line path1 = new Line(725, 67, 50,75);
////			// Set up a Path Transition for the Rectangle
////			
////			PathTransition trans1 = new PathTransition(Duration.seconds(2), path1, m2.getCorps());
////			
////			trans1.setOrientation(PathTransition.OrientationType.NONE);
////			// Let the animation run forever
////			trans1.setCycleCount(FadeTransition.INDEFINITE);
////			// Reverse direction on alternating cycles
////			trans1.setAutoReverse(true);
////			// Play the Animation
////			
////			trans1.play();
////			
////			
////			Line path2 = new Line(50, 100, 725,125);
////			// Set up a Path Transition for the Rectangle
////			PathTransition trans2 = new PathTransition(Duration.seconds(2), path2, m3.getCorps());
////			trans2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
////			// Let the animation run forever
////			trans2.setCycleCount(FadeTransition.INDEFINITE);
////			// Reverse direction on alternating cycles
////			trans2.setAutoReverse(true);
////			// Play the Animation
////			trans2.play();
////                        Line path4 = new Line(725, 132, 40,175);
////			// Set up a Path Transition for the Rectangle
////			PathTransition trans4 = new PathTransition(Duration.seconds(2), path4, m4.getCorps());
////			trans4.setOrientation(PathTransition.OrientationType.NONE);
////			// Let the animation run forever
////			trans4.setCycleCount(FadeTransition.INDEFINITE);
////			// Reverse direction on alternating cycles
////			trans4.setAutoReverse(true);
////			// Play the Animation
////			
////			trans4.play();
//
////                        AnimationTimer animation = new AnimationTimer() {
////                            @Override
////                            public void handle(long l) {
////                                for(Balle balle : balles){
////                                    balle.update();
////                                }
////                                for(Balle balle : balles){
////                                        if(balle.boom(m1)){
////                                            
////                                         if (m1.isAlive() && balle.isAlive()) {
////                                            //trans.stop();
////                                            
////                                            balle.setAlive(false);
////                                            m1.setAlive(false);
////                                            group.getChildren().removeAll(m1.getCorps(),balle.getCorps());
////                                            nbmonstrerest--;
////                                            nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
////                                            
////                                            continue;
////                                    }}
////                                       if(balle.boom(m2)){
////                                           if (m2.isAlive() && balle.isAlive()) {
////             
////                                            //trans1.stop();
////                                          
////                                            balle.setAlive(false);
////                                            m2.setAlive(false);
////                                            group.getChildren().removeAll(m2.getCorps(),balle.getCorps());
////                                           
////                                            nbmonstrerest--;
////                                            nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
////                                            continue;
////                                    }}
////                                       if(balle.boom(m3)){
////                                           
////                                        if (m3.isAlive() && balle.isAlive()) {
////                                            //trans2.stop();
////                                            
////                                            balle.setAlive(false);
////                                            m3.setAlive(false);
////                                            group.getChildren().removeAll(m3.getCorps(),balle.getCorps());
////                                            nbmonstrerest--;
////                                            nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
////                                            continue;
////                                    }}
////                                      if(balle.boom(m4)){
////                                            if (m4.isAlive() && balle.isAlive()) {
////                                            //trans4.stop();
////                                            
////                                            balle.setAlive(false);
////                                            m4.setAlive(false);
////                                            group.getChildren().removeAll(m4.getCorps(),balle.getCorps());
////                                            nbmonstrerest--;
////                                            nbmonstre.setText("Nombre des monstres restant: "+nbmonstrerest);
////                                            continue;
////                                    }}
////                                }
////                               balles.removeIf(Entete::ifalive); 
////                            }
////                        };
////                        animation.start();
//
//
//
//    AnimationTimer animation = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                theBalle(group);
//                    if(Math.random()<0.02 && Monstre.getNombre()<5){
//                    Monstre monstre = new Monstre(zone1);
//                    group.getChildren().add(monstre.getCorps());
//                    monstres.add(monstre);
//                }
//            }
//        };
//    animation.start();
//
//
//                        
////            AnimationTimer animation = new AnimationTimer() {
////            @Override
////            public void handle(long l) {
////                theBalle(group);
////                    if(Math.random()<0.02){
////                        group.getChildren().add(r)
////                    }
////            }
////            };
////            animation.start();
//    }
//    
////    public void stage2(Stage stage){
////        stage.close();
////        Runnable runnable = new Runnable() {
////            public void run() {
////                
////            }
////        };
////        runnable.run();
////    }
////    
//    
//    public void stage2Action(Scene newScene, Pane group, Stage stage){
//        newScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent t) {
//                switch (t.getCode()){
//                    case UP:hero.replace(0,-5);
//                            arme.replace(0, -5);
//                            arme.replaceCircle();
//                            break;
//                    case LEFT:hero.replace(-5,0);
//                            arme.replace(-5, 0);
//                            arme.replaceCircle();
//                            break;
//                    case RIGHT:hero.replace(5,0);
//                            arme.replace(5, 0);
//                            arme.replaceCircle();
//                            break;
//                    case DOWN:hero.replace(0,5);
//                            arme.replace(0, 5);
//                            arme.replaceCircle();
//                            break;
//                    case W:arme.retate();arme.rotate(5);break;
//                    case X:arme.retate();arme.rotate(-5);break;
//                    case SPACE: if(nbBalles>0){
//                                    Balle balle= new Balle(arme);
//                                    balles.add(balle);
//                                    nbBalles--;
//                                    nbBallesRest.setText("Balles restant: "+nbBalles);
//                                    group.getChildren().add(balle.getCorps());
//                                    if(hero.getBlood().ba9i()) reStage2(stage);
//                                    else hero.getBlood().n9es();
//                                }
//                    else reStage2(stage);
//                                break;
//                }
//            }
//        });
//    }
//    public void reStage2(Stage stage){
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Jeu de guere");
//        alert.setContentText("Vous avez perdu!! Tapez sur Ok pour recommancer");
//        alert.showAndWait();
//        stage2(stage);
//        
//    }
//    public void stage3(Stage stage){
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Jeu de guere");
//        alert.setContentText("Bravoooo!! Vous avez terminez avec succes le jeu");
//        alert.showAndWait();
//        //stage.close();
//    }
    
}
