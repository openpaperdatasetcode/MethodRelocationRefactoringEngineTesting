package test;
import java.util.ArrayList;import java.util.function.Function;
abstract class TargetClass {private String targetField;
public void methodWithLocalInner() {class TargetLocalInner {void updateTargetField(String value) {TargetClass.this.targetField = value;}
String getTargetField() {return TargetClass.this.targetField;}}}
public abstract void abstractMethod();}
class OtherClass {public TargetClass instanceMethod(TargetClass target, String... args) {target.methodWithLocalInner();return target;}}
protected class SourceClass<T extends TargetClass> {class SourceMemberInner1 {}class SourceMemberInner2 {}
public Object varargsMethod(T target, T... varargs) {ArrayList rawList = new ArrayList();OtherClass other = new OtherClass();Function<TargetClass, TargetClass> methodRef = OtherClass::instanceMethod;
synchronized (target) {for (int i = 0; i < varargs.length; i++) {rawList.add(varargs[i]);variableCall(varargs[i]);}}
int count = 0;do {TargetClass processedTarget = methodRef.apply(target);rawList.add(processedTarget);count++;} while (count < 2);
return rawList;}
private void variableCall(T target) {target.abstractMethod() {@Overridepublic void abstractMethod() {}};}}