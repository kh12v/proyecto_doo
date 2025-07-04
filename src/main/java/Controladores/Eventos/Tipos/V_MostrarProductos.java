package Controladores.Eventos.Tipos;

import Controladores.Eventos.DestinoEvento;
import Controladores.Eventos.Evento;
import Controladores.Eventos.TipoEvento;

public class V_MostrarProductos extends Evento {
    public int[] productos;

    public V_MostrarProductos(int alimentos, int medicamentos, int juguetes) {
        super(TipoEvento.MostrarProductos, DestinoEvento.Vista);

        productos = new int[]{alimentos, medicamentos, juguetes};
    }
}
