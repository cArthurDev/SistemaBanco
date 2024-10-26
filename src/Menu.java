import java.util.Scanner;

public class Menu {
    private Banco banco;
    private Scanner scanner;

    public Menu(Banco banco) {
        this.banco = banco;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("==== Menu Principal ====");
            System.out.println("1- Cadastrar Cliente");
            System.out.println("2- Acessar Conta");
            System.out.println("3- Listar Clientes");
            System.out.println("4- Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> acessarConta();
                case 3 -> banco.listarClientes();
                case 4 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
    }

    private void cadastrarCliente() {
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Número da Conta: ");
        int numConta = scanner.nextInt();
        System.out.print("Senha: ");
        int senha = scanner.nextInt();
        System.out.print("Saldo Inicial: ");
        double saldoInicial = scanner.nextDouble();

        banco.cadastrarCliente(nome, cpf, numConta, senha, saldoInicial);
    }

    private void acessarConta() {
        System.out.print("Digite o número da conta: ");
        int numConta = scanner.nextInt();
        System.out.print("Digite a senha: ");
        int senha = scanner.nextInt();

        Cliente cliente = banco.autenticar(numConta, senha);
        if (cliente != null) {
            exibirMenuConta(cliente);
        }
    }

    private void exibirMenuConta(Cliente cliente) {
        int opcao;
        do {
            System.out.println("\n==== Menu da Conta ====");
            System.out.println("1- Sacar");
            System.out.println("2- Depositar");
            System.out.println("3- Transferir");
            System.out.println("4- Solicitar Empréstimo");
            System.out.println("5- Ver Extrato");
            System.out.println("6- Analisar Crédito");
            System.out.println("7- Sair da Conta");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o valor do saque: ");
                    double valorSaque = scanner.nextDouble();
                    cliente.saque(valorSaque);
                }
                case 2 -> {
                    System.out.print("Digite o valor do depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    cliente.depositar(valorDeposito);
                }
                case 3 -> {
                    System.out.print("Digite o valor da transferência: ");
                    double valorTransferencia = scanner.nextDouble();
                    System.out.print("Número da conta do destinatário: ");
                    int numContaDestinatario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tipo de transferência: ");
                    System.out.println("MT - Mesma titularida");
                    System.out.println("OT - De outra titularidade");
                    System.out.println("OB - Outro banco)");

                    String tipoTransferencia = scanner.nextLine();
                    Cliente destinatario = banco.buscarCliente(numContaDestinatario);
                    if (destinatario != null) {
                        cliente.transferir(valorTransferencia, destinatario, tipoTransferencia);
                    } else {
                        System.out.println("Conta do destinatário não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("Digite o valor do empréstimo: ");
                    double valorEmprestimo = scanner.nextDouble();
                    cliente.solicitarEmprestimo(valorEmprestimo);
                }
                case 5 -> cliente.verExtrato();
                case 6 -> cliente.analisarCredito();
                case 7 -> System.out.println("Saindo da conta...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 7);
    }
}
