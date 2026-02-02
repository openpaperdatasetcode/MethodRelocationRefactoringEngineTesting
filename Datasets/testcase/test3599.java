package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnnotation {}
public class SourceClass<T extends Number> {private int outerPrivateField;TargetClass<String> targetField;
static class StaticNested {
private int nestedField;
final List<String> varargsMethod(Integer... args) {List<String> result = new ArrayList<>();LocalInner local = new LocalInner();
class LocalInner {String process() {return "processed";}}
@CustomAnnotationint localVar = 0;dependsOnInner(local.process());
protected class DoStatementHelper {void execute() {int i = 0;do {this.nestedField = i;result.add(String.valueOf(i));i++;} while (i < 2);}}new DoStatementHelper().execute();
TargetClass<String> newTarget = new TargetClass<>();result.add(newTarget.nestedStaticClass.toString());
switch (args.length) {case 0:result.add("empty");break;default:result.add("non-empty");}
final Object obj1 = "string";final Object obj2 = 123;final Object obj3 = 45.6f;result.add((String) obj1);result.add(String.valueOf((Integer) obj2));result.add(String.valueOf((Float) obj3));
result.add(String.valueOf(SourceClass.this.outerPrivateField));result.add(String.valueOf(targetField.targetGenericField));
return result;}
private void dependsOnInner(String s) {nestedField = s.length();}}}
protected class TargetClass<V> {V targetGenericField;static class NestedStatic<W extends CharSequence> {W value;@Overridepublic String toString() {return value.toString();}}NestedStatic<String> nestedStaticClass = new NestedStatic<>();}