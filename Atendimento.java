public class Atendimento {
private int codigo;
private String nomeAnimal;
private String especie;
private String nomeTutor;
private String data;
private String horario;
private String status;
private String observacoes;
private Procedimento procedimento;
private Sala sala;

public Atendimento(int codigo, String nomeAnimal, String especie, String nomeTutor, String data, String horario, String observacoes, Procedimento procedimento) {
this.codigo = codigo;
this.nomeAnimal = nomeAnimal;
this.especie = especie;
this.nomeTutor = nomeTutor;
this.data = data;
this.horario = horario;
this.observacoes = observacoes;
this.procedimento = procedimento;
this.status = "agendado";
}

public int getCodigo() { return codigo; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public Procedimento getProcedimento() { return procedimento; }
public Sala getSala() { return sala; }
public void setSala(Sala sala) { this.sala = sala; }

public String resumo() {
return "#" + codigo + " - " + nomeAnimal + " (" + especie + ") - " + status;
}

public String detalhes() {
String txt = "Codigo: " + codigo
+ "\nAnimal: " + nomeAnimal + " (" + especie + ")"
+ "\nTutor: " + nomeTutor
+ "\nData/hora: " + data + " " + horario
+ "\nStatus: " + status
+ "\nObs: " + observacoes
+ "\nProcedimento: " + procedimento;
if (sala != null) {
txt += "\nSala: " + sala.getNumero() + " bloco " + sala.getBloco();
if (sala.getResponsavel() != null) {
txt += "\nVeterinario: " + sala.getResponsavel().getNome();
} else {
txt += "\nVeterinario: nenhum";
}
} else {
txt += "\nSala: sem sala";
}
return txt;
}
}
