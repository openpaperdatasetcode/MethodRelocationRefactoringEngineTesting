package test;
private class SourceClass extends ParentClass {private TargetClass targetParam;
public static class StaticNested1 {}public static class StaticNested2 {}
protected int moveMethod(TargetClass target) {this.targetParam = target;super();
int result = 0;switch (target.getCode()) {case 1:result = target.staticNested.process("case1");break;case 2:result = target.staticNested.process("case2");break;default:result = 0;}
result += this.instanceMethod();return result;}
private int instanceMethod() {return targetParam.getValue() * 2;}}
private class TargetClass {private int code;private int value;
public static class StaticNested {public static int process(String input) {return input.length();}}
public int getCode() {return code;}
public int getValue() {return value;}}
class ParentClass {public ParentClass() {}}
