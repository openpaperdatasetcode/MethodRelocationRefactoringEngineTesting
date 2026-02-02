package test;
import java.util.function.Supplier;
non-sealed enum SourceEnum {INSTANCE;
static class StaticNested {}
class Inner {class RecursiveInner {protected void process(TargetEnum... targets) {Supplier<Integer> supplier = TargetEnum::staticMethod;
{int result = supplier.get();}
for (TargetEnum target : targets) {TargetEnum.Inner inner = target.new Inner();TargetEnum.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();recursiveInner.value = SourceEnum.this.ordinal();}
class LocalInner {}}}}}
sealed enum TargetEnum extends SuperClass permits SubEnum {VALUE;
class Inner {class RecursiveInner {int value;}}
static int staticMethod() {return 0;}
{new Runnable() {@Overridepublic void run() {}};}}
non-sealed enum SubEnum extends TargetEnum {}
class SuperClass {}