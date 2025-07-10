package Logica;

import Logica.Enums.Especie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JaulaTest {
    Jaula jaulaGrande;
    Jaula jaulaPequena;

    @BeforeEach
    void setUp() {
        jaulaGrande = new JaulaGrande();
        jaulaPequena = new JaulaPequena();
    }

    @Test
    void agregarMascotaExito() {
        Mascota mascota = new Mascota("", Especie.Perro);
        jaulaGrande.ingresarMascota(mascota);
        assert jaulaGrande.getMascota() == mascota;
    }

    @Test
    void agregarMascotaFallo() {
        jaulaPequena.ingresarMascota(new Mascota("", Especie.Gato));
        assertTrue(jaulaPequena.estaVacia());
    }

    @Test
    void quitarMascota() {
        jaulaGrande.ingresarMascota(new Mascota("", Especie.Perro));
        if (jaulaGrande.estaVacia()) fail();
        jaulaGrande.removerMascota();
        assertTrue(jaulaGrande.estaVacia());
    }
}