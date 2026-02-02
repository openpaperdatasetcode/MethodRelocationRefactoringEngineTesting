package test;
interface SourceInterface {int targetField = 0;
public default void methodToMove() {try {SuperClass superObj = new SuperClass();int localVar = targetField;TargetInterface.NestedClass.innerMethod();NestedClass inner = new NestedClass();inner.instanceMethod();} catch (Exception e) {e.printStackTrace();}}
class NestedClass {void instanceMethod() {}}}
protected interface TargetInterface {static class NestedClass {static void innerMethod() {}}}
class SuperClass {}