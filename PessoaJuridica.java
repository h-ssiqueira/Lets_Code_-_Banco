public class PessoaJuridica extends Pessoa{
    private String CNPJ;

    public PessoaJuridica(String nome, String CNPJ){
        super(nome);
        this.CNPJ = CNPJ;
    }

    // Getters
    public String get_CNPJ(){
        return this.CNPJ;
    }

    // Setters
    public void set_CNPJ(String CNPJ){
        this.CNPJ = CNPJ;
    }

    @Override
    public String toString(){
        return super.toString() + " CNPJ:" + get_CNPJ() + ".\n";
    }
}
