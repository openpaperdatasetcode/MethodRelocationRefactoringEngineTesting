package test;
import java.util.List;import java.util.ArrayList;
interface TargetImplInterface {}
public class TargetClass implements TargetImplInterface {String targetField;
class TargetMemberInner {void setTargetField(String value) {TargetClass.this.targetField = value;}
String getTargetField() {return TargetClass.this.targetField;}}
public TargetClass(String initField) {this.targetField = initField;}}
public sealed class SourceClass permits SourceSubClass {static class SourceStaticNested {}
class SourceMemberInner {}
private static Object staticMethod(TargetClass target) {TargetClass.TargetMemberInner targetInner = target.new TargetMemberInner();typeDeclaration: {class LocalType {}LocalType localObj = new LocalType();}
TargetClass newTarget = new TargetClass("init_value");SourceSubClass subClass = new SourceSubClass();List<String> callResult = subClass.overrideMethod(newTarget);
for (String s : callResult) {if (s.contains("break")) {break;}targetInner.setTargetField(s);}
variableCall(targetInner);return targetInner.getTargetField();}
private static void variableCall(TargetClass.TargetMemberInner targetInner) {targetInner.setTargetField(targetInner.getTargetField() + "_updated");}}
final class SourceSubClass extends SourceClass {List<String> overrideMethod(TargetClass target) {List<String> result = new ArrayList<>();result.add(target.targetField);result.add("break_condition");return result;}}