import java.math.BigDecimal;

public class ContaCorrente extends Conta{
    public ContaCorrente(String numero, Pessoa titular, String agencia){
        super(numero, titular, agencia);
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
}
