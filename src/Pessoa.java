public abstract class Pessoa {
    protected String nome;

    public Pessoa(String nome){
        this.nome = nome;
    }

    // Getters
    public String get_nome(){
        return this.nome;
    }

    // Setters
    public void set_nome(String nome){
        this.nome = nome;
    }

    public String toString(){
        return this.get_nome() + ".";
    }
}
