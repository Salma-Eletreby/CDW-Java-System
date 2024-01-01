package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class adminController {

	@FXML
	private ImageView bgd;

	@FXML
	void genRep(ActionEvent event) {
		displayMessage("Coming Soon!", AlertType.INFORMATION);
	}

	@FXML
	void prepareInv(ActionEvent event) {
		displayMessage("Coming Soon!", AlertType.INFORMATION);
	}

	@FXML
	void prepareSal(ActionEvent event) {
		displayMessage("Coming Soon!", AlertType.INFORMATION);
	}

	// opens the prepare schedule window
	@FXML
	void prepareSched(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Pane root = FXMLLoader.load(getClass().getResource("/views/scheduleView.fxml"));
		stage.setScene(new Scene(root));
		stage.getIcons().add(new Image("/images/CDWlogoV3.jpg"));
		stage.setTitle("Prepare Schedules");
		stage.show();
	}

	@FXML
	void requests(ActionEvent event) {
		displayMessage("Coming Soon!", AlertType.INFORMATION);
	}

	@FXML
	void logout(ActionEvent event) throws IOException {
		ButtonType res = displayMessage("Are you sure you want to logout?", AlertType.CONFIRMATION);

		if (res.equals(ButtonType.OK)) {
			bgd.getScene().getWindow().hide();
			Stage stage = new Stage();
			Pane root = FXMLLoader.load(getClass().getResource("/views/loginView.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("CDW Login");
			stage.getIcons().add(new Image("/images/CDWlogoV3.jpg"));
			stage.show();
		}
	}

	// to create pop up messages
	public ButtonType displayMessage(String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle("System Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
		
		return alert.getResult();
	}
}
