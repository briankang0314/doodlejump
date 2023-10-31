package doodlejump;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Doodle {
    private Rectangle doodle;
    private Circle doodleLeftEye;
    private Circle doodleRightEye;
    private Rectangle doodleLeftEyeBrow;
    private Rectangle doodleRightEyeBrow;

    private double currentVelocity = 0.0;
    private  double currentPosition;

    public Doodle(BorderPane root, Pane doodlePane) {
        this.doodle = this.createRectangle(Constants.DOODLE_X,Constants.DOODLE_Y,
                Constants.DOODLE_WIDTH,Constants.DOODLE_HEIGHT);
        this.doodleLeftEye = new Circle(Constants.DOODLE_LEFT_EYE_X,Constants.DOODLE_EYE_Y,
                Constants.DOODLE_EYE_RADIUS,Constants.DOODLE_EYE_COLOR);
        this.doodleRightEye = new Circle(Constants.DOODLE_RIGHT_EYE_X,Constants.DOODLE_EYE_Y,
                Constants.DOODLE_EYE_RADIUS,Constants.DOODLE_EYE_COLOR);
        this.doodleLeftEyeBrow = this.createRectangle(Constants.DOODLE_LEFT_EYE_BROW_X,
                Constants.DOODLE_EYE_BROW_Y,Constants.DOODLE_EYE_BROW_WIDTH,
                Constants.DOODLE_EYE_BROW_HEIGHT);
        this.doodleRightEyeBrow = this.createRectangle(Constants.DOODLE_RIGHT_EYE_BROW_X,
                Constants.DOODLE_EYE_BROW_Y,Constants.DOODLE_EYE_BROW_WIDTH,
                Constants.DOODLE_EYE_BROW_HEIGHT);

        this.doodle.setFill(Color.GREENYELLOW);
        this.doodleLeftEyeBrow.setFill(Color.HOTPINK);
        this.doodleRightEyeBrow.setFill(Color.BLUE);

        doodlePane.getChildren().addAll(this.doodle,this.doodleLeftEye,this.doodleRightEye,
                this.doodleLeftEyeBrow,this.doodleRightEyeBrow);
    }

    private Rectangle createRectangle(double x,double y,double width,double height){
        return new Rectangle(x,y,width,height);
    }

    private void setXloc(double x) {
        this.doodle.setX(x);
        this.doodleLeftEye.setCenterX(x + Constants.LEFT_EYE_OFFSET);
        this.doodleRightEye.setCenterX(x + Constants.RIGHT_EYE_OFFSET);
        this.doodleLeftEyeBrow.setX(x + Constants.LEFT_EYE_BROW_OFFSET);
        this.doodleRightEyeBrow.setX(x + Constants.RIGHT_EYE_BROW_OFFSET);
    }

    private void setYloc(double y) {
        this.doodle.setY(y);
        this.doodleLeftEye.setCenterY(y + Constants.EYE_OFFSET_Y);
        this.doodleRightEye.setCenterY(y + Constants.EYE_OFFSET_Y);
        this.doodleLeftEyeBrow.setY(y + Constants.EYE_BROW_OFFSET_Y);
        this.doodleRightEyeBrow.setY(y + Constants.EYE_BROW_OFFSET_Y);
    }

    public void doodleFall() {
        double doodleY = this.doodle.getY();
        this.setYloc(doodleY);

        double updatedVelocity = this.currentVelocity + Constants.GRAVITY * Constants.DURATION;
        double updatedPosition = this.currentPosition + updatedVelocity * Constants.DURATION;

        this.doodle.setY(updatedPosition);

        this.currentVelocity = updatedVelocity;
        this.currentPosition = updatedPosition;
    }

    private double getXLoc() {
        return this.doodle.getX();
    }

    public void moveLeft(){
        this.setXloc(this.doodle.getX() - Constants.DISTANCE_X);

    }

    public void moveRight(){
        this.setXloc(this.doodle.getX() + Constants.DISTANCE_X);
    }

    public void rebound(){
        this.currentVelocity = Constants.REBOUND_VELOCITY;
    }
    public void resetPos(){
        if (this.getXLoc() > Constants.SCENE_WIDTH - (double) Constants.DOODLE_WIDTH /2){
            this.doodle.setX((double) -Constants.DOODLE_WIDTH /2);
        }
        if (this.getXLoc() < -(double) Constants.DOODLE_WIDTH /2){
            this.doodle.setX(Constants.SCENE_WIDTH - (double) Constants.DOODLE_WIDTH /2);
        }
    }

    public boolean intersects(double x,double y, double width, double height){
        double doodleX = this.doodle.getX();
        double doodleY = this.doodle.getY();
        double doodleWidth = Constants.DOODLE_WIDTH;
        double doodleHeight = Constants.DOODLE_HEIGHT;

        // Check if the doodle overlaps with the platform
        if (doodleX < x + width &&
                doodleX + doodleWidth > x &&
                doodleY < y + height &&
                doodleY + doodleHeight > y) {
            // Collision detected, make the doodle jump
            this.currentVelocity = Constants.REBOUND_VELOCITY;
            return true;
        }

        else return false;
    }

    public void checkCollisionWithPlatform(Platform platform) {
        if (this.intersects(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight())) {
            switch (platform.getType()) {
                case STANDARD:
                    this.currentVelocity = Constants.REBOUND_VELOCITY;
                    break;
                case DISAPPEARING:
                    this.currentVelocity = Constants.REBOUND_VELOCITY;
                    platform.hide(); // Implement this method to hide the platform
                    break;
                case EXTRA_BOUNCY:
                    this.currentVelocity = Constants.BOUNCY_REBOUND_VELOCITY;
                    break;
                case MOVING:
                    // Handle moving platform collision
                    this.currentVelocity = Constants.REBOUND_VELOCITY;
                    break;
            }
        }
    }
}