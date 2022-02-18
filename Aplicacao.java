import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Aplicacao{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Banco banco = new Banco();
        BigDecimal valor;
        int opcao_menu = 1, tipo_conta = 0;
        boolean excecao = false;
        String nome = "", CPF_CNPJ = "", numero_conta = "", agencia = "";
        Pessoa titular;
        do{ // Loop do programa
            do{ // Loop do menu
                try{
                    excecao = false;
                    System.out.print("\n0 - Sair.\n1 - Abrir nova conta.\n2 - Sacar.\n3 - Depositar.\n4 - Transferência.\n5 - Investir.\n6 - Consultar saldo.\n-> ");
                    opcao_menu = input.nextInt();
                    if(opcao_menu < 0 || opcao_menu > 6){
                        System.out.println("\nInsira um número inteiro dentro do intervalo do menu para prosseguir.");
                        input.nextLine();
                    }
                }
                catch(InputMismatchException e){ // Caso não seja um número inteiro inserido
                    System.out.println("\nInsira um número inteiro dentro do intervalo do menu para prosseguir.");
                    input.nextLine();
                    excecao = true;
                }
            }while(excecao || opcao_menu < 0 || opcao_menu > 6);
            input.nextLine(); // Limpa o buffer (remove enter)
            switch(opcao_menu){ // Ação a se tomar de acordo com a escolha do menu
                case 1: // Abre nova conta
                    // Coleta inicialmnte as informações da pessoa
                    System.out.print("Digite o nome do titular da nova conta: ");
                    nome = input.nextLine();
                    do{
                        System.out.print("Insira o CPF/CNPJ do titular: ");
                        CPF_CNPJ = input.nextLine();
                    }while(!CPF_CNPJ.matches("[0-9]+") || CPF_CNPJ.length() != 11 && CPF_CNPJ.length() != 14); // Confere se há somente 11 ou 14 números
                    if(CPF_CNPJ.length() == 11)
                        titular = new PessoaFisica(nome, CPF_CNPJ.substring(0,3) + '.' + CPF_CNPJ.substring(3,6) + '.' + CPF_CNPJ.substring(6,9) + '-' + CPF_CNPJ.substring(9)); // Envia o CPF formatado (00000000000 -> 000.000.000-00)
                    else
                        titular = new PessoaJuridica(nome, CPF_CNPJ.substring(0,2) + "." + CPF_CNPJ.substring(2,5) + "." + CPF_CNPJ.substring(5,8) + "/" + CPF_CNPJ.substring(8,12) + "-" + CPF_CNPJ.substring(12)); // Envia o CNPJ formatado (00000000000000 -> 00.000.000/0000-00)
                    // Depois coleta-se as informações da conta a ser criada
                    do{
                        try{
                            excecao = false;
                            System.out.println("Insira o tipo de conta a ser criada.\n\t1 - Conta corrente.\n\t2 - Conta investimento.");
                            if(titular instanceof PessoaFisica)
                                System.out.println("\t3 - Conta poupança.");
                            System.out.print("-> ");
                            tipo_conta = input.nextInt();
                        }
                        catch(InputMismatchException e){
                            System.out.println("\nInsira um número inteiro dentro do intervalo para prosseguir.");
                            input.nextLine();
                        }
                    }while(excecao || tipo_conta < 1 || tipo_conta > 3 || tipo_conta == 3 && titular instanceof PessoaJuridica);
                    input.nextLine(); // Limpa o buffer (remove enter)
                    do{
                        System.out.print("Insira o número da nova conta.\n-> ");
                        numero_conta = input.nextLine();
                    }while(!numero_conta.matches("[0-9]+")); // Continua até que a string tenha apenas números
                    do{
                        System.out.print("Insira a agência da nova conta.\n-> ");
                        agencia = input.nextLine();
                    }while(!agencia.matches("[0-9]+")); // Continua até que a string tenha apenas números
                    // Para depois criar a conta no banco
                    if(banco.abrir_conta(tipo_conta == 1 ? new ContaCorrente(numero_conta, titular, agencia) : tipo_conta == 2 ? new ContaInvestimento(numero_conta, titular, agencia) : new ContaPoupanca(numero_conta, titular, agencia)))
                        System.out.println("Conta criada com sucesso.");
                    else
                        System.out.println("Falha ao criar nova conta: número de conta já existente no sistema.");
                    break;
                case 2: // Sacar TODO
                    break;
                case 3: // Depositar (conta poupança/corrente) TODO
                    break;
                case 4: // Transferência TODO
                    break;
                case 5: // Investir (conta investimento) TODO
                    break;
                case 6: // Consultar saldo
                    // Coleta o número da conta
                    do{
                        System.out.print("\nInsira o número da conta a ser consultada.\n-> ");
                        numero_conta = input.nextLine();
                    }while(!numero_conta.matches("[0-9]+")); // Verifica se há somente números na string
                    // Para procurar na lista de contas se a mesma existe
                    valor = banco.consulta_saldo(numero_conta);
                    if(valor == null) // Caso a conta não seja encontrada na lista de contas, o valor é nulo
                        System.out.println("Conta inexistente no sistema.");
                    else
                        System.out.println("Saldo da conta " + numero_conta + ": R$ " + valor + ".");
                    break;
            }
        }while(opcao_menu != 0);
    }

}