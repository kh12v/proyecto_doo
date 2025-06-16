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

    public boolean alimentar(Alimento alimento) {
        hambre.incrementar(alimento.getPuntosAlimento());
        return true;
    }

}
