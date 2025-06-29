package Logica;

import java.util.ArrayList;

public class Tienda implements Actualizable {
    String nombre;
    int dinero;
    int renta;
    int jaulasGrandes = 0;
    int jaulasPequenas = 0;
    ArrayList<Mascota> mascotas;
    ArrayList<Empleado> empleados;
    int[] stockMedicamentos;
    int[] stockAlimentos;

    public Tienda(String nombre, int dineroInicial) {
        this.nombre = nombre;
        dinero = dineroInicial;
        renta = 0;
        this.mascotas = new ArrayList<>();
        this.empleados = new ArrayList<>();
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

    public boolean comprarJaula(TipoContenedor contenedor) {
        if (contenedor.precio > dinero) {
            return false;
        }

        dinero -= contenedor.precio;
        if (contenedor == TipoContenedor.JaulaGrande) {
            jaulasGrandes++;
        } else if (contenedor == TipoContenedor.JaulaPequena) {
            jaulasPequenas++;
        }
        return true;
    }

    public void pagarSalario(int salario) {
        dinero -= salario;
    }

    public void actualizar(){
        dinero -= renta;
        mascotas.forEach(Mascota::actualizar);
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public boolean encontrarIDMascotas(int id){
        return mascotas.stream().anyMatch(i -> i.getID() == id);
    }
    public boolean encontrarIDEmpleados(int id){
        return empleados.stream().anyMatch(i -> i.getID() == id);
    }
    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public int getDinero() {
        return this.dinero;
    }

    public int getJaulasGrandes() {
        return jaulasGrandes;
    }

    public int getJaulasPequenas() {
        return jaulasPequenas;
    }
}
