import Controladores.Eventos.EventHandler;
import gui.Paneles.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        VentanaPrincipal ventanaTienda = new VentanaPrincipal("Tienda de mascotas");
        EventHandler handler = new EventHandler();
        ventanaTienda.enviarHandler(handler);
        ventanaTienda.mostrar();
    }
}