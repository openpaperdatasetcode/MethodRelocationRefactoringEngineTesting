package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.util.function.Function;
interface DataProcessor {<T> T process(T data);}
private class Target {public String field1;public int field2;
public static class Nested {private String nestedData;
public Nested(String data) {this.nestedData = data;}
public void update(String s) {nestedData += s;}
public String getNestedData() {return nestedData;}}
public Target(String f1, int f2) {this.field1 = f1;this.field2 = f2;}
public Nested createNested(String data) {return new Nested(data);}}
abstract class Source implements DataProcessor {static class StaticNested {static {// Static code block with 3 normal methods (lambda)Function<Target.Nested, List<String>> func1 = (n) -> {List<String> list = new ArrayList<>();list.add(n.getNestedData());return list;};
Function<Target.Nested, List<String>> func2 = (n) -> {List<String> list = new ArrayList<>();list.add(n.getNestedData().toUpperCase());return list;};
Function<Target.Nested, List<String>> func3 = (n) -> {List<String> list = new ArrayList<>();list.add(n.getNestedData().length() + "");return list;};}}
class MemberInner {Target.Nested processNested(Target.Nested nested) {nested.update("_processed");return nested;}}
Target handle(Target target) throws IllegalArgumentException {// ConstructorInvocation with 2 target object fieldsTarget newTarget = new Target(target.field1 + "_copy", target.field2 * 2);
// Super keywordssuper.process(target);
// Labeled statementprocessLoop: {if (target.field2 < 0) {break processLoop;}newTarget.field2 = target.field2;}
// Variable callMemberInner inner = new MemberInner();Target.Nested nested = inner.processNested(target.createNested("base"));
// Call method in do-whileint count = 0;do {new Target("do-while", count).createNested("loop").update("" + count);count++;} while (count < 2);
// Used by reflectiontry {Method method = Target.Nested.class.getMethod("getNestedData");String reflectedData = (String) method.invoke(nested);} catch (Exception e) {throw new IllegalArgumentException("Reflection failed", e);}
// Requires throwsif (newTarget.field1 == null) {throw new IllegalArgumentException("Field1 cannot be null");}
// Override violation (hypothetical - incorrect interface method implementation)return newTarget;}}