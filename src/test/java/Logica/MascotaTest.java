package Logica;

import Logica.Enums.Alimentos;
import Logica.Enums.Especie;
import Logica.Enums.Juguetes;
import Logica.Enums.Medicamentos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MascotaTest {
    Mascota perro;
    Mascota gato;
    Mascota loro;
    Mascota hamster;

    @BeforeEach
    void setUp() {
        perro = new Mascota("Doki", Especie.Perro);
        gato = new Mascota("Gato con botas", Especie.Gato);
        loro = new Mascota("Pajaro loco", Especie.Loro);
        hamster = new Mascota("Hamtaro", Especie.Hamster);
    }

    @Test
    void alimentarExito() {
        perro.setIndicador(Mascota.I_HAMBRE, 10);
        perro.alimentar(Alimentos.ComidaPerro);
        assert perro.getIndicador(Mascota.I_HAMBRE) == 10 + Alimentos.ComidaPerro.getValorNutritivo();
    }

    @Test
    void alimentarFallo() {
        gato.setIndicador(Mascota.I_HAMBRE, 10);
        gato.alimentar(Alimentos.ComidaHamster);
        assert gato.getIndicador(Mascota.I_HAMBRE) == 10;
    }

    @Test
    void jugarExito() {
        loro.setIndicador(Mascota.I_FELICIDAD, 10);
        loro.jugar(Juguetes.JugueteLoro);
        assert loro.getIndicador(Mascota.I_FELICIDAD) == 10 + Juguetes.JugueteLoro.getValorJuguete();
    }

    @Test
    void jugarFallo() {
        hamster.setIndicador(Mascota.I_FELICIDAD, 10);
        hamster.jugar(Juguetes.JugueteGato);
        assert hamster.getIndicador(Mascota.I_FELICIDAD) == 10;
    }

    @Test
    void medicarExito() {
        perro.setIndicador(Mascota.I_SALUD, 10);
        perro.darMedicamento(Medicamentos.MedicinaPerro);
        assert perro.getIndicador(Mascota.I_SALUD) == 10 + Medicamentos.MedicinaPerro.valorMedicinal();
    }

    @Test
    void medicarFallo() {
        gato.setIndicador(Mascota.I_SALUD, 10);
        gato.darMedicamento(Medicamentos.MedicinaLoro);
        assert gato.getIndicador(Mascota.I_SALUD) == 10;
    }

    @Test
    void limpiarExito() {
        loro.setIndicador(Mascota.I_HIGIENE, 10);
        loro.limpiar();
        assert loro.getIndicador(Mascota.I_HIGIENE) == 100;
    }
}