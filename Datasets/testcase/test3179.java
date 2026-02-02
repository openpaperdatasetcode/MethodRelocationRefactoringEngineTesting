package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public class TargetClass<T> {T targetField;
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
non-sealed class SourceClass<T> {class MemberInner {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
class SourceInner {private void methodToMove(TargetClass<T> target) {// Type declaration statementclass TypeDecl {}TypeDecl type = new TypeDecl();
// Variable call + Expression statementT var = target.targetField;target.targetField = (T) "updated";
// Switch statement + Break statementswitch (var != null ? var.toString().length() : 0) {case 1:int result = this.overloadMethod(target, 1); // 1 as requiredbreak;default:break;}
// Collection operationsList<T> list = new ArrayList<>();list.add(var);
// Depends on inner classMemberInner inner = new MemberInner();
// Used by reflectiontry {Method method = TargetClass.class.getMethod("toString");method.invoke(target);} catch (Exception e) {}}
// Overloading method 1private int overloadMethod(TargetClass<T> target, int num) {return num;}
// Overloading method 2 (method_feature: overloading)private int overloadMethod(TargetClass<T> target, String arg) {return arg.length();}}}