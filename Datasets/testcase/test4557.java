package test;
import java.util.ArrayList;import java.util.List;
public abstract class SourceClass {protected int outerField = 10;
class MemberInner {class InnerRecursive {/**
Processes target class with recursion and switch statements
@param target Target class instance to process
@param depth Recursion depth
@return Processed TargetClass instance*/TargetClass instanceMethod(TargetClass target, int depth) {type declaration statement:class LocalType {}LocalType local = new LocalType();
switch (depth) {case 0:return target;case 1:variableCall(target);break;default:switch (target.field % 2) {case 0:depth--;break;case 1:depth -= 2;break;}}
try {List<String> callResult = OthersClass.finalStaticMethod(this, target);target.field += callResult.size();} catch (Exception e) {target.field = -1;}
return new TargetClass(super.recursiveMethod(target, depth - 1));}
private void variableCall(TargetClass target) {target.field += SourceClass.this.outerField;target.overrideViolation();}}}
public TargetClass process(TargetClass target) {return new MemberInner.InnerRecursive().instanceMethod(target, 3);}
protected abstract TargetClass recursiveMethod(TargetClass target, int depth);}
private abstract class TargetClass {int field = 5;
TargetClass(TargetClass other) {this.field = other.field;}
void overrideViolation() {}
abstract void localInnerMethod();
void createLocalInner() {class TargetLocalInner {TargetClass process(TargetClass t) {t.field *= 2;return t;}}new TargetLocalInner().process(this);}}
class OthersClass {public static final List<String> finalStaticMethod(SourceClass.MemberInner.InnerRecursive ref, TargetClass target) {List<String> list = new ArrayList<>();list.add(String.valueOf(target.field));return list;}}