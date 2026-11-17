package leilao.categorias;

import leilao.Categoria;
import leilao.comissao.ComissaoVeiculos;
import leilao.comissao.RegraComissao;

public class Veiculos implements Categoria {
    @Override public String getNome() { return "Veículos"; }
    @Override public RegraComissao regraComissaoPadrao() { return new ComissaoVeiculos(); }
}
