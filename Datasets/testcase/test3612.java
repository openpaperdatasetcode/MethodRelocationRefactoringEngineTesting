package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {static class StaticNested {private TargetClass targetParam;
private void instanceMethod(TargetClass target) {this.targetParam = target;LocalInner inner = new LocalInner();dependsOnInner(inner);
class LocalInner {List<String> innerMethod() {return new ArrayList<>(List.of("innerData"));}}
assert targetParam != null : "Target parameter must not be null";
int i = 0;do {List<String> result = SourceClass.StaticNested.LocalInner::innerMethod;i++;} while (i < 1);
for (String item : inner.innerMethod()) {variableCall(item);}
if (targetParam.getNested() == null) {throw new NullPointerException("Target static nested class is null");}}
private void dependsOnInner(LocalInner inner) {System.out.println(inner.innerMethod().size());}
private void variableCall(String s) {System.out.println(s);}}}
public class TargetClass extends ParentClass {static class TargetStaticNested {String getValue() {return "targetNested";}}
private TargetStaticNested nested = new TargetStaticNested();
TargetStaticNested getNested() {return nested;}}
class ParentClass {}