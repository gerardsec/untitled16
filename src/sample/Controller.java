package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.model.Persona;

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
  TableView<Persona> personaTableView = new TableView<>();
  @FXML
  private TextField numCasos;

  public List<Persona> personaList = new ArrayList<Persona>();
  public ObservableList<Persona> personaObservableList;

  static public ObservableList<Persona> generaPersonas() {
    List<Persona> personaList = new ArrayList<Persona>();

    Map<Integer, Persona> personaMap = new HashMap<>();
    Integer clave;
    String nombre;
    Integer edad;

    //personaMap.put(persona.getClave(), persona);
    clave = 0;
    nombre = "Juan";
    edad = 20;
    personaMap.put(clave, new Persona(clave, nombre, edad));
    personaList.add(new Persona(clave, nombre, edad));

    System.out.println("----");
    personaMap.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v.getClave() + ", " + v.getNombre()));

    clave = 1;
    nombre = "Pedro";
    edad = 21;
    personaMap.put(clave, new Persona(clave, nombre, edad));
    personaList.add(new Persona(clave, nombre, edad));

    System.out.println("----");
    personaMap.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v.getClave() + ", " + v.getNombre()));

    clave = 2;
    nombre = "Luis";
    edad = 22;
    personaMap.put(clave, new Persona(clave, nombre, edad));
    personaList.add(new Persona(clave, nombre, edad));

    System.out.println("----");
    personaMap.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v.getClave() + ", " + v.getNombre()));

    ObservableList<Persona> personaObservableList0 = FXCollections.observableList(personaList);
    return personaObservableList0;

  }

  //@Override
  public void initialize() {
  personaObservableList = generaPersonas();
  personaTableView.setItems(personaObservableList);

//  claveTC.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("clave"));
//  nombreTC.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
//  edadTC.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
//  personaTableView.getItems().setAll(personaObservableList);

  }

  @FXML
  public void recibirNumero(ActionEvent actionEvent) {
    //generaPersonas();
    numCasosVar = "ok";
    String cadena = numCasos.getText();
    Integer largo = numCasos.getLength();
    System.out.println(cadena + " largo:" + largo);
    numCasos.textProperty().setValue(numCasosVar);

  }

  public void clickOnTable(MouseEvent mouseEvent) {

    System.out.println("Clic");
  }
}
