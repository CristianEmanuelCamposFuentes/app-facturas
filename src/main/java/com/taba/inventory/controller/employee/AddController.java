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

public class AddController implements Initializable, EmployeeInterface {

    @FXML
    private TextField firstField, lastField, usernameField, phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button saveButton;
    private EmployeeModel employeeModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeModel = new EmployeeModel();
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        firstField.setText("");
        lastField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        phoneField.setText("");
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (validateInput()) {

            Employee employee = new Employee(
                    firstField.getText(),
                    lastField.getText(),
                    usernameField.getText(),
                    DigestUtils.sha1Hex(passwordField.getText()),
                    phoneField.getText()
            );

            employeeModel.saveEmployee(employee);
            EMPLOYEELIST.clear();
            EMPLOYEELIST.addAll(employeeModel.getEmployees());

            ((Stage) saveButton.getScene().getWindow()).close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Completado");
            alert.setHeaderText("Empleado Creado!");
            alert.setContentText("El empleado fue creado exitosamente");
            alert.showAndWait();
        }
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

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos incorrectos");
            alert.setHeaderText("Por favor, corrija los campos incompletos");
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
