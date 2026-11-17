package leilao.categorias;

import leilao.Categoria;
import leilao.comissao.ComissaoArte;
import leilao.comissao.RegraComissao;

public class Arte implements Categoria {
    @Override public String getNome() { return "Arte"; }
    @Override public RegraComissao regraComissaoPadrao() { return new ComissaoArte(); }
}
