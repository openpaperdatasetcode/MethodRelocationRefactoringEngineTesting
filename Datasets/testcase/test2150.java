package test;
import other.DiffPackageClass;import java.util.List;import java.util.ArrayList;
abstract class SuperSource {protected abstract List<String> process(TargetClass target);}
abstract class SourceClass extends SuperSource {static class StaticNested {}
Runnable anonymous = new Runnable() {public void run() {}};
class MemberInner {@Overrideprotected List<String> process(TargetClass target) {List<String> result = new ArrayList<>();SourceClass.this.toString();
// Access instance fieldresult.add(String.valueOf(target.dataField));
// Continue statement in different package contextDiffPackageClass.process(target, () -> {if (target.this.field == null) {continue;}target.variableCall();});
// Override violation (assuming TargetClass's method is final)TargetClass violating = new TargetClass() {@Overridepublic void finalMethod() {} // Compile error expected};
return result;}}}
non-sealed class TargetClass permits SubTarget {int dataField;Object field;static class StaticNested {}
void variableCall() {}public final void finalMethod() {}}
final class SubTarget extends TargetClass {}
package other;
import test.TargetClass;
public class DiffPackageClass {static void process(TargetClass target, Runnable action) {action.run();}}