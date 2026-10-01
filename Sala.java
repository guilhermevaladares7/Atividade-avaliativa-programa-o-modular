import java.util.ArrayList;

public class Sala {
private int numero;
private String bloco;
private int capacidade;
private String tipo;
private Veterinario responsavel;
private ArrayList<Atendimento> atendimentos = new ArrayList<>();
private int totalFinalizados = 0;

public Sala(int numero, String bloco, int capacidade, String tipo) {
this.numero = numero;
this.bloco = bloco;
this.capacidade = capacidade;
this.tipo = tipo;
}

public int getNumero() { return numero; }
public String getBloco() { return bloco; }
public Veterinario getResponsavel() { return responsavel; }
public void setResponsavel(Veterinario v) { this.responsavel = v; }
public ArrayList<Atendimento> getAtendimentos() { return atendimentos; }
public int getTotalFinalizados() { return totalFinalizados; }

public String addAtendimento(Atendimento a) {
if (atendimentos.size() >= capacidade) {
return "Sala lotada.";
}
if (!atendimentos.isEmpty()) {
String procAtual = atendimentos.get(0).getProcedimento().getNome();
if (!procAtual.equalsIgnoreCase(a.getProcedimento().getNome())) {
return "Essa sala so aceita atendimentos de " + procAtual + ".";
}
}
atendimentos.add(a);
return null;
}

public void finalizar(Atendimento a) {
atendimentos.remove(a);
totalFinalizados++;
}

public String toString() {
String resp;
if (responsavel != null) {
resp = responsavel.getNome();
} else {
resp = "sem responsavel";
}
return "Sala " + numero + " - bloco " + bloco + " - " + tipo + " - cap. " + capacidade + " - " + resp;
}
}
