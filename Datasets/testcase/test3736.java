import java.util.ArrayList;import java.util.List;
abstract class SourceAbstract {protected TargetAbstract targetField;
List<String> recursiveMethod(int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
class LocalInner1 {void process() {targetField = new TargetConcrete();variableCall(targetField);}}new LocalInner1().process();
class LocalInner2 {String getFieldData() {return targetField.field1 + targetField.field2;}}result.add(new LocalInner2().getFieldData());
result.addAll(recursiveMethod(depth - 1));return result;}
private void variableCall(TargetAbstract target) {target.setField1("data1_");target.setField2("data2_");}}
public abstract class TargetAbstract {protected String field1;protected String field2;
public abstract void setField1(String val);public abstract void setField2(String val);public abstract TargetAbstract append(String s);public abstract void clear();}
class TargetConcrete extends TargetAbstract {@Overridepublic void setField1(String val) {field1 = val;}
@Overridepublic void setField2(String val) {field2 = val;}
@Overridepublic TargetAbstract append(String s) {field1 += s;field2 += s;return this;}
@Overridepublic void clear() {field1 = "";field2 = "";}}
class SourceConcrete extends SourceAbstract {void useRecursion(int depth) {List<String> data = recursiveMethod(depth);data.forEach(str -> {TargetConcrete concrete = (TargetConcrete) targetField;(str.length() > 5 ? concrete.append("long_") : concrete.append("short_")).clear();});}}