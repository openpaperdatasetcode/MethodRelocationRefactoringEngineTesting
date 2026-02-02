package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
interface MyInterface {}
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {String value() default "${sourceInnerRec.getList().toString()}";}
non-sealed abstract class SourceClass {static class StaticNested {}
{new Runnable() {};}
class SourceInner {class InnerRec {@MyAnnstrictfp List<String> instanceMethod(TargetClass target) {super();variableCall = target.field;target.instanceMethod();
private void privateExpression() {target.this.field = 1;}privateExpression();
int i = 0;do {synchronized (this) {target.field = i;}i++;} while (i <= 1);
try {Method method = InnerRec.class.getMethod("instanceMethod", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
return getList();}
private List<String> getList() {return new ArrayList<>();}
TargetClass variableCall;}}}
protected abstract class TargetClass implements MyInterface {int field;void instanceMethod() {}}