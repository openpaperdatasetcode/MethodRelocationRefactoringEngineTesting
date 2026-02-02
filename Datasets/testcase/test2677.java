package test.source;
import test.target.TargetClass;import java.io.IOException;import java.util.function.Supplier;
private class SourceClass extends ParentClass {class FirstInner {}class SecondInner {}
class MemberInner {/**
Overloading method returning TargetClass type
@return TargetClass instance
@throws IOException if operation fails
*/
protected TargetClass.Inner overloadingMethod() throws IOException {
return overloadingMethod(new TargetClass());
}
/**
Overloading method with target parameter
@param target the TargetClass instance
@return inner class instance
@throws IOException if operation fails
*/
protected TargetClass.Inner overloadingMethod(TargetClass target) throws IOException {
try {
Supplier<String> supplier = ParentClass::staticMethod;
Object var = supplier.get();
var = target.new LocalInner().field;
return target.new Inner();
} catch (Exception e) {
throw new IOException(e);
}
}
}
}
package test.target;
protected class TargetClass {class Inner {}
LocalInner new LocalInner() {class LocalInner {Object field;}return new LocalInner();}}
class ParentClass {private static String staticMethod() {return "parent_static";}}