package Logica;

import java.util.ArrayList;

public class ContenedorAnimal {
    TipoContenedor tipo;
    ArrayList<Mascota> mascotas;
    public ContenedorAnimal(TipoContenedor tipo) {
        this.tipo = tipo;
        mascotas = new ArrayList<>();
    }
    
    public boolean agregarMascota(Mascota mascota) {
        if (mascotas.size() < tipo.capacidad && tipo.especiesAdmisibles.contains(mascota.getEspecie())) {
            mascotas.add(mascota);
            return true;
        } 
        return false;
    }

    public boolean retirarMascota(Mascota mascota) {
        if (!mascotas.contains(mascota)) {
            return false;
        }
        mascotas.remove(mascota);
        return true;
    }
}
