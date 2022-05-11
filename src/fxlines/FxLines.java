/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxlines;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author shubh
 */
public class FxLines extends Application {
    Line line1 ;
    Pane root = new Pane();
    int x1, x2, y1, y2;
    int count = 0;
    boolean ready = false;
    int radius = 200;
// center of the circle
int centerX = 250, centerY = 300;

// The number of edges. Set to 5 for a pentagram
int mod = 136;
// The number of "points" to skip - set to 2 for a pentagram
int skip = 50;

int circle = 1; 
// Precalculated multipier for sin/cos
double multi = skip * 2.0 * Math.PI / mod; 


// First point, calculated by hand
int x11 = centerX; // sin(0) = 0
int y11 = centerY + radius; // cos(0) == 1
    
Button ConnectBtn = new Button();        
    
        
    Button rndBtn = new Button();        
    
    
    Button resetBtn = new Button();        
    
    
    Button PatternBtn = new Button();        
    
    
    Button slopeBtn = new Button();        
    
    
public static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }
    
void startScreen()
{
ConnectBtn.setText("Connect");
rndBtn.setText("random EVERYTHING (warning:might get a headache)");
resetBtn.setText("Reset");
PatternBtn.setText("Pretty pattern");
slopeBtn.setText("Slope pattern");
root.setStyle("-fx-background-color: #FAEBD7");
        root.getChildren().add(ConnectBtn);
        rndBtn.setLayoutX(60);
        resetBtn.setLayoutX(449);
        PatternBtn.setLayoutX(360);
        slopeBtn.setLayoutY(25);
        root.getChildren().add(rndBtn);
        root.getChildren().add(slopeBtn);
        root.getChildren().add(resetBtn);
        root.getChildren().add(PatternBtn);
}

void startGame(Stage stage) {
    // initialisation from start method goes here
    
    startScreen();
    PatternBtn.setOnAction(e -> {
        patternButton();
    });
    
    resetBtn.setOnAction(e -> {
       root.getChildren().clear();
       count = 0;
       startScreen();
    });
    
    slopeBtn.setOnAction(e -> {
        slopeButton();
    });
    ConnectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                connectButton();
            }
        });             
        
        rndBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                randomButton();
            }
        }); 
        
        Scene scene = new Scene(root, 500, 600);
    
    stage.setTitle("Draw lines, one at a time");
    stage.setScene(scene);
    stage.show();
    
}

    @Override
    public void start(Stage primaryStage) {
        
        startGame(primaryStage);
        
        }

    public void randomButton()
    {
    x1 = (int)(Math.random() * 500 + 1); x2 = (int)(Math.random() * 500 + 1); y1 =(int)(Math.random() * 600 + 1); y2 = (int)(Math.random() * 600 + 1);
        Line line = new Line(x1,y1,x2,y2);
        line.setStroke(Color.color(Math.random(),Math.random(),Math.random()));
        line.setStrokeWidth(Math.random() * 20 + 1);
        root.setStyle("-fx-background-color: "+ toRGBCode(Color.color(Math.random(),Math.random(),Math.random())) );
        root.getChildren().add(line);
    }
    public void patternButton()
    {
    if (circle <=mod)
        {
        int x22 = (int) (centerX + radius * Math.sin(circle * multi));
        int y22 = (int) (centerY + radius * Math.cos(circle * multi));
        Line line = new Line(x11,y11,x22,y22);  
       line.setStroke(Color.color(Math.random(),Math.random(),Math.random()));
       line.setStrokeWidth(2);
       root.getChildren().add(line);
    
        x11 = x22;
        y11 = y22;
        circle+=1;
        }
    }
    public void slopeButton()
    {
        if(ready == false)
        {
        x1 = 0; y1 = 0;
        x2 = 0; y2 = 600;
        ready = true;
        }
        else
        {
            Line line = new Line(x1,y1,x2,y2);  
            line.setStroke(Color.BLUE);
            line.setStrokeWidth(2);
            root.getChildren().add(line);
            y1+=20;                 
            x2+=20;                 
        }
    }
    public void connectButton()
    {
    if(count == 0)
    {
    x1 = 250;
    x2 = 250;
    y1 = (int)(Math.random() * 300 + 1); 
    y2 = (int)(Math.random() * 500 + 1);
    count++;
    }else{
        x1 = x2; 
        y1 = y2; 
        x2 = (int)(Math.random() * 300 + 1); 
        y2 = (int)(Math.random() * 500 + 1);
    }

    Line line = new Line(x1,y1,x2,y2);
    line.setStroke(Color.BLUE);
    line.setStrokeWidth(2);
    root.getChildren().add(line);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
}
