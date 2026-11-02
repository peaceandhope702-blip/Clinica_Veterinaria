package modelo;

import java.math.BigDecimal;

public class Pagamento {
    private int id;
    private Consulta consulta;
    private BigDecimal valor;
    private boolean efetuado;

    public Pagamento(int id, Consulta consulta, BigDecimal valor, boolean efetuado) {
        this.id = id;
        this.consulta = consulta;
        this.valor = valor;
        this.efetuado = efetuado;
    }

    public int getId() { return id; }
    public Consulta getConsulta() { return consulta; }
    public BigDecimal getValor() { return valor; }
    public boolean isEfetuado() { return efetuado; }
}
