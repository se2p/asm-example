package de.uni_passau.fim.se2.sa_examples.agent;

import java.lang.instrument.Instrumentation;

public class SimpleAgent {
  public static void premain(final String pAgentArgs, final Instrumentation pInstrumentation) {
    final SimpleClassTransformer transformer = new SimpleClassTransformer();
    pInstrumentation.addTransformer(transformer);
  }
}
