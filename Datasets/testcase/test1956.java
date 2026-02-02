package test;
import other.OtherPackageClass;
class SourceClass {private int outerPrivate = 10;
static class StaticNested {static int staticField = 1;}
// Anonymous inner classRunnable anonymous = () -> {new SourceClass().method(new TargetClass());};
private TargetClass method(TargetClass target) throws Exception {if (target == null) {throw new NullPointerException("Target cannot be null");}
// Access outer private fieldtarget.setValue(outerPrivate);
// Assert statementassert target.getStaticValue() == StaticNested.staticField : "Static field mismatch";
// Continue statement in loopfor (int i = 0; i < 5; i++) {if (i == 2) {continue;}target.increment();}
// SwitchStatement with super.field=1 in different packageOtherPackageClass.handleSwitch(target, (val) -> {static switch (val) {case 1 -> target.setSuperField(1);default -> target.setSuperField(0);}});
// Instance method call with lambda in expressionTargetClass result = new SubTarget().process((t) -> {t.setCount(target.getCount() + 1);return t;});
// Return thisreturn target;}}
strictfp class TargetClass extends ParentClass {private int count;private int value;
static class Nested {static int getDefault() {return 1;}}
void increment() {count++;}
int getCount() {return count;}
void setCount(int count) {this.count = count;}
void setValue(int value) {this.value = value;}
int getStaticValue() {return Nested.getDefault();}}
class ParentClass {protected int superField;
void setSuperField(int value) {this.superField = value;}}
class SubTarget extends TargetClass {TargetClass process(java.util.function.Function<TargetClass, TargetClass> func) {return func.apply(this);}}
package other;
import test.TargetClass;
public class OtherPackageClass {public static void handleSwitch(TargetClass target, java.util.function.Consumer<Integer> switchHandler) {switchHandler.accept(target.getStaticValue());}}