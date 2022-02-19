import java.math.BigDecimal;

public class ContaPoupanca extends Conta{
    private BigDecimal taxa_rendimento;

    public ContaPoupanca(String numero, Pessoa titular, String agencia){
        super(numero, titular, agencia);
        this.taxa_rendimento = BigDecimal.valueOf(1.03); // 3% de rendimento
    }

    public boolean sacar(BigDecimal valor){
        if(this.get_saldo().compareTo(valor.multiply(this.get_taxa())) >= 0){ // Confere se há saldo disponível para saque com possíveis taxas
            this.set_saldo(this.get_saldo().subtract(valor.multiply(this.get_taxa()))); // Aplica a taxa ao atualizar o valor (definida no construtor)
            return true;
        }
        return false;
    }

    public boolean transferencia(BigDecimal valor, boolean destinatario){
        if(destinatario){ // Destinatário somente recebe o dinheiro
            this.set_saldo(this.get_saldo().add(valor));
            return true;
        }
        else if(this.get_saldo().compareTo(valor.multiply(this.get_taxa())) >= 0){ // Remetente desconta o dinheiro com possíveis taxas com conferência de disponibilidade de saldo
            this.set_saldo(this.get_saldo().subtract(valor.multiply(this.get_taxa()))); // Atualiza valor do saldo com possíveis taxas
            return true;
        }
        return false;
    }

    public void depositar(BigDecimal valor){
        this.set_saldo(valor.add(this.get_saldo()));
    }

    public void poupando(){
        this.set_saldo(this.get_saldo().multiply(this.get_taxa_rendimento()));
    }

    // Getters
    public BigDecimal get_taxa_rendimento(){
        return this.taxa_rendimento;
    }

    // Setters
    public void set_taxa_rendimento(BigDecimal taxa_rendimento){
        this.taxa_rendimento = taxa_rendimento;
    }

    @Override
    public String toString(){
        return super.toString() + "Taxa de rendimento: " + (this.get_taxa_rendimento().subtract(BigDecimal.valueOf(1))).multiply(BigDecimal.valueOf(100)) + "%.\n";
    }
}
