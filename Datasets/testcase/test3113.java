package test;
import java.util.List;import java.util.ArrayList;
class ParentClass<T> {}
private class TargetClass<T> {T targetField;class TargetInner {record TargetInnerRec(T value) {}}
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
protected class SourceClass<T> extends ParentClass<T> {private class InnerClass {List<String> innerMethod() {return new ArrayList<>();}}
protected TargetClass<T> methodToMove(TargetClass<T>.TargetInner.TargetInnerRec param) {TargetClass<T> target = new TargetClass<>();
// Empty statement;
// Variable callT var = param.value();target.targetField = var;
// While statementint count = 0;while (count < 3) {// this.methodName(arguments)this.helperMethod(count);count++;}
// Call_method in if/else conditionsif (var != null) {List<String> result = SourceClass.this.new InnerClass().innerMethod();} else {List<String> result = SourceClass.this.new InnerClass().innerMethod();}
return target;}
private void helperMethod(int num) {}}