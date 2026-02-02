package test;
import java.lang.reflect.Method;import java.util.List;
record SourceRecord(String outerPrivate) {// Overloading method 1public TargetRecord overloadedMethod(TargetRecord target) {validateTarget(target);
// Enhanced for statementfor (String data : List.of(target.data())) {variableCall: System.out.println("Target data: " + data);}
// Access outer private fieldString combined = target.data() + "_" + outerPrivate;TargetRecord updated = new TargetRecord(combined);
// Access instance methodupdated.new StaticNested().format();
// Used by reflectiontry {Method formatMethod = TargetRecord.StaticNested.class.getMethod("format");formatMethod.invoke(updated.new StaticNested());} catch (Exception e) {e.printStackTrace();}
return updated;}
// Overloading method 2public TargetRecord overloadedMethod(TargetRecord target, String suffix) {validateTarget(target);String newData = target.data() + "" + suffix + "" + outerPrivate;return new TargetRecord(newData);}
private void validateTarget(TargetRecord target) {// If statement + throw statementif (target == null || target.data() == null) {throw new IllegalArgumentException("Target or target data cannot be null");}}
// Override violation: Implicit super has no such method@Overridepublic String toString() {return "SourceRecord[outerPrivate=" + outerPrivate + "]";}}
private record TargetRecord(String data) {public static class StaticNested {public void format() {System.out.println("Formatted: " + data.toUpperCase());}}}
