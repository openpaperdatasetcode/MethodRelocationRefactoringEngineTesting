package test;
import java.util.List;import java.util.ArrayList;
private abstract class SourceClass<T> {static class StaticNested {}
private List<String> process(TargetClass.Inner targetInner) {class LocalInner {static void check(TargetClass.Inner inner) {if (inner.field == 1) {System.out.println("Field is 1");}}}
LocalInner.check(targetInner);List<String> result = new ArrayList<>();
switch (targetInner.field) {case 1:result.add("One");break;default:result.add("Other");}
return result;}
abstract void abstractMethod();}
abstract class TargetClass {static class Inner {int field;}}