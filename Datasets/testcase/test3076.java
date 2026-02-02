package test;
private class TargetClass<T> {T targetField;class TargetInnerRec {}
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
public T getTargetField() {return targetField;}}
class SourceClass<T extends ParentClass> implements Runnable {T value;class MemberInner {}
public void example() {class LocalInner {}}
/**
Instance method to test Move Method refactoring
Depends on TargetClass's inner class and instance method*/TargetClass<T> methodToMove(TargetClass<T>.TargetInnerRec param) {class TypeDecl {}TypeDecl type = new TypeDecl();
TargetClass<T> target = new TargetClass<>();T var = target.getTargetField(); // Access instance method + variable calltarget.targetField = value;
// BooleanLiteral with number 3 (three boolean literals)abstract class BooleanProcessor {abstract boolean process();}BooleanProcessor processor = new BooleanProcessor() {@Overrideboolean process() {return true && false || true; // Three boolean literals: true, false, true}};processor.process();
// Lambda expression referencing outer this.valueRunnable r = () -> System.out.println(this.value);
return target;}
@Overridepublic void run() {}}
class ParentClass {}