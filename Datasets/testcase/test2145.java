package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
public class SourceClass {class MemberInner {class InnerRecursive {/**
Processes target inner recursive instances
@param target the target class instance
@return processed target instance*/TargetClass methodToMove(TargetClass target) {TargetClass.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();int count = 0;
if (innerRec.value > 0) {innerRec.variableCall();count++; // PostfixExpression}
System.out.println(SourceClass.this.toString());
try {int result = innerRec.processArgs("arg1", "arg2");} catch (Exception e) {}
SubTargetClass subTarget = new SubTargetClass();Supplier<List<String>> supplier = subTarget::normalMethod;List<String> list = supplier.get();
return target;}}}
Runnable anonymous = new Runnable() {public void run() {}};}
class TargetClass implements TestInterface {class MemberInner {class InnerRecursive {int value;
void variableCall() {}
public int processArgs(String... args) {return args.length;}}}}
interface TestInterface {}
class SubTargetClass extends TargetClass {final List<String> normalMethod() {return new ArrayList<>();}}
