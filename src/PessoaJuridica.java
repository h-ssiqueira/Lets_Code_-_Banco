public class PessoaJuridica extends Pessoa{
    private String CNPJ;

    public PessoaJuridica(String nome, String CNPJ){
        super(nome);
        this.CNPJ = CNPJ;
    }

    // Getters
    public String getCNPJ(){
        return this.CNPJ;
    }

    // Setters
    public void setCNPJ(String CNPJ){
        this.CNPJ = CNPJ;
    }

    @Override
    public String toString(){
        return super.toString() + " CNPJ: " + this.getCNPJ() + ".\n";
    }
}
