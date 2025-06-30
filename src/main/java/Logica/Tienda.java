package Logica;

import java.util.ArrayList;

public class Tienda implements Actualizable {
    String nombre;
    int dinero;
    int renta;
    ArrayList<Jaula> jaulas;
    ArrayList<Empleado> empleados;
    int[] stockMedicamentos;
    int[] stockAlimentos;

    public Tienda(String nombre, int dineroInicial) {
        this.nombre = nombre;
        dinero = dineroInicial;
        renta = 0;
        this.jaulas = new ArrayList<>();
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
        Jaula jaulaComprada = switch (contenedor){
            case JaulaGrande -> new JaulaGrande();
            case JaulaPequena -> new JaulaPequena();
        };
        jaulas.add(jaulaComprada);
        return true;
    }

    public void pagarSalario(int salario) {
        dinero -= salario;
    }

    public void actualizar(){
        dinero -= renta;
        jaulas.forEach(Jaula::actualizar);
    }

    public ArrayList<Jaula> getJaulas() {
        return jaulas;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public boolean encontrarIDMascotas(int id){
        return jaulas.stream().anyMatch(i -> i.getMascota().getID() == id);
    }
    public boolean encontrarIDEmpleados(int id){
        return empleados.stream().anyMatch(i -> i.getID() == id);
    }

    public int comprarMascota(Especies especie, String nombreMascota) {
        if (dinero < especie.precio){
            return -1;
        }
        dinero -= especie.precio;
        Mascota mascota = new Mascota(nombreMascota, especie);
        Jaula jaulaDestino = jaulas.stream()
                .filter(j -> j.estaVacia() && j.admiteEspecie(mascota.getEspecie()))
                .findFirst()
                .orElse(null);
        if (jaulaDestino != null) {
            jaulaDestino.ingresarMascota(mascota);
            return jaulaDestino.getID();
        }
        return -1;
    }

    public int getDinero() {
        return this.dinero;
    }

}
