package test.refactoring.movemethod;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface CustomAnn {String value() default "";}
class TargetClass {class TargetInner {private int value;
public TargetInner(int v) {this.value = v;}
public void printValue() {System.out.println(value);}
public TargetInner copy() {return new TargetInner(this.value);}}}
class ParentClass {TargetClass.TargetInner getInnerInstance(int v) {return new TargetClass().new TargetInner(v);}}
private class SourceClass extends ParentClass {class SourceInner {@CustomAnn(value = "#{this.processInner(target.new TargetInner(1))}")void processInner(TargetClass.TargetInner... targets) {TargetClass.TargetInner localVar = new TargetClass().new TargetInner(0);Object varCall = localVar.value;
for (TargetClass.TargetInner target : targets) {localVar = this.getInnerInstance(1);target.printValue();SourceClass.this.getInnerInstance(2).printValue();}}}
{new Runnable() {@Overridepublic void run() {SourceInner inner = new SourceInner();inner.processInner(new TargetClass().new TargetInner(5));}}.run();}}
import org.junit.Test;
public class MoveMethodTest3031 {@Testpublic void testVarargsMethod() {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();inner.processInner(new TargetClass().new TargetInner(3), new TargetClass().new TargetInner(7));}}