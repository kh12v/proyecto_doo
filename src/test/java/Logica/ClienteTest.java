package Logica;

import Logica.Enums.Especies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(Especies.Perro, 0.5);
    }

    @Test
    void entregarMascotaExito() {
        assertTrue(cliente.entregarMascota(new Mascota("", Especies.Perro)));
    }

    @Test
    void entregarMascotaFallo() {
        assertFalse(cliente.entregarMascota(new Mascota("", Especies.Loro)));
    }
}
