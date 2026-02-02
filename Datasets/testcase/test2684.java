package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass {// Member inner classes (source_feature)class SourceInner1 {}class SourceInner2 {class SourceInnerRec {// Overloading method 1private <T extends Number & Comparable<T>> int methodToMove(TargetClass<T> target) {super();
// Type declaration statementTargetClass.StaticNested typeDecl;// Variable call + contains target parameter (per_condition)target.toString();typeDecl = new TargetClass.StaticNested();
// Access instance fieldT targetField = target.instanceField;
return targetField.intValue();}
// Overloading method 2private <T extends Number & Comparable<T>> int methodToMove(TargetClass<T> target, String arg) {return target.instanceField.intValue() + arg.length();}}}}
protected class TargetClass<U extends Number & Comparable> extends ParentTargetClass implements TargetInterface {
public U instanceField; // Source contains target's field (per_condition)
// Static nested class (target_feature)public static class StaticNested {}}
class ParentTargetClass {}interface TargetInterface {}
final class OthersClass {public static <V extends Number & Comparable<V>> List<String> callMethod(TargetClass<V> target) {// Static method call in expression: instanceReference.methodName(arguments)SourceClass source = new SourceClass();int val = source.new SourceInner2().new SourceInnerRec().methodToMove(target);
List<String> result = new ArrayList<>();result.add(String.valueOf(val));return result;}}