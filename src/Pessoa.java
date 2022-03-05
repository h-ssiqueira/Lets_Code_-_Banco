public abstract class Pessoa {
    protected String nome;

    public Pessoa(String nome){
        this.nome = nome;
    }

    // Getters
    public String getNome(){
        return this.nome;
    }

    // Setters
    public void setNome(String nome){
        this.nome = nome;
    }

    public String toString(){
        return this.getNome() + ".";
    }
}
