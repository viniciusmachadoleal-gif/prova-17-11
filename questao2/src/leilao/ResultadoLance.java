package leilao;

public record ResultadoLance(boolean aceito, String mensagem) {
    public static ResultadoLance aceito(String mensagem) { return new ResultadoLance(true, mensagem); }
    public static ResultadoLance recusado(String mensagem) { return new ResultadoLance(false, mensagem); }
}
