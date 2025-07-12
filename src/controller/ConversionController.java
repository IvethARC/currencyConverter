package controller;

import api.CurrencyConverterAPI;
import model.Currency;

import java.util.Scanner;

public class ConversionController {

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0" +
                "\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0" +
                "\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0" +
                "\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0");
        System.out.println("\uD83D\uDCB1 Bienvenido a su convertidor de moneda \uD83E\uDE99");
        System.out.println("\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0" +
                "\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0" +
                "\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0" +
                "\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0\uD83D\uDFF0");
        currencyMenu();

        System.out.println("\n Seleccione la moneda base:");
        String baseCurrency;
        while (true) {
            if (scanner.hasNextInt()) {
                int userCurrency = scanner.nextInt();
                if (userCurrency == 0) {
                    System.out.println("\uD83E\uDEE1 Que tengas un gran día.");
                    return;
                }
                baseCurrency = getCurrencyCode(userCurrency);
                if (userCurrency < 0 || userCurrency > 10) {
                    System.out.println("❌ Opción fuera de rango. Intente nuevamente.");
                    continue;
                }
                if (baseCurrency == null) {
                    System.out.println("❌ Opción fuera de rango. Intente nuevamente.");
                    continue;
                }
                break;
            } else {
                System.out.println("\uD83E\uDD14 No has ingresado un número válido.");
                scanner.next();
            }
        }

        String currencyAmount;
        while (true) {
            System.out.println("Por favor, ingrese el monto a convertir: ");
            System.out.println("(Si desea salir, presione 0)");
            if (scanner.hasNextDouble()) {
                double userAmount = scanner.nextDouble();
                if (userAmount == 0) {
                    System.out.println("\uD83E\uDEE1 Que tengas un gran día.");
                    return;
                }
                currencyAmount = String.valueOf(userAmount);
                if (userAmount < 0) {
                    System.out.println("❌ Debe usar números positivos.");
                    continue;
                }
                break;
            } else {
                System.out.println("\uD83E\uDD14 No has ingresado un número válido.");
                scanner.next();
            }
        }

        String changeCurrency;
        while (true) {
            System.out.println("Seleccione la moneda de cambio:");
            if (scanner.hasNextInt()) {
                int userChange = scanner.nextInt();
                if (userChange == 0) {
                    System.out.println("\uD83E\uDEE1 Que tengas un gran día.");
                    return;
                }
                changeCurrency = getCurrencyCode(userChange);
                if (userChange < 0 || userChange > 10) {
                    System.out.println("❌ Opción fuera de rango. Intente nuevamente.");
                    continue;
                }
                if (changeCurrency == null) {
                    System.out.println("❌ Opción fuera de rango. Intente nuevamente.");
                    continue;
                }
                break;
            } else {
                System.out.println("\uD83E\uDD14 No has ingresado un número válido.");
                scanner.next();
            }
        }


        CurrencyConverterAPI conversionRequest = new CurrencyConverterAPI();
        Currency currency = conversionRequest
                .apiCurrencyConverter(baseCurrency, changeCurrency, currencyAmount);
        System.out.println("El cambio de "+baseCurrency+" "+currencyAmount+" a "+changeCurrency+" es de: "+currency.conversion_result());

    }


    private void currencyMenu(){
        System.out.println("Lista de divisas:");
        System.out.println("1. COP: Peso colombiano ($)");
        System.out.println("2. USD: Dólar estadounidense ($)");
        System.out.println("3. EUR: Euro (€)");
        System.out.println("4. GBP: Libra esterlina (£)");
        System.out.println("5. ARS: Peso argentino ($)");
        System.out.println("6. AUD: Dólar australiano ($)");
        System.out.println("7. BRL: Real brasileño (R$)");
        System.out.println("8. CAD: Dólar canadiense ($)");
        System.out.println("9. CLP: Peso chileno ($)");
        System.out.println("10. CNY: Yuan, Renminbi (¥, 元)");
        System.out.println("0. Salir");

    }

    private String getCurrencyCode(int option) {
        return switch (option) {
            case 1 -> "COP";
            case 2 -> "USD";
            case 3 -> "EUR";
            case 4 -> "GBP";
            case 5 -> "ARS";
            case 6 -> "AUD";
            case 7 -> "BRL";
            case 8 -> "CAD";
            case 9 -> "CLP";
            case 10 -> "CNY";
            default -> null;
        };
    }

}