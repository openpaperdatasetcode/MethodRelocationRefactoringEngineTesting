package test;
import java.lang.reflect.Method;import java.util.List;
non-sealed enum SourceEnum<T extends CharSequence & Comparable<T>> {INSTANCE;
private String outerPrivateField = "privateField";
class SourceInner {synchronized Object innerInstanceMethod() {return SourceEnum.this.outerPrivateField;}}
static {// Static code blocks: call OuterClass.InnerClass.methodName()Object result = INSTANCE.new SourceInner().innerInstanceMethod();}
// Overload exists (overload_exist feature)default void methodToMove(TargetEnum target) {}
default void methodToMove(TargetEnum... targets) {
// SuperFieldAccess (numbers:1, modifier:protected)
protected String superFieldVal = super.name();
// Variable call + access target's field (per_condition)for (TargetEnum target : targets) {target.toString();String targetField = target.targetField;if (targetField == null) return; // return statement}
// Access outer private fieldString privateVal = this.outerPrivateField;
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", TargetEnum[].class);method.invoke(this, (Object) targets);} catch (Exception e) {}}
void createLocalInner() {// Local inner class (source_feature)class LocalInner {}}}
final enum TargetEnum {INSTANCE;
// Target field (source contains this field: per_condition)public String targetField = "targetFieldValue";}
class CallerClass {private void callMethod() {// Object initialization: inner_class call with constructor + super.methodName(arguments)SourceEnum.SourceInner inner = SourceEnum.INSTANCE.new SourceInner() {{super.toString();}};}}