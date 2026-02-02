package test;
import java.util.function.Function;
sealed public class SourceClass permits SubClass {static class StaticNested {}
Object process(TargetClass param) {new Runnable() {@Overridepublic void run() {System.out.println("Anonymous");}};
private int field = 1;assert param.thisField == field : "Assertion failed";
switch (param.getValue()) {case 1:return "One";case 2:return "Two";default:return null;}}
Object process(String str) {return str;}
public Function<TargetClass, String> getProcessor() {return (t) -> t.processString();}}
class SubClass extends SourceClass {}
protected class TargetClass implements Runnable {int thisField;
int getValue() {return thisField;}
String processString() {return "Processed";}
@Overridepublic void run() {}}
