package test.pkg;
import java.util.ArrayList;import java.util.List;
sealed class SourceClass permits SubSourceClass {public <T> TargetClass<T> methodToMove(TargetClass<T> targetParam) {// Local inner classclass LocalInnerClass {public String process(T data) {return "localInner: " + data;}}LocalInnerClass localInner = new LocalInnerClass();
// Anonymous inner classRunnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("anonymous: " + targetParam.getTargetField());}};runnable.run();
if (targetParam != null) {// Abstract method call (1: count, target: owner, abstract: type)List<String> abstractResult = new TargetClass.AbstractNestedClass().abstractMethod("arg");targetParam.getStaticNestedClass().nestedMethod(abstractResult);} else {// CaseDefaultExpression with numbers=3int code = 2;String caseResult = switch (code) {case 1 -> "case1";case 2 -> "case2";default -> "default:" + 3; // numbers=3};targetParam = new TargetClass<>();targetParam.setTargetField((T) caseResult);}
// Variable call (target's static nested class method)TargetClass.StaticNestedClass staticNested = targetParam.getStaticNestedClass();staticNested.nestedMethod(new ArrayList<>());
// Local inner class variable callString localResult = localInner.process(targetParam.getTargetField());targetParam.setTargetField((T) localResult);
return targetParam;}}
final class SubSourceClass extends SourceClass {}
protected class TargetClass<T> {private T targetField;
// Static nested class (target_feature)public static class StaticNestedClass {public void nestedMethod(List<String> data) {}}
// Abstract static nested class for abstract method callpublic static abstract class AbstractNestedClass {public abstract List<String> abstractMethod(String arg);}
public T getTargetField() {return targetField;}
public void setTargetField(T targetField) {this.targetField = targetField;}
public StaticNestedClass getStaticNestedClass() {return new StaticNestedClass();}
// Method will be moved here:// public <T> TargetClass<T> methodToMove(TargetClass<T> targetParam) { ... }}