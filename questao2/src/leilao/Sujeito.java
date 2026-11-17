package leilao;

// Subject (GoF): quem pode ser acompanhado por observadores.
public interface Sujeito {
    void acompanhar(Observador observador);
    void deixarDeAcompanhar(Observador observador);
    void notificar(Lance lance);
}
