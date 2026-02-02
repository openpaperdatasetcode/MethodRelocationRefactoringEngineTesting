package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import java.io.IOException;
sealed class SourceClass<T> permits SourceSubClass {private String outerPrivateField = "private";static class SourceStaticNested {void nestedMethod() {}}
public List<String> methodToMove(TargetClass target) throws IOException {// For statementList<String> result = new ArrayList<>();for (int i = 0; i < target.count; i++) {result.add(String.valueOf(i));}
// Expression statementTargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();staticNested.setValue("test");
// Used by reflection (duplicate feature)try {Method method = target.getClass().getMethod("staticMethod");method.invoke(null);} catch (Exception e) {throw new IOException("Reflection error", e);}
// Variable callString var = target.name;int targetCount = target.count;
// Access outer private fieldresult.add(outerPrivateField);
// Override violation (assuming parent has different return type)class Sub extends ParentClass {@Overridepublic int overriddenMethod() { // Violation if parent returns Stringreturn targetCount;}}
// Depends on inner classSourceStaticNested nested = new SourceStaticNested();nested.nestedMethod();
// Local inner classclass LocalProcessor {void process() {result.add(var);}}new LocalProcessor().process();
return result;}}
final class SourceSubClass extends SourceClass<String> {}
strictfp class TargetClass {String name;int count = 3;
static class TargetStaticNested {private String value;void setValue(String val) { this.value = val; }String getValue() { return value; }}
public static int staticMethod() {return 10;}
public int callMethod() {// Property assignmentTargetClass instance = new TargetClass();instance.name = "target";return instance.new TargetStaticNested().setValue("chain").getValue().length();}}
class ParentClass {public String overriddenMethod() {return "parent";}}