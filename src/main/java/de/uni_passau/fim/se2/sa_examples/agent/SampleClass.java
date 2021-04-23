package de.uni_passau.fim.se2.sa_examples.agent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SampleClass {

  public static void main(String[] args) throws IOException {
    fetch("http://www.google.com");
    fetch("http://www.yahoo.com");
  }

  private static void fetch(final String pAddress) throws IOException {
    final URL url = new URL(pAddress);
    final URLConnection connection = url.openConnection();

    try (final BufferedReader in =
        new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
      String inputLine = null;
      final StringBuffer sb = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        sb.append(inputLine);
      }

      System.out.println("Content size: " + sb.length());
    }
  }
}
