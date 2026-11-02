package modelo;

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private Animal animal;
    private Veterinario veterinario;
    private LocalDateTime dataHora;
    private String tratamento;
    private String observacoes;

    public Consulta(int id, Animal animal, Veterinario veterinario,
                    LocalDateTime dataHora, String tratamento, String observacoes) {
        this.id = id;
        this.animal = animal;
        this.veterinario = veterinario;
        this.dataHora = dataHora;
        this.tratamento = tratamento;
        this.observacoes = observacoes;
    }

    public int getId() { return id; }
    public Animal getAnimal() { return animal; }
    public Veterinario getVeterinario() { return veterinario; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getTratamento() { return tratamento; }
    public String getObservacoes() { return observacoes; }
}
