package Logica;

import java.util.ArrayList;

public class Tienda implements Actualizable {
    private final String nombre;
    private int dinero;
    private int renta;
    private final ArrayList<Double> calificaciones;
    private final ArrayList<Jaula> jaulas;
    private final ArrayList<Empleado> empleados;
    private final ArrayList<Cliente> clientes;
    private final int[] stockMedicamentos;
    private final int[] stockJuguetes;
    private final int[] stockAlimentos;

    public static final int I_Perro = 0;
    public static final int I_Gato = 1;
    public static final int I_Loro = 2;
    public static final int I_Hamster = 3;

    public static final int C_Exito = 1;
    public static final int C_Error = -1;
    public static final int C_DineroInsuficiente = -2;
    public static final int C_NoJaulaDisponible = -3;

    public Tienda(String nombre, int dineroInicial) {
        this.nombre = nombre;
        dinero = dineroInicial;
        renta = 5;
        jaulas = new ArrayList<>();
        empleados = new ArrayList<>();
        calificaciones = new ArrayList<>();
        calificaciones.add(3.0);
        stockMedicamentos = new int[4];
        stockJuguetes = new int[4];
        stockAlimentos = new int[4];
        clientes = new ArrayList<>();
    }

    public int comprarProducto(Producto producto) {
        TipoProducto tipoProducto = producto.getTipoProducto();
        if (tipoProducto == TipoProducto.Mascota) return comprarMascota(producto);
        else if (tipoProducto == TipoProducto.Comida) return comprarAlimento(producto);
        else if (tipoProducto == TipoProducto.Juguete) return comprarJuguete(producto);
        else if (tipoProducto == TipoProducto.Medicamento) return comprarMedicamento(producto);

        return C_Error;
    }

    public int comprarMascota(Producto producto) {
        if (!producto.esMascota()) return C_Error;
        else if (producto.getPrecio() >= dinero ) return C_DineroInsuficiente;

        Especies especie = producto.getEspecie();

        for (Jaula jaula : jaulas) {
            // los operadores emplean short circuiting (no evalúa la derecha si la izquierda es falsa)
            if (jaula.estaVacia() && jaula.admiteEspecie(especie)) {
                jaula.ingresarMascota(new Mascota("wuah", especie));
                dinero -= producto.getPrecio();
                return jaula.getID();
            }
        }

        return C_NoJaulaDisponible;
    }

    public int comprarAlimento(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Comida) return C_Error;
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();
        switch (producto) {
            case ComidaPerro -> stockAlimentos[I_Perro]++;
            case ComidaGato -> stockAlimentos[I_Gato]++;
            case ComidaLoro -> stockAlimentos[I_Loro]++;
            case ComidaHamster -> stockAlimentos[I_Hamster]++;
        }
        return C_Exito;
    }

    public int comprarJuguete(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Juguete) return C_Error;
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();
        switch (producto) {
            case JuguetePerro -> stockJuguetes[I_Perro]++;
            case JugueteGato -> stockJuguetes[I_Gato]++;
            case JugueteLoro -> stockJuguetes[I_Loro]++;
            case JugueteHamster -> stockJuguetes[I_Hamster]++;
        }
        return C_Exito;
    }

    public int comprarMedicamento(Producto producto) {
        if (producto.getTipoProducto() != TipoProducto.Medicamento) return C_Error;
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();
        switch (producto) {
            case MedicamentoPerro -> stockMedicamentos[I_Perro]++;
            case MedicamentoGato -> stockMedicamentos[I_Gato]++;
            case MedicamentoLoro -> stockMedicamentos[I_Loro]++;
            case MedicamentoHamster -> stockMedicamentos[I_Hamster]++;
        }
        return C_Exito;
    }

    public int comprarJaula(TipoContenedor contenedor) {
        if (contenedor.precio > dinero) {
            return C_DineroInsuficiente;
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

    public int contratarEmpleado(Cargo cargo) {
        if (cargo.getSalario() > dinero) return C_DineroInsuficiente;

        Empleado empleado = new Empleado(this, cargo);
        empleados.add(empleado);
        return empleado.getID();
    }

    public int despedirEmpleado(int id) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getID() == id) {
                empleados.remove(i);
                return 1;
            }
        }

        return -1;
    }

    public void pagarSalario(int salario) {
        dinero -= salario;
    }

    public void actualizar(){
        dinero -= renta;
        jaulas.forEach(Jaula::actualizar);
        empleados.forEach(e -> pagarSalario(e.getSalario()));
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

    public double getCalificacion(){
        return calificaciones.stream().reduce(0.0,Double::sum)/calificaciones.size();
    }

    public int getStockAlimentos(int indiceEspecie) {
        return stockAlimentos[indiceEspecie];
    }

    public int getStockMedicamentos(int indiceEspecie) {
        return stockMedicamentos[indiceEspecie];
    }

    public int getStockJuguetes(int indiceEspecie) {
        return stockJuguetes[indiceEspecie];
    }
}
