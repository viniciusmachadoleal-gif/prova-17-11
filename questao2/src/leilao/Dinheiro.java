package leilao;

import java.util.Locale;

// Utilitário de formatação monetária (R$ 1.234,56).
public final class Dinheiro {
    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");
    private Dinheiro() {}
    public static String formatar(double valor) {
        return String.format(PT_BR, "R$ %,.2f", valor);
    }
}
