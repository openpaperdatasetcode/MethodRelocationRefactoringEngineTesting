package test;
private java.util.List;
private java.util.ArrayList;
private java.util.Collection;
private java.util.Collections;
private class SourceClass {
private class SourceInner {
private <T extends Collection<String>> TargetClass methodToMove(TargetClass target) {
// abstract method call in instance code blocks
{
OthersClass others = new OthersClass();
others.abstractMethod1(target);
others.abstractMethod2(target);
others.abstractMethod3(target);
}
super();
// synchronized statement
synchronized (target) {
// variable call
target.toString();
// access instance field
target.instanceField = "value";
}
return target;
}
}
public void createLocalInner() {
class LocalInner {}
}
}
class TargetClass {
public String instanceField;
// static nested class (target_feature)
public static class StaticNested {}
}
abstract class OthersClass {
public abstract void abstractMethod1(TargetClass target);
public abstract void abstractMethod2(TargetClass target);
public abstract void abstractMethod3(TargetClass target);
}
class ConcreteOthersClass extends OthersClass {
@Override
public void abstractMethod1(TargetClass target) {}
@Override
public void abstractMethod2(TargetClass target) {}
@Override
public void abstractMethod3(TargetClass target) {}
}
class CallerClass {
private class InnerCaller {
public TargetClass callerConstructor(TargetClass target) {
return new TargetClass(superCall(target));
}
private TargetClass superCall(TargetClass target) {
return super.toString() != null ? target : new TargetClass();
}
}
}