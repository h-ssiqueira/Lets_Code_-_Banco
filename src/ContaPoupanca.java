import java.math.BigDecimal;

public class ContaPoupanca extends Conta{
    private float taxa_rendimento;

    public ContaPoupanca(String numero, Pessoa titular, String agencia){
        super(numero, titular, agencia);
        this.taxa_rendimento = (float) 1.03;
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
