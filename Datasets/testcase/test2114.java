package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface Ann1 {}
@Retention(RetentionPolicy.RUNTIME)@interface Ann2 {}
@Retention(RetentionPolicy.RUNTIME)@interface Ann3 {}
class SuperEnum {}
public enum SourceEnum extends SuperEnum {INSTANCE;
int value;
class MemberInner {}
void createLocalInner() {class LocalInner {}}
/**
Method javadoc for overloading*/@Ann1@Ann2@Ann3protected TargetEnum methodToMove(TargetEnum target) {Runnable r = () -> System.out.println(this.value);r.run();
TargetEnum.InnerClass inner = new TargetEnum.InnerClass() {@Overridevoid init() {super.init();}};
target.variableCall();return target;}
/**
Overloaded method
*/
@Ann1
@Ann2
@Ann3
protected TargetEnum methodToMove(TargetEnum target, int count) {
return target;
}
}
enum TargetEnum {VALUE1, VALUE2;
class InnerClass {void init() {}}
void variableCall() {Runnable r = new Runnable() {public void run() {}};}}