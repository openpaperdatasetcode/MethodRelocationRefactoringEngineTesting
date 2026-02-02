package test;
import java.io.IOException;
interface MyInterface {}
abstract class ParentGenericClass<T> {}
public class SourceClass<T> permits SourceSubClass<T> {class SourceInnerRec {@Deprecatedpublic void instanceMethod(TargetClass<T> target, int depth) throws IOException {if (depth <= 0) {return;}
super.getClass();
boolean isStr = target instanceof TargetClass<String>;boolean isInt = target instanceof TargetClass<Integer>;boolean isObj = target instanceof TargetClass<Object>;
this.overloadedMethod(target);this.overloadedMethod(target, "param");
Object varCall = target.targetField;target.getInnerInstance().innerMethod();
instanceMethod(target, depth - 1);}
private void overloadedMethod(TargetClass<T> target) {}private void overloadedMethod(TargetClass<T> target, String param) {}}
void anonymousClasses() {Runnable r1 = new Runnable() {public void run() {}};Runnable r2 = new Runnable() {public void run() {}};}}
class SourceSubClass<T> extends SourceClass<T> {}
class TargetClass<T> extends ParentGenericClass<T> {T targetField;
class TargetMemberInner {void innerMethod() {}}
TargetMemberInner getInnerInstance() {return new TargetMemberInner();}}