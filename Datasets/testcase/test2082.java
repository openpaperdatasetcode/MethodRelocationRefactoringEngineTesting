package test;
sealed class ParentClass permits SourceClass {}
public class SourceClass extends ParentClass {private int outerPrivate = 100;static String staticField = "static";
static class StaticNested {}
void createLocalInner() {class LocalInner {}}
TargetClass methodToMove(Object... args) {TargetClass target = new TargetClass();target.thisField = "value";
Object[] array = { new TargetClass().varargsMethod(1, 2, 3) };
target.variableCall();System.out.println(outerPrivate);
if (args.length > 0) {TargetClass.InnerClass.method(target);} else {// do nothing}
return target;}
TargetClass methodToMove(String arg, Object... args) {return new TargetClass();}}
protected class TargetClass extends SuperClass {String thisField;
class InnerClass {static void method(TargetClass target) {class LocalInner {}new LocalInner();}}
void variableCall() {class TargetLocalInner {}new TargetLocalInner();}
private TargetClass varargsMethod(int... params) {return new TargetClass();}}
class SuperClass {}