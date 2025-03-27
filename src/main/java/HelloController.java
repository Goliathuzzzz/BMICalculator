import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Label lblDistance, lblFuel, lblFuelConsumption, lblResult, lblErrorDistance, lblErrorFuel;

    @FXML
    private TextField distanceInput, fuelInput;

    @FXML
    private HBox hbox;

    @FXML
    private Button btnCalculate, btnEnglish, btnFrench, btnJapanese, btnFarsi;

    private ResourceBundle rb;

    private FuelConsumptionCalc fuelConsumptionCalc;

    public void initialize() {
        fuelConsumptionCalc = new FuelConsumptionCalc();
        rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));
    }

    private void setLanguage(Locale locale) {
        rb = ResourceBundle.getBundle("messages", locale);
    }

    @FXML
    private void handleCalculateFuel() {
        double distance = 0;
        double fuel = 0;
        double consumption = 0;
        try {
            distance = Double.parseDouble(distanceInput.getText());
        } catch (NumberFormatException e) {
            lblErrorDistance.setText(rb.getString("error"));
        }
        try {
            fuel = Double.parseDouble(fuelInput.getText());
        } catch (NumberFormatException e) {
            lblErrorFuel.setText(rb.getString("error"));
        }
        try {
            consumption = fuelConsumptionCalc.calculateFuelConsumption(distance, fuel);
        } catch (Exception e) {
            lblResult.setText(rb.getString("error"));
        }
        String formattedConsumption = String.format("%.2f", consumption);
        lblResult.setText(" " + formattedConsumption + " l/100Km ");
    }

    @FXML
    private void handleSetEnglish() {
        setLanguage(new Locale("en", "US"));
        changeFieldLanguage(rb);
    }

    @FXML
    private void handleSetFrench() {
        setLanguage(new Locale("fr", "FR"));
        changeFieldLanguage(rb);
    }

    @FXML
    private void handleSetJapanese() {
        setLanguage(new Locale("ja", "JP"));
        changeFieldLanguage(rb);
    }

    @FXML
    private void handleSetFarsi() {
        setLanguage(new Locale("fa", "IR"));
        changeFieldLanguage(rb);
        hbox.getChildren().clear();
        hbox.getChildren().addAll(lblResult, lblFuelConsumption);
    }

    private void changeFieldLanguage(ResourceBundle resourceBundle) {
        lblDistance.setText(resourceBundle.getString("distance"));
        lblFuel.setText(resourceBundle.getString("fuel_used"));
        btnCalculate.setText(resourceBundle.getString("calculate_fuel_consumption"));
        lblFuelConsumption.setText(resourceBundle.getString("fuel_consumption"));
        if (!lblErrorFuel.getText().isEmpty()) {
            lblErrorFuel.setText(resourceBundle.getString("error"));
        }
        if (!lblErrorDistance.getText().isEmpty()) {
            lblErrorDistance.setText(resourceBundle.getString("error"));
        }
    }
}
