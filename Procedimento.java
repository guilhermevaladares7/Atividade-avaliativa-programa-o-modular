public class Procedimento {
private String nome;
private int duracao;
private double valor;
private String complexidade;

public Procedimento(String nome, int duracao, double valor, String complexidade) {
this.nome = nome;
this.duracao = duracao;
this.valor = valor;
this.complexidade = complexidade;
}

public String getNome() { return nome; }

public String toString() {
return nome + " | " + duracao + " min | R$ " + valor + " | complexidade: " + complexidade;
}
}