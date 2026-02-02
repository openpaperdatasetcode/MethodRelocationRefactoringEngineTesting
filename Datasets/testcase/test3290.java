package sourcepkg;
import targetpkg.TargetClass;import otherpkg.OtherDiffPackageClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.ArrayList;import java.util.function.Function;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
sealed abstract class SourceClass permits SourceSubClass {private String outerPrivateField = "privateData";
class SourceMemberInner {/**
Javadoc for final instance method
@param targetInnerParam target inner class parameter
@return list of strings*/@MethodAnnofinal List<String> instanceMethod(TargetClass.TargetInner targetInnerParam) {class SourceLocalInner {}new SourceLocalInner();
new Runnable() {@Overridepublic void run() {targetInnerParam.doAction();}}.run();
OtherDiffPackageClass.privateConstructorInvocation(targetInnerParam);
List<String> result = new ArrayList<>();do {for (int i = 0; i < 3; i++) {if (i == 1) continue;result.addAll(publicRecursionMethod(targetInnerParam, 3));}; // Empty statement} while (result.size() < 3);
result.add(outerPrivateField); // Access outer privateresult.add(this.outerPrivateField); // Uses outer thisSystem.out.println(super.toString()); // Super keywords
return result;}
public List<String> publicRecursionMethod(TargetClass.TargetInner target, int depth) {List<String> list = new ArrayList<>();if (depth == 0) {list.add(target.field);return list;}// Object initialization + recursion + super.methodName(arguments)TargetClass.TargetInner newInner = new TargetClass().new TargetInner();list.addAll(superMethodHelper(target, depth - 1));list.addAll(publicRecursionMethod(newInner, depth - 1));return list;}
private List<String> superMethodHelper(TargetClass.TargetInner target, int depth) {return new ArrayList<>(List.of(target.field + depth));}}
protected TargetClass.TargetInner callSourceMethod(TargetClass target) {SourceMemberInner inner = new SourceMemberInner();Function<TargetClass.TargetInner, List<String>> func = inner::instanceMethod;List<String>[] arr = new List[] { func.apply(target.new TargetInner()) };return target.new TargetInner();}}
class SourceSubClass extends SourceClass {}
package targetpkg;
public abstract class TargetClass {class TargetInner {String field = "innerField";void doAction() {}}}
package otherpkg;
import sourcepkg.SourceClass;import targetpkg.TargetClass;
public class OtherDiffPackageClass {private static void privateConstructorInvocation(TargetClass.TargetInner inner) {new TargetClass() {}; // ConstructorInvocation with obj.field (inner.field)assert inner.field != null : "Obj.field must not be null";}}