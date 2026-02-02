package test;
import java.util.List;
private record SourceClass(int id) {class MemberInner {}
/**
Calculates sum using target inner components
@param targets Varargs of TargetClass
@return Sum of target values
*/
protected int calculate(TargetClass... targets) {
class LocalInner {}
int sum = 0;
for (TargetClass target : targets) {
sum += target.value();
sum += target.nested().getValue();
}
return sum;
}
public TargetClass processOthers() {try {OthersClass<String> others = new OthersClass<>();return others.getTarget();} catch (Exception e) {return new TargetClass(0);}}}
protected record TargetClass(int value) {static class Nested {int getValue() {return 5;}}
Nested nested() {return new Nested();}}
class OthersClass<T> {public TargetClass getTarget() {return super.getClass().isInstance(TargetClass.class) ? new TargetClass(10) : new TargetClass(0);}}