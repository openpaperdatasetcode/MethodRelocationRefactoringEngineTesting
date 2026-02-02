package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
class ParentSourceClass {}
public class SourceClass extends ParentSourceClass {private TargetClass targetField = new TargetClass();
static class StaticNested {static void checkTargetFields(TargetClass target) {static void staticIfCheck() {if (target.field1 == null || target.field2 == null) {throw new IllegalArgumentException("2 obj.fields are null");}}staticIfCheck();}}
class SourceInner {@MyAnnotationpublic List<String> instanceMethod(TargetClass target) {List<String> result = new ArrayList<>();assert target != null : "Target cannot be null";
Function<TargetClass, Object> getField1 = TargetClass::getField1;Function<TargetClass, Object> getField2 = TargetClass::getField2;Object varCall1 = getField1.apply(target);Object varCall2 = getField2.apply(target);result.add(varCall1.toString());result.add(varCall2.toString());
int count = 0;while (count < 2) {StaticNested.checkTargetFields(target);count++;}
Runnable anon = new Runnable() {@Overridepublic void run() {TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();result.add(nested.process());}};anon.run();
OthersClass others = new OthersClass();int methodResult = others.callMethod(new TargetClass.TargetStaticNested(),target,result);result.add(String.valueOf(methodResult));
return result;}}}
protected class TargetClass {String field1;String field2;
public TargetClass() {this.field1 = "targetField1";this.field2 = "targetField2";}
public String getField1() { return field1; }public String getField2() { return field2; }
static class TargetStaticNested {public String process() {return "staticNestedProcessed";}}}
class OthersClass {int callMethod(TargetClass.TargetStaticNested nested, TargetClass target, List<String> list) {return nested.process().length() + target.getField1().length() + list.size();}}
@interface MyAnnotation {}