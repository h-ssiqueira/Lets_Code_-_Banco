public class PessoaFisica extends Pessoa{
    private String CPF;

    public PessoaFisica(String nome, String CPF){
        super(nome);
        this.CPF = CPF;
    }

    // Getters
    public String getCPF(){
        return this.CPF;
    }

    // Setters
    public void setCPF(String CPF){
        this.CPF = CPF;
    }

    @Override
    public String toString(){
        return super.toString() + " CPF: " + this.getCPF() + ".\n";
    }
}
