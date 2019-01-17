package controller;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Klijenti;
import model.Trajanje;

public class internetPaketController {

    @FXML
    private TableView<Klijenti> tableView;

    @FXML
    TableColumn<Klijenti, Integer> id = new TableColumn<Klijenti, Integer>("id");

    @FXML
    private TableColumn<Klijenti, String> ime;

    @FXML
    private TableColumn<Klijenti, String> prezime;

    @FXML
    private TableColumn<Klijenti, String> adresa;

    @FXML
    private TableColumn<Klijenti, String> trajanje;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldLastname;

    @FXML
    private TextField fieldAddress;

    @FXML
    private ToggleGroup duration;

    @FXML
    private RadioButton radio_1;

    @FXML
    private RadioButton radio_2;

    ObservableList<String> brzine = FXCollections.<String>observableArrayList("2 Mbit", "5 Mbit", "10 Mbit", "20 Mbit", "50 Mbit", "100 Mbit");

    @FXML
    private ChoiceBox<String> brzinaIzbor;

    @FXML
    private ChoiceBox<String> protokIzbor;

    ObservableList<Klijenti> clients = FXCollections.<Klijenti>observableArrayList();

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;
    
    int idP = 1;
    
    ObservableList<Integer> idList = FXCollections.<Integer>observableArrayList();
    

    Klijenti klijenti;

    public internetPaketController() {
    }

    @FXML
    public void initialize() {
        klijenti = new Klijenti();
        brzinaIzbor.getItems().addAll(brzine);
        fieldName.textProperty().bindBidirectional(klijenti.firstNameProperty());
        fieldLastname.textProperty().bindBidirectional(klijenti.lastNameProperty());
        fieldAddress.textProperty().bindBidirectional(klijenti.addressProperty());
        duration.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue != null) {
                    RadioButton selected = (RadioButton) newValue;
                    switch (selected.getId()) {
                        case "radio_1":
                            klijenti.trajanjeProperty().set(Trajanje.GODINA);
                            break;
                        case "radio_2":
                            klijenti.trajanjeProperty().set(Trajanje.DVIJE_GODINE);
                            break;
                    }
                }
            }
        });

        brzinaIzbor.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String brzina = brzinaIzbor.getValue();
                klijenti.brzinaIzborProperty().set(brzina);
            }
        });

        
        protokIzbor.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String protok = protokIzbor.getValue();
                klijenti.protokIzborProperty().set(protok);
            }
        });
        
    }

    @FXML
    private void saveClient() {
        if (klijenti.isValid()) {
            clients = tableView.getItems();
            int idPomocni = idP++;
            idList.addAll(idPomocni);
            RadioButton selected = (RadioButton) duration.getSelectedToggle();
            switch (selected.getId()) {
                case "radio_1":
                    clients.add(new Klijenti(idPomocni, fieldName.getText(), fieldLastname.getText(), fieldAddress.getText(), Trajanje.GODINA, brzinaIzbor.getValue(), protokIzbor.getValue()));
                    break;
                case "radio_2":
                    clients.add(new Klijenti(idPomocni, fieldName.getText(), fieldLastname.getText(), fieldAddress.getText(), Trajanje.DVIJE_GODINE, brzinaIzbor.getValue(), protokIzbor.getValue()));
                    break;
            }
            tableView.setItems(clients);
        } else {
            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = klijenti.errorsProperty().get();
            for (String errList1 : errList) {
                errMsg.append(errList1);
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Klijent ne moze biti unesen!");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }
    }

    @FXML
    private void deleteClient() {
        clients = tableView.getItems();
        int index = tableView.selectionModelProperty().getValue().getSelectedIndex();
        clients.remove(index);
        tableView.setItems(clients);
    }

}
