import java.math.BigDecimal;

public class ContaInvestimento extends Conta{
    private float taxa_rendimento;

    public ContaInvestimento(String numero, Pessoa titular, String agencia){
        super(numero, titular, agencia);
        if(titular instanceof PessoaJuridica)
            this.taxa_rendimento = (float) 1.07;
        else
            this.taxa_rendimento = (float) 1.05;
    }

    public BigDecimal sacar(){ // TODO
        return null;
    }

    public BigDecimal transferencia(){ // TODO
        return null;
    }

    public void investir(){ // TODO

    }

    public void investindo(){
        super.set_saldo(super.get_saldo().multiply(BigDecimal.valueOf(taxa_rendimento)));
    }

    // Getters
    public float get_taxa_rendimento(){
        return taxa_rendimento;
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
