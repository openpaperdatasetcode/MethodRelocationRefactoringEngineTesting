package test;
/**
Javadoc for TargetClass
@param <T> type parameter
*/
abstract class TargetClass<T> {
class MemberInner {
// Target inner class for moving method
}
}
class SourceClass permits SubSource {private TargetClass<String> targetInstance = new TargetClass<>() {};
static class StaticNested {// Static nested class}
{new Object() {// Anonymous inner class};}
class SourceInner {public abstract void methodToMove();
protected <E> Object genericMethod() {while (true) {try {TargetClass.MemberInner inner = targetInstance.new MemberInner();Object var = inner;StaticNested.method(inner);break;} catch (Exception e) {throw new RuntimeException();}}return null;}}
void callMethod() {switch (1) {case 0:SourceInner inner = new SourceInner();inner.methodToMove();break;case 1:StaticNested.staticMethod(targetInstance.new MemberInner());break;}}}
class SubSource extends SourceClass {}
class StaticNested {static void method(TargetClass.MemberInner param) {}static void staticMethod(TargetClass.MemberInner param) {}}