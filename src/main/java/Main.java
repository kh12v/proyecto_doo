import Controladores.ControladorPrincipal;
import Controladores.Eventos.EventHandler;
import Controladores.Eventos.Tipos.M_PedirMascotasEvento;
import Logica.Especies;
import Logica.FabricaMascotas;
import Logica.Mascota;
import Logica.Tienda;
import gui.Paneles.VentanaPrincipal;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        VentanaPrincipal ventanaTienda = new VentanaPrincipal("Tienda de mascotas");
        EventHandler handler = new EventHandler();
        Tienda t = new Tienda("Prueba");
        FabricaMascotas f = FabricaMascotas.getInstancia();
        t.agregarMascota(f.creaMascota("Pepe",Especies.Perro));
        t.agregarMascota(f.creaMascota("Vidal", Especies.Gato));
        t.agregarMascota(f.creaMascota("Geoffrey", Especies.Loro));
        t.agregarMascota(f.creaMascota("Pierluigi", Especies.Hamster));
        ControladorPrincipal p = new ControladorPrincipal(t);
        p.enviarHandler(handler);
        ventanaTienda.enviarHandler(handler);
        ventanaTienda.mostrar();
        Thread.sleep(5000);
        t.agregarMascota(f.creaMascota("Pierluigi", Especies.Hamster));
        t.agregarMascota(f.creaMascota("Pierluigi", Especies.Hamster));
        t.agregarMascota(f.creaMascota("Pierluigi", Especies.Hamster));
        handler.enviar(new M_PedirMascotasEvento(M_PedirMascotasEvento.WILD));
    }
}