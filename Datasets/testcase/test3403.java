package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
abstract class SourceClass {private TargetClass targetField = new TargetClass();
// Two static nested classes (source feature)public static class FirstStaticNested {}public static class SecondStaticNested {}
// Overloading methods (method type)public List<String> overloadedMethod() {return new ArrayList<>();}
public List<String> overloadedMethod(int param) {List<String> result = new ArrayList<>();int count = 0;
// Do statementdo {// Variable call to target's member inner classTargetClass.TargetInner inner = targetField.new TargetInner();result.add(inner.getValue());inner.variableCall();count++;} while (count < 3);
// Used by reflectiontry {Method method = TargetClass.TargetInner.class.getMethod("getValue");result.add((String) method.invoke(targetField.new TargetInner()));} catch (Exception e) {// No new exception (per feature)}
return result;}}
protected abstract class TargetClass {// Member inner class (target feature)public class TargetInner {public String getValue() {return "target_inner_value";}
public void variableCall() {}}}