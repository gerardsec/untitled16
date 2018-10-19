package sample;

import com.sun.prism.impl.Disposer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
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
    nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    //crea un campo text para hacer editable la celda
    nombre.setCellFactory(TextFieldTableCell.forTableColumn());
    edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    IntegerStringConverter converterInt = new IntegerStringConverter();
    edad.setCellFactory(TextFieldTableCell.<Persona, Integer>forTableColumn(converterInt));
    edad.setOnEditCommit(dataInt -> {
      dataInt.getRowValue().setEdad(dataInt.getNewValue());
    });
    edad.setOnEditCommit(data -> {
      data.getRowValue().setEdad(data.getNewValue());
    });
    nacimiento.setCellValueFactory(new PropertyValueFactory<>("nacimiento"));
    //Uso de converter para editar fechas
    LocalDateStringConverter converter = new LocalDateStringConverter();
    nacimiento.setCellFactory(TextFieldTableCell.<Persona, LocalDate>forTableColumn(converter));
    nacimiento.setOnEditCommit(data -> {
      data.getRowValue().setNacimiento(data.getNewValue());
    });
    //personaTableView.getColumns().setAll(clave, nombre, edad, nacimiento);

    //Agrega una columna con un botón
    TableColumn col_action = new TableColumn("Action");
    personaTableView.getColumns().add(col_action);

    col_action.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {
              @Override
              public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
              }
            }
    );
    //Agrega el botón a la celda
    col_action.setCellFactory(new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record,Boolean>>() {
      @Override
      public TableCell<Disposer.Record,Boolean> call(TableColumn<Disposer.Record, Boolean> param) {
        return new ButtonCell();
      }
    });
    personaTableView.setItems(personaObservableList);
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
      System.out.println(persona.getClave() + "," + persona.getNombre() + "," + persona.getNacimiento() + ", edad:" + persona.getEdad());
    }
  }

  public void clickOnTable(MouseEvent mouseEvent) {
    System.out.println("Clic");

  }

  public void editNombre(TableColumn.CellEditEvent<Persona, String> personaStringCellEditEvent) {
    System.out.println("Se modificó nombre:" + personaStringCellEditEvent.getOldValue() + " a:" + personaStringCellEditEvent.getNewValue());
    //personaStringCellEditEvent.getRowValue()
    Persona p = personaStringCellEditEvent.getRowValue();
    p.setNombre(personaStringCellEditEvent.getNewValue());
  }

  private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
    final Button cellButton = new Button("Borrar");
    ButtonCell(){

      //Action when the button is pressed
      cellButton.setOnAction(new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent t) {
          // get Selected Item
          Persona currentPerson = (Persona) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
          //remove selected item from the table list
          personaObservableList.remove(currentPerson);
        }
      });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
      super.updateItem(t, empty);
      if(!empty){
        setGraphic(cellButton);
      }
    }
  }

}