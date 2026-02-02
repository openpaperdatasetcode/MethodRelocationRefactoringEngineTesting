package test;
import java.io.IOException;
private class Source {private static int sourceStaticField = 10;private Target targetField = new Target();
static class StaticNested {static int nestedStaticField = 5;}
class Inner {private int varargsMethod(T... params) throws IOException {int result = 0;try {variableCall(targetField.new Inner());
for (T param : params) {if (param instanceof Number) {result += ((Number) param).intValue();}}
result += sourceStaticField + StaticNested.nestedStaticField;} catch (ClassCastException e) {throw new IOException("Parameter type mismatch", e);}return result;}
private void variableCall(Target.Inner targetInner) {int val = targetInner.innerField;val += Target.StaticNested.targetStaticField;}}
void methodWithLocalInner() {class LocalInner {void useVarargs() throws IOException {Inner inner = new Inner();int sum = inner.varargsMethod(1, 2, 3);}}new LocalInner().useVarargs();}}
class Target {static class StaticNested {static int targetStaticField = 3;}
class Inner {int innerField = 2;}}