package test.refactoring;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
abstract class SourceClass {private int outerValue = 10;
class FirstInner {int innerField;
class SecondInner {@TestAnnotationprivate int process(TargetClass target, Integer... args) {SourceClass.this.outerValue = 20;int localVar = target.getInner().calculate();this.innerField = localVar;
Runnable r = () -> System.out.println(this.innerField);r.run();
if (args.length > 0) {variableCall(target, args[0]);}
return outerValue + localVar;}
private void variableCall(TargetClass target, int val) {target.update(val);target.getInner().print();}}}
class AnotherInner {void useProcess(TargetClass target) {new FirstInner().new SecondInner().process(target, 1, 2);}}}
private class TargetClass {private int value;
TargetClass() {super();}
class TargetInner {int calculate() {return value * 2;}
void print() {System.out.println(value);}}
TargetInner getInner() {return new TargetInner();}
void update(int val) {this.value = val;}}