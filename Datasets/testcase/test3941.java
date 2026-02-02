import java.util.Objects;
public class SourceClass {private int sourceInstanceField = 5;private static int staticField = 10;
public static class FirstStaticNested {public class NestedInner {private int recursiveMethod(TargetClass<Integer> target, int depth) {if (depth > 2) {throw new IllegalArgumentException("Depth exceeds limit");}
class TypeDeclaration {int getCombinedValue() {return sourceInstanceField + staticField;}}TypeDeclaration typeDecl = new TypeDeclaration();
TargetClass<Integer> target1 = new TargetClass<>(10);TargetClass<Integer> target2 = new TargetClass<>(20);target1.sharedField = 1;target2.sharedField = 2;
variableCall(target.memberInner);int overloadedResult = overloadedMethod(target) + overloadedMethod(target, depth);
return depth + typeDecl.getCombinedValue() + overloadedResult + recursiveMethod(target, depth + 1);}
private void variableCall(TargetClass.MemberInner<Integer> inner) {inner.updateValue(sourceInstanceField);}
private TargetClass<Integer> overloadedMethod(TargetClass<Integer> target) {return target;}
private int overloadedMethod(TargetClass<Integer> target, int depth) {return target.value + depth;}}}
public static class SecondStaticNested {private ParentClass parent = new ParentClass();
public void callInLambda() {Runnable lambda = () -> {TargetClass<Integer> target = new TargetClass<>(15);int result = parent.callOverloaded(target) + parent.callOverloaded(target, 2);};lambda.run();}}}
public class TargetClass<T extends Number> {public int sharedField;public T value;public MemberInner<T> memberInner = new MemberInner<>();
public TargetClass(T value) {this.value = value;}
public class MemberInner<T extends Number> {private int innerValue;
public void updateValue(int val) {this.innerValue = val;}
public int getInnerValue() {return innerValue;}}}
class ParentClass {private int callOverloaded(TargetClass<Integer> target) {try {return target.value.intValue() + target.memberInner.getInnerValue();} catch (NullPointerException e) {return 0;}}
private int callOverloaded(TargetClass<Integer> target, int multiplier) {try {return (target.value.intValue() * multiplier) + target.sharedField;} catch (NullPointerException e) {return 0;}}}