package com.sample.controller.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import com.sample.controller.Controller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logical controller for the additional info scene.
 */
public class AddInfoController extends SceneController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private Controller controller;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    private Label description;

    @FXML
    private Label state;

    @FXML
    private TextArea textArea;

    public AddInfoController() {

    }

    public AddInfoController(Controller controller) {
        this.controller = controller;
    }
    
    /**
     * Set all action buttons.
     */
    @FXML
    public void setButtonActions() {
        logger.log(Level.INFO, "SET ACTIONS OF BUTTONS");
        previousButton.setOnAction(e -> {
            if(previousButton.getId().equals("previousButton")) {
                controller.getView().getPrimaryStage().setScene(controller.getView().getTitleScene());
            }
        });
        nextButton.setOnAction(e -> {
            if(nextButton.getId().equals("nextButton")) {
                controller.getView().getPrimaryStage().setScene(controller.getTheRightScene());
            }
        });
        yesButton.setOnAction(e -> {
            if(yesButton.getId().equals("yesButton")) {
                state.setVisible(true);
                state.setText("pump.isVibrating == true");
                controller.getModel().getPump().check("isVibrating");
                controller.getModel().getPump().setVibrating(true);
                controller.getKsession().update(controller.getFactHandle(), controller.getModel().getPump());
                controller.getKsession().fireAllRules();
            }
        });
        noButton.setOnAction(e -> {
            if(noButton.getId().equals("noButton")) {
            	state.setVisible(true);
                state.setText("pump.isVibrating == false");
                controller.getModel().getPump().check("isVibrating");
                controller.getModel().getPump().setVibrating(false);
                controller.getKsession().update(controller.getFactHandle(), controller.getModel().getPump());
                controller.getKsession().fireAllRules();
            }
        });
    }



    public Controller getController() {
        return controller;
    }
    
    public Label getDescription() {
    	return description;
    }

}
