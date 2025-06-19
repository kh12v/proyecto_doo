package Logica;

import java.util.ArrayList;

public class Tienda {
    String nombre;
    int dinero;
    ArrayList<Mascota> mascotas;
    int[] stockMedicamentos;
    int[] stockAlimentos;

    public Tienda(String nombre) {
        this.nombre = nombre;
        dinero = 0;
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

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }
}
