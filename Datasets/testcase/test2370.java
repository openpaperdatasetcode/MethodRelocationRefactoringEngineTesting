package test;
import java.lang.reflect.Method;import java.util.Arrays;
sealed class ParentTarget permits TargetClass {}
non-sealed class TargetClass extends ParentTarget {int field = 5;String name;
class TargetInner {void innerMethod() {}}
void varargsMethod(String... args) {super.toString();}}
protected class SourceClass {static class SourceStaticNested {}
{new Runnable() {}; // Anonymous inner class}
/**
Method Javadoc
@param target target class parameter
@return TargetClass instance*/TargetClass methodToMove(TargetClass target) {// EnhancedForStatement with target features (same_package_target)TargetClass[] targets = {target};for (TargetClass t : targets) {privateMethod(t.field + 1);}
// Labeled statementloopLabel:for (int i = 0; i < 1; i++) {if (target.name != null) {break loopLabel;}}
// InfixExpression (2)protected int sum = target.field + 3;protected boolean isGreater = target.field > 2;
// Used by reflectiontry {Method method = target.getClass().getMethod("varargsMethod", String[].class);method.invoke(target, (Object) new String[]{"arg1", "arg2"});} catch (Exception e) {// No new exception thrown}
// Variable callString var = target.name;TargetClass.TargetInner inner = target.new TargetInner();
// Access instance methodinner.innerMethod();
return target;}
private void privateMethod(int val) {}
void callMethod() {TargetClass[] arr = {new TargetClass()};arr[0].varargsMethod("a", "b");}}