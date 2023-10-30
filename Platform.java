package doodlejump;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Platform {
    private Rectangle standardPlatform;

    public Platform(BorderPane root, Pane doodlePane){
        this.standardPlatform = new Rectangle(180,500,
                Constants.PLATFORM_WIDTH,Constants.PLATFORM_HEIGHT);

        doodlePane.getChildren().add(this.standardPlatform);
    }

    public double getX() {
        return this.standardPlatform.getX();
    }

    public double getY() {
        return this.standardPlatform.getY();
    }

    public double getWidth() {
        return this.standardPlatform.getWidth();
    }

    public double getHeight() {
        return this.standardPlatform.getHeight();
    }

}
