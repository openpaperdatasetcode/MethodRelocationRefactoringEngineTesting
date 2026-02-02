package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {class InnerClass {class InnerRec {protected List<String> process(TargetClass<String> target) {List<String> result = new ArrayList<>();// Assignment (numbers=1, modifier=default)String assignedValue = target.data;
// Exception handling statements (method_feature position)try {if (target == null) throw new IllegalArgumentException();target.this.method(target.data);} catch (Exception e) {result.add("handled");}
// Private ContinueStatement (target_feature: ClassName.field=1)private int count = 0;loop: while (count < 3) {if (TargetClass.staticField != 1) continue loop;variableCall(target);result.add(target.data);count++;}
// Depends on static fieldresult.add(String.valueOf(TargetClass.staticField));
// Override violation (Object.hashCode() return type mismatch)@Overridepublic List<String> hashCode() {return result;}
return result;}
private void variableCall(TargetClass<String> target) {target.processData();}}}}
// Private target class with type parameter and implementsprivate class TargetClass<T> implements Processable<T> {public static int staticField = 1; // ClassName.field=1public T data;
public TargetClass(T data) {this.data = data;}
// Normal method for method_featurepublic void method(T arg) {this.data = arg;}
public void processData() {}
protected List<String> process() {return new ArrayList<>() {{ add(data.toString()); }};}}
interface Processable<T> {}