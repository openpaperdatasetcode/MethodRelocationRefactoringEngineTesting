package test;
import java.lang.reflect.Method;import java.util.function.Consumer;
protected record SourceRecord(int data) implements Consumer<String> {private int outerPrivate;static class StaticNested {}
class MemberInner {}
@Overridepublic void accept(String s) {}
public int moveMethod(TargetRecord target) {super();RawType raw = new RawType();
if (target == null) {throw new NullPointerException("Target is null");}
TargetRecord.Inner inner = target.new Inner();TargetRecord.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();outerPrivate = recursiveInner.value;
try {Method method = TargetRecord.Inner.RecursiveInner.class.getMethod("compute");} catch (NoSuchMethodException e) {throw new RuntimeException(e);}
return recursiveInner.compute() + outerPrivate;}}
record TargetRecord(String name) {class Inner {class RecursiveInner {int value;
int compute() {return value * 2;}}}}
class RawType<T> {}
