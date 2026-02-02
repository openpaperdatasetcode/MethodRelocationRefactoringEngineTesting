package test.refactoring.movemethod;
import java.io.IOException;import java.util.List;import java.util.ArrayList;import java.util.function.Runnable;
private class SourceClass {private static String staticField = "source_static";private String value = "source_value";
class MemberInnerSource {protected static List<String> fetchData(TargetClass targetParam) throws IOException {class SourceInner {List<String> process() throws IOException {List<String> result = new ArrayList<>();// Type declaration statementRunnable logger = () -> System.out.println(this.value);
try {TargetClass.LocalInnerTarget inner1 = targetParam.createLocalInner(TargetClass.FIELD1);TargetClass.LocalInnerTarget inner2 = targetParam.createLocalInner(TargetClass.FIELD2);TargetClass.LocalInnerTarget inner3 = targetParam.createLocalInner(TargetClass.FIELD3);
result.add(inner1.process(staticField));result.add(inner2.process(staticField));result.add(inner3.process(staticField));
logger.run();} catch (IOException e) {throw new IOException("Failed to process data", e);}
// Array initialization with others_class static method callint[] values = { OtherClass.staticMethod(), OtherClass.staticMethod() };result.add("Array sum: " + (values[0] + values[1]));
return result;}}return new SourceInner().process();}}
void createAnonymousClass() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous class: " + staticField);}};runnable.run();}}
protected class TargetClass extends SuperTargetClass {public static final String FIELD1 = "target_field1";public static final String FIELD2 = "target_field2";public static final String FIELD3 = "target_field3";
public LocalInnerTarget createLocalInner(String data) {class LocalInnerTarget {private String innerData;
LocalInnerTarget(String data) {this.innerData = data;}
String process(String sourceStatic) throws IOException {if (innerData == null) {throw new IOException("Inner data is null");}return innerData + "" + sourceStatic + "" + super.getSuperValue();}}return new LocalInnerTarget(data);}}
abstract class SuperTargetClass {protected String getSuperValue() {return "super_value";}}
public class OtherClass {public static int staticMethod() {return 10;}}
public class MoveMethodTest5196 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();source.createAnonymousClass();
try {// Direct call via inner classList<String> result = SourceClass.MemberInnerSource.fetchData(target);System.out.println(result);
// Used by reflectionClass<?> innerClass = Class.forName("test.refactoring.movemethod.SourceClass$MemberInnerSource");var method = innerClass.getDeclaredMethod("fetchData", TargetClass.class);method.setAccessible(true);List<String> reflectionResult = (List<String>) method.invoke(null, target);System.out.println("Reflection result: " + reflectionResult);} catch (Exception e) {e.printStackTrace();}}}