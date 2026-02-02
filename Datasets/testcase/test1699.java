package source;
import target.TargetClass;import java.lang.annotation.*;import java.util.function.IntSupplier;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
private class SourceClass<T> {class MemberInner {class InnerRec {@MyAnnotationpublic int normalMethod(TargetClass<Integer>.StaticNested.InnerRec param) {if (param == null) {throw new NullPointerException();}
// SuperFieldAccess expressionint superField = param.superField;variableCall();
// Overloaded methods existoverloadedMethod();overloadedMethod(1);
// Lambda in functional interfaceIntSupplier supplier = () -> SourceClass.this.protectedMethod();
return superField;}
private void variableCall() {}
private void overloadedMethod() {}private void overloadedMethod(int num) {}}}
protected int protectedMethod() {return 0;}}
package target;
class TargetClass extends ParentClass {
static class StaticNested {
class InnerRec {}
}
}
package target;
class ParentClass {protected int superField;}