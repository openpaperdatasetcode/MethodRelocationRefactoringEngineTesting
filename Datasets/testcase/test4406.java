package test;
import java.lang.reflect.Constructor;import java.util.function.Function;
sealed class ParentGenericClass<T> permits SourceClass {private String parentField;
private String parentMethod(int num) {return "Parent: " + num;}
private String parentMethod(String str) {return "Parent: " + str;}}
protected class SourceClass<T> extends ParentGenericClass<T> {static class StaticNestedSource {}
class MemberInnerSource {}
private SourceClass(TargetClass<T> target) {;int targetField = target.field;target.staticNestedTarget.doAction();
Function<T, Integer> func = t -> target.process(t);int result = func.apply(target.field);
try {Constructor<?> constructor = SourceClass.class.getConstructor(TargetClass.class);constructor.newInstance(target);} catch (Exception e) {}
ParentGenericClass<?> parent = this;String parentCallResult = parent.parentMethod(1).concat(" ").concat(parent.parentMethod("test"));}
public SourceClass(int value, TargetClass<T> target) {this(target);target.field = (T) Integer.valueOf(value);}}
/**
Javadoc for TargetClass
Generic class with static nested class*/class TargetClass<T> {T field;
static class StaticNestedTarget {void doAction() {}}
int process(T t) {return Integer.parseInt(t.toString());}}