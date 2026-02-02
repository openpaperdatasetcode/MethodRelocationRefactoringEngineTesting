package test;
import java.lang.reflect.Method;import java.util.List;
class ParentClass<T> {}
/**
Target class Javadoc
*/
abstract class TargetClass<T extends List<?>> {
class TargetInner {}
}
private class SourceClass<T extends Number> extends ParentClass<T> {class FirstInner {}
class SourceInner {/**
Method Javadoc*/protected void instanceMethod(TargetClass<?> target) throws Exception {TargetClass.TargetInner inner = new TargetClass<>().new TargetInner();variableCall = target.toString();
Method method = SourceInner.class.getMethod("instanceMethod", TargetClass.class);method.invoke(this, target);
SourceClass.this.someMethod();}
int variableCall;}
void someMethod() {}}