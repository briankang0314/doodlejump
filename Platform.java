package doodlejump;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Platform {
    private Rectangle platform;
    private PlatformType type;
    private Pane doodlePane;
    private double deltaX;
    private double minX;
    private double maxX;


    public Platform(BorderPane root, Pane doodlePane, double x, double y, PlatformType type) {
        this.doodlePane = doodlePane;
        this.type = type;
        this.platform = new Rectangle(x, y, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        setColorBasedOnType();
        doodlePane.getChildren().add(this.platform);

        if (type == PlatformType.MOVING) {
            this.deltaX = Constants.PLATFORM_MOVING_SPEED;
            this.minX = 0;
            this.maxX = Constants.SCENE_WIDTH - Constants.PLATFORM_WIDTH;
        }
    }

    private void setColorBasedOnType() {
        switch (type) {
            case STANDARD:
                platform.setFill(Color.GRAY);
                break;
            case DISAPPEARING:
                platform.setFill(Color.RED);
                break;
            case EXTRA_BOUNCY:
                platform.setFill(Color.GREEN);
                break;
            case MOVING:
                platform.setFill(Color.BLUE);
                break;
        }
    }

    public PlatformType getType() {
        return type;
    }

    public double getX() {
        return this.platform.getX();
    }

    public double getY() {
        return this.platform.getY();
    }

    public void setY(double y) {
        this.platform.setY(y);
    }

    public double getWidth() {
        return this.platform.getWidth();
    }

    public double getHeight() {
        return this.platform.getHeight();
    }

    public enum PlatformType {
        STANDARD,
        DISAPPEARING,
        EXTRA_BOUNCY,
        MOVING
    }

    public void update() {
        if (this.type == PlatformType.MOVING) {
            double newX = this.platform.getX() + this.deltaX;

            if (newX > maxX || newX < minX) {
                this.deltaX = -this.deltaX;
                newX = Math.min(Math.max(newX, minX), maxX);
            }

            this.platform.setX(newX);
        }
    }

    public void hide() {
        doodlePane.getChildren().remove(this.platform);
    }

    public static PlatformType getRandomPlatformType() {
        PlatformType[] types = PlatformType.values();
        int randomIndex = (int) (Math.random() * types.length);
        return types[randomIndex];
    }

    public void removeFromPane() {
        this.doodlePane.getChildren().remove(this.platform);
    }

    public Rectangle getShape() {
        return this.platform;
    }
}
