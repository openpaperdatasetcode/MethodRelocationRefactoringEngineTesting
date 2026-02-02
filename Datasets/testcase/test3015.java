package test;
public class SourceClass<T, U> {class InnerClass {public void varargsMethod(TargetClass<String, Integer> targetParam, String... args) {class LocalInner {}
TargetClass.MemberInner inner = targetParam.new MemberInner();inner.doAction();
String combined = args[0] + args[1];targetParam.process(combined);}}
static class StaticNested<V> {}}
private class TargetClass<K, V> implements MyInterface {class MemberInner {void doAction() {}}
void process(String input) {}}
interface MyInterface {}