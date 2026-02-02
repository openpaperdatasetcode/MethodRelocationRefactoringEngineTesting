package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
final class SourceClass<T> {static class StaticNested {}
class SourceInner {class SourceInnerRec {@MyAnnotTargetClass.TargetInner methodToMove(TargetClass.TargetInner targetParam) {int var = targetParam.value;variableCall(var);
assert targetParam != null;
for (int i = 0; i < 5; i++) {if (i == 2) continue;}
StaticNested nested = new StaticNested();dependsOnInnerClass(nested);
return new TargetClass().new TargetInner();}
private void variableCall(int val) {}private void dependsOnInnerClass(StaticNested sn) {}}}}
public class TargetClass {class TargetInner {int value;TargetInner() {}TargetInner(int v) {this.value = v;}}}