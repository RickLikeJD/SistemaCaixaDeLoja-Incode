package desafios;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.text.NumberFormat;

public class CaixaDeLoja {
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        ArrayList<Double> produtos = new ArrayList<>();
        double total = 0.0;

        while (true) {
            String opcao = JOptionPane.showInputDialog(
                    "Digite uma opção:" +
                            "\n1. Registrar Produto" +
                            "\n2. Finalizar Compra" +
                            "\n3. Sair"
            );

            if (opcao == null) return; // usuário cancelou

            switch (opcao) {
                case "1":
                    while (true) {
                        String entrada = JOptionPane.showInputDialog("Digite o valor do produto:");
                        if (entrada == null) break;

                        try {
                            double valor = Double.parseDouble(entrada);
                            if (valor < 0) {
                                JOptionPane.showMessageDialog(null, "Valor não pode ser negativo!");
                                continue;
                            }
                            produtos.add(valor);
                            total += valor;
                            break;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Digite um número válido!");
                        }
                    }
                    break;

                case "2":
                    if (produtos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado!");
                        break;
                    }

                    // aplica desconto por valor da compra
                    if (total >= 100) {
                        total *= 0.9; // 10% desconto
                        JOptionPane.showMessageDialog(null,
                                "Produtos: " + produtos +
                                        "\nTotal (com 10% de desconto): " + nf.format(total));
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Produtos: " + produtos +
                                        "\nTotal: " + nf.format(total) +
                                        "\n(Sem desconto)");
                    }

                    String formaPagamento = JOptionPane.showInputDialog(
                            "Selecione a forma de pagamento:" +
                                    "\n1. Dinheiro" +
                                    "\n2. Cartão" +
                                    "\n3. Pix"
                    );
                    if (formaPagamento == null) break;

                    switch (formaPagamento) {
                        case "1": // Dinheiro
                            while (true) {
                                String valorPagoStr = JOptionPane.showInputDialog("Valor entregue pelo cliente:");
                                if (valorPagoStr == null) break;

                                try {
                                    double valorPago = Double.parseDouble(valorPagoStr);
                                    if (valorPago < total) {
                                        JOptionPane.showMessageDialog(null,
                                                "Valor insuficiente! Falta " + nf.format(total - valorPago));
                                        continue;
                                    }
                                    double troco = valorPago - total;
                                    JOptionPane.showMessageDialog(null, "Troco a receber: " + nf.format(troco));
                                    break;
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Digite um número válido!");
                                }
                            }
                            break;

                        case "2": // Cartão
                            JOptionPane.showMessageDialog(null,
                                    "Pagamento com cartão aprovado!" +
                                            "\nTotal a pagar: " + nf.format(total));
                            break;

                        case "3": // Pix
                            double descontoPix = total * 0.02; // 2% desconto
                            total -= descontoPix;
                            JOptionPane.showMessageDialog(null,
                                    "Pagamento via Pix aprovado!" +
                                            "\nTotal com 2% de desconto: " + nf.format(total));

                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida");
                            break;
                    }

                    // Iniciar nova compra
                    String novaCompra = JOptionPane.showInputDialog(
                            "Deseja iniciar uma nova compra?" +
                                    "\n1. Sim" +
                                    "\n2. Não"
                    );
                    if ("1".equals(novaCompra)) {
                        produtos.clear();
                        total = 0.0;
                        JOptionPane.showMessageDialog(null, "Nova compra iniciada!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Compra finalizada. Volte sempre!");
                        return;
                    }
                    break;

                case "3":
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}
