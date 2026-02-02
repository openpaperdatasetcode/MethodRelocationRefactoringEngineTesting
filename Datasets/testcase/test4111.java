package test;
import java.lang.reflect.Method;
interface MyInterface {}
class SourceClass implements MyInterface {private String outerPrivateField = "sourcePrivateVal";private TargetClass targetField = new TargetClass();
class MemberInner {static TargetClass callInnerMethod(TargetClass target) {return target;}}
public <T extends TargetClass> int genericMethod(T target) {super.toString();
try {Method method = SourceClass.class.getMethod("genericMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {TargetClass result = MemberInner.callInnerMethod(target);System.out.println(result);}
class LocalInner {String getOuterPrivate() {return outerPrivateField;}}String varCall = new LocalInner().getOuterPrivate();
private void checkNull() {if (target.superField == null) {throw new NullPointerException("super.field is null");}}checkNull();
return varCall.length();}}
protected class TargetClass extends ParentClass {public TargetClass() {super();}
public void createLocal() {class TargetLocalInner {void printField() {System.out.println(superField);}}new TargetLocalInner().printField();}}
class ParentClass {String superField = "parentSuperField";}