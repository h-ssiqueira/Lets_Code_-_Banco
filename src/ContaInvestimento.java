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

    public boolean sacar(BigDecimal valor){
        if(this.get_saldo().compareTo(valor) >= 0){ // Confere se há saldo disponível para saque
            set_saldo(this.get_saldo().subtract(valor.multiply(BigDecimal.valueOf(this.get_taxa())))); // Aplica a taxa ao atualizar o valor (definida no construtor)
            return true;
        }
        return false;
    }

    public boolean transferencia(BigDecimal valor, boolean destinatario){
        if(destinatario){ // Destinatário somente recebe o dinheiro
            set_saldo(saldo.add(valor));
            return true;
        }
        else if(get_saldo().compareTo(valor.multiply(BigDecimal.valueOf(get_taxa()))) >= 0){ // Remetente desconta o dinheiro com possíveis taxas com conferência de disponibilidade de saldo
            set_saldo(saldo.subtract(valor.multiply(BigDecimal.valueOf(get_taxa())))); // Atualiza valor do saldo com possíveis taxas
            return true;
        }
        return false;
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
