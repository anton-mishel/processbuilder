package com.mycompany.app;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class App {
  public static void main(String[] args) throws InterruptedException ,
    IOException {
    List<String> list = new ArrayList<String>();
    list.addAll(Arrays.asList(args));
    System.out.println(list.size());
 
    ProcessBuilder pb = new ProcessBuilder(list);
    Process process = pb.start();
    int errCode = process.waitFor();
    System.out.println("Echo command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
    System.out.println("Echo Output:\n" + output(process.getInputStream()));  
  }

  private static String output(InputStream inputStream) throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(inputStream));
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line + System.getProperty("line.separator"));
      }
    } finally {
      br.close();
    }
    return sb.toString();
  }
}
