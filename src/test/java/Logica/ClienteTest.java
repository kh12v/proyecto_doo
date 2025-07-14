package Logica;

import Logica.Enums.Especie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        assertTrue(cliente.entregarMascota(new Mascota("", Especie.Perro)), "La mascota debería haberse entregado con éxito");
    }

    @Test
    void entregarMascotaFallo() {
        assertFalse(cliente.entregarMascota(new Mascota("", Especie.Loro)), "No se puede entregar una especie que no fue la solicitada");
    }

    @Test
    void entregarMascotaNull() { assertFalse(cliente.entregarMascota(new Mascota("", Especie.Null)), "No se puede entregar una mascota nula"); }

    @Test
    void testClienteAleatorio() {
        Cliente cliente = Cliente.clienteAleatorio();

        assertNotNull(cliente, "El cliente generado no puede ser nulo");
        assertNotNull(cliente.getEspeciePedida(), "La especie de la mascota no puede ser nula");

        assertTrue(cliente.getCalificacion() >= 0.5 && cliente.getCalificacion() <= 5.0,
                "La calificación inicial debe estar en el rango [0.5, 5.0]");
    }

    @Test
    void sesgoValido() {
        // Arrange
        Especie especie = Especie.Gato;
        double sesgo = 0.8;
        double calificacionEsperada = 3.0 * 0.8; // 2.4

        // Act
        Cliente cliente = new Cliente(especie, sesgo);

        // Assert
        assertEquals(calificacionEsperada, cliente.getCalificacion());
        assertEquals(especie, cliente.getEspeciePedida());
    }

    @Test
    void sesgoAlto() {
        // Arrange
        Especie especie = Especie.Hamster;
        double sesgo = 1.2;
        double calificacionEsperada = 3.0;

        // Act
        Cliente cliente = new Cliente(especie, sesgo);

        // Assert
        assertEquals(calificacionEsperada, cliente.getCalificacion());
    }

    @Test
    void calificacionMaxima() {
        Cliente cliente = new Cliente(Especie.Perro, 0.9);
        Mascota mascotaEntregada = new Mascota("", Especie.Perro);
        double calificacionEsperada = 5.0;

        cliente.entregarMascota(mascotaEntregada);

        assertEquals(calificacionEsperada, cliente.getCalificacion());
    }

    @Test
    void getEspeciePedida() {
        Especie especieEsperada = Especie.Hamster;
        Cliente cliente = new Cliente(especieEsperada, 0.5);

        Especie especieActual = cliente.getEspeciePedida();

        assertEquals(especieEsperada, especieActual);
    }
}
