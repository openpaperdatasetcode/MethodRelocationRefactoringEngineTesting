package same.pkg;
import java.util.function.Function;
public class SourceClass extends ParentClass {class SourceMemberInner {int processTargetInner(TargetClass.TargetInner targetInner) {return targetInner.getValue();}}
public int handleTargets(TargetClass.TargetInner... targetInnerParams) {if (targetInnerParams == null) {throw new NullPointerException("TargetInner parameters cannot be null");}
SourceMemberInner memberInner = new SourceMemberInner();int total = 0;
Function<TargetClass.TargetInner, Integer> func1 = TargetClass.TargetInner::getValue;Function<TargetClass.TargetInner, Integer> func2 = t -> t.getValue() * 2;
for (TargetClass.TargetInner inner : targetInnerParams) {if (inner == null) {continue;}
switch (inner.getValue() % 2) {case 0:total += func1.apply(inner);break;case 1:total += func2.apply(inner);break;}
total += outerProtectedField;}
Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Total: " + total);}};anon.run();
return total;}}
class ParentClass {protected int outerProtectedField = 5;}
public class TargetClass {public static class TargetInner {private int value;
public TargetInner(int value) {this.value = value;}
public int getValue() {return value;}
public TargetInner recursiveCreate(int depth) {if (depth <= 0) {return new TargetInner(value);}return new TargetInner(recursiveCreate(depth - 1).getValue() + 1);}}}