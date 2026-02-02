package test;
import java.util.List;import java.util.ArrayList;import java.util.Vector;
private class SourceClass {static class StaticNested {}
{new Runnable() {public void run() {}};}
public List<String> methodToMove(TargetClass<String> target) {// Access target's static nested classTargetClass<String>.StaticNested inner = new TargetClass<String>.StaticNested();int field = inner.targetField;
// Expression statement with variable callfield += target.genericField.length();
// Raw type usageVector rawVector = new Vector();
// Override violation: reducing access modifierTargetClass<String>.StaticNested sub = new TargetClass<String>.StaticNested() {@Overrideprivate void publicMethod() {}};
return new ArrayList<>();}}
public class TargetClass<T> {T genericField;
static class StaticNested {int targetField;
public void publicMethod() {}}}