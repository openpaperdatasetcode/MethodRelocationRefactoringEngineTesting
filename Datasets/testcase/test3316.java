package test;
import java.lang.reflect.Method;
final class SourceClass extends ParentClass {class InnerClass1 {}class InnerClass2 {}
class SourceInner {class SourceInnerRec {/**
Javadoc: Processes bounded target and accesses super fields
@param target Final target with upper bound constraint*/private <T extends Number> void moveMethod(FinalTarget<T> target) {super();labeledBlock: {protected int superField1 = SourceClass.super.superFieldA;protected int superField2 = SourceClass.super.superFieldB;protected int superField3 = SourceClass.super.superFieldC;
target.process(superField1);target.calculate(superField2, superField3);break labeledBlock;}
try {Method method = FinalTarget.class.getDeclaredMethod("process", int.class);method.setAccessible(true);method.invoke(target, 100);} catch (Exception e) {}}}}}
abstract class ParentClass {protected int superFieldA = 10;protected int superFieldB = 20;protected int superFieldC = 30;}
final class FinalTarget<T extends Number> {{this.process(5);this.process(10L);this.process(15.0);}
public void process(int val) {class LocalInner {}new LocalInner();}
public void process(long val) {class LocalInner {}new LocalInner();}
public void process(double val) {class LocalInner {}new LocalInner();}
public void calculate(int a, int b) {}}