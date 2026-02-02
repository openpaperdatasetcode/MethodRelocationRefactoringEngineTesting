package test;
class SourceClass<T extends Number> permits SubSource {class InnerRec {public Object recursiveMethod(TargetClass param, int n) {class LocalType {}LocalType local = new LocalType();
new Runnable() {public void run() {for (int i = 0; i < TargetClass.Nested.staticField; i++) {System.out.println(super.toString());}}};
if (n <= 0) {return param.targetField;}int var = param.targetField;System.out.println(var);return recursiveMethod(param, n - 1);}
public Object recursiveMethod(String s) {return s;}}}
private class TargetClass {int targetField = 1;static class Nested {static int staticField = 1;}}
sealed class SubSource extends SourceClass<Integer> permits {}