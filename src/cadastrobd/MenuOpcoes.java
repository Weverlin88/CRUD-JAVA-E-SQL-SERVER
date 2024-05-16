package cadastrobd;

import java.util.Scanner;

public class MenuOpcoes {
    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Incluir Pessoa Física");
            System.out.println("2 - Alterar Pessoa Física");
            System.out.println("3 - Excluir Pessoa Física");
            System.out.println("4 - Exibir Pessoa Física por ID");
            System.out.println("5 - Exibir todas as Pessoas Físicas");
            System.out.println("6 - Incluir Pessoa Jurídica");
            System.out.println("7 - Alterar Pessoa Jurídica");
            System.out.println("8 - Excluir Pessoa Jurídica");
            System.out.println("9 - Exibir Pessoa Jurídica por ID");
            System.out.println("10 - Exibir todas as Pessoas Jurídicas");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();

            
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando o programa...");
                    return;
                case 1:
                    // Lógica para incluir pessoa física
                    break;
                case 2:
                    // Lógica para alterar pessoa física
                    break;
                case 3:
                    // Lógica para excluir pessoa física
                    break;
                case 4:
                    // Lógica para exibir pessoa física por ID
                    break;
                case 5:
                    // Lógica para exibir todas as pessoas físicas
                    break;
                case 6:
                    // Lógica para incluir pessoa jurídica
                    break;
                case 7:
                    // Lógica para alterar pessoa jurídica
                    break;
                case 8:
                    // Lógica para excluir pessoa jurídica
                    break;
                case 9:
                    // Lógica para exibir pessoa jurídica por ID
                    break;
                case 10:
                    // Lógica para exibir todas as pessoas jurídicas
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                    break;
            }
        }
    }
}
