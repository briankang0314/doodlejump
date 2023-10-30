package doodlejump;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PaneOrganizer {

    private BorderPane root;
    private Pane doodlePane;

    public PaneOrganizer() {
        this.root = new BorderPane();
        this.doodlePane = new Pane();
        this.createDoodlePane();
        this.createButtonPane();
        new Game(this.root, this.doodlePane);
    }

    public BorderPane getRoot(){
        return this.root;
    }

    private void createDoodlePane() {
        this.doodlePane.setPrefSize(Constants.DOODLE_PANE_WIDTH,Constants.DOODLE_PANE_HEIGHT);
        this.doodlePane.setStyle(Constants.DOODLE_PANE_COLOR);
        this.root.setCenter(this.doodlePane);
        this.doodlePane.setFocusTraversable(true);
    }

    private void createButtonPane() {
        HBox buttonPane = new HBox();
        buttonPane.setPrefSize(Constants.BUTTON_PANE_WIDTH, Constants.BUTTON_PANE_HEIGHT);
        buttonPane.setStyle(Constants.BUTTON_PANE_COLOR);
        buttonPane.setAlignment(Pos.CENTER);
        this.root.setBottom(buttonPane);
        Button button = new Button("Quit");
        buttonPane.getChildren().add(button);
        button.setOnAction((ActionEvent e) -> System.exit(0));
        buttonPane.setFocusTraversable(false);
        button.setFocusTraversable(false);
    }
}
