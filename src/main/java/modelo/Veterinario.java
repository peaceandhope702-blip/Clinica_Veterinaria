package modelo;

public class Veterinario extends Usuario {
    private String crmv;

    public Veterinario(int id, String nome, String login, String senha, String crmv) {
        super(id, nome, login, senha, "Veterinário");
        this.crmv = crmv;
    }

    public String getCrmv() { return crmv; }
}
