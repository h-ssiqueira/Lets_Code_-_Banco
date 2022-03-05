import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Conta {
    protected BigDecimal saldo, taxa;
    protected Pessoa titular;
    protected String numero, agencia;

    public Conta(String numero, Pessoa titular, String agencia){
        this.numero = numero;
        this.titular = titular;
        this.saldo = BigDecimal.valueOf(0);
        this.agencia = agencia;
        if(this.titular instanceof PessoaJuridica)
            this.taxa = BigDecimal.valueOf(1.005); // 0,5%
        else
            this.taxa = BigDecimal.valueOf(1);
    }

    public abstract boolean sacar(BigDecimal valor);

    public abstract boolean transferencia(BigDecimal valor, boolean destinatario);

    // Getters e setters
    public String getNumero(){
        return this.numero;
    }

    public String getAgencia(){
        return this.agencia;
    }

    public BigDecimal getSaldo(){
        return this.saldo;
    }

    public String getTitular(){
        return this.titular.toString();
    }

    public BigDecimal getTaxa(){
        return this.taxa;
    }

    // Setters
    public void setNumero(String numero){
        this.numero = numero;
    }

    protected void setSaldo(BigDecimal saldo){
        this.saldo = saldo;
    }

    public void setAgencia(String agencia){
        this.agencia = agencia;
    }

    public void setTitular(Pessoa titular){
        this.titular = titular;
    }

    public void setTaxa(BigDecimal taxa){
        this.taxa = taxa;
    }

    public String toString(){
        return "Conta: " + this.getNumero() + ". Agência: " + this.getAgencia() + ".\nTitular: " + this.getTitular() + "Taxa: " + (this.getTaxa().subtract(BigDecimal.valueOf(1))).multiply(BigDecimal.valueOf(100)) + "%.\nSaldo: R$" + this.getSaldo().setScale(2, RoundingMode.HALF_UP) + ".\n";
    }

}
