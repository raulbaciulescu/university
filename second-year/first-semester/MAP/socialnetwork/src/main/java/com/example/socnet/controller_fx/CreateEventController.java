package com.example.socnet.controller_fx;

import com.example.socnet.domain.util.Constants;
import com.example.socnet.resources.Resources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class CreateEventController {
    @FXML public TextField tfName;
    @FXML public TextField tfTime;
    @FXML public Button btnGoBack;
    @FXML public Button btnCreateEvent;

    @FXML
    public void goBack(ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.EVENTS, event);
    }

    @FXML
    public void createEvent() {
        try {
            final String name = tfName.getText();
            final long seconds = Long.parseLong(tfTime.getText());
            if (0 > seconds || name.isBlank()) {
                return;
            }
            final LocalDateTime timestamp = LocalDateTime.now().plusSeconds(seconds);
            Resources.getInstance().getEventService().add(name, timestamp);
            tfName.clear();
            tfTime.clear();
        } catch (final @NotNull Exception e) {
            e.printStackTrace();
        }
    }
}
