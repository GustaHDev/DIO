import java.util.Scanner;
import java.text.DecimalFormat;

public class DescontoInteligente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // TODO: Leia o valor original do produto:
        System.out.println("Insira o valor do produto");
        Double produto = scanner.nextDouble();

        // TODO: Leia a porcentagem de desconto:
        System.out.println("Insira a porcentagem do desconto");
        Double desconto = scanner.nextDouble();

        // TODO: Verifique se o desconto está dentro de um intervalo válido:
        if (desconto < 0 || desconto > 100){
          System.out.println("Desconto invalido");
        }
            // TODO: Calcule o valor final do produto:
            Double valorFinal = produto * (desconto/100);

            // Formata e exibe o valor com duas casas decimais
            DecimalFormat df = new DecimalFormat("0.00");
            System.out.println(df.format(valorFinal));
            scanner.close();
        }
    }