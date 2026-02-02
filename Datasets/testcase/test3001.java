import java.util.ArrayList;import java.util.List;
interface ParentInterface {TargetClass process(TargetClass param);}
record SourceClass(String id) implements ParentInterface {@Overridepublic TargetClass process(TargetClass param) {transient String objField = param.inner.targetField;
TargetClass rawTarget = new TargetClass("raw");List rawList = new ArrayList();rawList.add(param.inner.getValue());
param.inner.updateField("updated");return new TargetClass(objField);}}
public record TargetClass(String data) {class MemberInner {String targetField = data;
String getValue() {return targetField;}
void updateField(String newValue) {this.targetField = newValue;}}
MemberInner inner = new MemberInner();}