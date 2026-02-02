package test;
import java.util.List;import java.util.ArrayList;import java.util.Collection;import otherpkg.OtherPackageClass;
sealed class SourceClass permits SourceSubClass {protected String outerProtectedField = "protected-data"; // For access_outer_protectedprivate TargetClass targetField = new TargetClass(); // Per condition
// Two anonymous inner classes (source feature){Runnable anon1 = () -> System.out.println("Anonymous 1");Runnable anon2 = () -> System.out.println("Anonymous 2");}
public class SourceInner {// Source inner recursive class (method_position: source_inner_rec)public class SourceInnerRec<T extends CharSequence> { // with_bounds// Abstract method (default access modifier, returns TargetClass Type)public abstract TargetClass abstractMethod();
// Final instance method feature (2, inner_class, instance, new ClassName().method(), pos: collection operations)public final List<String> instanceFeatureMethod(Collection<String> coll) {List<String> result = new ArrayList<>();coll.forEach(item -> result.add(new SourceInnerRec<>().processItem(item)));return result;}
private String processItem(String item) {return item.toUpperCase();}
// Concrete implementation helperpublic TargetClass implementAbstract() {// EnhancedForStatement (public, target_feature: obj.field x1, pos: diff_package_others)OtherPackageClass other = new OtherPackageClass();public List<TargetClass> targetList = List.of(targetField);for (TargetClass target : targetList) {System.out.println(other.field + target.targetField);}
// Do statementint count = 0;do {// Expression statement + variable calltargetField.targetMethod();count++;} while (count < 2);
// Access outer protected fieldString protectedVal = SourceClass.this.outerProtectedField;
// Depends on inner class: target's anonymous inner classtargetField.createAnonymousInner().action();
return targetField;}}}}
// Permitted subclass for sealed SourceClassnon-sealed class SourceSubClass extends SourceClass {}
// Target class (private modifier, anonymous inner class)private class TargetClass {public int targetField = 10; // Field for per_condition
public void targetMethod() {}
// Anonymous inner class (target_feature)public TargetInner createAnonymousInner() {return new TargetInner() {@Overridepublic void action() {}};}
public interface TargetInner {void action();}}
// Diff package class (for EnhancedForStatement pos: diff_package_others)package otherpkg;
public class OtherPackageClass {public String field = "other-";}