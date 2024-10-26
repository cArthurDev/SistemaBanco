import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private int numConta;
    private int senha;
    private double saldo;
    private double limiteDebito = 1000.00;
    private double limiteCredito = 5000.00;
    private List<Transacao> extrato = new ArrayList<>();

    public Cliente(String nome, String cpf, int numConta, int senha, double saldoInicial) {
        this.nome = nome;
        this.cpf = cpf;
        this.numConta = numConta;
        this.senha = senha;
        this.saldo = saldoInicial;
    }

    public String getNome() {
        return nome;
    }

    public int getNumConta() {
        return numConta;
    }

    public int getSenha() {
        return senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void saque(double valor) {
        if (valor <= saldo + limiteDebito) {
            saldo -= valor;
            registrarTransacao("Saque", valor);
        } else {
            System.out.println("Saldo insuficiente para o saque.");
        }
    }

    public void depositar(double valor) {
        saldo += valor;
        registrarTransacao("Depósito", valor);
    }

    public void transferir(double valor, Cliente destinatario, String tipoTransferencia) {
        double taxa = 0;
        if (tipoTransferencia.equals("outra titularidade")) {
            taxa = 5.00;
        } else if (tipoTransferencia.equals("outro banco")) {
            taxa = 10.00;
        }

        if (saldo + limiteDebito >= valor + taxa) {
            saldo -= (valor + taxa);
            destinatario.depositar(valor);
            registrarTransacao("Transferência para " + destinatario.getNome(), valor);
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    public void solicitarEmprestimo(double valor) {
        if (valor <= limiteCredito) {
            saldo += valor;
            registrarTransacao("Empréstimo", valor);
        } else {
            System.out.println("Valor do empréstimo excede o limite de crédito disponível.");
        }
    }

    public void verExtrato() {
        System.out.println("==== Extrato de Transações ====");
        if (extrato.isEmpty()) {
            System.out.println("Nenhuma transação realizada.");
        } else {
            for (Transacao transacao : extrato) {
                System.out.println(transacao.getDetalhes());
            }
        }
        System.out.println("===============================");
    }

    public void analisarCredito() {
        double saldoMedio = calcularSaldoMedio();
        if (saldoMedio > 2000) {
            limiteCredito += 1000;
            System.out.println("Limite de crédito aumentado para R$ " + limiteCredito);
        } else {
            limiteCredito = Math.max(limiteCredito - 500, 500);
            System.out.println("Limite de crédito ajustado para R$ " + limiteCredito);
        }
    }

    private double calcularSaldoMedio() {
        double total = 0;
        for (Transacao transacao : extrato) {
            total += transacao.getValor();
        }
        return extrato.isEmpty() ? 0 : total / extrato.size();
    }

    private void registrarTransacao(String tipo, double valor) {
        extrato.add(new Transacao(tipo, valor));
    }
}
