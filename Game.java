package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
public class Game {
    private Doodle doodle;
    private Platform standardPlatform;

    public Game(BorderPane root, Pane doodlePane) {
        this.doodle = new Doodle(root, doodlePane);
        this.standardPlatform = new Platform(root, doodlePane);
        root.setOnKeyPressed(this::handleKeyPress);

        this.startTimeline();
    }

    private void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case LEFT:
                this.doodle.moveLeft();
                break;
            case RIGHT:
                this.doodle.moveRight();
                break;
            default:
                break;
        }
        e.consume();
    }

    private void startTimeline() {
        KeyFrame kf = new KeyFrame(Duration.millis(50),
                (ActionEvent e) -> {
                    this.doodle.doodleFall();
                    this.doodle.resetPos();

                    double platformX = standardPlatform.getX();
                    double platformY = standardPlatform.getY();
                    double platformWidth = standardPlatform.getWidth();
                    double platformHeight = standardPlatform.getHeight();

                    boolean collisionDetected = this.doodle.intersects(platformX, platformY,
                            platformWidth, platformHeight);

                    if (collisionDetected) {
                        this.doodle.rebound();
                    }

                });
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
