package modelo;

public class Animal {
    private int id;
    private String nome;
    private String tipo;
    private String raca;
    private int idade;
    private Tutor tutor;

    public Animal(int id, String nome, String tipo, String raca, int idade, Tutor tutor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.raca = raca;
        this.idade = idade;
        this.tutor = tutor;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public String getRaca() { return raca; }
    public int getIdade() { return idade; }
    public Tutor getTutor() { return tutor; }

    @Override
    public String toString() {
        return nome + " (" + tipo + ", " + raca + "), tutor: " + tutor.getNome();
    }
}
