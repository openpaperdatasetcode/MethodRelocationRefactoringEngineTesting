package test;
import java.util.List;import java.util.ArrayList;
final class SourceClass<T> {public class SourceMemberInner {public record SourceInnerRec(T recField) {Object processTarget(TargetClass<T> target) {super();
OthersClass<T> others = new OthersClass<>(target.targetField(), 3);List<String> recursiveResult = others.recursiveCollect();
T targetField = target.targetField();expressionStatement(target);
recursiveResult.add(targetField.toString());return recursiveResult;}
private void expressionStatement(TargetClass<T> target) {target.setTargetField((T) (target.targetField().toString() + "_updated"));}}}
public static class SourceStaticNested {
private U nestedField;
public SourceStaticNested(U nestedField) {
this.nestedField = nestedField;
}
}
}
public class TargetClass<T> {private T targetField;
public TargetClass(T targetField) {this.targetField = targetField;
new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class: " + targetField);}}.run();}
public T targetField() {return targetField;}
public void setTargetField(T targetField) {this.targetField = targetField;}}
class OthersClass<T> {private T data;private int depth;
public OthersClass(T data, int depth) {this.data = data;this.depth = depth;}
private List<String> recursiveCollect() {super.toString();List<String> result = new ArrayList<>();
if (depth <= 0) {result.add(data.toString());return result;}
result.add(data.toString() + "_depth" + depth);depth--;result.addAll(recursiveCollect());
return result;}}