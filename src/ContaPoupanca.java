import java.math.BigDecimal;
import java.util.MissingFormatArgumentException;

public class ContaPoupanca extends Conta{
    private BigDecimal taxa_rendimento;

    public ContaPoupanca(String numero, Pessoa titular, String agencia) throws MissingFormatArgumentException{
        super(numero, titular, agencia);
        if(titular instanceof PessoaJuridica)
            throw new MissingFormatArgumentException("Erro! PJ não pode possuir conta poupança.");
        this.taxa_rendimento = BigDecimal.valueOf(1.03); // 3% de rendimento
    }

    public boolean sacar(BigDecimal valor){
        if(this.getSaldo().compareTo(valor.multiply(this.getTaxa())) >= 0){ // Confere se há saldo disponível para saque com possíveis taxas
            this.setSaldo(this.getSaldo().subtract(valor.multiply(this.getTaxa()))); // Aplica a taxa ao atualizar o valor (definida no construtor)
            return true;
        }
        return false;
    }

    public boolean transferencia(BigDecimal valor, boolean destinatario){
        if(destinatario){ // Destinatário somente recebe o dinheiro
            this.setSaldo(this.getSaldo().add(valor));
            return true;
        }
        else if(this.getSaldo().compareTo(valor.multiply(this.getTaxa())) >= 0){ // Remetente desconta o dinheiro com possíveis taxas com conferência de disponibilidade de saldo
            this.setSaldo(this.getSaldo().subtract(valor.multiply(this.getTaxa()))); // Atualiza valor do saldo com possíveis taxas
            return true;
        }
        return false;
    }

    public void depositar(BigDecimal valor){
        this.setSaldo(valor.add(this.getSaldo()));
    }

    public void poupando(){
        this.setSaldo(this.getSaldo().multiply(this.getTaxaRendimento()));
    }

    // Getters
    public BigDecimal getTaxaRendimento(){
        return this.taxa_rendimento;
    }

    // Setters
    public void setTaxaRendimento(BigDecimal taxa_rendimento){
        this.taxa_rendimento = taxa_rendimento;
    }

    @Override
    public String toString(){
        return super.toString() + "Taxa de rendimento: " + (this.getTaxaRendimento().subtract(BigDecimal.valueOf(1))).multiply(BigDecimal.valueOf(100)) + "%.\n";
    }
}
