import java.util.Objects;
class Container {private SourceClass source = new SourceClass();
private class SourceClass extends ParentClass {public class MemberInner {@RefactorAnnotation@Overrideprotected TargetClass method(TargetClass target) {if (target == null) {throw new NullPointerException("Target cannot be null");}
raw_type TargetClass rawTarget = new TargetClass();variableCall(target.memberInner);
TargetClass processed = null;try {processed = this.createTargetViaOthers(target);} catch (IllegalArgumentException e) {processed = rawTarget;}
return processed;}
private void variableCall(TargetClass.MemberInner inner) {inner.updateData("refactored_data");}
private TargetClass createTargetViaOthers(TargetClass target) {OthersClass.ConstructorFunc func = target::new;return OthersClass.createTarget(func, target);}
public void method() {}}
public void useLocalInner() {class LocalInner {TargetClass getTarget(TargetClass target) {return new MemberInner().method(target);}}new LocalInner().getTarget(new TargetClass());}}}
abstract class ParentClass {public abstract TargetClass method(TargetClass target);}
public class TargetClass {public MemberInner memberInner = new MemberInner();
public TargetClass() {}
public TargetClass(TargetClass other) {this.memberInner = other.memberInner;}
public class MemberInner {private String data;
public void updateData(String data) {this.data = data;}
public String getData() {return data;}}}
class OthersClass {@FunctionalInterfacepublic interface ConstructorFunc {TargetClass create();}
public static TargetClass createTarget(ConstructorFunc func, TargetClass ref) {TargetClass newTarget = func.create();newTarget.memberInner.updateData(ref.memberInner.getData());return newTarget;}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}