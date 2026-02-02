package test;
import other.DiffPackageClass;import java.util.List;
public class SourceClass {protected int protectedField;static class FirstStaticNested {}static class SecondStaticNested {}
final int methodToMove(TargetClass target) {super();if (target == null) {throw new NullPointerException();}
List<? extends Number> boundedList = target.boundedField;int outerProtected = this.protectedField;
DiffPackageClass.process(target);synchronized (target.lockField) {target.variableCall();}
try {throw new Exception();} catch (Exception e) {Runnable r = target::strictfpMethod;r.run();}
return target.countField;}}
/**
Target class with Javadoc*/private class TargetClass {int countField;Object lockField;List<? extends Number> boundedField;
class MemberInner {}
strictfp void strictfpMethod() {}
void variableCall() {}}
package other;
import test.TargetClass;
public class DiffPackageClass {public static void process(TargetClass target) {}}