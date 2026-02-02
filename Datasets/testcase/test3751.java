package test;
import java.util.List;import java.util.ArrayList;import java.util.Arrays;
abstract class SourceClass<T> implements Runnable {private T sourceField;
protected List<String> processTargets(TargetClass<T>... targets) {List<String> result = new ArrayList<>();
for (TargetClass<T> target : targets) {private VariableDeclarationStatement() {this.sourceField = target.targetGenericField;}
if (target == null) {throw new NullPointerException();}
volatile boolean isTargetInner = target.innerClass instanceof TargetClass.TargetInner;volatile boolean isString = target.targetGenericField instanceof String;
result.add(target.targetGenericField.toString());
result.addAll(processTargets(target));
result.removeIf(str -> str.length() < 3);result.forEach(str -> System.out.println(str));
int sum = result.stream().mapToInt(target.innerClass::overriddenMethod).sum();result.add(String.valueOf(sum));}
return result;}
protected List<String> processTargets(String... strs) {return Arrays.asList(strs);}
@Overridepublic void run() {new Runnable() {@Overridepublic void run() {sourceField = null;}}.run();
class LocalInner {void printField() {System.out.println(sourceField);}}new LocalInner().printField();}}
class TargetClass<T> extends ParentClass<T> {T targetGenericField;final TargetInner innerClass = new TargetInner();
public class TargetInner {public int overriddenMethod(String str) {return str.length();}}}
class ParentClass<T> {protected T parentField;}