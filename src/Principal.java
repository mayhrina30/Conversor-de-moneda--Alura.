import Model.ConectionApi;
import com.oracle.exchangerate.models.ExchangeRateApiResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    private static final Map<Integer, String[]> exchangeOptions = new HashMap<>();

    static {
        exchangeOptions.put(1, new String[]{"USD", "ARS"});
        exchangeOptions.put(2, new String[]{"ARS", "USD"});
        exchangeOptions.put(3, new String[]{"USD", "BRL"});
        exchangeOptions.put(4, new String[]{"BRL", "USD"});
        exchangeOptions.put(5, new String[]{"USD", "COP"});
        exchangeOptions.put(6, new String[]{"COP", "USD"});
        exchangeOptions.put(7, new String[]{"USD", "CRC"});
        exchangeOptions.put(8, new String[]{"CRC", "USD"});
        exchangeOptions.put(9, new String[]{"BRL", "ARS"});
        exchangeOptions.put(10, new String[]{"CLP", "ARS"});

    }

    public static void main(String[] args) {

        Scanner readKeyboard = new Scanner(System.in);
        ConectionApi conectionApi;
        conectionApi = new ConectionApi();

        System.out.print("Por favor, ingrese su nombre: ");
        String userName = readKeyboard.nextLine();

        System.out.println("--------------------------------------");
        System.out.printf("*** Bienvenida, %s! ***\n", userName);
        System.out.println("_______________________________________\n");
        System.out.println("Elija la Moneda que desea convertir, por favor\n");
        System.out.println("_________________**______________________\n");

        while (true) {
            printMenu();
            int option = readKeyboard.nextInt();

            if (option == 11) {
                printGoodbyeMessage();
                break;
            }

            if (!exchangeOptions.containsKey(option)) {
                System.out.println("\n\nOPCIÓN NO VÁLIDA\n\n");
                continue;
            }

            String[] codes = exchangeOptions.get(option);
            String baseCode = codes[0];
            String targetCode = codes[1];

            System.out.printf("\n----------------------------\n%s ( %s ) =>> %s ( %s )\n----------------------\n",
                    getCurrencyName(baseCode), baseCode, getCurrencyName(targetCode), targetCode);
            System.out.print("Ingrese el monto que desea cambiar: ");
            double amount = readKeyboard.nextDouble();

            ExchangeRateApiResponse response = conectionApi.createConsulChangeRateTotAPI(baseCode, targetCode, amount);
            System.out.printf("\n\n---Resultado de la Conversión-----: %s\n\n", response.getFormattedConversionResult());
        }
    }

    private static void printMenu() {
        System.out.println("1) Dólar ==> Peso Argentino");
        System.out.println("2) Dólar ==> Real Brasileño");
        System.out.println("3) Dólar ==> Peso Colombiano");
        System.out.println("4) Dólar ==> Colon Costa Rica");
        System.out.println("5) Peso Colombiano ==> Dólar");
        System.out.println("6) Peso Argentino ==> Dólar");
        System.out.println("7) Real Brasileño ==> Dólar");
        System.out.println("8) Colon Costa Rica ==> Dólar");
        System.out.println("9) Real Brasil ==> Peso Argentino");
        System.out.println("10) Chilean Peso ==> Peso Argentino");

        System.out.println("11) Salir");
        System.out.println("\n-------------------------------------");
        System.out.print("Elija una opción válida: ");
    }

    private static void printGoodbyeMessage() {

        String goodbyeMessage = "¡....MUCHAS GRACIAS...!";


        for (char ch : goodbyeMessage.toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(90);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getCurrencyName(String code) {
        switch (code) {
            case "USD": return "Dólar";
            case "ARS": return "Peso Argentino";
            case "BRL": return "Real Brasileño";
            case "COP": return "Peso Colombiano";
            case "CRC": return "Colon Costa Rica";
            case  "CLP": return "Chilean Peso";
            default: return "No se encontro Moneda";
        }
    }
}

