package Logica.Enums;

public enum Cargo {
    DESPEDIDO(0, "Null"), Cuidador(5, "Cuidador"), Recepcionista(5, "Recepcionista"), Gerente(7, "Gerente");

    private final int salario;
    private final String texto;

    Cargo(int salario, String texto) {
        this.salario = salario;
        this.texto = texto;
    }

    public int getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return texto;
    }
}
