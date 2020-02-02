package com.github.ruediste.lambdaInspector;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class LambdaPrinter {

  public static void printSourceCodeLocation(Lambda inspect) {
    System.out.println(inspect);
    System.out.println(inspect.static_.implementationMethod);
    Method method = inspect.static_.implementationMethod;
    try {
      runAssist(method.getDeclaringClass().getName(), method.getName());
    } catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private static void runAssist(String className, String methodName) throws NotFoundException {
    ClassPool pool = ClassPool.getDefault();
    CtClass cc = pool.get(className);
    CtMethod methodX = cc.getDeclaredMethod(methodName);

    int xlineNumber = methodX.getMethodInfo().getLineNumber(0);
    System.out.println("java class is " + className);
    System.out.println("method " + methodName + " is on line " + xlineNumber + "\n");
  }

}
