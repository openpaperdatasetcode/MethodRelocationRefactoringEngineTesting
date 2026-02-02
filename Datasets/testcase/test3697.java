package test;
import java.util.ArrayList;import java.util.List;
private class SourceClass {/**
Static method for testing Move Method refactoring
Processes target instances and returns field values as list
@param targets Varargs of TargetClass<T>
@return List of string representations of target fields*/public static final <T extends CharSequence> List<String> staticMethod(TargetClass<T>... targets) {List<String> results = new ArrayList<>();TargetClass<T>[] targetArray = (TargetClass<T>[]) new TargetClass[3];
new Runnable() {@Overridepublic void run() {for (int i = 0; i < Math.min(targets.length, 3); i++) {targetArray[i] = new TargetClass<>(targets[i].field);}}}.run();
for (TargetClass<T> target : targetArray) {if (target == null) break;results.add(target.getInner().processField(target.field));; // Empty statement}
Object recursiveResult = new RecursiveHelper<T>().recursiveMethod(targets[0], 1);results.add(recursiveResult.toString());
return results;}
public static class StaticNested {public static <T extends CharSequence> String formatField(T field) {return field.toString().toUpperCase();}}
private static class RecursiveHelper<T extends CharSequence> extends ParentHelper<T> {@OverrideObject recursiveMethod(TargetClass<T> target, int depth) {if (depth <= 0) return target.field;return this.recursiveMethod(new TargetClass<>((T) (target.field + "_rec" + depth)), depth - 1);}}}
class ParentHelper<T extends CharSequence> {protected Object recursiveMethod(TargetClass<T> target, int depth) {return target.field;}}
class TargetClass<T extends CharSequence> {public T field;
public TargetClass(T field) {this.field = field;}
public MemberInner getInner() {return new MemberInner();}
public class MemberInner {public String processField(T field) {return SourceClass.StaticNested.formatField(field);}}}