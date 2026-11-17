package leilao;

// Observer (GoF): qualquer interessado em um lote implementa esta interface.
// O Lote só conhece este contrato, nunca as classes concretas dos interessados.
public interface Observador {
    String getNome();
    void atualizar(Lote lote, Lance lance);
}
