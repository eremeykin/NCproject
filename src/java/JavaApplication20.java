/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication20;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pete
 */
public class JavaApplication20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        try {
            String line;
            String com1 = "y\n";
            String com2 = "exit";
            Process p = Runtime.getRuntime().exec("cmd /K C:\\ANSYS_call.bat");
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream(), "Cp866"));
            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream(), "Cp866"));
            while ((line = bri.readLine()) != null) {
                System.out.println(line);
                if (line.equals(" Do you wish to override this lock and continue (y or n)?")) {
                    p.getOutputStream().write(com1.getBytes(), 0, 2);
                }
                if (line.equals(" BEGIN:")) {
                    p.getOutputStream().write(com2.getBytes(), 0, 2);
                }
            }
            bri.close();
            while ((line = bre.readLine()) != null) {
                System.out.println(line);
            }

            bre.close();
            p.waitFor();
            System.out.println("Done.");
        } catch (Exception err) {
        }

    }
}
