package Logica;

public class Mascota {
    private String nombre;
    private Especies especie;
    Indicador hambre;
    Indicador salud;
    Indicador felicidad;

    public Mascota(String nombre, Especies especie) {
        this.nombre = nombre;
        this.especie = especie;
        hambre = new Indicador();
        salud = new Indicador();
        felicidad = new Indicador();
    }

    public boolean alimentar(Alimentos alimento) {
        if (puedeComer(alimento)) {
            hambre.incrementar(alimento.precio);
            return true;
        }
        return false;
    }

    public boolean puedeComer(Alimentos alimento) {
        return alimento.esComiblePor(especie);
    }

    public String getNombre() {
        return nombre;
    }

    public Especies getEspecie() {
        return especie;
    }

}
