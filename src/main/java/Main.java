import Logica.*;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){
//        Juego sim = new Juego("oo", 20000);
//        sim.iniciar();
        IntStream ints = IntStream.rangeClosed(1, 10).sorted();
        System.out.print(Arrays.toString(ints.toArray()));
    }
}