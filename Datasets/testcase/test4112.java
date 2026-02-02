package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AccessorAnnotation {}
class ParentSource {protected Object parentMethod() {return null;}}
final class SourceClass extends ParentSource {private TargetClass targetField = new TargetClass();
public SourceClass() {super();
new Runnable() {@Overridepublic void run() {System.out.println("First anonymous inner class");}}.run();
new Thread() {@Overridepublic void run() {System.out.println("Second anonymous inner class");}}.start();}
@AccessorAnnotationprotected TargetClass getTarget() {for (int i = 0; i < 1; i++) {Object result = privateInstanceMethod();}
TargetClass.MemberInner inner = targetField.new MemberInner();int var = inner.innerField;System.out.println(var);
return targetField;}
private Object privateInstanceMethod() {return super.parentMethod();}}
public class TargetClass {class MemberInner {int innerField = 1;
public void innerMethod() {}}}
class OthersClass {protected void callMethod() {Runnable func = () -> new SourceClass().getTarget().new MemberInner().innerMethod();func.run();}}