package test;
import java.util.List;import java.util.ArrayList;
final class SourceClass {static class StaticNested extends ParentClass {@Overrideprotected List<String> methodToMove(TargetClass.TargetInner target) {// Type declaration statement (with bounds)TargetClass.WithBounds<String> boundedObj = new TargetClass.WithBounds<>();// NullLiteral with numbers=1Object nullVal = null;
class LocalInner {}LocalInner local = new LocalInner();
// Variable call (source contains target's field)int targetFieldVal = target.field;List<String> result = new ArrayList<>();
// If statementif (targetFieldVal == 1) {result.add("value: 1");}
// Switch statementswitch (targetFieldVal) {case 0:result.add("zero");break;default:result.add("other");}
return result;}}}
abstract class ParentClass {protected abstract List<String> methodToMove(TargetClass.TargetInner target);}
public class TargetClass extends SuperClass {static class TargetStaticNested {}
class TargetInner {int field = 1; // Target field used in source}
// With boundsstatic class WithBounds<T extends CharSequence> {}}
class SuperClass {}