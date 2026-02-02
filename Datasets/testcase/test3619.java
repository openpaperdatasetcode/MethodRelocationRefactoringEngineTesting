package test;
final class SourceClass permits ExtendSourceClass {static class StaticNested {static int nestedStaticField;}
public TargetClass varargsMethod(TargetClass target, String... args) {class LocalInner {void process() {static int var1 = target.innerField;static int var2 = this.target.innerField;static int var3 = SourceClass.this::varargsMethod;}}new LocalInner().process();
variableCall(target.memberInner);StaticNested.nestedStaticField = args.length;
return target;}
private void variableCall(TargetClass.MemberInner inner) {inner.printField();}}
class ExtendSourceClass extends SourceClass {}
public class TargetClass {int innerField = 100;
class MemberInner {void printField() {System.out.println(innerField);}}
MemberInner memberInner = new MemberInner();
{new Runnable() {public void run() {innerField = 200;}}.run();}}