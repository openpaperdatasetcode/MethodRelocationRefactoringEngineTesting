package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
record SourceRecord(int id) {static class StaticNested {class SourceInner {record SourceInnerRec(String name) {private int process(TargetRecord target, String... args) {synchronized (this) {class LocalType {int value;}LocalType local = new LocalType();local.value = 0;int i = 0;while (i < args.length) {local.value += target.count + super.id;i++;}try {Object result = super.toString();local.value += (int) result.hashCode();} catch (Exception e) {// No new exception}return local.value;}}}}}
class MemberInner {void useMethod() {TargetRecord target = new TargetRecord(5, "test");int res = new StaticNested.SourceInner.SourceInnerRec("inner").process(target, "a", "b");}}}
/**
Target record with extensions and anonymous class
*/
public record TargetRecord(int count, String data) extends SuperRecord {
public TargetRecord {
Runnable anon = new Runnable() {
@Override
public void run() {
System.out.println(data);
}
};
anon.run();
}
}
class SuperRecord {int id = 10;
@Overridepublic String toString() {return "SuperRecord";}}
class SubClass extends TargetRecord {protected SubClass(int count, String data) {super(count, data);}
protected int calculate() {return super.count;}
protected int calculate(int multiplier) {return super.count * multiplier;}}
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {int value() default new SubClass(3, "").calculate();}