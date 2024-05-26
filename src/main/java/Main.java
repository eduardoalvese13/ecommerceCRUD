import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "produtos.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("");
            System.out.println(">>> MENU <<<");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Visualizar produtos");
            System.out.println("3. Editar produto");
            System.out.println("4. Deletar produto");
            System.out.println("5. Sair");
            System.out.print(">> Sua opção: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println(">> Saindo da aplicação...");
                    break;
                default:
                    System.out.println(">> Opção inválida!");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Método para adicionar um produto
    
    private static void addProduct() {
        FileWriter writer = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            writer = new FileWriter(FILE_NAME, true);
            bw = new BufferedWriter(writer);
            out = new PrintWriter(bw);


            System.out.println("");
            System.out.println(">>> ADICIONAR PRODUTOS <<<");
            Scanner scanner = new Scanner(System.in);
            System.out.println(">> Digite o nome do produto:");
            String nome = scanner.nextLine();
            System.out.println(">> Digite o preço do produto:");
            double preco = scanner.nextDouble();
            scanner.nextLine();
            out.println(nome + "," + preco);
            System.out.println(">> Produto adicionado com sucesso!");
        } catch (IOException e) {
            System.err.println(">> Erro ao adicionar o produto: " + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
                if (bw != null) bw.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println(">> Erro ao fechar os recursos: " + e.getMessage());
            }
        }
    }

    // Método para visualizar os produtos
    
    private static void viewProducts() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            System.out.println("");
            System.out.println(">>> VISUALIZAR PRODUTOS <<<");
            System.out.println(">> Produtos disponíveis:");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(">> Nome: " + parts[0] + ", Preço: " + parts[1]);
            }
        } catch (IOException e) {
            System.err.println(">> Erro ao visualizar os produtos: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.err.println(">> Erro ao fechar o leitor: " + e.getMessage());
            }
        }
    }

    // Método para editar um produto
    
    private static void editProduct() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            System.out.println("");
            System.out.println(">>> EDITAR PRODUTOS <<<");
            System.out.println(">> Digite o nome do produto que deseja editar:");

            Scanner scanner = new Scanner(System.in);
            String nomeProduto = scanner.nextLine();

            File inputFile = new File(FILE_NAME);
            File tempFile = new File("temp.txt");

            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(nomeProduto)) {
                    System.out.println(">> Digite o novo nome do produto:");
                    String novoNome = scanner.nextLine();
                    System.out.println(">> Digite o novo preço do produto:");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    writer.write(novoNome + "," + novoPreco + "\n");
                    found = true;
                    System.out.println(">> Produto editado com sucesso!");
                } else {
                    writer.write(line + "\n");
                }
            }

            if (!found) {
                System.out.println(">> Produto não encontrado!");
            }

        } catch (IOException e) {
            System.err.println(">> Erro ao editar o produto: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar os recursos: " + e.getMessage());
            }
        }

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }

    // Método para deletar um produto

    private static void deleteProduct() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("");
            System.out.println(">>> DELETAR PRODUTO <<<");
            System.out.println(">> Digite o nome do produto que deseja excluir:");
            String nomeProduto = scanner.nextLine();

            File inputFile = new File(FILE_NAME);
            File tempFile = new File("temp.txt");

            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equalsIgnoreCase(nomeProduto)) {
                    writer.write(line + "\n");
                } else {
                    found = true;
                }
            }

            if (found) {
                System.out.println(">> Produto excluído com sucesso!");
            } else {
                System.out.println(">> Produto não encontrado!");
            }

        } catch (IOException e) {
            System.err.println(">> Erro ao excluir o produto: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.err.println(">> Erro ao fechar os recursos: " + e.getMessage());
            }
        }

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }
}
