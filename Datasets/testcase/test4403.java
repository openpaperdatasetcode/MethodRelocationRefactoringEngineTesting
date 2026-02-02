package test;
import java.util.function.Function;
protected class Source {static class StaticNested {int nestedField = 1;}
private void instanceMethod(Target targetParam) {privateAssertStatement(targetParam);
class LocalType {int localField;}LocalType localObj = new LocalType();
for (int i = 0; i < 3; i++) {variableCall(targetParam);}
switch (targetParam.targetField) {case 1:Function<Integer, String> typeMethodRef = Target::protectedTypeMethod;String refResult = typeMethodRef.apply(1);break;default:break;}
Runnable lambda = () -> {int result = this::synchronizedVarargsMethod;};}
private void privateAssertStatement(Target target) {assert target.targetField == 1 : "Target field must be 1";}
private void variableCall(Target target) {int val = target.targetField;target.methodWithLocalInner();}
synchronized int synchronizedVarargsMethod(int... nums) {return nums.length > 0 ? nums[0] : 0;}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {Source.this.synchronizedVarargsMethod(1);}};}}
public class Target {int targetField = 1;
protected static String protectedTypeMethod(int num) {return String.valueOf(num);}
void methodWithLocalInner() {class LocalInner {void localMethod() {System.out.println(targetField);}}LocalInner local = new LocalInner();local.localMethod();}}