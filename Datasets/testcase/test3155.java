package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
public class TargetClass {int targetField;static class TargetStaticNested {} // target_feature: static nested class
private static TargetClass staticMethod() {return new TargetClass();}}
private class SourceClass {class InnerClass extends TargetClass {// Overloading method 1protected List<String> overloadMethod(int num) {return new ArrayList<>(List.of(String.valueOf(num)));}
// Overloading method 2protected List<String> overloadMethod(String str) {return new ArrayList<>(List.of(str));}}
/**
Method Javadoc
Instance method returning base type, with required features
@param target TargetClass instance
@return int base type result*/int methodToMove(TargetClass target) {// Variable callint var = target.targetField;InnerClass inner = new InnerClass();
// While statementwhile (var < 5) {var++;}
// Ternary operators with overloading method callList<String> resultList = (var > 3) ? inner.overloadMethod(1) : inner.overloadMethod("default");
// Used by reflectiontry {Method method = TargetClass.class.getDeclaredMethod("staticMethod");method.setAccessible(true);TargetClass reflected = (TargetClass) method.invoke(null);var += reflected.targetField;} catch (Exception e) {e.printStackTrace();}
// For loop with call_methodfor (int i = 0; i < 1; i++) {TargetClass called = TargetClass.staticMethod();var += called.targetField;}
return var;}}