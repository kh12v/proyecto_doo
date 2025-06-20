package Logica;

import java.util.ArrayList;

public class Tienda implements Actualizable {
    String nombre;
    int dinero;
    int renta;
    ArrayList<Mascota> mascotas;
    int[] stockMedicamentos;
    int[] stockAlimentos;

    public Tienda(String nombre) {
        this.nombre = nombre;
        dinero = 0;
        renta = 0;
        this.mascotas = new ArrayList<>();
        stockMedicamentos = new int[Medicamentos.values().length];
        stockAlimentos = new int[Alimentos.values().length];
    }

    public boolean comprarMedicamento(Medicamentos medicamento) {
        if (medicamento.precio > dinero) {
            return false;
        }
        dinero -= medicamento.precio;
        stockMedicamentos[medicamento.ordinal()]++;
        return true;
    }

    public boolean comprarAlimento(Alimentos alimento) {
        if (alimento.precio > dinero) {
            return false;
        }
        dinero -= alimento.precio;
        stockMedicamentos[alimento.ordinal()]++;
        return true;
    }

    public void actualizar(){
        dinero -= renta;
        mascotas.forEach(Mascota::actualizar);
    }
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public boolean encontrarID(int id){
        return mascotas.stream().anyMatch(i -> i.getID() == id);
    }
    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }
}
