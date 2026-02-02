package test;
import java.util.List;import java.util.ArrayList;
abstract class TargetGenericClass {
protected U targetField;
class TargetMemberInner {void doAction() {}}}
public class SourceGenericClass<T> {private TargetGenericClass<T> targetField = new TargetGenericClass<T>() {};
public List<String> normalMethod() {new Runnable() {@Overridepublic void run() {targetField.new TargetMemberInner().doAction();}}.run();
new Runnable() {@Overridepublic void run() {privateRecursionMethod(targetField, 3);}}.run();
OtherSamePackageClass.privateDeclareVariable(this, targetField);
class LocalDeclaredType {}LocalDeclaredType localType = new LocalDeclaredType();
List<String> result = new ArrayList<>();result.add(targetField.targetField.toString());return result;}
private Object privateRecursionMethod(TargetGenericClass<T> target, int depth) {if (depth == 0) {return target.new TargetMemberInner().toString();}for (int i = 0; i < depth; i++) {return OtherClass.helperRecursion(target, depth - 1).toString();}return null;}}
class OtherSamePackageClass {private static <T> void privateDeclareVariable(SourceGenericClass<T> source, TargetGenericClass<T> target) {TargetGenericClass<T>.TargetMemberInner var = target.new TargetMemberInner();var.doAction();}}
class OtherClass {static <T> Object helperRecursion(TargetGenericClass<T> target, int depth) {if (depth == 0) {return target.targetField;}return helperRecursion(target, depth - 1).toString().length();}}