package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private Pane doodlePane;
    private Doodle doodle;
    private List<Platform> platforms;
    private boolean isGameOver;
    private int score;
    private Timeline timeline;
    private static final double SCROLLING_THRESHOLD = Constants.SCENE_HEIGHT / 2;

    public Game(BorderPane root, Pane doodlePane) {
        this.doodle = new Doodle(root, doodlePane);
        this.doodlePane = doodlePane;
        this.platforms = new ArrayList<>();
        this.isGameOver = false;
        this.score = 0;
        root.setOnKeyPressed(this::handleKeyPress);

        this.startTimeline();
        generateInitialPlatforms();
    }

    private void handleKeyPress(KeyEvent e) {
        if (!isGameOver) {
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
    }

    private void startTimeline() {
        KeyFrame kf = new KeyFrame(Duration.millis(16),
                (ActionEvent e) -> {
                    if (!isGameOver) {
                        System.out.println("Doodle Position - X: " + this.doodle.getXLocation() + ", Y: " + this.doodle.getYLocation());
                        this.doodle.doodleFall();
                        this.doodle.resetPosition();
                        checkForScrolling();

                        for (Platform platform : platforms) {
                            platform.update();
                            if (this.doodle.checkCollisionWithPlatform(platform)) {
                                score++;
                            }
                        }

                        if (this.doodle.getYLocation() > Constants.GAME_OVER_Y_THRESHOLD) {
                            gameOver();
                        }
                    }
                });
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void checkForScrolling() {
        if (doodle.getYLocation() < SCROLLING_THRESHOLD) {
            double scrollAmount = SCROLLING_THRESHOLD - doodle.getYLocation();
            scrollPlatforms(scrollAmount);
            doodle.adjustYPosition(scrollAmount);
        }
    }

    private void scrollPlatforms(double scrollAmount) {
        Iterator<Platform> iterator = platforms.iterator();
        while (iterator.hasNext()) {
            Platform platform = iterator.next();
            platform.setY(platform.getY() + scrollAmount);

            if (platform.getY() > Constants.SCENE_HEIGHT) {
                platform.removeFromPane();
                iterator.remove();
            }
        }

        while (platforms.size() < Constants.MAX_PLATFORMS) {
            generateNewPlatformAtTop();
        }
    }


    private void generateInitialPlatforms() {
        for (int i = 0; i < Constants.INITIAL_PLATFORM_COUNT; i++) {
            double x = Math.random() * (Constants.SCENE_WIDTH - Constants.PLATFORM_WIDTH);
            double y = Math.random() * Constants.SCENE_HEIGHT;

            Platform.PlatformType type = Platform.getRandomPlatformType();
            Platform platform = new Platform(null, this.doodlePane, x, y, type);
            platforms.add(platform);
        }
    }

    private void generateNewPlatformAtTop() {
        double x = Math.random() * (Constants.SCENE_WIDTH - Constants.PLATFORM_WIDTH);
        double y = -Constants.PLATFORM_HEIGHT;

        Platform.PlatformType randomType = Platform.PlatformType.values()[(int) (Math.random() * Platform.PlatformType.values().length)];
        Platform newPlatform = new Platform(null, this.doodlePane, x, y, randomType);
        platforms.add(newPlatform);
    }

    private void gameOver() {
        isGameOver = true;
        if (timeline != null) {
            timeline.stop();
        }

        if (doodlePane != null) {
            Label gameOverLabel = new Label("Game Over! Score: " + score);
            gameOverLabel.setFont(new Font("Arial", 24));
            doodlePane.getChildren().add(gameOverLabel);
            doodlePane.setOnKeyPressed(null);
        }
    }
}
