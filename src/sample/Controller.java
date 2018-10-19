package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.LocalDateStringConverter;
import sample.model.Persona;

import java.time.LocalDate;
import java.util.*;

public class Controller {
  public String numCasosVar;
  @FXML
  public TableColumn<Persona, Integer> clave;
  @FXML
  public TableColumn<Persona, String> nombre;
  @FXML
  public TableColumn<Persona, Integer> edad;
  @FXML
  public TableColumn<Persona, LocalDate> nacimiento;

  public ObservableList<Persona> personaObservableList;

  @FXML
  TableView<Persona> personaTableView = new TableView<>();
  @FXML
  private TextField numCasos;

  static public ObservableList<Persona> generaPersonas() {
    List<Persona> personaList = new ArrayList<Persona>();

    Integer clave;
    String nombre;
    Integer edad;
    LocalDate nacimiento;

    clave = 0;
    nombre = "Juan";
    edad = 20;
    nacimiento = LocalDate.now();
    personaList.add(new Persona(clave, nombre, edad, nacimiento));

    clave = 1;
    nombre = "Pedro";
    edad = 21;
    personaList.add(new Persona(clave, nombre, edad, nacimiento));

    clave = 2;
    nombre = "Luis";
    edad = 22;
    personaList.add(new Persona(clave, nombre, edad, nacimiento));

    ObservableList<Persona> personaObservableList0 = FXCollections.observableList(personaList);
    return personaObservableList0;
  }

  //@Override
  public void initialize() {
    personaObservableList = generaPersonas();

    personaTableView.setEditable(true);
    personaTableView.setItems(personaObservableList);
    personaTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    clave.setCellValueFactory(new PropertyValueFactory<>("clave"));
    //clave.setCellFactory(TextFieldTableCell.forTableColumn(Callback<>));
    nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    //crea un campo text para hacer editable la celda
    nombre.setCellFactory(TextFieldTableCell.forTableColumn());
    edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    nacimiento.setCellValueFactory(new PropertyValueFactory<>("nacimiento"));
    //Uso de converter para editar fechas
    LocalDateStringConverter converter=new LocalDateStringConverter();
    nacimiento.setCellFactory(TextFieldTableCell.<Persona, LocalDate>forTableColumn(converter));
    nacimiento.setOnEditCommit(data -> {data.getRowValue().setNacimiento(data.getNewValue());});
    //nacimiento.setCellFactory();
    personaTableView.getColumns().setAll(clave, nombre, edad, nacimiento);
  }

  @FXML
  public void recibirNumero(ActionEvent actionEvent) {
    //generaPersonas();
    numCasosVar = "ok";
    String cadena = numCasos.getText();
    Integer largo = numCasos.getLength();
    System.out.println(cadena + " largo:" + largo);
    numCasos.textProperty().setValue(numCasosVar);
    for (Persona persona : personaObservableList) {
      System.out.println(persona.getClave()+","+persona.getNombre()+","+persona.getNacimiento());
    }
  }

  public void clickOnTable(MouseEvent mouseEvent) {

    System.out.println("Clic");
  }
}
