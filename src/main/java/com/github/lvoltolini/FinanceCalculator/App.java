package com.github.lvoltolini.FinanceCalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Hello world!
 *
 */
public class App 
extends Application
{
    public static void main( String[] args )
    {
    	launch(args);
    }
    private double xOffset = 0;
    private double yOffset = 0;

    private static final double RESIZE_MARGIN = 6;

    @Override
    public void start(Stage primaryStage) {

        // Main stage (can be hidden if you don't need it)
        primaryStage.setTitle("Main App");
        primaryStage.show();

        // Floating bar
        Stage floatingBar = new Stage();
        floatingBar.initOwner(primaryStage); // prevents taskbar icon
        floatingBar.initStyle(StageStyle.TRANSPARENT);
        floatingBar.setAlwaysOnTop(true);

        Pane root = new Pane();
        root.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-background-radius: 15;");
        root.setPrefSize(300, 120);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT); // important!

        floatingBar.setScene(scene);

        // --------------------
        // DRAGGING
        // --------------------
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            if (!isInResizeZone(root, event.getX(), event.getY())) {
                floatingBar.setX(event.getScreenX() - xOffset);
                floatingBar.setY(event.getScreenY() - yOffset);
            }
        });

        // --------------------
        // RESIZING (bottom-right corner)
        // --------------------
        root.setOnMouseMoved(event -> {
            if (isInResizeZone(root, event.getX(), event.getY())) {
                root.setCursor(javafx.scene.Cursor.SE_RESIZE);
            } else {
                root.setCursor(javafx.scene.Cursor.DEFAULT);
            }
        });

        root.setOnMouseDragged(event -> {
            if (isInResizeZone(root, event.getX(), event.getY())) {
                floatingBar.setWidth(event.getX());
                floatingBar.setHeight(event.getY());
            }
        });

        floatingBar.show();
    }

    private boolean isInResizeZone(Pane pane, double x, double y) {
        return 
    		(x > pane.getWidth() - RESIZE_MARGIN) 
    		&&
			(y > pane.getHeight() - RESIZE_MARGIN);
    }
}
		
