package com.taba.inventory.controller.employee;

import com.taba.inventory.entity.Employee;
import com.taba.inventory.interfaces.EmployeeInterface;
import com.taba.inventory.model.EmployeeModel;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class EditController implements Initializable, EmployeeInterface {

    @FXML
    private TextField firstField, lastField, usernameField, phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextArea addressArea;
    @FXML
    private Button saveButton;
    private long selectedEmployeeId;
    private EmployeeModel employeeModel;
    private Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeModel = new EmployeeModel();
        resetValues();
    }

    public void setEmployee(Employee employee, long selectedEmployeeId) {
        this.employee = employee;
        this.selectedEmployeeId = selectedEmployeeId;
        setData();
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Employee editedEmployee = new Employee(
                    employee.getId(),
                    firstField.getText(),
                    lastField.getText(),
                    usernameField.getText(),
                    DigestUtils.sha1Hex(passwordField.getText()),
                    phoneField.getText(),
                    addressArea.getText()
            );

            employeeModel.updateEmployee(editedEmployee);
            EMPLOYEELIST.set((int) selectedEmployeeId, editedEmployee);

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Completado");
            alert.setHeaderText("Empleado actualizado!");
            alert.setContentText("Empleado actualizado exitosamente");
            alert.showAndWait();
        }
    }

    private void setData() {
        firstField.setText(employee.getFirstName());
        lastField.setText(employee.getLastName());
        usernameField.setText(employee.getUserName());
        passwordField.setText(employee.getPassword());
        phoneField.setText(employee.getPhone());
    }

    private void resetValues() {
        firstField.setText("");
        lastField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        phoneField.setText("");
        addressArea.setText("");
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        resetValues();
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (firstField.getText() == null || firstField.getText().isEmpty()) {
            errorMessage += "No valid first name!\n";
        }

        if (lastField.getText() == null || lastField.getText().isEmpty()) {
            errorMessage += "No valid last name!\n";
        }

        if (usernameField.getText() == null || usernameField.getText().isEmpty()) {
            errorMessage += "No valid username!\n";
        }

        if (passwordField.getText() == null || passwordField.getText().isEmpty()) {
            errorMessage += "No valid password!\n";
        }

        if (phoneField.getText() == null || phoneField.getText().isEmpty()) {
            errorMessage += "No valid phone number!\n";
        }

        if (addressArea.getText() == null || addressArea.getText().isEmpty()) {
            errorMessage += "No email address!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
