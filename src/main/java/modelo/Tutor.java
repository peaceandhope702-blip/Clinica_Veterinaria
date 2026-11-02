package modelo;

public class Tutor {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;

    public Tutor(int id, String nome, String cpf, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEndereco() { return endereco; }

    @Override
    public String toString() {
        return nome + " - CPF: " + cpf;
    }
}
