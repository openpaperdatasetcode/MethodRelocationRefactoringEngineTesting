package same.pkg;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
public class SourceClass {private TargetClass targetField = new TargetClass() {};protected String outerProtectedField = "protectedFieldValue";
class SourceInner {final void instanceMethod() throws Exception {variableCall();access_outer_protected();
String exprStmtResult = outerProtectedField + "_appended";
TargetClass.TargetInner.TargetInnerRec targetInnerRec = targetField.new TargetInner().new TargetInnerRec();
try {Method refMethod = TargetClass.TargetInnerRec.class.getMethod("recMethod");refMethod.invoke(targetInnerRec);} catch (Exception e) {List<String> chainResult = new SourceSubClass().m1().m2().m3();throw new Exception("Reflection failed", e);}}
private void variableCall() {int localVar = 100;}
private void access_outer_protected() {outerProtectedField = outerProtectedField.toUpperCase();}}
static class StaticNestedClass {void nestedMethod() {}}
class SourceSubClass extends SourceSuperClass {@OverrideSourceIntermediate1 m1() {return new SourceIntermediate1();}}
static class SourceSuperClass {SourceIntermediate1 m1() {return new SourceIntermediate1();}}
static class SourceIntermediate1 {SourceIntermediate2 m2() {return new SourceIntermediate2();}}
static class SourceIntermediate2 {List<String> m3() {return new ArrayList<>();}}}
abstract class TargetClass {class TargetInner {class TargetInnerRec {void recMethod() {}}}}