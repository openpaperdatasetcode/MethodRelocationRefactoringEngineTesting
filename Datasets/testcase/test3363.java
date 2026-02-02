package test;
protected class SourceClass {public static class StaticNested {}
{Runnable r = new Runnable() {public void run() {}};}
public int varargsMethod(TargetClass... targets) {super();int[] arr = new int[1];for (int i = 0; i < 3; i++) {TargetClass target = targets[i].m1().m2().m3();target.variableCall();}return 0;}}
public class TargetClass {public static class TargetInner<T extends Number> {}
public TargetClass m1() {return this;}
public TargetClass m2() {return this;}
public TargetClass m3() {return this;}
public void variableCall() {}}