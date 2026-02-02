package source;

import target.TargetInterface;
import other.OtherPackageClass;
import java.lang.reflect.Method;
import java.util.List;

// Source: interface in different package with 2 member inner classes
interface SourceInterface {
// Member inner class 1
class SourceInnerOne {
public String process(String data) {
return "processed: " + data;
}
}

// Member inner class 2 (source_inner)
class SourceInnerTwo {
// Private overloading method 1
private TargetInterface.TargetStaticNested overloadedMethod(TargetInterface target) {
// Variable call
TargetInterface.TargetStaticNested nested = target.createNested(1);

// Expression statement
String exprResult = new SourceInnerOne().process(target.getData());

// Requires try-catch (reflection)
try {
Method method = TargetInterface.TargetStaticNested.class.getMethod("getValue");
Object reflected = method.invoke(nested);
} catch (Exception e) {
// Handle exception
}

// TypeDeclarationStatement in diff_package_others
OtherPackageClass.TypeHolder holder = new OtherPackageClass.TypeHolder(nested);

return nested;
}

// Private overloading method 2 (overload_exist)
private TargetInterface.TargetStaticNested overloadedMethod(TargetInterface target, int param) {
TargetInterface.TargetStaticNested base = overloadedMethod(target);
base.setValue(param);
return base;
}
}
}

package target;

import java.io.Serializable;
interface TargetInterface extends Serializable {
String getData ();
TargetStaticNested createNested(int value);
// Static nested class (target_feature)
class TargetStaticNested {
private int value;
protected int superField = 1; // For super.field feature
public TargetStaticNested(int value) {
this.value = value;
}
public int getValue() {
return value;
}
public void setValue(int value) {
this.value = value;
}
}
}

package other;

import target.TargetInterface;

// Other package class for diff_package_others position
public class OtherPackageClass {
// TypeDeclarationStatement with super.field and "1"
public static class TypeHolder {
private TargetInterface.TargetStaticNested nested;

// Private TypeDeclarationStatement (modifier: private)
private TypeHolder(TargetInterface.TargetStaticNested nested) {
this.nested = nested;
// target_feature: super.field and "1"
if (nested.superField == 1) {
nested.setValue(nested.superField);
}
}
}
}