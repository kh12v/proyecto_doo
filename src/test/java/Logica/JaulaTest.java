package Logica;

import Logica.Enums.Especie;
import Logica.Enums.TipoJaula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    void constructorInicializaCorrectamente() {
        assertTrue(jaulaGrande.estaVacia());
        assertNull(jaulaGrande.getMascota());
        assertEquals(TipoJaula.JaulaGrande, jaulaGrande.getTipoJaula());
        assertTrue(jaulaGrande.getID() >= 294187);
    }

    @Test
    void admiteEspeciePermitida() {
        assertTrue(jaulaGrande.admiteEspecie(Especie.Perro));
        assertTrue(jaulaPequena.admiteEspecie(Especie.Loro));
    }

    @Test
    void noAdmiteEspecieNoPermitida() {
        assertFalse(jaulaGrande.admiteEspecie(Especie.Loro));
        assertFalse(jaulaPequena.admiteEspecie(Especie.Perro));
    }

    @Test
    void ingresarMascotaPermitidaEnJaulaVacia() {
        Mascota mascota = new Mascota("", Especie.Perro);

        jaulaGrande.ingresarMascota(mascota);

        assertFalse(jaulaGrande.estaVacia());
        assertEquals(mascota, jaulaGrande.getMascota());
    }

    @Test
    void ingresarMascotaNoPermitida() {
        Mascota mascota = new Mascota("", Especie.Loro);

        jaulaGrande.ingresarMascota(mascota);

        assertTrue(jaulaGrande.estaVacia());
        assertNull(jaulaGrande.getMascota());
    }

    @Test
    void ingresarMascotaEnJaulaOcupada() {
        Mascota mascota1 = new Mascota("", Especie.Perro);
        Mascota mascota2 = new Mascota("", Especie.Gato);

        jaulaGrande.ingresarMascota(mascota1);

        jaulaGrande.ingresarMascota(mascota2);

        assertEquals(mascota1, jaulaGrande.getMascota());
        assertNotEquals(mascota2, jaulaGrande.getMascota());
    }

    @Test
    void removerMascotaCorrectamente() {
        Mascota mascota = new Mascota("", Especie.Hamster);

        jaulaPequena.ingresarMascota(mascota);
        assertFalse(jaulaPequena.estaVacia());

        jaulaPequena.removerMascota();

        assertTrue(jaulaPequena.estaVacia());
        assertNull(jaulaPequena.getMascota());
    }

    @Test
    void getIndicadoresJaulaVacia() {
        int[] indicadoresEsperados = {0, 0, 0, 0};

        int[] indicadoresActuales = jaulaGrande.getIndicadores();

        assertArrayEquals(indicadoresEsperados, indicadoresActuales);
    }

    @Test
    void getIndicadoresConMascota() {
        Mascota mascota = new Mascota("", Especie.Loro);
        mascota.setIndicador(Mascota.I_HAMBRE, 80);
        mascota.setIndicador(Mascota.I_SALUD, 90);

        jaulaPequena.ingresarMascota(mascota);
        int[] indicadoresEsperados = mascota.getIndicadores();

        int[] indicadoresActuales = jaulaPequena.getIndicadores();

        assertArrayEquals(indicadoresEsperados, indicadoresActuales);
    }

    @Test
    void actualizarJaulaVacia() {
        assertDoesNotThrow(() -> {
            jaulaPequena.actualizar();
        });
    }

    @Test
    void actualizarLlamaActualizarDeMascota() {
        Mascota mascota = new Mascota("", Especie.Perro);

        jaulaGrande.ingresarMascota(mascota);
        int[] indicadoresAntes = jaulaGrande.getIndicadores();

        jaulaGrande.actualizar();

        int[] indicadoresDespues = jaulaGrande.getIndicadores();
        assertFalse(Arrays.equals(indicadoresAntes, indicadoresDespues));
    }
}