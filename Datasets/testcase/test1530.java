package source;
import target.Target;import java.util.List;import java.util.ArrayList;import java.lang.reflect.Constructor;import java.lang.reflect.Field;
private record Source<T>(T value) {static class StaticNested {static final String STATIC_FIELD = "source_static";}
class MemberInner {class InnerRec {// Overriding method in source_inner_rec position@Overridepublic Object toString() {return "InnerRec: " + Source.this.value;}
public Object process(Target<String>.Nested.InnerRec targetRec) {List<Object> result = new ArrayList<>();
// Constructor invocationTarget<String> target = new Target<>("base_value");Target.Nested nested = new Target.Nested(100);Target.Nested.InnerRec newRec = nested.new InnerRec("new_rec");
// Type declaration statementTarget.Nested.InnerRec processedRec = targetRec;
// Super keywordsresult.add(super.toString());
// Lambda expression: () -> System.out.println(this.value)Runnable printer = () -> System.out.println(this.value);printer.run();
// Variable callresult.add(processedRec.getData());
// Access outer private (record's component is implicitly private)result.add(Source.this.value);
// Depends on static fieldresult.add(StaticNested.STATIC_FIELD);
// VariableDeclarationStatement with 3 target super.fieldsprivate String superField1 = (String) target.superField1;private int superField2 = target.superField2;private boolean superField3 = target.superField3;result.add(superField1 + superField2 + superField3);
// Call method in expression (target constructor + super.method)String callResult = new Target<>("call").superMethod("arg");result.add(callResult);
// Used by reflectiontry {Constructor<Target.Nested.InnerRec> constructor =Target.Nested.InnerRec.class.getDeclaredConstructor(Target.Nested.class, String.class);constructor.setAccessible(true);Target.Nested.InnerRec reflectedRec = constructor.newInstance(nested, "reflected");result.add(reflectedRec.getData());} catch (Exception e) {// No new exception}
return result;}}}}
package target;
public class SuperTarget {protected String superField1 = "super1";protected int superField2 = 200;protected boolean superField3 = true;
protected String superMethod(String arg) {return "super_" + arg;}}
strictfp record Target<T>(T value) extends SuperTarget {static class Nested {private int nestedValue;
public Nested(int value) {this.nestedValue = value;}
class InnerRec {private String data;
public InnerRec(String data) {this.data = data;}
public String getData() {return data + "_" + nestedValue;}}}}