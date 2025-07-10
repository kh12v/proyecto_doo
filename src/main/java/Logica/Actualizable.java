package Logica;

public interface Actualizable {
    /**
     * Este método se usa en clases que implementen variables que cambien con el tiempo,
     * se llama cada 'tick' y solo modifica el estado interno.
     */
    void actualizar();
}
