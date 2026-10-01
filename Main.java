import java.util.*;
public class Main {
static Scanner sc = new Scanner(System.in);
static ArrayList<Veterinario> veterinarios = new ArrayList<>();
static ArrayList<Sala> salas = new ArrayList<>();
static ArrayList<Atendimento> atendimentos = new ArrayList<>();
static int proxCodigo = 1;

public static void main(String[] args) {
criarDados();
int op = -1;
while (op != 0) {
System.out.println("clinica veterinaria");
System.out.println("1 - cadastrar atendimento");
System.out.println("2 - associar veterinario a sala");
System.out.println("3 - atribuir atendimento a sala");
System.out.println("4 - atendimentos de uma sala");
System.out.println("5 - finalizados por sala");
System.out.println("6 - buscar por status");
System.out.println("7 - detalhes de um atendimento");
System.out.println("8 - finalizar atendimento");
System.out.println("0 - sair");
op = lerInt("Opcao: ");
switch (op) {
case 1: cadastrarAtendimento(); break;
case 2: associarVeterinario(); break;
case 3: atribuirSala(); break;
case 4: listarPorSala(); break;
case 5: finalizadosPorSala(); break;
case 6: buscarPorStatus(); break;
case 7: detalhesAtendimento(); break;
case 8: finalizarAtendimento(); break;
case 0: System.out.println("Falou!"); break;
default: System.out.println("Opcao invalida.");
}
}
}
static void criarDados() {
veterinarios.add(new Veterinario("ana", "111.111.111-11", "Clinica geral", "31 99999-1111"));
veterinarios.add(new Veterinario("bruno", "222.222.222-22", "Cirurgia", "31 99999-2222"));
veterinarios.add(new Veterinario("carlos", "333.333.333-33", "Dermatologia", "31 99999-3333"));
salas.add(new Sala(101, "A", 3, "Consulta"));
salas.add(new Sala(102, "A", 2, "Cirurgia"));
salas.add(new Sala(201, "B", 4, "Vacinacao"));
}
static void cadastrarAtendimento() {
System.out.print("Nome do animal: ");
String animal = sc.nextLine();
System.out.print("Especie: ");
String especie = sc.nextLine();
System.out.print("Nome do tutor: ");
String tutor = sc.nextLine();
System.out.print("Data: ");
String data = sc.nextLine();
System.out.print("Horario: ");
String horario = sc.nextLine();
System.out.print("Observacoes: ");
String obs = sc.nextLine();
System.out.print("Procedimento: ");
String nomeProc = sc.nextLine();
int duracao = lerInt("Duracao (min): ");
System.out.print("Valor: ");
double valor = Double.parseDouble(sc.nextLine().replace(",", "."));
System.out.print("Complexidade (baixa/media/alta): ");
String complexidade = sc.nextLine();
Procedimento p = new Procedimento(nomeProc, duracao, valor, complexidade);
Atendimento a = new Atendimento(proxCodigo, animal, especie, tutor, data, horario, obs, p);
atendimentos.add(a);
proxCodigo++;
System.out.println("Atendimento #" + a.getCodigo() + " cadastrado como agendado.");
}
static void associarVeterinario() {
for (int i = 0; i < veterinarios.size(); i++) {
System.out.println(i + " - " + veterinarios.get(i));
}
int iv = lerInt("Veterinario: ");
if (iv < 0 || iv >= veterinarios.size()) {
System.out.println("Veterinario invalido.");
return;
}
Veterinario v = veterinarios.get(iv);
if (v.getSala() != null) {
System.out.println("Esse veterinario ja e responsavel pela sala " + v.getSala().getNumero() + ".");
return;
}
Sala s = escolherSala();
if (s == null) {
return;
}
if (s.getResponsavel() != null) {
s.getResponsavel().setSala(null);
}
s.setResponsavel(v);
v.setSala(s);
System.out.println(v.getNome() + " agora cuida da sala " + s.getNumero() + ".");
}
static void atribuirSala() {
Atendimento a = buscarAtendimento(lerInt("codigo do atendimento: "));
if (a == null) {
System.out.println("atendimento nao encontrado.");
return;
}
if (a.getStatus().equals("finalizado")) {
System.out.println("atendimento ja finalizado.");
return;
}
if (a.getSala() != null) {
System.out.println("esse atendimento ja esta na sala " + a.getSala().getNumero() + ".");
return;
}
Sala s = escolherSala();
if (s == null) {
return;
}
if (s.getResponsavel() == null) {
System.out.println("sala precisa de veterinario.");
return;
}
String erro = s.addAtendimento(a);
if (erro != null) {
System.out.println(erro);
} else {
a.setSala(s);
a.setStatus("em andamento");
System.out.println("Atendimento #" + a.getCodigo() + " foi pra sala " + s.getNumero() + " (em andamento).");
}
}
static void listarPorSala() {
Sala s = escolherSala();
if (s == null) {
return;
}
if (s.getAtendimentos().isEmpty()) {
System.out.println("em atendimento nessa sala.");
} else {
for (Atendimento a : s.getAtendimentos()) {
System.out.println(a.resumo());
}
}
System.out.println("Total: " + s.getAtendimentos().size());
}
static void finalizadosPorSala() {
for (Sala s : salas) {
System.out.println("Sala " + s.getNumero() + ": " + s.getTotalFinalizados() + " finalizado(s)");
}
}
static void buscarPorStatus() {
System.out.print("Status (agendado / em andamento / finalizado): ");
String status = sc.nextLine().trim();
int achados = 0;
for (Atendimento a : atendimentos) {
if (a.getStatus().equalsIgnoreCase(status)) {
System.out.println("\n" + a.detalhes());
achados++;
}
}
if (achados == 0) {
System.out.println("Nenhum atendimento com esse status.");
}
}
static void detalhesAtendimento() {
Atendimento a = buscarAtendimento(lerInt("Codigo: "));
if (a == null) {
System.out.println("Atendimento nao encontrado.");
} else {
System.out.println(a.detalhes());
}
}
static void finalizarAtendimento() {
Atendimento a = buscarAtendimento(lerInt("Codigo: "));
if (a == null) {
System.out.println("Atendimento nao encontrado.");
return;
}
if (!a.getStatus().equals("em andamento")) {
System.out.println("So da pra finalizar atendimento em andamento.");
return;
}
a.getSala().finalizar(a);
a.setStatus("finalizado");
System.out.println("Atendimento #" + a.getCodigo() + " finalizado.");
}
static Sala escolherSala() {
for (Sala s : salas) {
System.out.println(s);
}
int num = lerInt("Numero da sala: ");
for (Sala s : salas) {
if (s.getNumero() == num) {
return s;
}
}
System.out.println("Sala nao encontrada.");
return null;
}
static Atendimento buscarAtendimento(int codigo) {
for (Atendimento a : atendimentos) {
if (a.getCodigo() == codigo) {
return a;
}
}
return null;
}

static int lerInt(String msg) {
while (true) {
System.out.print(msg);
try {
return Integer.parseInt(sc.nextLine().trim());
} catch (NumberFormatException e) {
System.out.println("Digita um numero.");
}
}
}
}