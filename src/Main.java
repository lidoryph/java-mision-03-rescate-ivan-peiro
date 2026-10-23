import java.util.Random;
import java.util.Scanner;

public class Main {

    static char[][] matriz = new char[10][10];
    static char prevChar = '*';
    static int vidas = 10;
    static int robLin = 0;
    static int robCol = 0;

    public static void main(String[] args) {

        System.out.println("Misión de rescate preparada");
        System.out.println("Vidas iniciales: " + vidas);

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = '*';
            }
        }

        matriz[0][0] = 'R';
        matriz[9][9] = 'F';

        int randFila, randColn;
        Random rand = new Random();
        int contador = 0;

        do {
            randFila = rand.nextInt(10);
            randColn = rand.nextInt(10);

            if(matriz[randFila][randColn] == '*') {
                matriz[randFila][randColn] = 'X';
                contador++;
            }
        } while(contador < 5);

        do {
            randFila = rand.nextInt(10);
            randColn = rand.nextInt(10);

            if(matriz[randFila][randColn] == '*') {
                matriz[randFila][randColn] = 'B';
                break;
            }
        } while(true);

        printTable();

        Scanner sc = new Scanner(System.in);
        char input;
        while (vidas > 0) {
            System.out.print("Escribe un movimiento [W A S D]: ");
            input = sc.next().charAt(0);

            switch (input) {
                case 'D' -> { moveRobot(0, 1); }
                case 'A' -> { moveRobot(0, -1); }
                case 'W' -> { moveRobot(-1, 0); }
                case 'S' -> { moveRobot(1, 0);}
                default -> { System.out.println("Entrada no valida."); }
            }

            if (prevChar == 'F') {
                System.out.println("Rescate completado.");
                break;
            }
        }

        if(vidas < 1) System.out.println("Rescate no completado.");
    }

    static void printTable() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Tienes " + vidas + " de bateria.");
    }

    static void moveRobot(int y, int x) {
        int locX = robCol + x;
        int locY = robLin + y;

        if(locX > 9 || locX < 0) {
            printTable();
            return;
        }
        if(locY > 9 || locY < 0) {
            printTable();
            return;
        }

        if(matriz[locY][locX] == 'X') {
            printTable();
            System.out.println("Movimiento bloqueado.");
            return;
        }

        matriz[robLin][robCol] = prevChar;

        if(matriz[locY][locX] == 'B') {
            vidas += 2;
            prevChar = '*';
        } else prevChar = matriz[locY][locX];
        matriz[locY][locX] = 'R';
        robLin = locY;
        robCol = locX;

        printTable();
    }


}