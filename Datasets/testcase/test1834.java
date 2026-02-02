// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import java.util.function.Function;
protected class SourceClass {// Overloading method 1public final int overloadedMethod(TargetClass target) {// Local inner classclass LocalHandler {int handle() {return target.publicField + 5;}}
// ConstructorInvocation with 2 target fields (diff package)TargetClass.InnerClass inner = new TargetClass.InnerClass(target.publicField, target.getField2());
// Variable callint result = new LocalHandler().handle() + inner.getValue();
return result;}
// Overloading method 2public final int overloadedMethod(TargetClass target, int multiplier) {// Anonymous inner classFunction<TargetClass.InnerClass, Integer> func = new Function<>() {@Overridepublic Integer apply(TargetClass.InnerClass inner) {return inner.getValue() * multiplier;}};
// With boundsclass BoundedType<T extends TargetClass.InnerClass> {int process(T t) {return t.getValue();}}
// ConstructorInvocation with 2 target fields (diff package)TargetClass.InnerClass inner = new TargetClass.InnerClass(target.publicField, target.getField2());
// Variable call and depends on inner classreturn new BoundedType<>().process(inner) + func.apply(inner);}}
// Target package: com.targetpackage com.target;
class TargetClass {public int publicField;private int field2;
public TargetClass(int f1, int f2) {this.publicField = f1;this.field2 = f2;
// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {publicField *= 2;}};init.run();}
public int getField2() {return field2;}
public static class InnerClass {private int val1;private int val2;
public InnerClass(int v1, int v2) {this.val1 = v1;this.val2 = v2;}
public int getValue() {return val1 + val2;}}}