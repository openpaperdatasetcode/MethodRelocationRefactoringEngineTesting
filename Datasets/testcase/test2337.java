package same.pkg;
import java.util.List;import java.io.IOException;
protected class Source<T<T> {static class StaticNested {}
class SourceInner {/**
Instance method in source_inner
@param targetParam parameter of Target type
@throws IOException required by requires_throws*/private void instanceMethod(Target targetParam) throws IOException {// Enhanced for statementfor (Target.Inner inner : targetParam.getInners()) {System.out.println(inner.getValue());}
// Variable callTarget.Inner innerVar = targetParam.new Inner();int varVal = innerVar.getValue();
// With boundsClass<? extends Number> boundedClass = targetParam.getNumberClass();
// Access instance methodString methodResult = targetParam.process();
// Requires throwstargetParam.throwCheckedException();}}
void methodWithLocal() {class LocalInner {}}}
abstract class Target {private int value;
class Inner {int getValue() {return value;}}
// Accessor methodsprivate int getValue() {return value;}
private void setValue(int val) {value = val;}
List<Inner> getInners() {return List.of(new Inner());}
Class<? extends Number> getNumberClass() {return Integer.class;}
String process() {return "processed";}
void throwCheckedException() throws IOException {}
// Object initialization with call_methodTarget initializedTarget = new Target() {@Overridevoid throwCheckedException() throws IOException {new Target().setValue(10);}};}
