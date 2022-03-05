import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Banco{
    private List<Conta> contas;

    public Banco(){
        this.contas = new ArrayList<Conta>();
    }

    public Boolean abrirConta(Conta nova_conta){
        Conta conta = procurarConta(nova_conta.getNumero());
        if(conta != null) // Verifica se o número da nova conta é o mesmo que alguma outra existente
            return false;
        this.contas.add(nova_conta); // Adiciona a nova conta na lista de contas
        return true;
    }

    public boolean sacar(String numero_conta, BigDecimal valor){
        Conta conta = procurarConta(numero_conta);
        if(conta != null && conta.sacar(valor)){
            this.atualizarConta(conta);
            return true;
        }
        return false;
    }

    // Atualiza a conta modificada dentro da lista de contas
    public void atualizarConta(Conta conta){
        for(Conta index : this.contas){
            if(conta.getNumero().equals(index.getNumero())){
                index = conta;
                break;
            }
        }
    }

    public boolean depositar(String numero_conta, BigDecimal valor){
        Conta conta = procurarConta(numero_conta);
        if(conta != null && conta instanceof ContaInvestimento == false){
            if(conta instanceof ContaCorrente)
                ((ContaCorrente) conta).depositar(valor);
            else
                ((ContaPoupanca) conta).depositar(valor);
            this.atualizarConta(conta);
            return true;
        }
        return false;
    }

    public boolean investir(String numero_conta, BigDecimal valor){
        Conta conta = procurarConta(numero_conta);
        if(conta != null && conta instanceof ContaInvestimento){
            ((ContaInvestimento) conta).investir(valor);
            return true;
        }
        return false;
    }

    public boolean transferir(String numero_conta_destinatario, String numero_conta_remetente, BigDecimal valor){
        Conta destinatario = procurarConta(numero_conta_destinatario), remetente = procurarConta(numero_conta_remetente);
        if(destinatario != null && remetente != null && remetente.transferencia(valor, false)) // Verifica se as contas existem e se o remetente possui saldo disponível
            return destinatario.transferencia(valor, true);
        return false;
    }

    public Conta procurarConta(String numero_conta){
        if(this.contas == null)
            return null;
        for(Conta conta : this.contas){
            if(numero_conta.compareTo(conta.getNumero()) == 0)
                return conta;
        }
        return null;
    }
}
