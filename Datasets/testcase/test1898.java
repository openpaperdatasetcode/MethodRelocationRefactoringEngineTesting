package test;
import java.util.ArrayList;import java.util.List;
class SourceClass<T> {// First static nested classpublic static class StaticNested1 {
public U value;
}
// Second static nested classpublic static class StaticNested2 {public static String STATIC_DATA = "source_static";}
// Record-like inner structure (source_inner_rec position)public record ProcessRecord(TargetClass target) {
/**
Overriding method to process target inner class
Handles null checks and static field dependencies
*/
@Override
public void finalize() {
processInner(target.new Inner<>());
}
/**
Processes target's inner class with required features
@param inner Target's static nested class instance*/private final void processInner(TargetClass.Inner<T> inner) {// Type declaration statementList<T> dataList = new ArrayList<>();StaticNested1<T> nested1 = new StaticNested1<>();
try {// Variable calldataList.add(inner.getValue());nested1.value = inner.getValue();
// Depends on static fieldinner.setSuffix(StaticNested2.STATIC_DATA + "_" + TargetClass.StaticNested.STATIC_FIELD);
// Potential NullPointerExceptionif (inner.getSuffix() == null) {throw new NullPointerException("Suffix cannot be null");}} catch (NullPointerException e) {// No new exceptionSystem.err.println("Null handling: " + e.getMessage());}}}
public void handleTarget(TargetClass<T> target) {new ProcessRecord<>(target);}}
strictfp class TargetClass<T> {// Static nested class (target feature)public static class StaticNested {public static int STATIC_FIELD = 100;}
// Inner class used as target_innerpublic class Inner {
private U value;
private String suffix;
public Inner(U value) {this.value = value;}
public U getValue() {return value;}
public String getSuffix() {return suffix;}
public void setSuffix(String suffix) {this.suffix = suffix;}}}
