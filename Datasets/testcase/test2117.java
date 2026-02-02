package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
public class SourceClass {class InnerClass {@MyAnnotationfinal void methodToMove(TargetClass target) {TargetClass.StaticNested rawNested = new TargetClass.StaticNested();class LocalType {}LocalType local = new LocalType();
for (int i = 0; i < 3; i++) {target.new InnerClass().variableCall();rawNested.value = i;this.recursiveMethod(i);}}
private void recursiveMethod(int n) {if (n <= 0) return;new SourceClass().new InnerClass().recursiveMethod(n - 1);}}
Runnable anonymous = new Runnable() {public void run() {}};}
class TargetClass {static class StaticNested {int value;}
class InnerClass {void variableCall() {}}}