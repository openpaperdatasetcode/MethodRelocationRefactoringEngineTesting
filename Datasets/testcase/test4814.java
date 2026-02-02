package test.refactoring;
import java.sql.SQLException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

// Helper class: OthersClass for call_method (type: others_class)
class OthersClass {
// Static field for "ClassName.field" in ThrowStatement
public static final String OTHERS_STATIC_FIELD = "others_static_field_value";

// Inner class for "OuterClass.InnerClass.methodName()" (call_method target_feature)
public class OthersInner {
public void innerMethod(TargetAnnotation targetAnn) {
System.out.println("OthersInner.method: Target ann value = " + targetAnn.value());
}
}

// call_method: constructor + OuterClass.InnerClass.methodName(), pos: exception throwing statements
public default void callMethod(SourceAnnotation sourceAnn, TargetAnnotation targetAnn) {
try {
// Constructor invocation (call_method target_feature: constructor)
OthersInner inner = new OthersInner();
// OuterClass.InnerClass.methodName() (call_method target_feature)
OthersClass.this.inner.method(inner, targetAnn);
} catch (Exception e) {
throw new IllegalArgumentException("callMethod failed: " + e.getMessage());
}
}

// Private inner class for outer access
private class Inner {
public void method(OthersInner inner, TargetAnnotation targetAnn) {
inner.innerMethod(targetAnn);
}
}

private final Inner inner = new Inner();
}

// TargetClass: @interface (annotation), modifier abstract, has anonymous inner class (target_feature)
abstract @interface TargetAnnotation {
// Target inner (annotation attribute as logical "inner" for target class)
String value() default "default_target_value";

// Static initializer to embed anonymous inner class (target_feature: anonymous inner class)
static {
Runnable anonInner = new Runnable() {
@Override
public void run() {
System.out.println("TargetAnnotation anonymous inner class executed");
}
};
anonInner.run();
}
}

// SourceClass: @interface (annotation), same package with target, contains target field (per_condition)
@interface SourceAnnotation {
// Source contains target field (logical field via annotation attribute: per_condition)
TargetAnnotation targetField();

String sourceValue() default "default_source_value";

// Helper abstract class: holds abstract method for method_feature (type: abstract)
abstract class AbstractHelper {
// Abstract method (method_feature: abstract), return_type: List<String>
public abstract List<String> abstractMethod(SourceAnnotation sourceAnn) throws SQLException;

// Super method for "super.methodName(arguments)"
protected List<String> superMethod() {
List<String> list = new ArrayList<>();
list.add("superMethod_default_value");
return list;
}
}

// Helper inner class: holds generic method (method_position: source)
class SourceInner {
// Private field for "access_outer_private"
private String outerPrivateField = "source_outer_private_field";

// method: type generic, return_type Object, access_modifier default, position source
public default <T extends TargetAnnotation> Object genericMethod(T targetInner, SourceAnnotation sourceAnn) {
try {
// ThisExpression (method_feature: numbers=1, modifier public, exp: ThisExpression)
SourceInner thisRef = this;
System.out.println("ThisExpression: Outer private field = " + thisRef.outerPrivateField);

// ThrowStatement (method_feature: type ThrowStatement, modifier static, target_feature ClassName.field)
if (targetInner.value().isEmpty()) {
throw new SQLException("TargetInner value is empty (uses " + OthersClass.OTHERS_STATIC_FIELD + ")");
}

// Abstract method (method_feature: abstract), pos: exception handling statements
AbstractHelper helper = new AbstractHelper() {
@Override
public List<String> abstractMethod(SourceAnnotation ann) throws SQLException {
List<String> list = super.superMethod(); // super.methodName(arguments)
list.add("abstractMethod: " + ann.sourceValue());
list.add("abstractMethod: TargetInner value = " + targetInner.value());
return list;
}
};
List<String> abstractResult = helper.abstractMethod(sourceAnn);

// Access outer private (method_feature: access_outer_private)
String privateAccessResult = "Access outer private: " + outerPrivateField;
abstractResult.add(privateAccessResult);

// Variable call (method_feature)
variableCall(targetInner, "Generic method processed, abstract result size: " + abstractResult.size());

// Used by reflection (method_feature)
Method reflectMethod = TargetAnnotation.class.getMethod("value");
String reflectValue = (String) reflectMethod.invoke(targetInner);
abstractResult.add("Reflection result: " + reflectValue);

// call_method: others_class, pos: exception throwing statements
OthersClass others = new OthersClass();
others.callMethod(sourceAnn, targetInner);

return abstractResult;
} catch (SQLException e) {
System.err.println("SQLException handled: " + e.getMessage());
return null;
} catch (Exception e) {
System.err.println("Unexpected exception: " + e.getMessage());
return null;
}
}

// Variable call (method_feature)
private void variableCall(TargetAnnotation targetInner, String message) {
System.out.printf("[SourceInner] %s | TargetInner value: %s%n",
message, targetInner.value());
}
}

// Static inner instance to trigger generic method
static final SourceInner INNER = new SourceInner();
}

// Test entry
public class TestRefactor {
public static void main(String[] args) {
// Initialize target (target_inner: TargetAnnotation)
@TargetAnnotation("test_target_inner_value")
class TargetHolder {}
TargetAnnotation targetInner = TargetHolder.class.getAnnotation(TargetAnnotation.class);

// Initialize source (contains target field: per_condition)
@SourceAnnotation(
targetField = @TargetAnnotation("test_target_field_value"),
sourceValue = "test_source_value"
)
class SourceHolder {}
SourceAnnotation sourceAnn = SourceHolder.class.getAnnotation(SourceAnnotation.class);

// Call source's generic method
Object result = SourceAnnotation.INNER.genericMethod(targetInner, sourceAnn);
System.out.println("\nGeneric method final result: " + result);
}
}