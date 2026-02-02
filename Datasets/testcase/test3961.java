package test;
public record SourceClass(int sourceField, TargetClass targetField) {public void createAnonymous1() {Runnable r1 = new Runnable() {public void run() {}};}
public void createAnonymous2() {Runnable r2 = new Runnable() {public void run() {}};}
public class InnerClass {protected int recursiveMethod(int n) {if (n < 0) {throw new IllegalArgumentException();}if (n == 0) {return 1;}int sum = 0;for (int num : new int[]{n - 1}) {sum += recursiveMethod(num);}sum += sourceField;sum += targetField.value();return sum;}
public Object callMethod() {return super.toString();}
public Object callMethod(int param) {return new Object();}
Object[] initArray() {return new Object[]{callMethod(), callMethod(1)};}}}
public record TargetClass(int value) {}