import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Banco{
    private List<Conta> contas;

    public Banco(){
        this.contas = new LinkedList<Conta>();
    }

    public BigDecimal consulta_saldo(String numero_conta){
        Conta conta = procurar_conta(numero_conta);
        if(conta != null){
            return conta.get_saldo();
        }
        return null;
    }

    public Boolean abrir_conta(Conta nova_conta){
        Conta conta = procurar_conta(nova_conta.get_numero());
        if(conta != null) // Verifica se o número da nova conta é o mesmo que alguma outra á existente
            return false;
        this.contas.add(nova_conta); // Adiciona a nova conta na lista de contas
        return true;
    }

    public boolean sacar(String numero_conta, BigDecimal valor){
        Conta conta = procurar_conta(numero_conta);
        if(conta != null && conta.sacar(valor)){
            this.atualizar_conta(conta);
            return true;
        }
        return false;
    }

    public void atualizar_conta(Conta conta){
        for(Conta index : this.contas){
            if(conta.get_numero().equals(index.get_numero())){
                index = conta;
                break;
            }
        }
    }

    public boolean depositar(String numero_conta, BigDecimal valor){
        Conta conta = procurar_conta(numero_conta);
        if(conta != null && conta instanceof ContaInvestimento == false){
            conta.set_saldo(valor.add(conta.get_saldo()));
            this.atualizar_conta(conta);
            return true;
        }
        return false;
    }

    public boolean investir(String numero_conta, BigDecimal valor){ // TODO consertar investir e depositar, usando os métodos das subclasses
        Conta conta = procurar_conta(numero_conta);
        if(conta != null && conta instanceof ContaInvestimento){
            //conta.set_saldo(valor.add(conta.get_saldo()).multiply(BigDecimal.valueOf(conta.get))));
        }
        return false;
    }

    public boolean transferir(String numero_conta_destinatario, String numero_conta_remetente, BigDecimal valor){
        Conta destinatario = procurar_conta(numero_conta_destinatario), remetente = procurar_conta(numero_conta_remetente);
        if(destinatario != null && remetente != null && remetente.transferencia(valor, false)) // Verifica se as contas existem e se o remetente possui saldo disponível
            return destinatario.transferencia(valor, true);
        return false;
    }

    public Conta procurar_conta(String numero_conta){
        if(this.contas == null)
            return null;
        for(Conta conta : this.contas){
            if(numero_conta.compareTo(conta.get_numero()) == 0)
                return conta;
        }
        return null;
    }
}
