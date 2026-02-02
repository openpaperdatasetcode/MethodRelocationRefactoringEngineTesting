package test;
import java.lang.reflect.Method;import java.util.List;
protected interface TargetClass {static class Nested {int targetField;}Object m1();}
interface SourceClass extends ParentInterface {class Inner1 {}class Inner2 {}
default Object methodToMove() {return methodToMove(new TargetClass.Nested());}
default Object methodToMove(TargetClass.Nested obj) {private class PrivateInner {PrivateInner() {int val = obj.targetField;obj.targetField = 1;}}new PrivateInner();
{private abstract class AbstractSubClass extends Inner1 {abstract TargetClass method();TargetClass callChain() {return obj.m1().m2().m3();}}}
Object expr = (1);
try {Method method = SourceClass.class.getMethod("methodToMove");method.invoke(this);} catch (Exception e) {}
Inner2 inner = new Inner2();return inner;}
public default List<String> callMethod() {Object obj = new Object() {{SourceClass::methodToMove;methodToMove(1);}};return null;}}
interface ParentInterface {void methodToMove(int i);}
interface Intermediate1 {Intermediate2 m2();}
interface Intermediate2 {TargetClass m3();}