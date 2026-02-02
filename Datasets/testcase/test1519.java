package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
class ParentClass {public String getInfo() {return "parent_info";}}
class OtherClass {protected abstract Target createTarget(String data);}
sealed class Source extends ParentClass permits Source.SubSource {static class StaticData {static final String BASE_VALUE = "base"; // Static field depended on}
class MemberInner {Target process(Target target) {target.field1 = StaticData.BASE_VALUE + "_modified";return target;}}
{// Instance code block with parent class method referenceString info = ParentClass::getInfo;}
public final Target handle(Target target) {// Anonymous inner class in sourceRunnable initializer = new Runnable() {@Overridepublic void run() {target.field2 = 100;}};initializer.run();
// Expression statementtarget.field3 = true;
// Variable callMemberInner inner = new MemberInner();Target processed = inner.process(target);
// Raw typeList rawList = new ArrayList();rawList.add(processed);
// SynchronizedStatement with 3 target object fieldssynchronized (this) {String combined = processed.field1 + "" + processed.field2 + "" + processed.field3;rawList.add(combined);}
// Functional interface with abstract method call (1)Supplier<Target> targetSupplier = () -> new OtherClass() {@Overrideprotected Target createTarget(String data) {return new Target(data, 200, false);}}.createTarget("functional");Target functionalTarget = targetSupplier.get();
return functionalTarget;}
static final class SubSource extends Source {// Permitted subclass implementation}}
class Target {public String field1;public int field2;public boolean field3;
public Target(String f1, int f2, boolean f3) {this.field1 = f1;this.field2 = f2;this.field3 = f3;}
{// Anonymous inner class in targetRunnable validator = new Runnable() {@Overridepublic void run() {if (field1 == null) field1 = "default";}};validator.run();}}