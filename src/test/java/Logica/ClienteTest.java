package Logica;

import Logica.Enums.Especie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(Especie.Perro, 0.5);
    }

    @Test
    void entregarMascotaExito() {
        assertTrue(cliente.entregarMascota(new Mascota("", Especie.Perro)));
    }

    @Test
    void entregarMascotaFallo() {
        assertFalse(cliente.entregarMascota(new Mascota("", Especie.Loro)));
    }
}
