package source;
import java.util.ArrayList;import java.util.List;import target.TargetClass;
strictfp class SourceClass {class MemberInner {}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
strictfp List<String> moveMethod(TargetClass target) {class LocalType {}LocalType local = new LocalType();
List<String> result = new ArrayList<>();result.add(target.staticNested.process("data"));result.add(target.inner.getValue());return result;}}
package target;
private class TargetClass extends ParentTarget {public static class StaticNested {public static String process(String input) {return input.toUpperCase();}}
TargetInner inner = new TargetInner();
class TargetInner {String getValue() {return "targetValue";}}}
abstract class ParentTarget {}
