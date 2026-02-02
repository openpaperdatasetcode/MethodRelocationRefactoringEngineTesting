package test;
import java.lang.reflect.Method;import java.util.List;
sealed record SourceClass<T>(T data) permits SubSourceRecord {class MemberInner1 {void accessTargetInner(TargetClass<T> target) {target.new InnerInner inner = target.new Inner();inner.modifyField(data);}}
class MemberInner2 {}
private void method(TargetClass<T> target) {try {new MemberInner1().accessTargetInner(target);
Method innerMethod = TargetClass.Inner.class.getMethod("modifyField", Object.class);innerMethod.invoke(target.new Inner(), data);
target.items().add(data);} catch (Exception e) {throw new RuntimeException(e);}}}
record SubSourceRecord<T>(T data) extends SourceClass<T> {SubSourceRecord(T data) {super(data);}}
/**
Sealed target record class with member inner class
Stores a list of items as its component
/
sealed record TargetClass<S>(List<S> items) permits SubTargetRecord {
/*
Member inner class for modifying the outer record's items
*/
class Inner {
void modifyField(S value) {
items.add(value);
}
}
}
record SubTargetRecord<S>(List<S> items) extends TargetClass<S> {SubTargetRecord(List<S> items) {super(items);}}