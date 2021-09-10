import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;
import java.io.InputStreamReader;


public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println("        Validador de simbolos       \n ");

        System.out.println("Esse programa verefica se o arquivo de imput de simbolos, como (){}[] esta correto");
        System.out.println("Coloque o arquivo input.txt dentro da pasta App, com o testo que voce quer validar");
        System.out.println("O resultado pode ser visto no arquivo output.txt dentro da pasta App");
        System.out.println("");
        Leitor();
        
    }
    
    public static void Leitor() throws FileNotFoundException, IOException {

        try {

            FileInputStream arquivo = new FileInputStream("App/input.txt");
            BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivo));
            FileWriter escritor = new FileWriter("App/output.txt");
            String linha;

            while ((linha = leitor.readLine()) != null) {

                if (Vereficador(linha)) 
                {
                    escritor.write(linha + "  valido  \n");
                }

                else 
                {
                    escritor.write(linha + "  invalido  \n");
                }
            }

            escritor.close();
            leitor.close();

        } catch (Exception erro) {

            System.out.println("Houve um erro ao tentar ler o arquivo Imput: " + erro);
        }
    }

    public static boolean Vereficador(String line) {

        Stack<Character> simbolo = new Stack<Character>();
        boolean isValid = false;

        try {

            for (int cont = 0; cont < line.length(); cont++) {

                if (line.charAt(cont) == '(' || line.charAt(cont) == '[' || line.charAt(cont) == '{') 
                    simbolo.push(line.charAt(cont));

                else 
                {
                    if (simbolo.isEmpty()) 
                    {
                        isValid = false;
                        break;
                    }

                    else if (line.charAt(cont) == ')' && simbolo.peek() != '(' || line.charAt(cont) == '}' && simbolo.peek() != '{' || line.charAt(cont) == ']' && simbolo.peek() != '[') 
                    {            
                        isValid = false;
                        break;
                    }
                    simbolo.pop();
                }

                if (cont == line.length() - 1 && simbolo.isEmpty())
                    isValid = true;
            }
            return isValid;

        } catch (Exception erro) {

            System.out.println("Houve um erro ao tentar verificar os dados do arquivo de entrada: " + erro);
            return false;
        }
    }
}
