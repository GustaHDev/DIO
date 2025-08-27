public class Account {
    private final String name;

    private double chequeEspecial;

    private double limiteChequeEspecial;

    private double depositoInicial;

    private double saldo;

    public Account(double depositoInicial, String name) {
        this.name = name;
        this.depositoInicial = depositoInicial;
        this.saldo = depositoInicial;
        if (depositoInicial < 500 && depositoInicial > 0) {
            chequeEspecial = 50;
        } else if (depositoInicial >= 500) {
            chequeEspecial = depositoInicial / 2;
        } else{  
            chequeEspecial = 0;
            System.out.println("Depósito inicial inválido.");
            return;
        }
        updateLimiteChequeEspecial();
    }

    public void updateLimiteChequeEspecial() {
        limiteChequeEspecial = saldo + chequeEspecial;
        if (limiteChequeEspecial <= 0) {
            saldo -= chequeEspecial * 0.20;
        }
    }

    public String getName() {
        return name;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public void addSaldo(double value) {
        if(value < 0) {
            System.out.println("Insira um valor válido para depósito.");
            return;
        }
        saldo += value;
        updateLimiteChequeEspecial();
    }

    public void takeSaldo(double value) {
        if (value < 0) {
            System.out.println("Insira um valor válido para saque.");
            return;
        }
        saldo -= value;
        updateLimiteChequeEspecial();
            
    }

    public void payDivida(double value) {
        if (value > 0) {
            saldo -= value;
            updateLimiteChequeEspecial();
            System.out.println("Pagamento efetuado com sucesso.");
            return;
        } else {
            System.out.println("Insira um valor válido para efetuar o pagamento.");
            return;
        }
    }

    public String isUsingChequeEspecial() {
        if(saldo < 0){
            return "A conta está utilizando cheque especial.";
        } else{
            return "A conta não está utilizando cheque especial.";
        }
    }

}