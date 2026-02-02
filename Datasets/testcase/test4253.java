package test;
import java.lang.reflect.Constructor;
interface SourceInterface extends ParentInterface {TargetInterface targetField = new TargetInterface() {};
class MemberInner {}
default void createAnonymous() {Runnable anon = new Runnable() {public void run() {}};}
protected TargetInterface newTargetInner() {// CreationReferenceRunnable create = MemberInner::new;
// Lambda expressionRunnable lambda = () -> System.out.println(this.value);
// Used by reflectiontry {Constructor<?> ctor = TargetInterface.NestedClass.class.getConstructor();ctor.newInstance();} catch (Exception e) {}
// Variable call + access outer protectedtargetField.protectedMethod();return targetField.new TargetInner();}
protected int value = 0;}
interface ParentInterface {}
public interface TargetInterface {static class NestedClass {}
class TargetInner {}
default void protectedMethod() {}}