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
                        //processInput.close();
                    } catch (Exception ex) {

                    }
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread outThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.print(Character.toChars(processOutput.read()));
                        //System.out.println(br.readLine());
                    } catch (IOException ex) {

                    }
                }
                //Thread.currentThread().interrupt();
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
                //Thread.currentThread().interrupt();
            }
        });
        inThread.start();
        outThread.start();
        errThread.start();

//        Scanner scan = new Scanner(System.in);
//        ProcessBuilder pb = new ProcessBuilder("C:\\ANSYS_call.bat");
//        //pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("C:\\input.txt")));
//        Process process = pb.start();
//        //Process process = Runtime.getRuntime().exec("C:\\Ansys_call.bat");
//        //Thread.sleep(10000);
//        OutputStream stdin = process.getOutputStream();
//        InputStream stderr = process.getErrorStream();
//        InputStream stdout = process.getInputStream();
//        BufferedReader out_reader = new BufferedReader(new InputStreamReader(stdout, "Cp866"));
//        BufferedReader err_reader = new BufferedReader(new InputStreamReader(stderr, "Cp866"));
//        OutputStreamWriter OSW = new OutputStreamWriter(stdin, "Cp866");
//        BufferedWriter writer = new BufferedWriter(OSW);
//        Thread inThread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while (process.isAlive()) {
//                    BufferedWriter writer = null;
//                    try {
//                        OutputStream stdin = process.getOutputStream();
//                        writer = new BufferedWriter(new OutputStreamWriter(stdin, "Cp866"));
//                        String input = scan.nextLine();
//                        input += "\n";
//                        try {
//                            //writer.write(input);
//                            //writer.flush();
//                            //OSW.flush();
//                            stdin.write(input.getBytes());
//                            stdin.flush();
//                        } catch (IOException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    } catch (UnsupportedEncodingException ex) {
//                        Logger.getLogger(JavaApplication20.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    //stdin.close();
//                    Thread.currentThread().interrupt();
//                }
//            }
//        });
//        Thread outThread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while (process.isAlive()) {
//                    try {
//                        System.out.println(out_reader.readLine());
//                    } catch (IOException ex) {
//                        Logger.getLogger(JavaApplication20.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                Thread.currentThread().interrupt();
//            }
//        });
//        Thread errThread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while (process.isAlive()) {
//                    try {
//                        System.out.println(err_reader.readLine());
//                    } catch (IOException ex) {
//                        Logger.getLogger(JavaApplication20.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                Thread.currentThread().interrupt();
//            }
//        });
//
//        inThread.start();
//
//        outThread.start();
//
//        errThread.start();
    }
}
