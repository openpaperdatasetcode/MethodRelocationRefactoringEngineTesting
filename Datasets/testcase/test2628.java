package test.same;
import java.util.ArrayList;import java.util.List;import test.other.OtherClass;
abstract class SourceClass {static class StaticNested {}
private <T> List<String> instanceMethod(TargetClass target) {List<String> result = new ArrayList<>();TargetClass.StaticNested nested = target.new StaticNested();Object var = nested.targetField;
String[] array = { getTargetInstance(2).toString() };
loopLabel:for (int i = 0; i < 3; i++) {new OtherClass().process(nested);synchronized (nested) {if (nested.targetField.equals(3)) {break loopLabel;}var = nested.targetField;result.add(var.toString());}}
Runnable anon = new Runnable() {public void run() {}};
return result;}
public TargetClass getTargetInstance(int num) {return (TargetClass) super.clone();}}
class TargetClass {static class StaticNested {Object targetField;}}
package test.other;
import test.same.TargetClass;
class OtherClass {void process(TargetClass.StaticNested nested) {nested.targetField = 3;}}