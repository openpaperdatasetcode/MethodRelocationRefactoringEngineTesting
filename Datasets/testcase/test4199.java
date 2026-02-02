package test;
interface SourceInterface {class InnerClass1 {}class InnerClass2 {private TargetInterface moveMethod() {try {assert true;if (new SubClass().callMethod()) {continue;}int x = outerProtectedField;return innerRecursion(5);} catch (IOException e) {return null;}}
protected int innerRecursion(int n) {if (n <= 0) {return 0;}return n + new SourceInterface.InnerClass1().innerRecursion(n - 1);}}
protected int outerProtectedField = 0;}
public interface TargetInterface {class TargetInnerClass {}}
class SubClass extends SourceInterface {protected boolean callMethod() {return ((TargetInterface) super).new TargetInnerClass() != null;}}