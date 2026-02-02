package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
interface TargetInterface {}
public abstract class SourceClass {class SourceInner {class SourceInnerRec {/**
Method Javadoc for generic varargs method to test Move Method refactoring
@param targets Varargs of TargetClass instances
@param <T> Generic type parameter
@return Combined result of target operations*/public <T extends CharSequence> Object methodToMove(TargetClass<T>... targets) {super();
List<Object> results = new ArrayList<>();for (TargetClass<T> target : targets) {// Constructor invocation (target's static nested class)TargetClass.StaticNested nested = new TargetClass.StaticNested();
// Variable call + contains target parameter (per_condition)target.toString();T targetField = target.targetField;
// Depends on inner classtarget.getInner().innerMethod();
results.add(targetField);}
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", TargetClass[].class);method.invoke(this, (Object) targets);} catch (Exception e) {}
return results;}}}
{// Anonymous inner class (source_feature)new Runnable() {};}}
private abstract class TargetClass implements TargetInterface {
public U targetField; // Source contains target's field (per_condition)
// Static nested class (target_feature)public static class StaticNested {}
class TargetInner {public void innerMethod() {}}
public TargetInner getInner() {return new TargetInner();}}