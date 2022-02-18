import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Conta {
    protected BigDecimal saldo;
    protected Pessoa titular;
    protected float taxa;
    protected String numero, agencia;

    public Conta(String numero, Pessoa titular, String agencia){
        this.numero = numero;
        this.titular = titular;
        this.saldo = BigDecimal.valueOf(0);
        this.agencia = agencia;
        if(this.titular instanceof PessoaJuridica)
            this.taxa = (float) 1.005;
        else
            this.taxa = 1;
    }

    public abstract BigDecimal sacar();

    public abstract BigDecimal transferencia();

    // Getters e setters
    public String get_numero(){
        return this.numero;
    }

    public String get_agencia(){
        return this.agencia;
    }

    public BigDecimal get_saldo(){
        return this.saldo.setScale(2, RoundingMode.CEILING);
    }

    public String get_titular(){
        return this.titular.toString();
    }

    public float get_taxa(){
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

    public void set_taxa(float taxa){
        this.taxa = taxa;
    }

    public String toString(){
        return "Conta: " + get_numero() + ". Agencia: " + get_agencia() + ". Titular: " + get_titular() + ". Taxa: " + get_taxa() + "%.\nSaldo: R$" + get_saldo() + ".\n";
    }
}
