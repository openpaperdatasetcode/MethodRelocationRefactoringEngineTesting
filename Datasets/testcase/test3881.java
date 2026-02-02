 
package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnnotation {}
abstract class SourceAbstractClass {// Two anonymous inner classes (source class feature){new Runnable() {@Overridepublic void run() {}};new Comparable<String>() {@Overridepublic int compareTo(String o) {return 0;}};}
@RefactorTestAnnotation // has_annotation (method feature)private List<String> recursiveMethod(TargetAbstractClass targetParam, int depth) {List<String> result = new ArrayList<>();// Base case for recursionif (depth <= 0) {return result;}
// Expression statement (method feature)String targetData = targetParam.getTargetData();result.add(targetData);
// Variable call (method feature)helperMethod();
// Recursion (method type & feature)result.addAll(recursiveMethod(targetParam, depth - 1));
return result;}
private void helperMethod() {}}
// Target abstract class (implements interface, target feature)public abstract class TargetAbstractClass implements DataProvider {public abstract String getTargetData();}
// Interface for target class to implementinterface DataProvider {}
// Interface for override violation (method feature)interface OverrideCheck {// Different return type from source method -> override violationList<Integer> recursiveMethod(TargetAbstractClass targetParam, int depth);}