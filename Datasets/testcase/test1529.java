package source;
import target.Target;import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface RecursiveAnnotation {String value() default "";}
class ParentClass {protected static final String STATIC_FIELD = "parent_static";
protected List<String> recursiveMethod(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}result.add("parent_depth_" + depth);result.addAll(recursiveMethod(depth - 1)); // Recursionreturn result;}}
private class Source<T, U> extends ParentClass {private String outerPrivate = "source_private";
class FirstInner {// Member inner class feature}
class SecondInner {// Second member inner class feature}
/**
Processes the target instance and returns a list of strings.
Includes recursion, variable declarations, and various control structures.
@param target the target instance to process
@return list of processed strings*/@RecursiveAnnotation(value = "recursion_in_annotation")public List<String> process(Target<T> target) {List<String> result = new ArrayList<>();
// VariableDeclarationStatement with 1 target this.fieldprivate String targetFieldValue = (String) target.data; // Access target's this.field
// Super keywordsresult.addAll(super.recursiveMethod(2));
// Switch statementswitch (target.getStatus()) {case "ACTIVE":result.add("active");break;case "INACTIVE":result.add("inactive");break;default:result.add("unknown");}
// Continue statementfor (int i = 0; i < 5; i++) {if (i % 2 == 0) {continue;}result.add("odd_" + i);}
// Variable callTarget<T>.Inner targetInner = target.new Inner();result.add(targetInner.getInnerData());
// Access outer privateresult.add(outerPrivate);
// Depends on static fieldresult.add(STATIC_FIELD);
// Recursion (2 instances) from parent class with outer instance inner creationresult.addAll(outerRecursion(target, 2));
return result;}
// Recursive method using parent class recursionprotected List<String> outerRecursion(Target<T> target, int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}// outerInstance.new InnerClass().methodName()result.add(this.new FirstInner().toString() + "depth" + depth);result.addAll(outerRecursion(target, depth - 1)); // Recursionreturn result;}}
package target;
public class Target<T> {public T data;private String status;
public Target(T data, String status) {this.data = data;this.status = status;}
public String getStatus() {return status;}
class Inner {private String innerData = "inner_data";
public String getInnerData() {return innerData;}}}