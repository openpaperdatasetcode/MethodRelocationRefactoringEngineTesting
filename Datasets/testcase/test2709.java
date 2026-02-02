package test;
import java.util.List;import java.util.ArrayList;import java.util.regex.Pattern;
public class SourceClass extends ParentSourceClass {// Static nested class (source_feature)public static class SourceStaticNested {}
{// Anonymous inner class (source_feature)new Runnable() {@Overridepublic void run() {}};}
public class SourceInner {public class SourceInnerRec {/**
Method Javadoc for overloading methods testing Move Method refactoring
@param target TargetClass instance with nested structure
@return List of processed strings
*/
public List<String> methodToMove(TargetClass target) {
return processTarget(target, null);
}
/**
Overloading method with additional parameter
@param target TargetClass instance
@param suffix String suffix to append
@return List of processed strings
*/
public List<String> methodToMove(TargetClass target, String suffix) {
return processTarget(target, suffix);
}
private List<String> processTarget(TargetClass target, String suffix) {super(); // Super constructor invocationList<String> results = new ArrayList<>();
// Constructor invocation (raw_type)TargetClass.RawInner rawInner = new TargetClass.RawInner();// Variable call + contains target parameter (per_condition)target.toString();TargetClass.Inner.InnerRec rec = target.new Inner().new InnerRec();
// Pattern expression (numbers:1, modifier:default)Pattern pattern = Pattern.compile("\w+");
try {// Access instance method + NullPointerException handlingString targetField = rec.getField();results.add(targetField + (suffix != null ? suffix : ""));// obj.m1().m2().m3() chainresults.add(rec.setValue(targetField).getField().toUpperCase());} catch (NullPointerException e) {// No new exceptionresults.add("default");}
return results;}}}
@Overridepublic List<String> callMethod(TargetClass target) {// Abstract parent method + obj.m1().m2().m3() in array initializationreturn super.callMethod(target);}}
abstract class ParentSourceClass {// Parent class abstract method (target_feature)public abstract List<String> callMethod(TargetClass target);
protected List<String> processParentCall(TargetClass target) {// obj.m1().m2().m3() chain in array initializationSourceClass source = new SourceClass();return source.new SourceInner().new SourceInnerRec().methodToMove(target);}}
protected class TargetClass {public class Inner {public class InnerRec {private String field = "targetInnerField";
public String getField() {return field;}
public InnerRec setValue(String value) {this.field = value;return this;}}}
// Raw type inner classpublic class RawInner {}
{// Anonymous inner class (target_feature)new Runnable() {@Overridepublic void run() {}};}
// Access instance methodpublic String getTargetField() {return new Inner().new InnerRec().getField();}}