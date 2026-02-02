package test;
import java.lang.reflect.Method;import java.util.regex.Pattern;import java.util.function.Function;
abstract class SourceClass<T> {class FirstInner {}class SecondInner {}
/**
Processes variable arguments of TargetClass.InnerRec
@param innerRecs varargs of TargetClass.InnerRec to process*/void methodToMove(TargetClass<String>.InnerRec... innerRecs) {// Constructor invocationTargetClass<String> target = new TargetClass<>();TargetClass.StaticNested nested = new TargetClass.StaticNested();
// this(arguments) - constructor chaining in inner classclass ChainedInner {ChainedInner() {this(0);}ChainedInner(int i) {}}
// 1 public Pattern expressionPattern pattern = Pattern.compile("publicPattern");
// Overriding method in Lambda expressionsFunction<TargetClass<String>.InnerRec, TargetClass<String>> func = rec -> this.protectedOverrideMethod(rec);
// Used by reflectiontry {Method method = SourceClass.class.getMethod("methodToMove", innerRecs.getClass());method.invoke(this, (Object) innerRecs);} catch (Exception e) {}
// Variable call and access instance methodfor (TargetClass<String>.InnerRec rec : innerRecs) {nested.process(rec.getValue());func.apply(rec);}}
// Overriding method (assumes parent class has this method)protected TargetClass<String> protectedOverrideMethod(TargetClass<String>.InnerRec rec) {return new TargetClass<>();}}
public class TargetClass<V> {static class StaticNested {void process(V value) {}}
record InnerRec(V value) {}}