package Logica;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Jaula implements Actualizable{
    private Mascota mascota;
    private final Especies[] especiesPermitidas;
    private final Indicador limpieza;
    private static int idCounter = 0;
    private final int id;
    private final TipoContenedor tipoContenedor;
    public Jaula(Especies[] especiesPermitidas, TipoContenedor tipo) {
        id = idCounter++;
        this.especiesPermitidas = especiesPermitidas;
        limpieza = new Indicador();
        tipoContenedor = tipo;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Mascota removerMascota() {
        Mascota m = mascota;
        mascota = null;
        return m;
    }

    public ArrayList<Indicador> getIndicadores() {
        ArrayList<Indicador> indicadores = new ArrayList<>(mascota.getIndicadores());
        indicadores.add(limpieza);
        return indicadores;
    }

    public void ingresarMascota(Mascota mascota) {
        if (this.mascota != null || !admiteEspecie(mascota.getEspecie())) {return;}
        this.mascota = mascota;
    }

    public boolean admiteEspecie(Especies especie) {
        return Arrays.stream(especiesPermitidas).anyMatch(i->i==mascota.getEspecie());
    }
    public boolean estaVacia() {
        return mascota==null;
    }
    @Override
    public void actualizar() {
        limpieza.actualizar();
        mascota.actualizar();
    }

    public int getID() {
        return id;
    }

    public TipoContenedor getTipoContenedor() {
        return tipoContenedor;
    }
}

