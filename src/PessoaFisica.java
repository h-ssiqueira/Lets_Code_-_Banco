public class PessoaFisica extends Pessoa{
    private String CPF;

    public PessoaFisica(String nome, String CPF){
        super(nome);
        this.CPF = CPF;
    }

    // Getters
    public String get_CPF(){
        return this.CPF;
    }

    // Setters
    public void set_CPF(String CPF){
        this.CPF = CPF;
    }

    @Override
    public String toString(){
        return super.toString() + " CPF: " + this.get_CPF() + ".\n";
    }
}
