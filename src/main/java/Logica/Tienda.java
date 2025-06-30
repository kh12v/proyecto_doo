package Logica;

import java.util.ArrayList;

public class Tienda implements Actualizable {
    String nombre;
    int dinero;
    int renta;
    ArrayList<Jaula> jaulas;
    ArrayList<Empleado> empleados;
    int[] stockMedicamentos;
    int[] stockJuguetes;
    int[] stockAlimentos;

    static final int I_Perro = 0;
    static final int I_Gato = 1;
    static final int I_Loro = 2;
    static final int I_Hamster = 3;

    public Tienda(String nombre, int dineroInicial) {
        this.nombre = nombre;
        dinero = dineroInicial;
        renta = 0;
        this.jaulas = new ArrayList<>();
        this.empleados = new ArrayList<>();
        stockMedicamentos = new int[4];
        stockJuguetes = new int[4];
        stockAlimentos = new int[4];
    }

    public boolean comprarProducto(Producto producto) {
        TipoProducto tipoProducto = producto.getTipoProducto();
        if (tipoProducto == TipoProducto.Mascota) return comprarMascota(producto);
        else if (tipoProducto == TipoProducto.Comida) return comprarAlimento(producto);
        else if (tipoProducto == TipoProducto.Juguete) return comprarJuguete(producto);
        else if (tipoProducto == TipoProducto.Medicamento) return comprarMedicamento(producto);

        return false;
    }

    public boolean comprarMascota(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Mascota) return false;
        if (producto.getPrecio() > dinero) return false;

        Especies especie;
        if (producto == Producto.Perro) especie = Especies.Perro;
        else if (producto == Producto.Gato) especie = Especies.Gato;
        else if (producto == Producto.Loro) especie = Especies.Loro;
        else if (producto == Producto.Hamster) especie = Especies.Hamster;
        else return false;

        for (Jaula jaula : jaulas) {
            if (!jaula.estaVacia()) continue;

            if (jaula.admiteEspecie(especie)) {
                jaula.ingresarMascota(new Mascota("wuah", especie));
                dinero -= producto.getPrecio();
                return true;
            }
        }

        return false;
    }

    public boolean comprarAlimento(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Comida) return false;
        if (producto.getPrecio() > dinero) return false;

        dinero -= producto.getPrecio();
        switch (producto) {
            case ComidaPerro -> stockAlimentos[I_Perro]++;
            case ComidaGato -> stockAlimentos[I_Gato]++;
            case ComidaLoro -> stockAlimentos[I_Loro]++;
            case ComidaHamster -> stockAlimentos[I_Hamster]++;
        }
        return true;
    }

    public boolean comprarJuguete(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Juguete) return false;
        if (producto.getPrecio() > dinero) return false;

        dinero -= producto.getPrecio();
        switch (producto) {
            case JuguetePerro -> stockJuguetes[I_Perro]++;
            case JugueteGato -> stockJuguetes[I_Gato]++;
            case JugueteLoro -> stockJuguetes[I_Loro]++;
            case JugueteHamster -> stockJuguetes[I_Hamster]++;
        }
        return true;
    }

    public boolean comprarMedicamento(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Medicamento) return false;
        if (producto.getPrecio() > dinero) return false;

        dinero -= producto.getPrecio();
        switch (producto) {
            case MedicamentoPerro -> stockMedicamentos[I_Perro]++;
            case MedicamentoGato -> stockMedicamentos[I_Gato]++;
            case MedicamentoLoro -> stockMedicamentos[I_Loro]++;
            case MedicamentoHamster -> stockMedicamentos[I_Hamster]++;
        }
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
