package source;
import java.io.IOException;import java.util.List;import java.util.ArrayList;import target.TargetEnum;
protected enum SourceEnum {INSTANCE;
private String sourceInstanceField = "sourceFieldValue";
private List<String> recursiveMethod(TargetEnum target, int depth) throws IOException {List<String> result = new ArrayList<>();
if (depth <= 0) {result.add(target.targetField);return result;}
{TargetEnum.TargetStaticNested.GenericInner<String> genericInner =new TargetEnum.TargetStaticNested.GenericInner<>();String genericResult = genericInner.protectedGenericMethod(target.targetField);result.add(genericResult);}
String var = target.targetField;expressionStatement(var);
result.add(this.sourceInstanceField);
result.addAll(recursiveMethod(target, depth - 1));return result;}
private void expressionStatement(String param) {param = param + "_processed";}}
package target;
import java.util.List;
public enum TargetEnum {INSTANCE;
public String targetField = "targetFieldValue";
public static class TargetStaticNested {public static class GenericInner<T> {protected T protectedGenericMethod(T value) {super.toString();return value;}}}}