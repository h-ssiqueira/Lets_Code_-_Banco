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
    public String get_numero(){
        return this.numero;
    }

    public String get_agencia(){
        return this.agencia;
    }

    public BigDecimal get_saldo(){
        return this.saldo;
    }

    public String get_titular(){
        return this.titular.toString();
    }

    public BigDecimal get_taxa(){
        return this.taxa;
    }

    // Setters
    public void set_numero(String numero){
        this.numero = numero;
    }

    public void set_saldo(BigDecimal saldo){
        this.saldo = saldo;
    }

    public void set_agencia(String agencia){
        this.agencia = agencia;
    }

    public void set_titular(Pessoa titular){
        this.titular = titular;
    }

    public void set_taxa(BigDecimal taxa){
        this.taxa = taxa;
    }

    public String toString(){
        return "Conta: " + this.get_numero() + ". Agência: " + this.get_agencia() + ".\nTitular: " + this.get_titular() + "Taxa: " + (this.get_taxa().subtract(BigDecimal.valueOf(1))).multiply(BigDecimal.valueOf(100)) + "%.\nSaldo: R$" + this.get_saldo().setScale(2, RoundingMode.HALF_UP) + ".\n";
    }

}
