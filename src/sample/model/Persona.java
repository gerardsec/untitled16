package sample.model;

public class Persona {
  private Integer clave;
  private String nombre;
  private Integer edad;

  public Persona(){}

  public Persona(Integer clave, String nombre, Integer edad){
    this.clave=clave;
    this.nombre=nombre;
    this.edad=edad;
  }

  public Integer getClave() {
    return clave;
  }

  public void setClave(Integer clave) {
    this.clave = clave;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getEdad() {
    return edad;
  }

  public void setEdad(Integer edad) {
    this.edad = edad;
  }


}
