package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class loginController {

	@FXML
	private ImageView bgd;

	@FXML
	private Button loginBTN;

	@FXML
	private TextField userTXT;

	@FXML
	private Label helpTXT;

	@FXML
	private PasswordField passTXT;

	// to keep track of how many logins for security reasons
	private Integer counter = 2;

	@FXML
	void loginACT(ActionEvent event) throws FileNotFoundException, IOException {

		boolean success = false;
		File file;

		// i am going to add a syntax similar to the one we have in QU for login
		// ie the system will recognize the type of the user from the email/username
		// admins will have 1 after two letters
		// drivers will have 4 after two letters
		// this will help us determine which file to go through instead of looping
		// through both

		// this to prevent further errors (length error) down the line

		if (userTXT.getText().length() < 3)
			userTXT.setText("4078328311");

		if (userTXT.getText().charAt(2) == '1')
			file = new File("file\\adminLOGIN.txt");
		else
			file = new File("file\\driverLOGIN.txt");

		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			if (userTXT.getText().equals(sc.next()) && passTXT.getText().equals(sc.next())) {
				success = true;
				displayMessage("You have successfully logged in. Loading...", AlertType.INFORMATION);

				Stage stage = new Stage();
				Pane root;

				if (userTXT.getText().charAt(2) == '1')
					root = FXMLLoader.load(getClass().getResource("/views/adminView.fxml"));
				else
					root = FXMLLoader.load(getClass().getResource("/views/driverView.fxml"));

				stage.setScene(new Scene(root));
				stage.getIcons().add(new Image("/images/CDWlogoV3.jpg"));
				stage.setTitle("CDW Main Menu");
				stage.show();

				((Window) loginBTN.getScene().getWindow()).hide();
			}
		}

		sc.close();

		if (!success && counter != 0) {
			displayMessage("Unsuccessful login. Please try again.\nAttempts left: " + counter, AlertType.ERROR);
			userTXT.clear();
			passTXT.clear();
		}

		if (counter == 0) {
			displayMessage("You have exceeded the number of login attempts. Exiting...", AlertType.ERROR);
			((Window) loginBTN.getScene().getWindow()).hide();
		}

		counter--;
	}

	public void displayMessage(String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle("System Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
