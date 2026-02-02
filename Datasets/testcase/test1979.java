package test;
import java.util.List;import java.util.ArrayList;
sealed abstract class SourceClass permits SubClassOne, SubClassTwo {static class StaticNestedOne {}static class StaticNestedTwo {}
class Inner {class InnerRec {final int process(TargetClass target) {private int count = 1;count += target.superField;
for (int i = 0; i < count; i++) {TargetClass result = this.process(i);}
assert target.privateField > 0 : "Invalid value";if (target.privateField < 0) {throw new IllegalArgumentException();}
return count;}
private TargetClass process(int num) {return new TargetClass();}}}}
class SubClassOne extends SourceClass {}class SubClassTwo extends SourceClass {}
strictfp abstract class TargetClass {int superField;private int privateField;
void method() {class LocalInner {}}
TargetClass chainMethod() {return this;}
List<String> getList() {return new ArrayList<>();}}
@interface MyAnnotation {String value() default new TargetClass().chainMethod().chainMethod().getList().toString();}