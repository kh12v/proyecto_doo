package Logica;

import java.util.ArrayList;
import java.util.List;

public class Mascota implements Actualizable {
    private final String nombre;
    private final Especies especie;
    private final int id;
    private static int idActual = 0;
    Indicador hambre;
    Indicador salud;
    Indicador felicidad;

    public Mascota(String nombre, Especies especie) {
        this.nombre = nombre;
        this.especie = especie;
        this.id = idActual++;
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

    public int getID() {return id;}
    public ArrayList<Indicador> getIndicadores(){
        return new ArrayList<>(List.of(new Indicador[]{hambre, salud, felicidad}));
    }

    @Override
    public void actualizar() {
        hambre.actualizar();
        salud.actualizar();
        felicidad.actualizar();
    }
}
