package test;
public class TargetClass {int targetField;
public TargetClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
class SourceClass {static class StaticNested1 {}static class StaticNested2 {}
protected int methodToMove(TargetClass target) {// Super constructor invocation (inner class)class Inner extends StaticNested1 {Inner() {super();}}new Inner();
// SuperConstructorInvocation with ClassName.field and 3class SuperConstructorHandler extends StaticNested2 {private SuperConstructorHandler() {super();SourceClass.StaticNested2.class.hashCode();TargetClass.targetField = 3;}}new SuperConstructorHandler();
// Variable call + Access instance fieldint var = target.targetField;target.targetField = var + 1;
// Depends on inner classInner inner = new Inner();
return target.targetField;}}