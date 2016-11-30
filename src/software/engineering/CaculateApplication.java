package software.engineering;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;


public class CaculateApplication extends Application{
    private Polygon creatAsterisk(double r1){
        double r2 = r1*(Math.cos(Math.toRadians(36))-Math.sin(Math.toRadians(36))*Math.cos(Math.toRadians(54))/Math.sin(Math.toRadians(54)));
        Polygon asterisk = new Polygon();
        asterisk.getPoints().addAll(new Double[]{
           0.0,r1,
           r2*Math.cos(Math.toRadians(54)),r2*Math.sin(Math.toRadians(54)),
           r1*Math.cos(Math.toRadians(18)),r1*Math.sin(Math.toRadians(18)),
           r2*Math.cos(Math.toRadians(18)),r2*Math.sin(Math.toRadians(-18)),
           r1*Math.sin(Math.toRadians(36)),-r1*Math.cos(Math.toRadians(36)),
           0.0,-r2,
           -r1*Math.sin(Math.toRadians(36)),-r1*Math.cos(Math.toRadians(36)),
           -r2*Math.cos(Math.toRadians(18)),r2*Math.sin(Math.toRadians(-18)),
           -r1*Math.cos(Math.toRadians(18)),r1*Math.sin(Math.toRadians(18)),
           -r2*Math.cos(Math.toRadians(54)),r2*Math.sin(Math.toRadians(54))
        });
        
        return asterisk;
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        GridPane grid = new GridPane();
        Scene scene = new Scene(root,600,400);
        SubScene subscene = new SubScene(grid,600,400);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(25);
        grid.setVgap(15);
        grid.setBackground(Background.EMPTY);
        final CaculateControl caculate = new CaculateControl();
        
        final Text info = new Text();
        info.setText("输入多项式开始使用");
        grid.add(info, 0, 2);
        
        final Text alertText = new Text();
        grid.add(alertText, 0, 1);
        
        final Text previousText = new Text();
        grid.add(previousText, 0, 0);
        
        final TextField expressionText= new TextField();
        expressionText.setPromptText("输入你的多项式");
        grid.add(expressionText,0,3);

        final Button expressionButton = new Button("提交多项式");
        grid.add(expressionButton, 1, 3);
        
        final Button simplifyButton = new Button("!simplify");
        simplifyButton.setDisable(true);
        grid.add(simplifyButton, 1, 4);
        
        final TextField simplifyText= new TextField();
        simplifyText.setPromptText("输入化简指令");
        simplifyText.setDisable(true);
        grid.add(simplifyText, 0, 4);
        
        final Button derivativeButton = new Button("!d/d");
        derivativeButton.setDisable(true);
        grid.add(derivativeButton, 1, 5);
        
        final TextField derivativeText = new TextField();
        derivativeText.setPromptText("输入求导指令");
        derivativeText.setDisable(true);
        grid.add(derivativeText, 0, 5);
        
        
        /*输入表达式的动作*/
        expressionText.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e) { 
                if(e.getCode() == KeyCode.ENTER){
                    String expression = caculate.inputExpression(expressionText.getText());
                    if(expression!=""){
                    info.setText("当前多项式："+expression);
                    simplifyText.setDisable(false);
                    simplifyButton.setDisable(false);
                    derivativeButton.setDisable(false);
                    derivativeText.setDisable(false);
                    previousText.setText("");
                    expressionText.setPromptText("输入新的多项式");
                    expressionButton.setText("提交新的多项式");
                    expressionText.clear();
                    }else{
                        alertText.setText("多项式格式不正确！");
                        expressionText.clear();
                    }
                }
            };
        });
        
        expressionButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                String expression = caculate.inputExpression(expressionText.getText());
                if(expression!=""){
                info.setText("当前多项式："+expression);
                simplifyText.setDisable(false);
                simplifyButton.setDisable(false);
                derivativeButton.setDisable(false);
                derivativeText.setDisable(false);
                previousText.setText("");
                expressionText.setPromptText("输入新的多项式");
                expressionButton.setText("提交新的多项式");
                expressionText.clear();
                }else{
                    alertText.setText("多项式格式不正确！");
                    expressionText.clear();
                }
            }
        });
        
        simplifyText.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e) { 
                if(e.getCode() == KeyCode.ENTER){
                    String previous = caculate.outputExpression();
                    String result = caculate.simplifyExpression(simplifyText.getText());
                    if(result=="formatError"){
                        alertText.setText("化简指令格式不正确!");
                    }else{
                        alertText.setText("化简:"+simplifyText.getText());
                        previousText.setText("上一多项式:"+previous);
                        info.setText("当前多项式:"+caculate.outputExpression());
                        simplifyText.clear();
                    }
                    
                }
            };
        });
        
        simplifyButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                String previous = caculate.outputExpression();
                String result = caculate.simplifyExpression(simplifyText.getText());
                if(result=="formatError"){
                    alertText.setText("化简指令格式不正确!");
                }else{
                    alertText.setText("化简:"+simplifyText.getText());
                    previousText.setText("上一多项式:"+previous);
                    info.setText("当前多项式:"+caculate.outputExpression());
                    simplifyText.clear();
                }
            }
        });
        
        derivativeText.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e) { 
                if(e.getCode() == KeyCode.ENTER){
                    String previous = caculate.outputExpression();
                    String result = caculate.derivativeExpression(derivativeText.getText());
                    if(result=="formatError"){
                        alertText.setText("求导指令格式不正确!");
                    }else if(result=="noVar"){
                        alertText.setText("变量不存在!");
                    }else{
                        alertText.setText("对多项式求"+derivativeText.getText()+"的导数");
                        previousText.setText("上一多项式:"+previous);
                        info.setText("当前多项式:"+caculate.outputExpression());
                        derivativeText.clear();
                    }
                }
            };
        });
        
        derivativeButton.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                String previous = caculate.outputExpression();
                String result = caculate.derivativeExpression(derivativeText.getText());
                if(result=="formatError"){
                    alertText.setText("求导指令格式不正确!");
                }else if(result=="noVar"){
                    alertText.setText("变量不存在!");
                }else{
                    alertText.setText("对多项式求"+derivativeText.getText()+"的导数");
                    previousText.setText("上一多项式:"+previous);
                    info.setText("当前多项式:"+caculate.outputExpression());
                    derivativeText.clear();
                }
            }
        });
        
        
        /*for fun*/
        Group asterisks = new Group();
        //Polygon asterisk = new Polygon();
        for (int i = 0; i < 20; i++) {
            //Circle circle = new Circle(150, Color.web("white", 0.05));
            Polygon asterisk = creatAsterisk(60);
            asterisk.setFill(Color.web("black",0.08));
            asterisk.setStrokeType(StrokeType.OUTSIDE);
            asterisk.setStroke(Color.web("black", 0.2));
            asterisk.setStrokeWidth(5);
            asterisks.getChildren().add(asterisk);
        }
        asterisks.setEffect(new BoxBlur(7, 7, 1));
        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE,
                        new Stop[] { new Stop(0, Color.web("#f8bd55")), new Stop(0.14, Color.web("#c0fe56")),
                                new Stop(0.28, Color.web("#5dfbc1")), new Stop(0.43, Color.web("#64c2f8")),
                                new Stop(0.57, Color.web("#be4af7")), new Stop(0.71, Color.web("#ed5fc2")),
                                new Stop(0.85, Color.web("#ef504c")), new Stop(1, Color.web("#f2660f")), }));

        colors.widthProperty().bind(scene.widthProperty());
        colors.heightProperty().bind(scene.heightProperty());
        Group blendModeGroup = new Group(
                new Group(new Rectangle(scene.getWidth(), scene.getHeight(), Color.WHITE), asterisks), colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        
        
        root.getChildren().add(blendModeGroup);
        root.getChildren().add(subscene);
        
        Timeline timeline = new Timeline();
        for (Node circle: asterisks.getChildren()) {
            timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                    new KeyValue(circle.translateXProperty(), Math.random() * 800),
                    new KeyValue(circle.translateYProperty(), Math.random() * 600),
                    new KeyValue(circle.rotateProperty(),Math.random() * 360)
                )
                /*new KeyFrame(new Duration(10000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(), Math.random() * 800),
                    new KeyValue(circle.translateYProperty(), Math.random() * 600),
                    new KeyValue(circle.rotateProperty(),Math.random() * 360)
                )*/
            );
            
        }
        for (int i = 0;i<3;i++){
            for (Node circle: asterisks.getChildren()) {
                timeline.getKeyFrames().addAll(
                    new KeyFrame(new Duration(40000+i*40000), // set end position at 40s
                        new KeyValue(circle.translateXProperty(), Math.random()*Math.random() * 800),
                        new KeyValue(circle.translateYProperty(), Math.random() * 600),
                        new KeyValue(circle.rotateProperty(),Math.random() * 720)
                    )
                );
                
            }
        }
        
        // play 40s of animation
        
        timeline.setAutoReverse(true);
        timeline.setCycleCount(10);
        timeline.play();
        
        
        
        
        
        
        
        
        primaryStage.setTitle("Caculate");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


public static void main(String[] args){
    launch(args);
}
}