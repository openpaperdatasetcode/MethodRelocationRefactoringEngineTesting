package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
protected record SourceClass(TargetClass targetField) {@MyAnnotprivate void instanceMethod() {TargetClass.Inner inner = targetField.new Inner();TargetClass[] array = {targetField, new TargetClass(1), new TargetClass(2)};int val = array[0].id() + array[1].id() + array[2].id();
Runnable r = inner::overrideMethod;try {r.run();} catch (Exception e) {throw new RuntimeException(e);}
SubClass sub = new SubClass();int i = 0;while (i < 3) {Object obj = sub.genericMethod(List.of(array[i]));i++;}
variableCall(inner);}
private void variableCall(TargetClass.Inner inner) {TargetClass.Inner local = inner;}
abstract static class AbstractHelper {abstract void processArray(TargetClass[] arr);}
private static class SubClass extends AbstractHelper {@Overridesynchronized Object genericMethod(List<TargetClass> list) {return list.get(0);}
@Overridevoid processArray(TargetClass[] arr) {}}}
public record TargetClass(int id) {class Inner extends SuperInner {@Overridepublic void overrideMethod() {super.overrideMethod();}}}
class SuperInner {public void overrideMethod() {}}