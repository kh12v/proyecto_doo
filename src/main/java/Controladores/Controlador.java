package Controladores;

import Controladores.Eventos.Publicador;
import Controladores.Eventos.Suscriptor;

/**
 * Interfaz que implementan todas las clases que hacen de puente entre el modelo y la vista del código,
 * comúnmente reciben eventos y envían eventos a cambio, por lo que nunca finalizan una cadena de eventos.
 */
public interface Controlador extends Publicador, Suscriptor {
}
