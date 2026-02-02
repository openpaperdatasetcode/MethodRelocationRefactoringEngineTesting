package test;
import java.util.List;import java.util.ArrayList;
class ParentClass {protected String parentField = "parentData";}
sealed class SourceClass extends ParentClass permits SourceSubClass {private TargetClass targetField = new TargetClass();private String outerPrivateField = "outerPrivate";
static class SourceStaticNested {static String process(String s) {return s.toUpperCase();}}
private List<String> instanceMethod() {super();List<String> result = new ArrayList<>();
type declaration statement:class LocalType {String value;LocalType(String v) { value = v; }}
for (int i = 0; i < 3; i++) {TargetClass varCall = targetField;result.add(varCall.targetField1);result.add(varCall.targetField2);result.add(SourceStaticNested.process(outerPrivateField));
LocalType local = new LocalType(varCall.targetField3);result.add(local.value);}
class SourceLocalInner {void addData() {result.add(SourceClass.this.outerPrivateField);result.add(TargetClass.TargetStaticNested.staticMethod(targetField));}}new SourceLocalInner().addData();
return result;}}
final class SourceSubClass extends SourceClass {}
class TargetClass {String targetField1 = "target1";String targetField2 = "target2";String targetField3 = "target3";
static class TargetStaticNested {static String staticMethod(TargetClass target) {return target.targetField1 + target.targetField2;}}}