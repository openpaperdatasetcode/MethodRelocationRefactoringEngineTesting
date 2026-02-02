package test;
interface SourceInterface {static class StaticNested1 {}static class StaticNested2 {}
final void process(TargetInterface target) {if (target == null) {throw new NullPointerException();}
volatile Runnable ref1 = TargetInterface.SuperInterface::method1;volatile Runnable ref2 = TargetInterface.SuperInterface::method2;
String field = target.inner.field;target.inner.method(field);}}
/**
Javadoc for TargetInterface (target_feature: javadoc)/
public interface TargetInterface extends SuperInterface {
/*
Member inner class (target_feature: member inner class)
*/
class Inner {
String field;
void method(String param) {}
}
Inner inner = new Inner();}
interface SuperInterface {default void method1() {}default void method2() {}}