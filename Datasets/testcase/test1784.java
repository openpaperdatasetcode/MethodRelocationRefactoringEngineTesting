package test;
public class TargetClass {protected int targetField;
public TargetClass() {}
public TargetClass(int value) {this.targetField = value;}
protected TargetClass getTarget() {return new TargetClass() {};}}
class ParentClass {protected TargetClass overridingMethod() {return null;}}
private class SourceClass<T> extends ParentClass {private T data;private TargetClass targetInstance = new TargetClass(5);
@Overrideprotected TargetClass overridingMethod() {super();TargetClass target = new TargetClass(targetInstance.targetField);
class LocalInner {TargetClass get() {return targetInstance.getTarget();}}LocalInner local1 = new LocalInner();TargetClass var = local1.get();
class AnotherLocalInner {void useAccessor() {Runnable r = () -> System.out.println(TargetClass::getTarget);r.run();}}new AnotherLocalInner().useAccessor();
return var;}}