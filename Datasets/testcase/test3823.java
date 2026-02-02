package samepkg;
import java.util.List;import java.util.ArrayList;
sealed class SourceClass<T> permits SubSourceClass {class MemberInner {default int overloadedMethod() {return 0;}
default int overloadedMethod(List<String> listParam, TargetClass<Integer> targetParam) {class LocalType {}LocalType local = new LocalType();
int count = 2;if (targetParam.targetField == count) {};
do {TargetClass<Integer> varCall = targetParam;count--;} while (count > 0);
for (String item : listParam) {System.out.println(item + targetParam.targetField);}
return targetParam.targetField;}}}
final class SubSourceClass<T> extends SourceClass<T> {}
private class TargetClass<V> {int targetField;
{Runnable r = new Runnable() {@Overridepublic void run() {new TargetClass<>().targetField = 5;}};}}