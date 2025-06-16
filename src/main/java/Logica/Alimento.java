package Logica;

public class Alimento {
    Alimentos tipoAlimento;

    public boolean puedeComer(Especies e){
        return tipoAlimento.especies.contains(e);
    }

    public int getPuntosAlimento(){
        return tipoAlimento.valor;
    }
}
