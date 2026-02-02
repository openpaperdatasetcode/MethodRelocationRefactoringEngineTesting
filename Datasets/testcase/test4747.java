package test.source;
import java.lang.reflect.Method;import test.target.TargetClass;
class SourceClass {static class StaticNested {}
Object anonymous = new Object() {};
public Object moveMethod(TargetClass.Inner targetInner) throws Exception {class LocalType {}LocalType lt = new LocalType();
TargetClass.StaticNested staticNested = new TargetClass.StaticNested();staticNested.superField = super.hashCode();
variableCall(targetInner);Object outerThis = SourceClass.this;
Method method = TargetClass.Inner.class.getMethod("method");method.invoke(targetInner);
return outerThis;}
private void variableCall(TargetClass.Inner inner) {}}
package test.target;
class ParentClass {int superField;}
public class TargetClass extends ParentClass {static class StaticNested {}
class Inner {void method() {}}}