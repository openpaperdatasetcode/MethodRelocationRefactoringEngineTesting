package test;
interface Processor<T> {int process(T data);}
class SourceClass<T> implements Processor<T> {public static class StaticNested {public static int staticHelper(U value) {
return value.toString().length();
}
}
public class MemberInner {public int innerProcess(U value) {
return value.hashCode();
}
}
@Overridepublic int process(TargetClass<T> targetParam) {TargetClass<T>.LocalInner targetLocal = targetParam.new LocalInner();MemberInner inner = new MemberInner();
int result = 0;for (int i = 0; i < 3; i++) {if (i == 1) {break;}result += targetLocal.calculate(targetParam.targetField);result += inner.innerProcess(targetParam.targetField);result += StaticNested.staticHelper(targetParam.targetField);}
return result;}}
class TargetClass<T> {public T targetField;
public class LocalInner {public int calculate(T value) {class NestedLocal {int compute() {return value.hashCode() * 2;}}return new NestedLocal().compute();}}}
