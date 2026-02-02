package source;
import java.util.List;import java.lang.reflect.Method;import target.TargetClass;
private class SourceClass {private TargetClass target = new TargetClass();protected int outerProtected = 10;
protected class MemberInner {void innerMethod() {}}
protected List<String> methodToMove() {labeled: {do {Object val = new TargetClass.AnonymousInner().getField();if (val.equals(1)) break labeled;} while (target.count < 5);}
target.anonymous = new Object() {int field = 0;};super.toString();this.methodToMove();MemberInner mi = new MemberInner();mi.innerMethod();
try {Method method = TargetClass.class.getMethod("methodToMove");method.invoke(target);} catch (Exception e) {}
if (target.getOuterProtected() != outerProtected) {}return List.of();}}
package target;
import java.util.List;
non-sealed class TargetClass extends ParentClass {public int count = 0;Object anonymous;private int targetField = 1;
class AnonymousInner {public Object getField() {return targetField;}}
protected int getOuterProtected() {return 0;}
private List<String> methodToMove() {return List.of();}}
package target;
class ParentClass {}
