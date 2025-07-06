package Logica;

import Logica.Enums.Cargo;
import Logica.Enums.Producto;
import Logica.Enums.TipoContenedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TiendaTest {
    Tienda tienda0;
    Tienda tienda10000;

    @BeforeEach
    void setUp() {
        tienda0 = new Tienda("", 0);
        tienda10000 = new Tienda("", 20000);
    }

    @Test
    void comprarProdutoAnimalConJaula() {
        tienda10000.comprarJaula(TipoContenedor.JaulaGrande);
        assert(tienda10000.comprarProducto(Producto.Perro) == Tienda.C_Exito
                && tienda10000.getJaulas().size() == 1);
    }

    @Test
    void comprarProdutoAnimalSinJaula() {
        assert(tienda10000.comprarProducto(Producto.Perro) == Tienda.C_NoJaulaDisponible
                && tienda10000.getJaulas().isEmpty());
    }

    @Test
    void comprarProdutoAnimalConJaulaEquivocada() {
        tienda10000.comprarJaula(TipoContenedor.JaulaPequena);
        assert(tienda10000.comprarProducto(Producto.Perro) == Tienda.C_NoJaulaDisponible);
    }

    @Test
    void comprarProdutoAlimento() {
        assert(tienda10000.comprarProducto(Producto.ComidaGato) == Tienda.C_Exito
                && tienda10000.getStockAlimentos(Tienda.I_Gato) == 1);
    }

    @Test
    void comprarProdutoMedicamento() {
        assert(tienda10000.comprarProducto(Producto.MedicamentoLoro) == Tienda.C_Exito
                && tienda10000.getStockMedicamentos(Tienda.I_Loro) == 1);
    }

    @Test
    void comprarProdutoJuguete() {
        assert(tienda10000.comprarProducto(Producto.JugueteHamster) == Tienda.C_Exito
                && tienda10000.getStockJuguetes(Tienda.I_Hamster) == 1);
    }

    @Test
    void comprarProductoHigiene() {
        assert(tienda10000.comprarProducto(Producto.Jabon) == Tienda.C_Exito
                && tienda10000.getStockJabones() == 1);
    }

    @Test
    void comprarProdutoSinDinero() {
        assert(tienda0.comprarProducto(Producto.JugueteLoro) == Tienda.C_DineroInsuficiente
                && tienda0.getStockJuguetes(Tienda.I_Loro) == 0);
    }

    @Test
    void comprarJaula() {
        tienda10000.comprarJaula(TipoContenedor.JaulaGrande);
        assert(tienda10000.getJaulas().size() == 1);
    }

    @Test
    void contratarEmpleado() {
        tienda10000.contratarEmpleado(Cargo.Gerente);
        assert tienda10000.getEmpleados().getFirst().getCargo() == Cargo.Gerente;
    }

    @Test
    void despedirEmpleado() {
        tienda10000.contratarEmpleado(Cargo.Cuidador);
        tienda10000.despedirEmpleado(tienda10000.getEmpleados().getFirst().getID());
        assert tienda10000.getEmpleados().isEmpty();
    }

    @Test
    void pagarSalario() {
        int dineroInicial = tienda10000.getDinero();

        tienda10000.contratarEmpleado(Cargo.Cuidador);
        tienda10000.contratarEmpleado(Cargo.Gerente);
        tienda10000.pagarSalario(tienda10000.getEmpleados().getFirst());
        tienda10000.pagarSalario(tienda10000.getEmpleados().get(1));

        int salario1 = tienda10000.getEmpleados().getFirst().getSalario();
        int salario2 = tienda10000.getEmpleados().get(1).getSalario();

        assert tienda10000.getDinero() == dineroInicial - salario1 - salario2;
    }

    @Test
    void empleadosInactivos() {
        Tienda tienda = new Tienda("", Cargo.Recepcionista.getSalario());
        tienda.contratarEmpleado(Cargo.Recepcionista);
        tienda.pagarSalario(tienda.getEmpleados().getFirst());
        tienda.pagarSalario(tienda.getEmpleados().getFirst());
        assert tienda.getEmpleadosInactivos().length == 1;
    }
}