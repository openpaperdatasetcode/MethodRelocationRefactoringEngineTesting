package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
// Target interface (for target's implements feature)interface TargetInterface {}
// Target class (private modifier + implements)private class TargetClass implements TargetInterface {String value;}
// Source interface (for source's implements feature)interface SourceInterface {}
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnn {}
// Source class (public modifier + implements + two local inner classes)public class SourceClass implements SourceInterface {class SourceInner {// Parent static method (default access)static Object processTarget(TargetClass target) {return target.value;}}
@MethodAnn // has_annotationstatic Object methodToMove(TargetClass target) {// Variable callString var = target.value;
// Type declaration statementclass LocalType {String getValue() {return var + "_processed";}}LocalType local = new LocalType();
// Expression statementtarget.value = local.getValue();
// While statementint count = 0;while (count < 2) {target.value += "_loop" + count;count++;}
// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(target.value);lambda.run();
// Source feature: first local inner classclass LocalInner1 {Object getResult() {return target.value;}}
// Source feature: second local inner class (override_violation)class LocalInner2 extends SourceInner {// Override violation: parent static method cannot be overridden by inner classstatic Object processTarget(TargetClass t) {return t.value + "_violation";}}
return new LocalInner1().getResult();}}