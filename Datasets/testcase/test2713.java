package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
strictfp class SourceClass<T extends CharSequence & Comparable<T>> { // with_bounds// Member inner class (source_feature)public class SourceMemberInner {public void processTarget(TargetClass target) {target.toString();}}
strictfp public void methodToMove(TargetClass... targets) throws IOException { // requires_throwssuper(); // Super constructor invocationSourceMemberInner memberInner = new SourceMemberInner();
// BooleanLiteral (numbers:1, modifier:default)default boolean isProcessed = false;
for (TargetClass target : targets) {// Variable call + contains target parameter (per_condition)target.toString();memberInner.processTarget(target);
// Synchronized statementsynchronized (TargetClass.StaticNested.class) {// Depends on static fieldtarget.setData(TargetClass.StaticNested.STATIC_FIELD + target.getField());
// Empty statement;
// Requires_throws: throw conditionif (target.getField() == null) {throw new IOException("Target field is null");}
// Local inner class (source_feature)class SourceLocalInner {public void validate() {isProcessed = true;}}new SourceLocalInner().validate();}}}}
class SubSourceClass extends SourceClass<String> {@Overridefinal public Object callMethod(List<TargetClass> targetList) throws IOException { // final modifier// Normal + OuterClass.InnerClass.methodName() in collection operationstargetList.forEach(target -> {try {new SourceClass<String>().methodToMove(target);} catch (IOException e) {throw new RuntimeException(e);}});return targetList;}}
public class TargetClass {private String field = "targetData";
// Static nested class (target_feature)public static class StaticNested {public static final String STATIC_FIELD = "STATIC_";}
public String getField() {return field;}
public void setData(String data) {this.field = data;}}