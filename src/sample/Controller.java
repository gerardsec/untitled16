package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Persona;

import java.util.*;

public class Controller {
  public String numCasosVar;
  @FXML
  public TableColumn<Persona, Integer> claveTC;
  @FXML
  public TableColumn<Persona, String> nombreTC;
  @FXML
  public TableColumn<Persona, Integer> edadTC;
  @FXML
  private TableView<Persona> personaTableView;
  @FXML
  private TextField numCasos;

  public List<Persona> personaList = new ArrayList<Persona>();

  static public List<Persona> generaPersonas() {
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

    return personaList;
    //ObservableList<Persona> personaObservableList = FXCollections.observableList(personaList);

  }

  //@Override
  public void initialize() {
  personaList = generaPersonas();
  claveTC.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("clave"));
  nombreTC.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
  edadTC.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
  personaTableView.getItems().setAll(personaList);
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
}
