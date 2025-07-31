package ThreadCre.DifferentWayThreadCreation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorEx {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();


        Callable<String> task = () -> {
            return "Prashan";
        };


    }
}
