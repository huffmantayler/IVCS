package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Main extends Application {

	private static int azithInt;
	private static int cefInt;
	private static int clin6Int;
	private static int clin9Int;
	private static int insulinInt;
	private static int int750;
	private static int int1gm;
	private static int int1250;
	private static int int1500;
	private static int int2gm;

	private static TextField numAzith = new TextField();
	private static TextField numCef = new TextField();
	private static TextField numClin6 = new TextField();
	private static TextField numClin9 = new TextField();
	private static TextField numInsulin = new TextField();
	private static TextField num750 = new TextField();
	private static TextField num1gm = new TextField();
	private static TextField num1250 = new TextField();
	private static TextField num1500 = new TextField();
	private static TextField num2gm = new TextField();
	
	private static ArrayList<TextField> fields = new ArrayList<TextField>();

	private static CategoryAxis xAxis = new CategoryAxis();
	private static NumberAxis yAxis = new NumberAxis();
	private static BarChart<String, Number> chart = new BarChart<String, Number>(xAxis, yAxis);
	private static XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();

	private static ArrayList<String> drugs = new ArrayList<String>();
	private static ArrayList<Integer> amount = new ArrayList<Integer>();

	@Override
	public void start(Stage primaryStage) {
		try {

			GridPane gridPane = new GridPane();
			StackPane stack = new StackPane();
			Scene scene = new Scene(stack, 700, 550);
			primaryStage.setTitle("Daily Work Load");
			gridPane.setPadding(new Insets(30, 30, 30, 30));
			gridPane.setVgap(20);
			gridPane.setHgap(50);

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open CSV File");

			Label cefazolin = new Label("Cefazolin");
			Label clinda6 = new Label("Clindamycin 600mg");
			Label clinda9 = new Label("Clindamycin 900mg");
			Label insulin = new Label("Insulin");
			Label azith = new Label("Azithromycin");
			Label vanc750 = new Label("Vancomycin 750mg");
			Label vanc1 = new Label("Vancomycin 1gm");
			Label vanc1250 = new Label("Vancomycin 1250mg");
			Label vanc1500 = new Label("Vancomycin 1500mg");
			Label vanc2 = new Label("Vancomycin 2gm");

			fields.add(numAzith);
			numAzith.setMaxWidth(40);
			numAzith.setAlignment(Pos.CENTER_RIGHT);
			fields.add(numCef);
			numCef.setMaxWidth(40);
			numCef.setAlignment(Pos.CENTER_RIGHT);
			fields.add(numClin6);
			numClin6.setMaxWidth(40);
			numClin6.setAlignment(Pos.CENTER_RIGHT);
			fields.add(numClin9);
			numClin9.setMaxWidth(40);
			numClin9.setAlignment(Pos.CENTER_RIGHT);
			fields.add(numInsulin);
			numInsulin.setMaxWidth(40);
			numInsulin.setAlignment(Pos.CENTER_RIGHT);
			fields.add(num750);
			num750.setMaxWidth(40);
			num750.setAlignment(Pos.CENTER_RIGHT);
			fields.add(num1gm);
			num1gm.setMaxWidth(40);
			num1gm.setAlignment(Pos.CENTER_RIGHT);
			num1250.setAlignment(Pos.CENTER_RIGHT);
			num1250.setMaxWidth(40);
			fields.add(num1250);
			num1500.setAlignment(Pos.CENTER_RIGHT);
			num1500.setMaxWidth(40);
			fields.add(num1500);
			num2gm.setMaxWidth(40);
			num2gm.setAlignment(Pos.CENTER_RIGHT);
			fields.add(num2gm);

			Button reset = new Button("Reset");
			Button calc = new Button("Calculate Total");
			Button returnBtn = new Button("Return to Main Screen");
			Button standardBtn = new Button("Standards");

			Button monday = new Button("Monday Standard");
			Button tuesday = new Button("Tuesday Standard");
			Button thursday = new Button("Thursday Standard");
			Button friday = new Button("Friday Standard");

			Button loadFile = new Button("Load File");

			Image logo = new Image("File:src/Resource/OmnicellLogo.png");
			Image robot = new Image("File:src/Resource/Robot.png");
			ImageView device = new ImageView(robot);
			ImageView logo1 = new ImageView(logo);
			logo1.setFitHeight(100);
			logo1.setFitWidth(200);
			device.setFitHeight(300);
			device.setFitWidth(180);

			VBox vboxDrug = new VBox(20.25);
			VBox vboxText = new VBox(10);
			VBox vboxImage = new VBox(20);
			vboxDrug.setPadding(new Insets(25, 25, 25, 25));
			vboxText.setPadding(new Insets(25, 25, 25, 25));
			vboxImage.setAlignment(Pos.CENTER);
			gridPane.add(vboxDrug, 0, 0);
			gridPane.add(vboxText, 1, 0);
			gridPane.add(vboxImage, 2, 0);

			vboxDrug.getChildren().addAll(azith, cefazolin, clinda6, clinda9, insulin, vanc750, vanc1, vanc1250,
					vanc1500, vanc2, reset, standardBtn);
			vboxText.getChildren().addAll(numAzith, numCef, numClin6, numClin9, numInsulin, num750, num1gm, num1250,
					num1500, num2gm, calc, loadFile);
			vboxImage.getChildren().addAll(logo1, device);

			VBox totalBox = new VBox(20);
			Text finalText = new Text();
			finalText.setStyle("-fx-font: normal bold 50px 'serif' ");
			totalBox.getChildren().addAll(finalText, returnBtn);
			totalBox.setAlignment(Pos.CENTER);
			Scene totalScene = new Scene(totalBox, 700, 550);

			Stage stage2 = new Stage();
			stage2.initOwner(primaryStage);
			stage2.initModality(Modality.APPLICATION_MODAL);
			VBox standards = new VBox(10);
			standards.setAlignment(Pos.CENTER);
			standards.setPadding(new Insets(25, 25, 25, 25));
			Scene standardScene = new Scene(standards, 200, 200);
			standards.getChildren().addAll(monday, tuesday, thursday, friday);
			stage2.setScene(standardScene);

			reset.setOnAction(e -> {
				numAzith.clear();
				numCef.clear();
				numClin6.clear();
				numClin9.clear();
				numInsulin.clear();
				num750.clear();
				num1gm.clear();
				num1250.clear();
				num1500.clear();
				num2gm.clear();
			});

			calc.setOnAction(e -> {
				

				double total = 0;

				azithInt = Integer.parseInt(numAzith.getText());
				cefInt = Integer.parseInt(numCef.getText());
				clin6Int = Integer.parseInt(numClin6.getText());
				clin9Int = Integer.parseInt(numClin9.getText());
				insulinInt = Integer.parseInt(numInsulin.getText());
				int750 = Integer.parseInt(num750.getText());
				int1gm = Integer.parseInt(num1gm.getText());
				int1250 = Integer.parseInt(num1250.getText());
				int1500 = Integer.parseInt(num1500.getText());
				int2gm = Integer.parseInt(num2gm.getText());

				double azithDoub = (azithInt * 2.0);
				double cefDoub = (cefInt * 1.5);
				double clin6Doub = (clin6Int * 0.75);
				double clin9Doub = (clin9Int * 1.0);
				double insulinDoub = (insulinInt * 1.0);
				double doub750 = (int750 * 1.25);
				double doub1gm = (int1gm * 1.3);
				double doub1250 = (int1250 * 1.4);
				double doub1500 = (int1500 * 1.5);
				double doub2gm = (int2gm * 1.75);
				total = azithDoub + cefDoub + clin6Doub + clin9Doub + insulinDoub + doub750 + doub1gm + doub1250
						+ doub1500 + doub2gm;

				DecimalFormat df = new DecimalFormat("#.00");

//				if (total < 12) {
//					totalBox.setBackground(
//							new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
//				} else if (total < 18) {
//					totalBox.setBackground(
//							new Background(new BackgroundFill(Color.LIME, CornerRadii.EMPTY, Insets.EMPTY)));
//				} else {
//					totalBox.setBackground(
//							new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
//				}

				chart.setTitle("Hours Per Protocol");
				xAxis.setLabel("Drug");
				yAxis.setLabel("Hours");
				series1.setName("Hours");
				series1.getData().add(new XYChart.Data<String, Number>("Azithromycin", azithDoub));
				series1.getData().add(new XYChart.Data<String, Number>("Cefazolin", cefDoub));
				series1.getData().add(new XYChart.Data<String, Number>("Clindamycin 600mg", clin6Doub));
				series1.getData().add(new XYChart.Data<String, Number>("Clindamycin 900mg", clin9Doub));
				series1.getData().add(new XYChart.Data<String, Number>("Insulin", insulinDoub));
				series1.getData().add(new XYChart.Data<String, Number>("Vancomycin 750mg", doub750));
				series1.getData().add(new XYChart.Data<String, Number>("Vancomycin 1gm", doub1gm));
				series1.getData().add(new XYChart.Data<String, Number>("Vancomycin 1250mg", doub1250));
				series1.getData().add(new XYChart.Data<String, Number>("Vancomycin 1500mg", doub1500));
				series1.getData().add(new XYChart.Data<String, Number>("Vancomycin 2gm", doub2gm));
				chart.getData().add(series1);
				
				
				checkTextField(fields);
				finalText.setId("finalText");
				finalText.setText(df.format(total) + " Hours");
				primaryStage.setScene(totalScene);

			});

			returnBtn.setOnAction(e -> {
				series1.getData().clear();
				primaryStage.setScene(scene);
			});

			standardBtn.setOnAction(e -> {
				stage2.showAndWait();
			});

			monday.setOnAction(e -> {
				numAzith.setText("1");
				numCef.setText("3");
				numClin6.setText("0");
				numClin9.setText("1");
				numInsulin.setText("2");
				num750.setText("0");
				num1gm.setText("2");
				num1250.setText("2");
				num1500.setText("2");
				num2gm.setText("0");
				stage2.close();
			});

			tuesday.setOnAction(e -> {
				numAzith.setText("0");
				numCef.setText("2");
				numClin6.setText("1");
				numClin9.setText("0");
				numInsulin.setText("3");
				num750.setText("2");
				num1gm.setText("1");
				num1250.setText("1");
				num1500.setText("2");
				num2gm.setText("1");
				stage2.close();
			});

			thursday.setOnAction(e -> {
				numAzith.setText("0");
				numCef.setText("1");
				numClin6.setText("2");
				numClin9.setText("0");
				numInsulin.setText("3");
				num750.setText("1");
				num1gm.setText("3");
				num1250.setText("1");
				num1500.setText("2");
				num2gm.setText("1");
				stage2.close();
			});

			friday.setOnAction(e -> {
				numAzith.setText("1");
				numCef.setText("3");
				numClin6.setText("1");
				numClin9.setText("1");
				numInsulin.setText("2");
				num750.setText("1");
				num1gm.setText("3");
				num1250.setText("3");
				num1500.setText("2");
				num2gm.setText("0");
				stage2.close();
			});

			loadFile.setOnAction(e -> {

				File CSVfile = fileChooser.showOpenDialog(primaryStage);
				if (CSVfile != null) {
					try {

						readFile(CSVfile);
						loadNumbers(drugs, amount);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});

			totalBox.getChildren().add(chart);
			stack.getChildren().addAll(gridPane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			standardScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void readFile(File fileName) throws IOException {

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while (line != null) {
				String[] values = line.split(",");
				drugs.add(values[0]);
				amount.add(Integer.parseInt(values[1]));
				line = br.readLine();

			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}

}

	public static void loadNumbers(ArrayList<String> drug, ArrayList<Integer> amount) {

		for (int i = 0; i < drug.size(); i++) {
			if (drug.get(i).contains("AZITHROMYCIN")) {
				if (amount.get(i) == 0) {
					numAzith.setText("0");
				} else {
					int azithDivide = (amount.get(i) / 21);
					numAzith.setText(String.valueOf(azithDivide));
				}
			} else if (drug.get(i).contains("CEFAZOLIN")) {
				if (amount.get(i) == 0) {
					numCef.setText("0");
				} else {
					int cefDivide = (amount.get(i) / 21);
					numCef.setText(String.valueOf(cefDivide));
				}
			} else if (drug.get(i).contains("CLINDAMYCIN 600MG")) {
				if (amount.get(i) == 0) {
					numClin6.setText("0");
				} else {
					int clin6Divide = (amount.get(i) / 21);
					numClin6.setText(String.valueOf(clin6Divide));
				}
			} else if (drug.get(i).contains("CLINDAMYCIN 900MG")) {
				if (amount.get(i) == 0) {
					numClin9.setText("0");
				} else {
					int clin9Divide = (amount.get(i) / 21);
					numClin9.setText(String.valueOf(clin9Divide));
				}
			} else if (drug.get(i).contains("INSULIN")) {
				if (amount.get(i) == 0) {
					numInsulin.setText("0");
				} else {
					int insulinDivide = (amount.get(i) / 21);
					numInsulin.setText(String.valueOf(insulinDivide));
				}
			} else if (drug.get(i).contains("VANCOMYCIN 750MG")) {
				if (amount.get(i) == 0) {
					num750.setText("0");
				} else {
					int v750Divide = (amount.get(i) / 21);
					num750.setText(String.valueOf(v750Divide));
				}
			} else if (drug.get(i).contains("VANCOMYCIN 1G")) {
				if (amount.get(i) == 0) {
					num1gm.setText("0");
				} else {
					int v1gmDivide = (amount.get(i) / 21);
					num1gm.setText(String.valueOf(v1gmDivide));
				}
			} else if (drug.get(i).contains("VANCOMYCIN 1250MG")) {
				if (amount.get(i) == 0) {
					num1250.setText("0");
				} else {
					int v1250Divide = (amount.get(i) / 21);
					num1250.setText(String.valueOf(v1250Divide));
				}
			} else if (drug.get(i).contains("VANCOMYCIN 1500MG")) {
				if (amount.get(i) == 0) {
					num1500.setText("0");
				} else {
					int v1500Divide = (amount.get(i) / 21);
					num1500.setText(String.valueOf(v1500Divide));
				}
			} else if (drug.get(i).contains("VANCOMYCIN 2G")) {
				if (amount.get(i) == 0) {
					num2gm.setText("0");
				} else {
					int v2gmDivide = (amount.get(i) / 21);
					num2gm.setText(String.valueOf(v2gmDivide));
				}
			}
		}
	}
	
	
	public void checkTextField(ArrayList<TextField> fields) {
		
		for(TextField field : fields) {
			if(field.getText() == "") {
				field.setText("0");
				System.out.println(field.getText());
			}
		}
	}
	
	

}
