package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
abstract class ParentClass {private List<String> parentMethod(TargetClass outer) {return outer.new Inner().getList();}}
abstract class SourceClass extends ParentClass {static class Nested1 {}static class Nested2 {}
<T> List<String> moveMethod(TargetClass.Inner targetInner) {class LocalType {}LocalType lt = new LocalType();
protected Supplier<Integer> lambda = () -> 1;
String[] array = { parentMethod(new TargetClass()) };
switch (targetInner.field) {case 1:break;default:break;}
variableCall(targetInner);return new ArrayList<>();}
private void variableCall(TargetClass.Inner inner) {}}
private class TargetClass {class Inner {int field;List<String> getList() {class LocalInner {}return new ArrayList<>();}}}