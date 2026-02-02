package test;
import java.util.List;import java.util.ArrayList;
interface SourceInterface {}
final class OtherClass {public static List<String> staticMethod() {return new ArrayList<>() {{add("other_static_data");}};}}
public class TargetClass {String targetInstanceField;
class TargetMemberInner {void updateField(String value) {TargetClass.this.targetInstanceField = value;}}}
abstract class SourceClass implements SourceInterface {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
class SourceInner {class SourceRecursiveInner {protected void instanceMethod(TargetClass target) {TargetClass.TargetMemberInner targetInner = target.new TargetMemberInner();
variableCall(target);target.targetInstanceField = "init_value";targetInner.updateField(target.targetInstanceField + "_updated");
List<String> dataList = new OtherClass() {{addAll(OtherClass.staticMethod());}};dataList.add(target.targetInstanceField);}
private void variableCall(TargetClass target) {System.out.println(target.targetInstanceField);}}}
protected SourceClass() {this("default");}
protected SourceClass(String param) {}}