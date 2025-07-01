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

    public int comprarProducto(Producto producto) {
        TipoProducto tipoProducto = producto.getTipoProducto();
        if (tipoProducto == TipoProducto.Mascota) return comprarMascota(producto);
        else if (tipoProducto == TipoProducto.Comida) return comprarAlimento(producto);
        else if (tipoProducto == TipoProducto.Juguete) return comprarJuguete(producto);
        else if (tipoProducto == TipoProducto.Medicamento) return comprarMedicamento(producto);

        return -1;
    }

    public int comprarMascota(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Mascota) return -1;
        if (producto.getPrecio() > dinero) return -1;

        Especies especie;
        if (producto == Producto.Perro) especie = Especies.Perro;
        else if (producto == Producto.Gato) especie = Especies.Gato;
        else if (producto == Producto.Loro) especie = Especies.Loro;
        else if (producto == Producto.Hamster) especie = Especies.Hamster;
        else return -1;

        for (Jaula jaula : jaulas) {
            if (!jaula.estaVacia()) continue;

            if (jaula.admiteEspecie(especie)) {
                jaula.ingresarMascota(new Mascota("wuah", especie));
                dinero -= producto.getPrecio();
                return jaula.getID();
            }
        }

        return -1;
    }

    public int comprarAlimento(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Comida) return -1;
        if (producto.getPrecio() > dinero) return -1;

        dinero -= producto.getPrecio();
        switch (producto) {
            case ComidaPerro -> stockAlimentos[I_Perro]++;
            case ComidaGato -> stockAlimentos[I_Gato]++;
            case ComidaLoro -> stockAlimentos[I_Loro]++;
            case ComidaHamster -> stockAlimentos[I_Hamster]++;
        }
        return 1;
    }

    public int comprarJuguete(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Juguete) return -1;
        if (producto.getPrecio() > dinero) return -1;

        dinero -= producto.getPrecio();
        switch (producto) {
            case JuguetePerro -> stockJuguetes[I_Perro]++;
            case JugueteGato -> stockJuguetes[I_Gato]++;
            case JugueteLoro -> stockJuguetes[I_Loro]++;
            case JugueteHamster -> stockJuguetes[I_Hamster]++;
        }
        return 1;
    }

    public int comprarMedicamento(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Medicamento) return -1;
        if (producto.getPrecio() > dinero) return -1;

        dinero -= producto.getPrecio();
        switch (producto) {
            case MedicamentoPerro -> stockMedicamentos[I_Perro]++;
            case MedicamentoGato -> stockMedicamentos[I_Gato]++;
            case MedicamentoLoro -> stockMedicamentos[I_Loro]++;
            case MedicamentoHamster -> stockMedicamentos[I_Hamster]++;
        }
        return 1;
    }

    public int comprarJaula(TipoContenedor contenedor) {
        if (contenedor.precio > dinero) {
            return -1;
        }

        dinero -= contenedor.precio;
        Jaula jaulaComprada = switch (contenedor){
            case JaulaGrande -> new JaulaGrande();
            case JaulaPequena -> new JaulaPequena();
        };
        jaulas.add(jaulaComprada);
        System.out.println(jaulaComprada.getID());
        return jaulaComprada.getID();
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

    public boolean encontrarIDJaulas(int id){
        return jaulas.stream().anyMatch(i -> i.getID() == id);
    }

    public int getDinero() {
        return this.dinero;
    }

}
