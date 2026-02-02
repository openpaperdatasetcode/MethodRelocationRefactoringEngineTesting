package test;
import java.util.ArrayList;
class ParentClass {int superField1;String superField2;Object superField3;
void parentMethod() {}}
class SourceClass<T> extends ParentClass {/**
Javadoc for the instance method
@param target the target class parameter
@return Object result*/Object methodToMove(TargetClass target) {TargetClass.StaticNested nested = new TargetClass.StaticNested();rawTypeMethod(new ArrayList());
volatile TargetClass volatileInstance = new TargetClass();int val1 = super.superField1;String val2 = super.superField2;Object val3 = super.superField3;
SourceClass<T> source = new SourceClass<>();super.toString();
int i = 0;while (i < 5) {i++;}
Runnable runnable;if (val1 > 0) {runnable = target::abstractMethod;} else {runnable = target::abstractMethod;}
Runnable ternaryRunnable = (val2 != null) ? source::parentMethod : source::parentMethod;
return target;}
void rawTypeMethod(ArrayList list) {}
public abstract void abstractMethod();}
protected class TargetClass {static class StaticNested {}
public abstract void abstractMethod();}