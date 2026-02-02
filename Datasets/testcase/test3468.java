package test;
import java.lang.reflect.Method;
/**
Interface for source_class implements feature
*/
interface Processable {
Object process(TargetClass target) throws Exception;
}
/**
Public source class (same package) with implements feature/
public class SourceClass implements Processable {
/*
Public abstract method_feature (1 parameter, source, abstract)
*/
public abstract int abstractHelper(TargetClass target);
// Static code block (method_feature position)static {TargetClass initTarget = new TargetClass();SourceClass source = new SourceClass() {@Overridepublic int abstractHelper(TargetClass target) {// instanceReference.methodName(arguments)target.getInner().processInner();return target.getCount();}};try {source.process(initTarget);} catch (Exception e) {throw new RuntimeException("Static init failed", e);}}
/**
Final overriding method (position: source)*/@Overridepublic final Object process(TargetClass target) throws Exception { // requires_throws// Variable callvariableCall(target);
// Call abstract method_feature via instanceReferenceint helperResult = this.abstractHelper(target);
// Used by reflectionMethod innerMethod = TargetClass.Inner.class.getMethod("processInner");innerMethod.invoke(target.getInner());
return helperResult + target.getCount();}
private void variableCall(TargetClass target) {target.doTask();}}
/**
Public target class with javadoc and member inner class (target_features)*/public class TargetClass {private int count = 0;
/**
Javadoc for target class member inner class
Provides inner processing logic
/
public class Inner {
/*
Processes inner business logic
*/
public void processInner() {
count++;
}
}
/**
Gets inner class instance
@return Inner class instance
*/
public Inner getInner() {
return new Inner();
}
/**
Gets count value
@return Current count
*/
public int getCount() {
return count;
}
/**
Executes target class task
*/
public void doTask() {
count++;
}
}
/**
Concrete implementation of SourceClass for test
*/
class SourceImpl extends SourceClass {
@Override
public int abstractHelper(TargetClass target) {
return target.getCount() * 2;
}
}
/**
Test class for Move Method refactoring verification
*/
public class RefactoringTest {
public static void main(String[] args) throws Exception {
TargetClass target = new TargetClass();
SourceClass source = new SourceImpl();
Object result = source.process(target);
assert result.equals(2 + 2) : "Refactoring test failed";
System.out.println("Refactoring test passed");
}
}