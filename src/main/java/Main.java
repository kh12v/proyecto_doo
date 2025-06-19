import Controladores.Eventos.EventHandler;
import Logica.Tienda;
import gui.Paneles.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        VentanaPrincipal ventanaTienda = new VentanaPrincipal("Tienda de mascotas");
        EventHandler handler = new EventHandler();
        ventanaTienda.enviarHandler(handler);
        Tienda t = new Tienda("Prueba");

        ventanaTienda.mostrar();
    }
}