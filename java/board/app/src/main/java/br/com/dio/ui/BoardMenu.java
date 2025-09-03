package br.com.dio.ui;

import static br.com.dio.persistence.config.ConnectionConfig.getConnection;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.dio.persistence.entity.BoardColumnKindEnum.INITIAL;
import br.com.dio.persistence.entity.BoardColumnEntity;
import br.com.dio.persistence.entity.BoardEntity;
import br.com.dio.persistence.entity.CardEntity;
import br.com.dio.service.BoardColumnQueryService;
import br.com.dio.service.BoardQueryService;
import br.com.dio.service.CardQueryService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final BoardEntity entity;

    public void execute() {
        try {
            System.out.printf("Você selecionou o board %s, selecione a operação desejada \n", entity.getId());
            var option = -1;
            while (option != 9) {
                System.out.println("1 - Criar card");
                System.out.println("2 - Mover card");
                System.out.println("3 - Bloquear card");
                System.out.println("4 - Desbloquear card");
                System.out.println("5 - Cancelar card");
                System.out.println("6 - Visualizar board");
                System.out.println("7 - Visualizar colunas com card");
                System.out.println("8 - Visualizar card");
                System.out.println("9 - Voltar ao menu de boards");

                System.out.println("10 - Sair");
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> createCard();
                    case 2 -> moveCardToNextColumn();
                    case 3 -> unblockCard();
                    case 4 -> unblockCard();
                    case 5 -> cancelCard();
                    case 6 -> showBoard();
                    case 7 -> showColumn();
                    case 8 -> showCard();
                    case 9 -> System.out.println("Voltando ao menu anterior");
                    case 10 -> System.exit(0);
                    default -> System.out.println("Opção inválida! Informe uma opção presente no menu.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

    private void createCard() throws SQLException {
        var card = new CardEntity();
        System.out.println("Informe o título do card");
        card.setTitle(scanner.next());
        System.out.println("Informe a descrição do card");
        card.setDescription(scanner.next());

        var initialColumn = entity.getBoardColumns().stream().filter(bc -> bc.getKind().equals(INITIAL))
    }

    private void moveCardToNextColumn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveCardToNextColumn'");
    }

    private void unblockCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unblockCard'");
    }

    private void cancelCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelCard'");
    }

    private void showBoard() throws SQLException {
        try (var connection = getConnection()) {
            var optional = new BoardQueryService(connection).showBoardDetails(entity.getId());
            optional.ifPresent(b -> {
                System.out.printf("Board [%s, $s] \n", b.id(), b.name());
                b.columns().forEach(c -> {
                    System.out.printf("Coluna [%s] tipo: [%s] tem %s cards \n", c.name(), c.kind(), c.cardsAmount());
                });
            });
        }
    }

    private void showColumn() throws SQLException {
        var columnsIds = entity.getBoardColumns().stream().map(BoardColumnEntity::getId).toList();
        var selectedColumn = -1L;
        while (!columnsIds.contains(selectedColumn)) {
            System.out.printf("Escolha uma coluna do board %s \n", entity.getName());
            entity.getBoardColumns()
                    .forEach(c -> System.out.printf("%s - %s [%s] \n", c.getId(), c.getName(), c.getKind()));
            selectedColumn = scanner.nextLong();
        }
        try (var connection = getConnection()) {
            var column = new BoardColumnQueryService(connection).findById(selectedColumn);
            column.ifPresent(co -> {
                System.out.printf("Coluna %s tipo %s \n", co.getName(), co.getKind());
                co.getCards().forEach(ca -> System.out.printf("Card %s - %s \n Descrição: %s",
                        ca.getId(), ca.getTitle(), ca.getDescription()));
            });
        }

    }

    private void showCard() throws SQLException{
        System.out.println("Informe o id do card a ser que deseja ver");
        var selectedCardId = scanner.nextLong();
        try(var connection = getConnection()){
             new CardQueryService(connection).findById(selectedCardId)
                .ifPresentOrElse(c -> {
                    System.out.printf("Card %s - %s. \n", c.id(), c.title());
                    System.out.printf(" Descrição: %s \n", c.description());
                    System.out.println(c.blocked() ? 
                    "Está bloqueado. Motivo: " + c.blockReason() : 
                    "Não está bloqueado");
                    System.out.printf("Já foi bloqueado %s vezes \n", c.blocksAmount());
                    System.out.printf("Se encontra na coluna %s - %s \n", c.columnId(), c.columnName());
                }, 
                () -> System.out.printf("Não foi possível encontrar um card com o id %s \n", selectedCardId));
        }
    }

}
