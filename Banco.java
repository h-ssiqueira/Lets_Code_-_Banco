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
