package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
abstract class AbstractParentClass {protected void parentMethod() {}}
abstract class AbstractTargetClass {static class TargetStaticNested {void nestedMethod() {}}}
class SubClass extends AbstractTargetClass {static Object subClassMethod(String arg) {return arg;}}
public class SourceClass extends AbstractParentClass {static class SourceStaticNested {}
class MemberInner extends AbstractParentClass {@Override@MethodAnnotpublic void parentMethod() {super();AbstractTargetClass target = new AbstractTargetClass() {};SourceStaticNested sourceNested = new SourceStaticNested();
try {throw new Exception(() -> super.parentMethod());} catch (Exception e) {}
for (int i = 0; i < 3; i++) {if (i == 1) continue;
variableCall(target.new TargetStaticNested());
Object ref1 = SourceClass::new;Object ref2 = SubClass::subClassMethod;
if (i == 0) {SubClass.subClassMethod("arg1");} else {SubClass.subClassMethod("arg2");}}}
private void variableCall(AbstractTargetClass.TargetStaticNested nested) {nested.nestedMethod();}}
public final AbstractTargetClass instanceMethod(AbstractTargetClass target) {MemberInner inner = new MemberInner();inner.parentMethod();return target;}}