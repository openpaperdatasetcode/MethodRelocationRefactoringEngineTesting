package test.same;
import java.util.function.Supplier;
class SourceClass {class MemberInner {}static class StaticNested {}
/**
Method Javadoc for overloading methods*/final Object overloadMethod(TargetClass target) {super.toString();Object var = target;
Supplier<Integer> lambda = () -> new TargetClass.StaticNested().createInner().new InnerClass(1).getValue();
int result = (lambda.get() > 0) ? TargetClass.StaticNested.staticMethod() : 0;return var;}
final Object overloadMethod(TargetClass target, String str) {return null;}}
class TargetClass extends ParentClass {static class StaticNested {InnerHolder createInner() {return new InnerHolder();}
static int staticMethod() {return 1;}
class InnerHolder {final class InnerClass {int value;
final InnerClass(int val) {this.value = val;}
int getValue() {return value;}}}}}
class ParentClass {}