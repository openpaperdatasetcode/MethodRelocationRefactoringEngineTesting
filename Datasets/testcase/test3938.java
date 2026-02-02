import java.util.ArrayList;import java.util.List;import java.util.Objects;
public class SourceClass {private String outerPrivateField = "privateValue";protected TargetClass targetField = new TargetClass();
default List<String> getTargetData() {List<String> data = new ArrayList<>();assert targetField != null : "Target cannot be null";
data.add(outerPrivateField);variableCall(targetField);return data;}
private void variableCall(TargetClass target) {target.setData(outerPrivateField);}
public class MemberInnerClass {protected <T> List<String> processWithLambda(List<T> items) {List<String> result = new ArrayList<>();items.forEach(item -> {result.add(item.toString());result.addAll(getTargetData());});return result;}}
{new Runnable() {@Overridepublic void run() {new MemberInnerClass().processWithLambda(List.of("a", "b"));}}.run();}}
protected class TargetClass {private List<String> data = new ArrayList<>();
public void setData(String value) {data.add(value);}
public List<String> getData() {return new ArrayList<>(data);}}
class SuperAccessorClass {public List<String> getTargetData() {return new ArrayList<>();}}
