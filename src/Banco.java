import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Cliente> clientes = new ArrayList<>();

    public void cadastrarCliente(String nome, String cpf, int numConta, int senha, double saldoInicial) {
        Cliente novoCliente = new Cliente(nome, cpf, numConta, senha, saldoInicial);
        clientes.add(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public Cliente autenticar(int numConta, int senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getNumConta() == numConta && cliente.getSenha() == senha) {
                return cliente;
            }
        }
        System.out.println("Conta ou senha incorretos.");
        return null;
    }

    public Cliente buscarCliente(int numConta) {
        for (Cliente cliente : clientes) {
            if (cliente.getNumConta() == numConta) {
                return cliente;
            }
        }
        return null;
    }

    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getNome() + " - Conta: " + cliente.getNumConta());
        }
    }
}
