package test.source;
import java.util.List;import java.util.ArrayList;import test.target.TargetClass;
interface SomeInterface {}
protected class SourceClass implements SomeInterface {class MemberInner {class InnerRec {final List<String> moveMethod(TargetClass.Inner... inners) {class LocalInner {}LocalInner li = new LocalInner();
List<String> list = new ArrayList<>();if (inners.length > 0) {list.add(inners[0].field);variableCall(inners[0]);}
TargetClass.Inner inner = (inners.length > 0) ? SourceClass.callMethod(inners[0]) : null;return list;}
private void variableCall(TargetClass.Inner inner) {}}}
protected static TargetClass.Inner callMethod(TargetClass.Inner param) {return param;}
protected static TargetClass.Inner callMethod(String param) {return new TargetClass().new Inner();}}
package test.target;
import java.util.List;
private class TargetClass {class Inner {String field;void method() {class LocalInner {}}}}