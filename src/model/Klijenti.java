package model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Klijenti {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");
    private final ObjectProperty<Trajanje> trajanje = new SimpleObjectProperty<>(this, "trajanje", Trajanje.GODINA);
    private final StringProperty brzina = new SimpleStringProperty(this, "brzina", "");
    private final StringProperty protok = new SimpleStringProperty(this, "protok", "");

    public Klijenti() {

    }

    public Klijenti(Integer id, String firstName, String lastName, String address, Trajanje contractDuration, String brzina, String protok) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
        this.trajanje.set(contractDuration);
        this.brzina.set(brzina);
        this.protok.set(protok);
    }

    public Klijenti(String firstName) {
        this.firstName.set(firstName);
    }

    public Klijenti(String firstName, String lastName) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public Trajanje getTrajanje() {
        return trajanje.get();
    }

    public void setTrajanje(Trajanje duration) {
        this.trajanje.set(duration);
    }

    public ObjectProperty<Trajanje> trajanjeProperty() {
        return trajanje;
    }

    public String getBrzina() {
        return brzina.get();
    }

    public void setBrzina(String brzinaIzbor) {
        this.brzina.set(brzinaIzbor);
    }

    public StringProperty brzinaIzborProperty() {
        return brzina;
    }

    public String getProtok() {
        return protok.get();
    }

    public void setProtok(String protokIzbor) {
        this.protok.set(protokIzbor);
    }

    public StringProperty protokIzborProperty() {
        return protok;
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty() {
        return errorList;
    }

    public boolean isValid() {
        boolean isValid = true;
        if (firstName.get() != null && firstName.get().equals("")) {
            errorList.getValue().add(" Polje ime ne moze biti prazno! ");
            isValid = false;
        }
        if (lastName.get().equals("")) {
            errorList.getValue().add(" Polje prezime ne moze biti prazno! ");
            isValid = false;
        }
        if (address.get().equals("")) {
            errorList.getValue().add(" Polje adresa ne moze biti prazno! ");
            isValid = false;
        }
        return isValid;
    }

}
