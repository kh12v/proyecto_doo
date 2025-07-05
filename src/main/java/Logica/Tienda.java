package Logica;

import Logica.Enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Tienda implements Actualizable {
    public static final int I_Perro = 0;
    public static final int I_Gato = 1;
    public static final int I_Loro = 2;
    public static final int I_Hamster = 3;
    public static final int C_Exito = 1;
    public static final int C_Error = -1;
    public static final int C_DineroInsuficiente = -2;
    public static final int C_NoJaulaDisponible = -3;
    public static final int C_StockInsuficiente = -4;
    private final String nombre;
    private final ArrayList<Double> calificaciones;
    private final ArrayList<Jaula> jaulas;
    private final ArrayList<Empleado> empleados;
    private final ArrayList<Cliente> clientes;
    private final int[] stockMedicamentos;
    private final int[] stockJuguetes;
    private final int[] stockAlimentos;
    private int dinero;
    private final int renta;

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

    private int comprarMascota(Producto producto) {
        if (producto.getPrecio() >= dinero) return C_DineroInsuficiente;

        Especies especie = (Especies) producto.getEnumReal();

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

    private int comprarAlimento(Producto producto) {
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();

        Alimentos alimento = (Alimentos) producto.getEnumReal();
        stockAlimentos[alimento.ordinal()]++;

        return C_Exito;
    }

    private int comprarJuguete(Producto producto) {
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();

        Juguetes juguete = (Juguetes) producto.getEnumReal();
        stockJuguetes[juguete.ordinal()]++;

        return C_Exito;
    }

    private int comprarMedicamento(Producto producto) {
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();

        Medicamentos medicamento = (Medicamentos) producto.getEnumReal();
        stockMedicamentos[medicamento.ordinal()]++;

        return C_Exito;
    }

    public int comprarJaula(TipoContenedor contenedor) {
        if (contenedor.getPrecio() > dinero) {
            return C_DineroInsuficiente;
        }

        dinero -= contenedor.getPrecio();
        Jaula jaulaComprada = switch (contenedor) {
            case JaulaGrande -> new JaulaGrande();
            case JaulaPequena -> new JaulaPequena();
        };
        jaulas.add(jaulaComprada);
        System.out.println(jaulaComprada.getID());
        return jaulaComprada.getID();
    }

    public int contratarEmpleado(Cargo cargo) {
        if (cargo.getSalario() > dinero) return C_DineroInsuficiente;

        Empleado empleado = new Empleado(cargo);
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

    public void pagarSalario(Empleado e) {
        int salario = e.getSalario();
        if (salario > dinero) {
            e.setTrabajando(false);
        } else {
            dinero -= salario;
            e.setTrabajando(true);
        }
    }

    @Override
    public void actualizar() {
        dinero -= renta;
        jaulas.forEach(Jaula::actualizar);
        empleados.forEach(this::pagarSalario);
        cuidarMascotasEmpleados();
    }

    private void cuidarMascotasEmpleados() {
        if (empleados.isEmpty()) {
            return;
        }
        Stream<Mascota> mascotasOrdenadas = jaulas.stream()
                .filter(Predicate.not(Jaula::estaVacia))
                .map(Jaula::getMascota)
                .sorted(Comparator.comparingInt(k -> Arrays.stream(k.getIndicadores()).reduce(0, Integer::sum)));

        for (Empleado e : empleados) {
            if (e.isTrabajando() && e.getCargo() == Cargo.Cuidador) {
                Mascota m = mascotasOrdenadas.findFirst().orElse(null);
                if (m == null) {
                    break;
                }

                Alimentos alimento = Alimentos.getAlimento(m.getEspecie());
                if (stockAlimentos[alimento.ordinal()] > 0 && m.getIndicadores()[Mascota.I_HAMBRE] < 100 - alimento.getValorNutritivo()) {
                    m.alimentar(alimento);
                    stockAlimentos[alimento.ordinal()]--;
                }

                m.limpiar();

                Medicamentos medicamento = Medicamentos.getMedicamento(m.getEspecie());

                if (stockMedicamentos[medicamento.ordinal()] > 0 && m.getIndicadores()[Mascota.I_SALUD] < 100 - medicamento.valorMedicinal()) {
                    m.darMedicamento(medicamento);
                    stockMedicamentos[medicamento.ordinal()]--;
                }
            }
        }
    }

    public ArrayList<Jaula> getJaulas() {
        return jaulas;
    }

    public int[] getEmpleadosInactivos() {
        return empleados.stream().filter(Predicate.not(Empleado::isTrabajando)).mapToInt(Empleado::getID).toArray();
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public boolean encontrarIDMascotas(int id) {
        return jaulas.stream().anyMatch(i -> i.getMascota().getID() == id);
    }

    public boolean encontrarIDEmpleados(int id) {
        return empleados.stream().anyMatch(i -> i.getID() == id);
    }

    public boolean encontrarIDJaulas(int id) {
        return jaulas.stream().anyMatch(i -> i.getID() == id);
    }

    public int getDinero() {
        return this.dinero;
    }

    public double getCalificacion() {
        return calificaciones.stream().reduce(0.0, Double::sum) / calificaciones.size();
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

    int getIndice(Especies especie) {
        return (especie == Especies.Perro) ? I_Perro
                : (especie == Especies.Gato) ? I_Gato
                : (especie == Especies.Loro) ? I_Loro
                : I_Hamster;
    }

    public int consumirAlimento(int id, Especies especie) {
        int indice = getIndice(especie);

        if (stockAlimentos[indice] <= 0) return C_StockInsuficiente;

        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia()) continue;
            Mascota mascota = jaula.getMascota();
            if (mascota.getID() == id && mascota.alimentar(Alimentos.deEspecie(especie))) {
                stockAlimentos[indice]--;
                return C_Exito;
            }
        }

        return C_Error;
    }

    public int consumirMedicamento(int id, Especies especie) {
        int indice = getIndice(especie);

        if (stockMedicamentos[indice] <= 0) return C_StockInsuficiente;

        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia()) continue;
            Mascota mascota = jaula.getMascota();
            if (mascota.getID() == id && mascota.darMedicamento(Medicamentos.deEspecie(especie))) {
                stockMedicamentos[indice]--;
                return C_Exito;
            }
        }

        return C_Error;
    }

    public int consumirJuguete(int id, Especies especie) {
        int indice = getIndice(especie);

        if (stockJuguetes[indice] <= 0) return C_StockInsuficiente;

        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia()) continue;
            Mascota mascota = jaula.getMascota();
            if (mascota.getID() == id && mascota.jugar(Juguetes.deEspecie(especie))) {
                stockJuguetes[indice]--;
                return C_Exito;
            }
        }

        return C_Error;
    }

    public int[] getIndicadores(int id) {
        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia()) continue;
            Mascota mascota = jaula.getMascota();
            if (mascota.getID() == id) return mascota.getIndicadores();
        }

        return new int[]{};
    }
}
