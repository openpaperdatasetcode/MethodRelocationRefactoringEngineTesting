package source.pkg;
import target.pkg.TargetClass;import java.lang.reflect.Method;import java.util.function.Supplier;
non-sealed record SourceClass<T extends Number>(T data) {static class StaticNestedFirst {TargetClass wrapTarget(TargetClass target) {return target;}}
static class StaticNestedSecond {boolean isValid(TargetClass target) {return target != null;}}
TargetClass process(TargetClass targetParam) {TargetClass[] targetArray = {new TargetClass(1), new TargetClass(2), targetParam};;
OthersClass others = new OthersClass();TargetClass result = others.isValid(targetParam)? others.process(targetParam, this): others.getDefault(this);
try {Method method = TargetClass.class.getMethod("getValue");Object val = method.invoke(targetArray[2]);} catch (Exception e) {e.printStackTrace();}
Supplier<TargetClass> supplier = () -> {StaticNestedFirst first = new StaticNestedFirst();return first.wrapTarget(this.process(targetArray[0]));};supplier.get();
return result;}}
class OthersClass {public boolean isValid(TargetClass target) {return target != null;}
public TargetClass process(TargetClass target, SourceClass<?> source) {return target;}
public TargetClass getDefault(SourceClass<?> source) {return new TargetClass(0) {@Overridepublic int getValue() {return super.getValue() + 5;}};}}
package target.pkg;
protected record TargetClass(int value) {public int getValue() {return value;}}
