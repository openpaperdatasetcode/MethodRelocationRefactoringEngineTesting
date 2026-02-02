package samepkg;
import java.util.function.Function;
class SourceClass<T> {public class MemberInnerClass {public record SourceInnerRec() {public final int processVarargs(TargetClass targetParam, U... args) {
// VariableDeclarationStatement (source pos, this.field x3)
private int field1 = targetParam.fieldA;
private int field2 = targetParam.fieldB;
private int field3 = targetParam.fieldC;
// 3 final SuperMethodReference expressionsfinal Function<U, Integer> ref1 = OthersClass.super::getVal1;final Function<U, Integer> ref2 = OthersClass.super::getVal2;final Function<U, Integer> ref3 = OthersClass.super::getVal3;
// While statementint sum = 0;int index = 0;while (index < args.length) {sum += ref1.apply(args[index]) + ref2.apply(args[index]) + ref3.apply(args[index]);index++;}
// Access instance method + variable calltargetParam.updateFields(sum);OthersClass others = new OthersClass();
// Accessor method in ternary operators (2 accessors, chain call)int accessorVal = (sum > 0) ? others.getAccessor1(targetParam).getAccessor2().getAccessor3() : 0;
// Throw statementif (targetParam.fieldA < 0) {throw new IllegalArgumentException("FieldA cannot be negative");}
// Call sub_class overloading method in object initialization (chain call)String subResult = new SubClass().m1(targetParam).m2(sum).m3();
return sum + accessorVal + subResult.length();}}}
public void outerMethod() {// Local inner classclass LocalInnerClass {void invokeProcess() {new MemberInnerClass.SourceInnerRec().processVarargs(new TargetClass<>(), (T) Integer.valueOf(1), (T) Integer.valueOf(2));}}new LocalInnerClass().invokeProcess();}}
class TargetClass {
public int fieldA;
public int fieldB;
public int fieldC;
public void updateFields(int val) {// Local inner classclass LocalInnerTarget {void calculate() {fieldA += val;fieldB += val;fieldC += val;}}new LocalInnerTarget().calculate();}
public record TargetInnerRec(int value) {public int getValue() {return value;}}}
class ParentOthers {protected int getVal1(Object obj) { return 1; }protected int getVal2(Object obj) { return 2; }protected int getVal3(Object obj) { return 3; }}
class OthersClass extends ParentOthers {// Accessor methods (chain call)public TargetClass getAccessor1(TargetClass target) { return target; }public TargetClass<?> getAccessor2() { return new TargetClass<>(); }public int getAccessor3() { return 5; }}
class SubClass extends ParentSub {// Overloading methods (chain call)public SubClass m1(TargetClass<?> target) { return this; }public SubClass m2(int val) { return this; }public String m3() { return "subResult"; }public String m3(String suffix) { return "subResult" + suffix; }}
class ParentSub {}