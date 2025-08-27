import java.util.Scanner;

public class Main {

    
    private static Scanner scanner = new Scanner(System.in);
    
    public static Account iniciarConta(){
        System.out.print("Nome do titular da conta: ");
        String name = scanner.nextLine();
        System.out.print("Depósito inicial: ");
        double depositoInicial = Double.parseDouble(scanner.nextLine());
        var account = new Account(depositoInicial, name);
        System.out.println("Conta criada com sucesso para " + name + " com depósito inicial de " + depositoInicial);
        return account;
    }
    private static Account account = iniciarConta();
    public static void main(String[] args) {

        int option = -1;

        do {
            System.out.printf("Bem vindo ao GBank, %s%n", account.getName());
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Ver cheque especial");
            System.out.println("3 - Fazer um depósito");
            System.out.println("4 - Sacar");
            System.out.println("5 - Realizar pagamento");
            System.out.println("6 - Ver se a conta está utilizando cheque especial");
            System.out.println("0 - Sair");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> System.out.println("Saldo: " + account.getSaldo());
                case 2 -> System.out.println("Cheque especial: " + account.getChequeEspecial());
                case 3 -> System.out.println(deposito()); 
                case 4 -> System.out.println(saque());
                case 5 -> System.out.println(pagamento());
                case 6 -> System.out.println(account.isUsingChequeEspecial());
                case 0 -> sair();
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    public static String deposito(){
        System.out.print("Valor para depositar: ");
        double valorDeposito = Double.parseDouble(scanner.nextLine());
        account.addSaldo(valorDeposito);
        System.out.println("Depósito realizado.");
        return "saldo atual " + account.getSaldo();
}

    public static String saque(){
        System.out.print("Valor para sacar: ");
        double valorSaque = Double.parseDouble(scanner.nextLine());
        account.takeSaldo(valorSaque);
        System.out.println("Saque realizado.");
        return "saldo atual " + account.getSaldo();
}

    public static String pagamento(){
        System.out.println("Valor do boleto: ");
        double valorBoleto = Double.parseDouble(scanner.nextLine());
        account.payDivida(valorBoleto);
        return "saldo atual " + account.getSaldo();
    }

    public static void sair(){
        System.out.println("Encerrando o programa...");
        System.exit(0);
    }
}