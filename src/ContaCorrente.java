import java.math.BigDecimal;

public class ContaCorrente extends Conta{
    public ContaCorrente(String numero, Pessoa titular, String agencia){
        super(numero, titular, agencia);
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

    public void depositar(BigDecimal valor){

    }
}
