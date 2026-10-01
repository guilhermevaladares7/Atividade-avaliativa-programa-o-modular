
public class Veterinario {
private String nome;
private String cpf;
private String especialidade;
private String telefone;
private Sala sala;

public Veterinario(String nome, String cpf, String especialidade, String telefone) {
this.nome = nome;
this.cpf = cpf;
this.especialidade = especialidade;
this.telefone = telefone;
}

public String getNome() { return nome; }
public Sala getSala() { return sala; }
public void setSala(Sala sala) { this.sala = sala; }

public String toString() {
return nome + " - " + especialidade + " - CPF: " + cpf + " - Tel: " + telefone;
}
}