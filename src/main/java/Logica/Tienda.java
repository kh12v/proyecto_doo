package Logica;

import Logica.Enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Representa una tienda de mascotas, la cual puede emplear gente y comprar suministros para mantener a las mascotas con dinero
 * y vender mascotas para pagar la renta y los suministros para mantención.
 */
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
    private int stockJabones;
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
        stockJabones = 0;
        clientes = new ArrayList<>();
    }

    /**
     * Agrega un {@code Cliente} a la fila en la {@code Tienda}.
     * @param cliente un {@code Cliente}
     */
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Compra un {@code Producto}.
     * @param producto el tipo de {@code Producto}
     * @return un código sobre el éxito o fracaso de la operación,
     * o la ID de la {@code Jaula} de la {@code Mascota} si se compró una exitosamente
     */
    public int comprarProducto(Producto producto) {
        TipoProducto tipoProducto = producto.getTipoProducto();
        if (tipoProducto == TipoProducto.Mascota) return comprarMascota(producto);
        else if (tipoProducto == TipoProducto.Comida) return comprarAlimento(producto);
        else if (tipoProducto == TipoProducto.Juguete) return comprarJuguete(producto);
        else if (tipoProducto == TipoProducto.Medicamento) return comprarMedicamento(producto);
        else if (tipoProducto == TipoProducto.Higiene) return comprarJabon();

        return C_Error;
    }

    /**
     * Compra una {@code Mascota}, verifica si se tiene el dinero necesario y si hay una {@code Jaula} que admita a la nueva {@code Mascota}.
     * @param producto el tipo de {@code Producto} que representa una {@code Mascota}
     * @return la ID de la {@code Jaula} en caso de éxito, un código de error en caso contrario
     */
    private int comprarMascota(Producto producto) {
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        Especie especie = (Especie) producto.getEnumReal();

        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia() && jaula.admiteEspecie(especie)) {
                jaula.ingresarMascota(new Mascota("wuah", especie));
                dinero -= producto.getPrecio();
                return jaula.getID();
            }
        }

        return C_NoJaulaDisponible;
    }

    /**
     * Compra un tipo de {@code Alimento} y lo agrega al stock de la {@code Tienda}.
     * @param producto el código de {@code Producto} que representa un {@code Alimento}
     * @return un código de éxito o fracaso
     */
    private int comprarAlimento(Producto producto) {
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();

        Alimentos alimento = (Alimentos) producto.getEnumReal();
        stockAlimentos[alimento.ordinal()]++;

        return C_Exito;
    }

    /**
     * Compra un tipo de {@code Juguete} y lo agrega al stock de la {@code Tienda}.
     * @param producto el código de {@code Producto} que representa un {@code Juguete}
     * @return un código de éxito o fracaso
     */
    private int comprarJuguete(Producto producto) {
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();

        Juguetes juguete = (Juguetes) producto.getEnumReal();
        stockJuguetes[juguete.ordinal()]++;

        return C_Exito;
    }

    /**
     * Compra un tipo de {@code Medicamento} y lo agrega al stock de la {@code Tienda}.
     * @param producto el código de {@code Producto} que representa un {@code Medicamento}
     * @return un código de éxito o fracaso
     */
    private int comprarMedicamento(Producto producto) {
        if (producto.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= producto.getPrecio();

        Medicamentos medicamento = (Medicamentos) producto.getEnumReal();
        stockMedicamentos[medicamento.ordinal()]++;

        return C_Exito;
    }

    /**
     * Compra jabón para limpiar a las mascotas.
     * @return un código de éxito o fracaso
     */
    private int comprarJabon() {
        if (Producto.Jabon.getPrecio() > dinero) return C_DineroInsuficiente;

        dinero -= Producto.Jabon.getPrecio();

        stockJabones++;

        return C_Exito;
    }

    /**
     * Compra una {@code Jaula} y la agrega al stock de la {@code Tienda}.
     * @param jaula el tipo de {@code Jaula}
     * @return un código de éxito o fracaso
     */
    public int comprarJaula(TipoJaula jaula) {
        if (jaula.getPrecio() > dinero) {
            return C_DineroInsuficiente;
        }

        dinero -= jaula.getPrecio();
        Jaula jaulaComprada = switch (jaula) {
            case JaulaGrande -> new JaulaGrande();
            case JaulaPequena -> new JaulaPequena();
        };
        jaulas.add(jaulaComprada);
        System.out.println(jaulaComprada.getID());
        return jaulaComprada.getID();
    }

    /**
     * Contrata a un {@code Empleado}.
     * @param cargo el {@code Cargo} que tendra el empleado
     * @return la ID del empleado, o un codigo de fracaso
     */

    public int contratarEmpleado(Cargo cargo) {
        if (cargo.getSalario() > dinero) return C_DineroInsuficiente;

        Empleado empleado = new Empleado(cargo);
        empleados.add(empleado);
        return empleado.getID();
    }

    /**
     * Despide a un {@code Empleado}
     * @param id la ID del {@code Empleado}
     * @return un código de éxito o fracaso
     */
    public int despedirEmpleado(int id) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getID() == id) {
                empleados.remove(i);
                return 1;
            }
        }

        return -1;
    }

    /**
     * Paga el salario a un {@code Empleado}, si no se tiene el dinero necesario al momento de pagar,
     * este se va a huelga, por lo cual deja de trabajar hasta que se le pague.
     * @param e el {@code Empleado}
     */
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
        trabajar();
    }

    /**
     * Este metodo hace que cada {@code Empleado} activo haga su labor.<br>
     * Los que tienen el cargo de Cuidador eligen a la mascota con los peores indicadores, y verifican cada uno
     * individualmente para ver si vale la pena atenderlos.
     */
    private void trabajar() {
        if (empleados.isEmpty()) {
            return;
        }
        ArrayList<Jaula> mascotasOrdenadas = new ArrayList<>(jaulas.stream()
                .filter(Predicate.not(Jaula::estaVacia))
                .sorted(Comparator.comparingInt(k -> Arrays.stream(k.getIndicadores()).reduce(0, Integer::sum)))
                .toList());

        for (Empleado e : empleados) {
            if(!e.isTrabajando()){continue;}
            if (e.getCargo() == Cargo.Cuidador && !mascotasOrdenadas.isEmpty()) {
                Mascota m = mascotasOrdenadas.removeFirst().getMascota();

                Alimentos alimento = Alimentos.deEspecie(m.getEspecie());
                if (stockAlimentos[alimento.ordinal()] > 0 && m.getIndicadores()[Mascota.I_HAMBRE] < 100 - alimento.getValorNutritivo()) {
                    m.alimentar(alimento);
                    stockAlimentos[alimento.ordinal()]--;
                }

                if (stockJabones > 0 && m.getIndicadores()[Mascota.I_HIGIENE] < 50) {
                    m.limpiar();
                    stockJabones--;
                }

                Medicamentos medicamento = Medicamentos.getMedicamento(m.getEspecie());

                if (stockMedicamentos[medicamento.ordinal()] > 0 && m.getIndicadores()[Mascota.I_SALUD] < 100 - medicamento.valorMedicinal()) {
                    m.darMedicamento(medicamento);
                    stockMedicamentos[medicamento.ordinal()]--;
                }
            }
            if (e.getCargo() == Cargo.Recepcionista && !clientes.isEmpty() && !mascotasOrdenadas.isEmpty()) {
                Especie especieRequerida = clientes.getFirst().getEspeciePedida();
                List<Jaula> mascotasQueCumplen = mascotasOrdenadas.stream().filter(j -> j.getMascota().getEspecie() == especieRequerida).toList();
                if(mascotasQueCumplen.isEmpty()){
                    continue;
                }
                mascotasOrdenadas.remove(mascotasQueCumplen.getLast());
                Mascota m = mascotasQueCumplen.removeLast().getMascota();
                servirCliente(m);
            }
        }
    }

    /**
     * Le entrega al primer {@code Cliente} de la fila una {@code Mascota}.
     * @param id la ID de la {@code Jaula} donde se encuentra la {@code Mascota}
     * @return {@code true} si el {@code Cliente} aceptó la mascota, {@code false} si no
     */
    public boolean servirCliente(int id){
        return servirCliente(encontrarMascota(id));
    }

    /**
     * Le entrega al primer {@code Cliente} de la fila una {@code Mascota}.
     * @param m la {@code Mascota}
     * @return {@code true} si el {@code Cliente} aceptó la mascota, {@code false} si no
     */
    private boolean servirCliente(Mascota m) {
        if(clientes.isEmpty()){return false;}
        Cliente c = clientes.getFirst();
        if(c.entregarMascota(m)){
            clientes.removeFirst();
            removerMascota(m);
            calificaciones.add(c.getCalificacion());
            return true;
        }
        return false;
    }

    /**
     * Entrega cada {@code Jaula} que guarda la {@code Tienda}.
     * @return un {@code ArrayList} de {@code Jaula}
     */
    public ArrayList<Jaula> getJaulas() {
        return jaulas;
    }

    /**
     * Entrega la {@code Especie} de {@code Mascota} que pide el primer {@code Cliente} de la fila.
     * @return la {@code Especie}
     */
    public Especie getEspeciePedida(){
        if(clientes.isEmpty()){return Especie.Null;}
        return clientes.getFirst().getEspeciePedida();
    }

    /**
     * Entrega una lista de cada {@code Empleado} inactivo.
     * @return un arreglo de {@code Empleado}
     */
    public int[] getEmpleadosInactivos() {
        return empleados.stream().filter(Predicate.not(Empleado::isTrabajando)).mapToInt(Empleado::getID).toArray();
    }

    /**
     * Entrega una lista de cada {@code Empleado}.
     * @return un arreglo de {@code Empleado}
     */
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * Verifica si existe un {@code Empleado} con una ID especifica en la {@code Tienda}.
     * @param id la ID a buscar
     * @return {@code true} si el {@code Empleado} trabaja en la {@code Tienda}, {@code false} si no
     */
    public boolean encontrarIDEmpleados(int id) {
        return empleados.stream().anyMatch(i -> i.getID() == id);
    }

    /**
     * Verifica si existe una {@code Jaula} con una ID especifica en la {@code Tienda}.
     * @param id la ID a buscar
     * @return {@code true} si el {@code Jaula} está en la {@code Tienda}, {@code false} si no
     */
    public boolean encontrarIDJaulas(int id) {
        return jaulas.stream().anyMatch(i -> i.getID() == id);
    }

    /**
     * Verifica si existe un {@code Mascota} con una ID especifica en la {@code Tienda}, y la entrega.
     * @param id la ID a buscar
     * @return la {@code Mascota} pedida, o {@code null} si no
     */
    private Mascota encontrarMascota(int id) {
        return jaulas.stream().filter(j -> !j.estaVacia() && j.getID()==id).findFirst().map(Jaula::getMascota).orElse(null);
    }

    /**
     * Elimina una {@code Mascota} de la {@code Tienda}.
     * @param m la {@code Mascota}
     */
    private void removerMascota(Mascota m) {
        encontrarJaulaMascota(m).removerMascota();
    }

    /**
     * Encuentra la {@code Jaula} que guarda a una {@code Mascota} específica.
     * @param m la {@code Mascota} a buscar
     * @return la {@code Jaula}, o {@code null} si la {@code Mascota} no existe
     */
    private Jaula encontrarJaulaMascota(Mascota m) {
        return jaulas.stream().filter(j -> !j.estaVacia() && j.getMascota() == m).findFirst().orElse(null);
    }

    /**
     * Entrega el dinero que tiene la {@code Tienda}.
     * @return el dinero
     */
    public int getDinero() {
        return this.dinero;
    }

    /**
     * Entrega la calificación actual de la {@code Tienda}.
     * @return la calificación
     */
    public double getCalificacion() {
        return calificaciones.stream().reduce(0.0, Double::sum) / calificaciones.size();
    }

    /**
     * Regresa el stock de un {@code Alimento} de la {@code Tienda}.
     * @param indiceEspecie el indice del {@code Alimento}
     * @return el stock del {@code Alimento}
     */
    public int getStockAlimentos(int indiceEspecie) {
        return stockAlimentos[indiceEspecie];
    }

    /**
     * Regresa el stock de un {@code Medicamento} de la {@code Tienda}.
     * @param indiceEspecie el indice del {@code Medicamento}
     * @return el stock del {@code Medicamento}
     */
    public int getStockMedicamentos(int indiceEspecie) {
        return stockMedicamentos[indiceEspecie];
    }

    /**
     * Regresa el stock de un {@code Juguete} de la {@code Tienda}.
     * @param indiceEspecie el indice del {@code Juguete}
     * @return el stock del {@code Juguete}
     */
    public int getStockJuguetes(int indiceEspecie) {
        return stockJuguetes[indiceEspecie];
    }

    /**
     * Regresa el stock del jabón de la {@code Tienda}.
     * @return el stock de jabón
     */
    public int getStockJabones() {
        return stockJabones;
    }

    /**
     * Entrega el índice interno utilizado por la {@code Tienda}
     * en los arreglos referentes a objetos relacionados con una {@code Especie} de {@code Mascota}.
     * @param especie la {@code Especie}
     * @return él índice
     */
    public int getIndice(Especie especie) {
        return (especie == Especie.Perro) ? I_Perro
                : (especie == Especie.Gato) ? I_Gato
                : (especie == Especie.Loro) ? I_Loro
                : I_Hamster;
    }

    /**
     * Hace que una {@code Mascota} de la {@code Tienda} consuma un {@code Alimento} del stock.
     * @param id la ID de la {@code Mascota}
     * @param especie la {@code Especie} de la {@code Mascota} a alimentar
     * @return un código de éxito o fracaso
     */
    public int consumirAlimento(int id, Especie especie) {
        int indice = getIndice(especie);

        if (stockAlimentos[indice] <= 0) return C_StockInsuficiente;

        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia()) continue;
            if (jaula.getID() == id && jaula.getMascota().getEspecie() == especie && jaula.getMascota().alimentar(Alimentos.deEspecie(especie))) {
                stockAlimentos[indice]--;
                return C_Exito;
            }
        }

        return C_Error;
    }

    /**
     * Hace que una {@code Mascota} de la {@code Tienda} consuma un {@code Medicamento} del stock.
     * @param id la ID de la {@code Mascota}
     * @param especie la {@code Especie} de la {@code Mascota} a curar
     * @return un código de éxito o fracaso
     */
    public int consumirMedicamento(int id, Especie especie) {
        int indice = getIndice(especie);

        if (stockMedicamentos[indice] <= 0) return C_StockInsuficiente;

        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia()) continue;
            if (jaula.getID() == id && jaula.getMascota().getEspecie() == especie && jaula.getMascota().darMedicamento(Medicamentos.deEspecie(especie))) {
                stockMedicamentos[indice]--;
                return C_Exito;
            }
        }

        return C_Error;
    }

    /**
     * Hace que una {@code Mascota} de la {@code Tienda} consuma un {@code Juguete} del stock.
     * @param id la ID de la {@code Mascota}
     * @param especie la {@code Especie} de la {@code Mascota} que usara el {@code Juguete}
     * @return un código de éxito o fracaso
     */
    public int consumirJuguete(int id, Especie especie) {
        int indice = getIndice(especie);

        if (stockJuguetes[indice] <= 0) return C_StockInsuficiente;

        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia()) continue;
            if (jaula.getID() == id && jaula.getMascota().getEspecie() == especie && jaula.getMascota().jugar(Juguetes.deEspecie(especie))) {
                stockJuguetes[indice]--;
                return C_Exito;
            }
        }

        return C_Error;
    }

    /**
     * Hace que una {@code Mascota} de la {@code Tienda} consuma un jabón del stock.
     * @param id la ID de la {@code Mascota}
     * @return un código de éxito o fracaso
     */
    public int consumirHigiene(int id) {
        if (stockJabones <= 0) return C_StockInsuficiente;

        for (Jaula jaula : jaulas) {
            if (jaula.estaVacia()) continue;
            if (jaula.getID() == id) {
                jaula.getMascota().limpiar();
                stockJabones--;
                return C_Exito;
            }
        }

        return C_Error;
    }

    /**
     * Regresa los indicadores de una {@code Mascota} de la {@code Tienda}.
     * @param id la ID de la {@code Jaula} con la {@code Mascota}
     * @return un arreglo con los indicadores, o un arreglo de ceros si la mascota no existe
     */
    public int[] getIndicadores(int id) {
        return jaulas.stream().filter(j->j.getID()==id).findFirst().map(Jaula::getIndicadores).orElse(new int[]{0,0,0,0});
    }
}
