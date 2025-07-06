package Logica;

import Logica.Enums.Especies;
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
        Mascota mascota = new Mascota("", Especies.Perro);
        jaulaGrande.ingresarMascota(mascota);
        assert jaulaGrande.getMascota().getID() == mascota.getID();
    }

    @Test
    void agregarMascotaFallo() {
        jaulaPequena.ingresarMascota(new Mascota("", Especies.Gato));
        assertTrue(jaulaPequena.estaVacia());
    }

    @Test
    void quitarMascota() {
        jaulaGrande.ingresarMascota(new Mascota("", Especies.Perro));
        if (jaulaGrande.estaVacia()) fail();
        jaulaGrande.removerMascota();
        assertTrue(jaulaGrande.estaVacia());
    }
}