import java.math.BigDecimal;

public class ContaPoupanca extends Conta{
    private float taxa_rendimento;

    public ContaPoupanca(String numero, Pessoa titular, String agencia){
        super(numero, titular, agencia);
        this.taxa_rendimento = (float) 1.03;
    }

    public BigDecimal sacar(){ // TODO
        return null;
    }

    public BigDecimal transferencia(){ // TODO
        return null;
    }

    public void depositar(){ // TODO

    }

    // Getters
    public float get_taxa_rendimento(){
        return this.taxa_rendimento;
    }

    // Setters
    public void set_taxa_rendimento(float taxa_rendimento){
        this.taxa_rendimento = taxa_rendimento;
    }

    @Override
    public String toString(){
        return super.toString() + " Taxa de rendimento: " + get_taxa_rendimento() + " %.\n";
    }
}
