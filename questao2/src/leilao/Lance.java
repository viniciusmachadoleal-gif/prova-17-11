package leilao;

public final class Lance {
    private final Observador autor;
    private final double valor;

    public Lance(Observador autor, double valor) {
        this.autor = autor;
        this.valor = valor;
    }
    public Observador getAutor() { return autor; }
    public double getValor() { return valor; }
}
