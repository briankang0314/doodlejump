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

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Doodle doodle;
    private List<Platform> platforms;

    public Game(BorderPane root, Pane doodlePane) {
        this.doodle = new Doodle(root, doodlePane);
        this.platforms = new ArrayList<>();
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
                    this.doodle.resetPosition();

                    for (Platform platform : platforms) {
                        platform.update();
                        this.doodle.checkCollisionWithPlatform(platform);
                    }
                });
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
