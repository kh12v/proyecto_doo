package Logica;

import Logica.Enums.Especie;
import Logica.Enums.TipoJaula;

import java.util.Arrays;

/**
 * Representa una jaula que puede almacenar una sola {@code Mascota} de una variedad de especies
 */
public abstract class Jaula implements Actualizable {
    private static int idCounter = 294187;
    private final Especie[] especiesPermitidas;
    private final int id;
    private final TipoJaula tipoJaula;
    private Mascota mascota;
    private boolean vacia;

    public Jaula(Especie[] especiesPermitidas, TipoJaula tipo) {
        id = idCounter++;
        this.especiesPermitidas = especiesPermitidas;
        tipoJaula = tipo;
        vacia = true;
    }


    /**
     * Regresa la {@code Mascota} en la {@code Jaula}
     * @return la {@code Mascota}, o null en caso de esté vacía
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Elimina la {@code Mascota} en la {@code Jaula}
     */
    public void removerMascota() {
        mascota = null;
        vacia = true;
    }
    /**
     * Los indicadores de una mascota
     * @return un arreglo con los valores en cada indicador
     */
    public int[] getIndicadores() {
        if (vacia) {
            return new int[]{0,0,0,0};
        }
        return mascota.getIndicadores();
    }

    /**
     * Ingresa una {@code Mascota} a la {@code Jaula}, pero solo si la {@code Jaula} admite su especie
     * @param mascota la {@code Mascota} a ingresar
     */
    public void ingresarMascota(Mascota mascota) {
        if (estaVacia() && admiteEspecie(mascota.getEspecie())) {
            this.mascota = mascota;
            vacia = false;
            System.out.println("Mascota: " + mascota);
        }
    }


    /**
     * Verifica si una especie de animal puede vivir en la jaula
     * @param especie la especie que se quiere consultar
     * @return {@code true} si la jaula admite la especie, {@code false} si no
     */
    public boolean admiteEspecie(Especie especie) {
        return Arrays.stream(especiesPermitidas).anyMatch(i -> i == especie);
    }

    /**
     * Verifica si la jaula esta vacia
     * @return {@code true} si la jaula esta vacia, {@code false} si no
     */
    public boolean estaVacia() {
        return vacia;
    }

    @Override
    public void actualizar() {
        if (vacia) {
            return;
        }
        mascota.actualizar();
    }


    /**
     * Obtiene la ID de la jaula
     * @return La ID
     */
    public int getID() {
        return id;
    }

    /**
     * Obtiene el tipo de jaula
     * @return El tipo
     */
    public TipoJaula getTipoJaula() {
        return tipoJaula;
    }
}

