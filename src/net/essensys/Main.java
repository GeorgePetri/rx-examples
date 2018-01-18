package net.essensys;


import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new RxExamples();

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
