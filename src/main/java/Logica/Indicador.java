package Logica;

public class Indicador implements Actualizable {
    int valor;
    int frecuenciaReduccion;

    public Indicador() {
        this.valor = 0;
        this.frecuenciaReduccion = 0;
    }
    public int incrementar(int incremento) {
        valor = Integer.min(valor + incremento, 100);
        return valor;
    }
    public void maximizar(){
        valor = 100;
    }

    public void minimizar(){}

    @Override
    public void actualizar() {
        valor -= frecuenciaReduccion;
    }

    public int getValor() {
        return valor;
    }
    public int getFrecuenciaReduccion() {
        return frecuenciaReduccion;
    }
}
