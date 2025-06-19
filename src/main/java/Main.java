import Controladores.ControladorPrincipal;
import Controladores.Eventos.EventHandler;
import Logica.Especies;
import Logica.Mascota;
import Logica.Tienda;
import gui.Paneles.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        VentanaPrincipal ventanaTienda = new VentanaPrincipal("Tienda de mascotas");
        EventHandler handler = new EventHandler();
        Tienda t = new Tienda("Prueba");
        t.agregarMascota(new Mascota("Pepe", Especies.Perro));
        t.agregarMascota(new Mascota("Vidal", Especies.Gato));
        t.agregarMascota(new Mascota("Geoffrey", Especies.Loro));
        t.agregarMascota(new Mascota("Pierluigi", Especies.Hamster));
        t.agregarMascota(new Mascota("Pierluigi", Especies.Hamster));
        t.agregarMascota(new Mascota("Pierluigi", Especies.Hamster));
        t.agregarMascota(new Mascota("Pierluigi", Especies.Hamster));
        ControladorPrincipal p = new ControladorPrincipal(t);
        p.enviarHandler(handler);
        ventanaTienda.enviarHandler(handler);
        ventanaTienda.mostrar();
    }
}