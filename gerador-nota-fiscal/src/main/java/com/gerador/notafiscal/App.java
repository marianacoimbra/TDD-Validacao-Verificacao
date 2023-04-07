package com.gerador.notafiscal;


import com.gerador.notafiscal.controllers.NotaFiscalController;
import com.gerador.notafiscal.models.NotaFiscal;
import com.gerador.notafiscal.models.enums.ServiceType;

import java.util.List;
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

    public static String getServiceTypeDescription() {
        List<String> serviceTypeDescriptions = ServiceType.getDescriptions();

        StringBuilder showOptions = new StringBuilder("Qual é o tipo de serviço?\n");
        for (int i = 0; i < serviceTypeDescriptions.size(); i++) {
            int position = i + 1;
            showOptions.append(position)
                    .append(" - ")
                    .append(serviceTypeDescriptions.get(i))
                    .append("\n");
        }
        System.out.println(showOptions);

        String choosedOption = input("Digite apenas o número do tipo: ");
        try {
            int index = Integer.parseInt(choosedOption) - 1;
            return serviceTypeDescriptions.get(index);
        } catch (Exception e) {
            return getServiceTypeDescription();
        }
    }

    public static void main( String[] args ) {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Gerador de Nota Fiscal");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=\n");

        String clientName = input("Digite o nome do cliente: ");
        String clientAddress = input("Digite o endereço do cliente: ");

        String serviceTypeDescription = getServiceTypeDescription();

        System.out.print("Digite o valor da fatura: ");
        double billValue = sc.nextDouble();

        System.out.println("\nVamos gerar a nota fiscal para você!");
        NotaFiscal notaFiscal = controller.generate(clientName, clientAddress, serviceTypeDescription, billValue);

        System.out.println("\nPronto!\nA sua nota fiscal possui os seguintes dados:");
        System.out.println(notaFiscal);
    }
}
