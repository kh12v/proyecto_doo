import Controladores.ControladorPrincipal;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Tipos.M_PedirMascotasEvento;
import Logica.*;
import gui.Paneles.VentanaPrincipal;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SimulacionTienda sim = new SimulacionTienda("oo");
        sim.iniciar();
    }
}