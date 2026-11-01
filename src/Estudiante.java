public class Estudiante {
    private int id;
    private String nombre;
    private String tipoDocumento;
    public Estudiante(int id, String nombre, String tipoDocumento) {
        this.id = id;
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
    }
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

}