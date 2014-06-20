/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication20;

import org.apache.commons.exec.*;
import java.io.*;
import java.util.*;
import org.apache.commons.exec.DefaultExecutor;
import com.mangofactory.typescript.tss.io.*;

/**
 *
 * @author Pete
 */
public class JavaApplication20 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("WaitWhileNotSynced")
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);

        DefaultExecutor executor = new DefaultExecutor();
        CommandLine cmdLine = new CommandLine("calc.exe");

        PipedOutputStream stdout = new PipedOutputStream();
        PipedOutputStream stderr = new PipedOutputStream();
        PipedInputStream stdin = new PipedInputStream();

        AutoFlushingPumpStreamHandler streamHandler = new AutoFlushingPumpStreamHandler(stdout, stderr, stdin);

        executor.setStreamHandler(streamHandler);

        BufferedInputStream processOutput = new BufferedInputStream(new PipedInputStream(stdout));
        BufferedInputStream processError = new BufferedInputStream(new PipedInputStream(stderr));
        BufferedOutputStream processInput = new BufferedOutputStream(new PipedOutputStream(stdin));

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

        executor.execute(cmdLine, resultHandler);
        
        Thread inThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        String input = scan.nextLine();
                        input += "\n";
                        processInput.write(input.getBytes());
                        processInput.flush();
                    } catch (Exception ex) {

                    }
                }
            }
        });
        Thread outThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.print(Character.toChars(processOutput.read()));
                    } catch (IOException ex) {

                    }
                }
            }
        });
        Thread errThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.print(Character.toChars(processError.read()));
                    } catch (IOException ex) {

                    }
                }
            }
        });
        inThread.start();
        outThread.start();
        errThread.start();
    }
}
