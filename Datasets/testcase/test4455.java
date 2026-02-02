package test;
public class SourceClass<T> {public class MemberInner {}
{Runnable r = new Runnable() {public void run() {}};}
protected int recursiveMethod(TargetClass<T>.InnerRec targetParam, int count) {super();if (count <= 0) {return 0;}for (int i = 0; i < count; i++) {targetParam.value++;}return count + recursiveMethod(targetParam, count - 1);}
static {InnerCaller::call;}
private static class InnerCaller {private static void call() {}}}
private class TargetClass<T> {public class InnerRec {int value;}}