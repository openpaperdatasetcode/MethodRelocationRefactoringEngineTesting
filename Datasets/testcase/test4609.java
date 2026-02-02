package test;
interface SourceInterface<T> {class StaticNested {}
class MemberInner {int field;}
/**
Constructor javadoc
*/
protected int SourceInterface(TargetInterface<Integer> param) {
param.instanceMethod(2);
MemberInner inner = new MemberInner();
int val = inner.field;
return 0;
}
TargetInterface<Integer> method(int i) throws Exception {return new TargetInterface<>() {};}
TargetInterface<Integer> method(String s) {SourceInterface<?> source = new SourceInterface<>() {};return source.method(1);}}
public interface TargetInterface<V> {default void functionalInterface() {class LocalInner {}new LocalInner();}
protected String instanceMethod(int num) {return super.toString();}}