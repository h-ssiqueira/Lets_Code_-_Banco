import java.math.BigDecimal;

public class ContaCorrente extends Conta{
    public ContaCorrente(String numero, Pessoa titular, String agencia){
        super(numero, titular, agencia);
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
}
