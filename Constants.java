package doodlejump;

import javafx.scene.paint.Color;
import java.awt.*;

/**
 * This is your Constants class. It defines some constants you will need
 * in DoodleJump, using the default values from the demo--you shouldn't
 * need to change any of these values unless you want to experiment. Feel
 * free to add more constants to this class!
 *
 * A NOTE ON THE GRAVITY CONSTANT:
 *   Because our y-position is in pixels rather than meters, we'll need our
 *   gravity to be in units of pixels/sec^2 rather than the usual 9.8m/sec^2.
 *   There's not an exact conversion from pixels to meters since different
 *   monitors have varying numbers of pixels per inch, but assuming a fairly
 *   standard 72 pixels per inch, that means that one meter is roughly 2800
 *   pixels. However, a gravity of 2800 pixels/sec2 might feel a bit fast. We
 *   suggest you use a gravity of about 1000 pixels/sec2. Feel free to change
 *   this value, but make sure your game is playable with the value you choose.
 */
public class Constants {

    public static final int GRAVITY = 1000; // acceleration constant (UNITS: pixels/s^2)
    public static final int REBOUND_VELOCITY = -600; // initial jump velocity (UNITS: pixels/s)
    public static final int BOUNCY_REBOUND_VELOCITY = -1200; //bouncy platform initial jump velocity (UNITS: pixels/s)
    public static final double DURATION = 0.016; // KeyFrame duration (UNITS: s)
    public static final double DISTANCE_X = 10;
    public static final int GAME_OVER_Y_THRESHOLD = 700;

    public static final int PLATFORM_WIDTH = 40; // (UNITS: pixels)
    public static final int PLATFORM_HEIGHT = 10; // (UNITS: pixels)
    public static final double PLATFORM_MOVING_SPEED = 0.33; // (UNITS: pixels/second)
    public static final int INITIAL_PLATFORM_COUNT = 10;
    public static final int MAX_PLATFORMS = 10;
    public static final int DOODLE_WIDTH = 20; // (UNITS: pixels)
    public static final int DOODLE_HEIGHT = 40; // (UNITS: pixels)
    public static final int DOODLE_X = 200;
    public static final int DOODLE_Y = 500;
    public static final int DOODLE_LEFT_EYE_X = 205;
    public static final int DOODLE_RIGHT_EYE_X = 215;
    public static final int DOODLE_EYE_Y = 508;
    public static final int DOODLE_EYE_RADIUS = 3;
    public static final int DOODLE_LEFT_EYE_BROW_X = 202;
    public static final int DOODLE_RIGHT_EYE_BROW_X = 213;
    public static final int DOODLE_EYE_BROW_Y = 502;
    public static final int DOODLE_EYE_BROW_WIDTH = 5;
    public static final int DOODLE_EYE_BROW_HEIGHT = 2;
    public static final int RIGHT_EYE_OFFSET = 14;
    public static final int LEFT_EYE_OFFSET = 4;
    public static final int LEFT_EYE_BROW_OFFSET = 1;
    public static final int RIGHT_EYE_BROW_OFFSET = 12;
    public static final int EYE_OFFSET_Y = 10;
    public static final int EYE_BROW_OFFSET_Y = 5;
    public static final Color DOODLE_EYE_COLOR = Color.BLACK;


    public static final int SCENE_WIDTH = 400;
    public static final int SCENE_HEIGHT = 600;
    public static final int DOODLE_PANE_WIDTH = 400;
    public static final int DOODLE_PANE_HEIGHT = 550;
    public static final int BUTTON_PANE_WIDTH = 400;
    public static final int BUTTON_PANE_HEIGHT = 50;

    public static final String BUTTON_PANE_COLOR = "-fx-background-color: #778899";
    public static final String DOODLE_PANE_COLOR = "-fx-background-color: #F0FFFF";
}
