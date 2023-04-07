package com.gerador.notafiscal;


import com.gerador.notafiscal.controllers.NotaFiscalController;

import java.util.Scanner;

public class App {
    public static Scanner sc = new Scanner(System.in);
    public static NotaFiscalController controller = new NotaFiscalController();

    public static String input(String message) {
        String result = "";
        while (result.length() == 0) {
            System.out.print(message);
            result = sc.nextLine();
        }

        return result;
    }

    public static void main( String[] args ) {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Gerador de Nota Fiscal");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=\n");

        String clientName = input("Digite o nome do cliente: ");
        String clientAddress = input("Digite o endereço do cliente: ");

        String showOptions = "Qual é o tipo de serviço?\n";
    }
}
