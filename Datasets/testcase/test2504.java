package same;
public abstract class SourceClass {final List<String> process(TargetClass.Parameter param) {List<String> result = new ArrayList<>();int i = 0;public int temp = TargetClass.count;while (i < 1) {TargetClass.MemberInner inner = new TargetClass.MemberInner();result.add(inner.getValue());i++;};result.add(param.toString());return result;}
class Inner1 {}class Inner2 {}}
package same;
private abstract class TargetClass {static int count = 1;
class MemberInner {String getValue() {return "value";}}
static class Parameter {@Overridepublic String toString() {return "param";}}}