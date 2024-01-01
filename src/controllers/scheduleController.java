package controllers;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class scheduleController {

	ArrayList<WasteCollectionSchedule> w;

	@FXML
	private CheckBox hazCHK;

	@FXML
	private TableView<ScheduleEntry> schedTable;

	@FXML
	private TableColumn<ScheduleEntry, Integer> entryNO;

	@FXML
	private TableColumn<ScheduleEntry, String> custName;

	@FXML
	private TableColumn<ScheduleEntry, String> custAddr;

	@FXML
	private TableColumn<ScheduleEntry, String> serviceType;

	@FXML
	private TableColumn<ScheduleEntry, String> zone;

	@FXML
	private TextField driverTXT;

	@FXML
	private TextField truckTXT;

	@FXML
	private ComboBox<String> zoneChoice;

	@FXML
	private ToggleGroup schedType;

	@FXML
	private RadioButton binOPT;

	@FXML
	private RadioButton wasteOPT;

	@FXML
	private DatePicker datePick;

	@FXML
	private ComboBox<String> schedCombo;

	@FXML
	private Button rfrshBTN;

	@FXML
	private Button saveBTN;

	public void initialize() {

		zoneChoice.getItems().removeAll(zoneChoice.getItems());
		zoneChoice.getItems().addAll("North", "South");
		zoneChoice.setValue("");

		datePick.setDisable(true);
		datePick.setValue(LocalDate.now());
		hazCHK.setSelected(false);
		hazCHK.setVisible(false);
		saveBTN.setDisable(true);

		entryNO.setCellValueFactory(new PropertyValueFactory<ScheduleEntry, Integer>("entryNo"));
		serviceType.setCellValueFactory(new PropertyValueFactory<ScheduleEntry, String>("serviceType"));

		custName.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ScheduleEntry, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<ScheduleEntry, String> arg0) {
						return new SimpleStringProperty(arg0.getValue().getCustomer().getCustName());
					}
				});

		custAddr.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ScheduleEntry, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<ScheduleEntry, String> arg0) {
						int[] temp = arg0.getValue().getCustomer().getCustAddress();
						return new SimpleStringProperty("Zone: " + temp[0] + " Street: " + temp[1] + " Building: "
								+ temp[2] + " Unit: " + temp[3]);

					}
				});

		zone.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<ScheduleEntry, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<ScheduleEntry, String> arg0) {
						boolean z = arg0.getValue().isForNorthZone();

						if (z)
							return new SimpleStringProperty("North");
						else
							return new SimpleStringProperty("South");
					}
				});
	}

	@FXML
	void isBin(ActionEvent event) {
		hazCHK.setVisible(false);
	}

	@FXML
	void isHaz(ActionEvent event) {
		if (wasteOPT.isSelected())
			hazCHK.setVisible(true);
		else
			hazCHK.setVisible(false);
	}

	@FXML
	void cancel(ActionEvent event) {
		ButtonType res = displayMessage("Are you sure you want to cancel? Schedule will not be generated or saved.",
				AlertType.CONFIRMATION);

		if (res.equals(ButtonType.OK))
			driverTXT.getScene().getWindow().hide();
	}

	@FXML
	void generate(ActionEvent event) {
		schedCombo.getItems().removeAll(schedCombo.getItems());
		driverTXT.clear();
		truckTXT.clear();

		CDW.tempWasteSchedules.clear();
		CDW.tempDrivers.clear();
		CDW.tempTrucks.clear();

		String zone = zoneChoice.getSelectionModel().getSelectedItem();

		if (zone.isBlank() || (schedType.getSelectedToggle() == null))
			displayMessage("Mandatory fields were left empty!", AlertType.ERROR);
		else {
			boolean isNorth = false;

			if (zoneChoice.getSelectionModel().getSelectedItem().equals("North"))
				isNorth = true;

			if (wasteOPT.isSelected()) {
				boolean isHaz = false;

				if (hazCHK.isSelected())
					isHaz = true;

				w = CDW.prepareWasteSchedule(isNorth, true, isHaz);

				if (w == null) {
					displayMessage("No entries found.", AlertType.INFORMATION);
					clear();
				} else {
					for (int i = 0; i < w.size(); ++i)
						schedCombo.getItems().add("Schedule No. " + w.get(i).getScheduleNo());

					schedCombo.setValue(schedCombo.getItems().get(0));
					displayMessage("Number of schedules generated: " + w.size(), AlertType.INFORMATION);
				}
			} else
				displayMessage("'Prepare Bin Schedule' Feature Coming Soon!", AlertType.INFORMATION);

			if (schedCombo.getValue() != null) {
				tableValue(w.get(schedCombo.getSelectionModel().getSelectedIndex()));
				rfrshBTN.setDisable(false);
				saveBTN.setDisable(false);
			}
		}
	}

	@FXML
	void save(ActionEvent event) {
		Boolean saved = true;
		for (int i = 0; i < w.size(); i++)
			if (w.get(i).getDriver() != null && w.get(i).getTruck() != null)
				CDW.saveSchedule(w.get(i));
			else
				saved = false;

		if (saved)
			displayMessage("Schedule saving complete! You may exit.", AlertType.INFORMATION);
		else
			displayMessage("Some schedules have not been saved because\nthey are missing a driver or/and a truck.",
					AlertType.INFORMATION);
		clear();
	}

	@FXML
	public void clearACT(ActionEvent event) {
		try {
			clear();
		} catch (java.lang.NullPointerException e) {
			displayMessage("Fields are already empty.", AlertType.ERROR);
		}
	}

	public ButtonType displayMessage(String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle("System Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();

		return alert.getResult();
	}

	@FXML
	void updateTable(ActionEvent event) {
		tableValue(w.get(schedCombo.getSelectionModel().getSelectedIndex()));
	}

	public void tableValue(WasteCollectionSchedule w) {

		if (w.getDriver() == null && w.getTruck() == null) {
			displayMessage("No available driver and truck found!", AlertType.ERROR);
			driverTXT.setText("N/A");
			truckTXT.setText("N/A");
			saveBTN.setDisable(true);
		} else if (w.getDriver() == null) {
			displayMessage("No available driver found!", AlertType.ERROR);
			driverTXT.setText("N/A");
			saveBTN.setDisable(true);
		} else if (w.getTruck() == null) {
			displayMessage("No available truck found!", AlertType.ERROR);
			truckTXT.setText("N/A");
			saveBTN.setDisable(true);
		} else {
			driverTXT.setText(w.getDriver().getDriverName());
			truckTXT.setText(w.getTruck().getLicenseNo());
			saveBTN.setDisable(false);
		}

		if (w.isForNorthZone())
			zone.setUserData("North");
		else
			zone.setUserData("South");

		schedTable.setItems(FXCollections.observableArrayList(w.getScheduleEntries()));
	}

	public void clear() {
		schedType.getSelectedToggle().setSelected(false);
		hazCHK.setSelected(false);
		hazCHK.setVisible(false);
		saveBTN.setDisable(true);
		schedTable.getItems().clear();
		driverTXT.clear();
		truckTXT.clear();
		schedCombo.getItems().removeAll(schedCombo.getItems());
		zoneChoice.setValue("");
		datePick.setValue(LocalDate.now());
		CDW.tempWasteSchedules.clear();
		CDW.tempDrivers.clear();
		CDW.tempTrucks.clear();
	}
}
