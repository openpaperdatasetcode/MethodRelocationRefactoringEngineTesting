package test;
import java.util.ArrayList;import java.util.List;
class ParentAbstractClass {protected String parentField = "parentData";}
strictfp abstract class SourceClass extends ParentAbstractClass {private String instanceField = "sourceField";
static class StaticOuter {class NestedInner {protected static <T extends CharSequence> List<String> staticMethod(PrivateAbstractClass target) {List<String> result = new ArrayList<>();
class FirstLocalInner {<T extends String> void addData(T data) {result.add(data);}}FirstLocalInner firstLocal = new FirstLocalInner();firstLocal.addData("local1");
class SecondLocalInner {void callStatic() {result.addAll(StaticOuter.NestedInner.staticMethod(target));}}new SecondLocalInner().callStatic();
variableCall(target);result.add(target.instanceField);this.addResult(result, "chainData");
return result;}
private void addResult(List<String> list, String data) {list.add(data);}}}
private static void variableCall(PrivateAbstractClass target) {target.process();}}
private abstract class PrivateAbstractClass {String instanceField = "targetField";
void process() {}}