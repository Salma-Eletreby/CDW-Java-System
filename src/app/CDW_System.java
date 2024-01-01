package app;

import javafx.application.Application;
import model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.time.LocalDate;

public class CDW_System extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = FXMLLoader.load(getClass().getResource("/views/loginView.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("CDW Login");
		stage.getIcons().add(new Image("/images/CDWlogoV3.jpg"));
		stage.show();
	}

	public static void main(String[] args) {
		LocalDate date = LocalDate.now(); // year,month,day

		//north
		Customer Nc0 = CDW.registerCustomer("Eren Yeager", new int[] {33, 450, 23, 71}, 98765432, CDW.bins[1]); 
		Customer Nc1 = CDW.registerCustomer("Jean Kirsten", new int[] {25, 256, 43, 18}, 12568327, CDW.bins[2]);
		Customer Nc2 = CDW.registerCustomer("Mikasa Ackerman", new int[] {15, 354, 16, 31}, 31283273, CDW.bins[0]);
		Customer Nc3 = CDW.registerCustomer("Armin Arlert", new int[] {40, 516, 21, 19}, 89274279, CDW.bins[1]);
		Customer Nc4 = CDW.registerCustomer("Annie Leonhart", new int[] {3, 167, 53, 90}, 47829374, CDW.bins[2]);
		Customer Nc5 = CDW.registerCustomer("Archer", new int[] {13, 867, 74, 21}, 31273187, CDW.bins[0]); //shouldn't show up
		
		//south
		Customer Sc1 = CDW.registerCustomer("Levi Ackerman", new int[] {96, 450, 23, 71}, 54692833, CDW.bins[0]); 
		Customer Sc2 = CDW.registerCustomer("Conny Springer", new int[] {78, 555, 13, 21}, 231873137, CDW.bins[1]);
		Customer Sc3 = CDW.registerCustomer("Sasha Braus", new int[] {57, 678, 12, 58}, 892173897, CDW.bins[2]);
		Customer Sc4 = CDW.registerCustomer("Sabre", new int[] {48, 100, 16, 15}, 32387278, CDW.bins[0]);
		
		//north - haz
		Customer HNc1 = CDW.registerCustomer("Ciel Phantomhive", new int[] {33, 450, 23, 71}, 12321324, CDW.bins[3]);
		Customer HNc2 = CDW.registerCustomer("Sebastian Michaelis", new int[] {25, 256, 43, 18}, 123214, CDW.bins[3]);
		Customer HNc3 = CDW.registerCustomer("Mey-Rin", new int[] {15, 354, 16, 31}, 76574564, CDW.bins[3]);
		Customer HNc4 = CDW.registerCustomer("Caster", new int[] {3, 167, 53, 90}, 364987213, CDW.bins[3]); //not show
		
		//south - haz
		Customer HSc1 = CDW.registerCustomer("William Spears", new int[] {96, 450, 23, 71}, 57238619, CDW.bins[3]);
		Customer HSc2 = CDW.registerCustomer("Elizabeth Cordellia", new int[] {78, 555, 13, 21}, 1284798, CDW.bins[3]);
		Customer HSc3 = CDW.registerCustomer("Grell Sutchiff", new int[] {57, 678, 12, 58}, 109381640, CDW.bins[3]);
		Customer HSc4 = CDW.registerCustomer("Breserker", new int[] {48, 100, 16, 15}, 327361881, CDW.bins[3]); //not show
		
		//Permanent
		CDW.addDriver("Shirou Emiya", 12345678, true, 0.3);
		CDW.addDriver("Rin Tohsaka", 28549231, true, 1.1);
		CDW.addDriver("Illyasviel von Einzbern", 54280303, true, 2.8);
		CDW.addDriver("Kiritsugu Emiya", 37240342, true, 6.7);
		
		//casual
		CDW.addDriver("Sakura Matou", 22478342, false, 2.6);
		CDW.addDriver("Shinji Matou", 35279375, false, 0.7);
		CDW.addDriver("Artoria Pendragon", 10920380, false, 4.2);
		CDW.addDriver("Tokiomi Tohsaka", 89263321, false, 8.1);

//		CDW.addTruck("18T7", TruckType.HazardousTruck, false);
//		CDW.addTruck("75E6", TruckType.HazardousTruck, false);
//		CDW.addTruck("29F3", TruckType.HazardousTruck, true);
//		CDW.addTruck("1EB6", TruckType.HazardousTruck, true);
//		CDW.addTruck("32A9", TruckType.WasteTruck, false);
//		CDW.addTruck("73C2", TruckType.WasteTruck, false);
//		CDW.addTruck("99T9", TruckType.WasteTruck, true);
//		CDW.addTruck("78FA", TruckType.WasteTruck, true);

		CDW.addCustomerEntry(Nc0, date, "On demand");
		CDW.addCustomerEntry(Nc1, date, "Regular");
		CDW.addCustomerEntry(Nc2, date, "Regular");
		CDW.addCustomerEntry(Nc3, date, "Regular");
		CDW.addCustomerEntry(Nc4, date, "Regular");
		//temp objects for collection requirements testing
//		CDW.addCustomerEntry(Nc0, date, "On demand");
//		CDW.addCustomerEntry(Nc1, date, "Regular");
//		CDW.addCustomerEntry(Nc2, date, "Regular");
//		CDW.addCustomerEntry(Nc3, date, "Regular");
//		CDW.addCustomerEntry(Nc4, date, "Regular");
//		CDW.addCustomerEntry(Nc0, date, "On demand");
//		CDW.addCustomerEntry(Nc1, date, "Regular");
//		CDW.addCustomerEntry(Nc2, date, "Regular");
//		CDW.addCustomerEntry(Nc3, date, "Regular");
//		CDW.addCustomerEntry(Nc4, date, "Regular");
//		CDW.addCustomerEntry(Nc5, date.plusDays(5), "On demand");//shouldn't show up
		
		CDW.addCustomerEntry(Sc1, date, "Regualr");
		CDW.addCustomerEntry(Sc2, date, "Regular");
		CDW.addCustomerEntry(Sc3, date, "On demand");
		CDW.addCustomerEntry(Sc4, date.plusDays(5), "On demand"); //shouldn't show up
		
		CDW.addCustomerEntry(HNc1, date, "On demand");
		CDW.addCustomerEntry(HNc2, date, "On demand");
		CDW.addCustomerEntry(HNc3, date, "Regular");
		CDW.addCustomerEntry(HNc4, date.plusDays(5), "Regular"); //shouldn't show up
		
		CDW.addCustomerEntry(HSc1, date, "Regular");
		CDW.addCustomerEntry(HSc2, date, "On demand");
		CDW.addCustomerEntry(HSc3, date, "On demand");
		CDW.addCustomerEntry(HSc4, date.plusDays(5), "On demand"); //shouldn't show up
		
		launch(args);
	}
}
