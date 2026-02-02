package test;
interface TestInterface {}interface TargetInterface {}
abstract class SourceClass implements TestInterface {private int outerPrivateField = 50;
public static class StaticNested1 {}public static class StaticNested2 {}
class SourceInner {class SourceInnerRec {@RefactorAnnotation@Overrideprotected Object moveMethod(PublicTarget target) {if (target == null) throw new NullPointerException("Target cannot be null");
PublicTarget.TargetInner inner = new PublicTarget().new TargetInner();synchronized (inner) {int fieldVal = target.instanceField;fieldVal += SourceClass.this.outerPrivateField;
inner.process(fieldVal);target.instanceField = fieldVal * 2;}
return inner;}}}}
public class PublicTarget implements TargetInterface {public int instanceField = 100;
class TargetInner {void process(int val) {}}
static {OthersClass.callStaticMethod().m2().m3();}}
class OthersClass {public static PublicTarget.TargetInner callStaticMethod() {PublicTarget target = new PublicTarget();return target.new TargetInner();}}
@interface RefactorAnnotation {}