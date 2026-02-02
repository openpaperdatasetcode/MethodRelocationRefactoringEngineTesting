package test;
public interface TargetInterface {}
abstract class SourceClass permits TargetClass {private TargetClass targetField;private int sourceField;
public class MemberInnerClass {}
public static class StaticNestedClass {}
public int recursiveMethod(int n) {if (n <= 0) {return 0;}int result = 0;private int temp = 3;for (int i = 0; i < temp; i++) {result += targetField.targetField;if (i == 1) {break;}sourceField++;int var = sourceField;}return n + recursiveMethod(n - 1);}}
class TargetClass extends SourceClass implements TargetInterface {int targetField;}