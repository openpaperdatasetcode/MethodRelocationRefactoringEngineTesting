package test;
interface SomeInterface {}
public abstract class SourceClass implements SomeInterface {private String outerPrivateField = "privateData";
public class MemberInner {public static <T> TargetClass<T> staticMethod(TargetClass<T> target) {new Runnable() {public void run() {System.out.println(SourceClass.this.outerPrivateField);}}.run();
private switch (target.thisField) {case "val1":variableCall(target.staticNested);break;case "val2":continue;default:System.out.println(target.thisField);}
for (int i = 0; i < 3; i++) {if (i == 1) continue;variableCall(target.staticNested);}
return target;}
private static <T> void variableCall(TargetClass<T>.StaticNested nested) {nested.nestedMethod();}}}
public abstract class TargetClass<T> {T thisField;
static class StaticNested {void nestedMethod() {}}
StaticNested staticNested = new StaticNested();}