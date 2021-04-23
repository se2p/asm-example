package de.uni_passau.fim.se2.sa_examples.agent;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

public class SimpleClassTransformer implements ClassFileTransformer {

  @Override
  public byte[] transform(
      ClassLoader loader,
      String className,
      Class<?> classBeingRedefined,
      ProtectionDomain protectionDomain,
      byte[] classfileBuffer)
      throws IllegalClassFormatException {
    if (className.endsWith("sun/net/www/protocol/http/HttpURLConnection")) {
      final ClassPool classPool = ClassPool.getDefault();
      try {
        final CtClass clazz = classPool.get("sun.net.www.protocol.http.HttpURLConnection");
        for (final CtConstructor constructor : clazz.getConstructors()) {
          constructor.insertAfter("System.out.println(this.getURL());");
        }
        byte[] byteCode = clazz.toBytecode();
        clazz.detach();
        return byteCode;
      } catch (NotFoundException | CannotCompileException | IOException pE) {
        pE.printStackTrace();
      }
    }
    return null;
  }
}

// java -javaagent:build/libs/foo.jar=foobarbaz
