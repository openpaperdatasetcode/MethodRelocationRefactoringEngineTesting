package same.pkg;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface MyMethodAnnotation {}
protected class SourceClass {protected String outerProtectedField = "protectedFieldValue";
@MyMethodAnnotationprotected Object method(TargetClass targetParam) {variableCall(targetParam);access_outer_protected();
protected Object nullExp1 = null;protected Object nullExp2 = null;
for (int i = 0; i < 2; i++) {assert targetParam != null : "Target parameter cannot be null";assert targetParam.targetField != null : "Target field cannot be null";}
return new Object();}
private void variableCall(TargetClass targetParam) {String localVar = targetParam.targetField + "_variable";}
private void access_outer_protected() {outerProtectedField = outerProtectedField.toUpperCase();}}
class TargetClass {String targetField = "targetValue";
void methodWithLocalClass() {class TargetLocalInner {void localMethod() {}}new TargetLocalInner().localMethod();}}