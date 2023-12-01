package school.sptech;

import school.sptech.utils.LeitoresUtils;
import school.sptech.view.Menu;

public class App {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.logarUsuario();

        try {
            menu.menuPrincipal();
        } catch (InterruptedException e) {
            System.out.println("Houve um problema durante a execução: ");
            System.out.println(e);
        }

        LeitoresUtils.finalizarLeitores();
    }
}
