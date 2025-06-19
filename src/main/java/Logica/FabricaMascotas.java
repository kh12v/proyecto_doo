package Logica;

public class FabricaMascotas {
    private static FabricaMascotas instance;
    private int id = 0;
    private FabricaMascotas() {}
    public static FabricaMascotas getInstancia() {
        if (instance == null) {
            instance = new FabricaMascotas();
        }
        return instance;
    }
    public Mascota creaMascota(String nombre, Especies especie) {
        id++;
        return new Mascota(nombre, especie, id);
    }
}
