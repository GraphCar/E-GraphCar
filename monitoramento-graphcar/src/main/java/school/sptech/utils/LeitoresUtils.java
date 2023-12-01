package school.sptech.utils;

import java.util.Scanner;

public class LeitoresUtils {

    public static final Scanner LEITOR_TEXTO = new Scanner(System.in);
    public static final Scanner LEITOR_NUM = new Scanner(System.in);

    public static void finalizarLeitores() {
        LEITOR_TEXTO.close();
        LEITOR_NUM.close();
    }
}
