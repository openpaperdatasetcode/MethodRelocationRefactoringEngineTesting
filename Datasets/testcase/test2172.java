package test;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
private class SourceClass<T> {protected T outerProtectedField;static class StaticNested {}
class SourceInner {// Overloading method 1public int methodToMove(TargetClass<String> target) {return processTarget(target);}
// Overloading method 2public int methodToMove(TargetClass<Integer> target) {return processTarget(target);}
private <V> int processTarget(TargetClass<V> target) {// Super constructor invocation (implicit for inner class)super.toString();
// LabeledStatement with 3 ClassName.fieldsTargetLabel: {int field1 = TargetClass.staticField1;String field2 = TargetClass.staticField2;Object field3 = TargetClass.staticField3;if (field1 < 0) break TargetLabel;}
// Static method reference in Lambda (3 features)List<String> list = Arrays.asList("a", "b", "c");list.forEach(OtherClass::staticMethod);
// Switch statementswitch (target.getFlag()) {case 1:OtherClass.process(this);break;case 2:return 0;default:break;}
// Access outer protected fieldV val = (V) outerProtectedField;
// Call method in collection operationsList<String> result = list.stream().map(s -> SourceInner.this.synchronizedMethod()).toList();
return result.size();}
// Synchronized source method for call_methodsynchronized List<String> synchronizedMethod() {return new ArrayList<>();}}}
final class TargetClass<V> {static int staticField1;static String staticField2;static Object staticField3;
int getFlag() {// Local inner classclass LocalInner {}new LocalInner();return 1;}}
class OtherClass {static void process(Object obj) {}static void staticMethod(String s) {}}