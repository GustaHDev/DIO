import br.com.dio.model.Board;
import br.com.dio.model.Space;

import java.util.Scanner;
import java.util.stream.Stream;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Map;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    private static Board board;

    private final static int boardSize = 9;

    public static void main(String[] args) throws Exception {
        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]));

        var option = -1;
        while (true) {
            System.out.printf("SUDOKU!");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - New game");
            System.out.println("2 - Colocar número");
            System.out.println("3 - Remover número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Visualizar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> startGame(positions);

                case 2 -> putNumber();

                case 3 -> removeNumber();

                case 4 -> showCurrentGame();

                case 5 -> showGameStatus();

                case 6 -> clearGame();

                case 7 -> endGame();

                case 8 -> System.exit(0);
            }
        }
    }

    private static void startGame(final Map<String, String> positions) {
        if(nonNull(board)){
            System.out.println("Há um jogo em andamento, encerre para começar outro.");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < boardSize; i++){
            spaces.add(new ArrayList<>());
            for(int j = 0; j < boardSize; j++){
                var positionConfig = positions.get("%s,%s".formatted(i, j));
                
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);

                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        board = new Board(spaces);
        System.out.println("Jogo iniciado");
    }

    private static void putNumber() {
        if(isNull(board)){
            System.out.println("Primeiro é preciso iniciar um jogo.");
            return;
        }

        System.out.println("Digite a coluna em que o número será inserido.");
        var col = runUntilGetValidNumber(0, boardSize - 1);

        System.out.println("Agora digite a linha em que o número será inserido.");
        var row = runUntilGetValidNumber(0, boardSize - 1);

        System.out.printf("Informe o número a ser colocado na posição [%s, %s] \\n", col, row);
        var value = runUntilGetValidNumber(1, 9);
        if(!board.changeValue(col, row, value)){
            System.out.printf("A posição [%s, %s] possui um valor fixo \n", col, row);
        }

    }

    private static void removeNumber() {
        if(isNull(board)){
            System.out.println("Primeiro é preciso iniciar um jogo.");
            return;
        }

        System.out.println("Digite a coluna do número a ser removido.");
        var col = runUntilGetValidNumber(0, boardSize - 1);

        System.out.println("Agora digite a linha do número a ser removido");
        var row = runUntilGetValidNumber(0, boardSize - 1);
         
        if(!board.clearValue(col, row)){
            System.out.printf("A posição [%s, %s] possui um valor fixo \n", col, row);
        }
    }

    private static void showCurrentGame() {
        if(isNull(board)){
            System.out.println("Primeiro é preciso iniciar um jogo.");
            return;
        }

        var args = new Object[81];
        var argPos = 0;

        for(int i = 0; i < boardSize; i++){
            for(var col: board.getSpaces()){
                args[argPos ++] = " " + ((isNull(col.get(i).getActual())) ? " " : col.get(i).getActual());
            }
        }
    }

    private static void showGameStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showGameStatus'");
    }

    private static void clearGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clearGame'");
    }

    private static void endGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endGame'");
    }

    private static int runUntilGetValidNumber(final int min, final int max){
        var current = scanner.nextInt();
        while (current < min || current > max) {
            System.out.printf("O número deve ser entre %d e %d", min, max);
            current = scanner.nextInt();
        }
        return current;
    } 
}
