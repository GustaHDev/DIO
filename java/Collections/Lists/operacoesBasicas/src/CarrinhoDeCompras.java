import java.util.List;
import java.util.ArrayList;

public class CarrinhoDeCompras {
    private List<Item> itemList;

    public CarrinhoDeCompras(){
        this.itemList = new ArrayList<>();
    }

    public void adicionarItem(String nome, double preco, int quantidade){
        itemList.add(new Item(nome, preco, quantidade));
    }

    public void removerItem(String nome){
        List<Item> itensParaRemover = new ArrayList<>();
        for (Item i : itemList){
            if(i.getNome().equalsIgnoreCase(nome)){
                itensParaRemover.add(i);
            }
        }
        itemList.removeAll(itensParaRemover);
        }

    public double calcularValorTotal(){
        double total = 0;
        for (Item i : itemList){
            total += i.getPreço() * i.getQuantidade();
        }
        return total;
    }

    public List<Item> exibirItens(){
        return itemList;
    }

    public static void main(String[] args){
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionarItem("Notebook", 3500.00, 1);
        carrinho.adicionarItem("Mouse", 150.00, 2);
        carrinho.adicionarItem("Teclado", 200.00, 1);

        System.out.println("Itens no carrinho:");
        System.out.println(carrinho.exibirItens());

        System.out.printf("Valor total: R$ %.2f%n", carrinho.calcularValorTotal());

        carrinho.removerItem("Mouse");
        System.out.println("Itens no carrinho após remover o mouse:");
        System.out.println(carrinho.exibirItens());
        System.out.printf("Valor total após remoção: R$ %.2f%n", carrinho.calcularValorTotal());
    }
}
