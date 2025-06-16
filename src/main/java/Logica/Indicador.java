package Logica;

public class Indicador {
    int valor;
    int frecuenciaReduccion;

    public int incrementar(int incremento) {
        valor = Integer.min(valor + incremento, 100);
        return valor;
    }
    public void maximizar(){
        valor = 100;
    }

    public void minimizar(){}
}
