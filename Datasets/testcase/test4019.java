package test;
public class TargetClass<T> {private T targetField;
class TargetInner {T innerField;
TargetInner(T val) {this.innerField = val;}
void printInner() {System.out.println(innerField);}}
public TargetClass(T val) {this.targetField = val;}
public TargetInner newInner(T val) {return new TargetInner(val);}}
private class SourceClass<T extends Number> extends ParentClass {class SourceInner1 {void helper(T param) {System.out.println("Helper: " + param);}}
class SourceInner2 {
void varargsRecursiveMethod (int depth, TargetClass<T>.TargetInner... targets) {if (depth <= 0 || targets == null || targets.length == 0) {return;}
SourceInner1 inner1 = new SourceInner1();super.parentMethod();
for (TargetClass<T>.TargetInner target : targets) {TargetClass<T> targetObj = new TargetClass<>( (T) Integer.valueOf(10) );TargetClass<T>.TargetInner newInner = targetObj.newInner( (T) Integer.valueOf(depth) );
inner1.helper( (T) Integer.valueOf(depth) );newInner.printInner();target.printInner();}
varargsRecursiveMethod(depth - 1, targets);}}}
class ParentClass {protected void parentMethod() {}}