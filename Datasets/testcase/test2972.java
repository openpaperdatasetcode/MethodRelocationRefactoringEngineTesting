package test;
import java.util.ArrayList;import java.util.List;
class SourceClass<T> {private int process(TargetClass targetParam, U... values) {
class LocalType {
String format(U val) {
return val.toString() + "_formatted";
}
}
LocalType formatter = new LocalType();
targetParam.targetField = values[0];List<String> processed = this.processHelper(targetParam, formatter.format(values[0]));
int result = 0;switch (values.length) {case 1:result = targetParam.targetField.intValue() + processed.size();break;default:result = OthersClass.calculate(targetParam.targetField);}
return result;}
protected List<String> processHelper(TargetClass target, String arg) {
List<String> list = new ArrayList<>();
list.add(target.targetField.toString() + arg);
return list;
}
}
class TargetClass<T> {public T targetField;
@Overridepublic String toString() {return targetField.toString();}
@Overrideprivate void finalize() throws Throwable {super.finalize();}}
class OthersClass {public static int calculate(U value) {
return value.intValue() * 2;
}
}