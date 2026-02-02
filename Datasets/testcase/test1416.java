package test.refactor.movemethod;
private class SourceClass {private class InnerRecClass {public abstract Object methodWithVarargs(final String... args);
public Object methodWithVarargs(int num) {return new Object();}}}
public class TargetClass {public class TargetInnerClass {public abstract Object methodWithVarargs(final String... args);
public Object methodWithVarargs(int num) {return new Object();}}
public void useTargetInner() {TargetInnerClass inner = new TargetInnerClass();Object result1 = inner.methodWithVarargs("arg1", "arg2");Object result2 = inner.methodWithVarargs(10);
synchronized (this) {Class<?> type = String.class;int count = 0;while (count < 2) {Object chainResult = inner.methodWithVarargs().toString().hashCode().toString();count++;}}}}
// Test class for refactoring verificationpublic class MoveMethodTest5045 {public static void main(String[] args) {TargetClass target = new TargetClass();target.useTargetInner();
SourceClass source = new SourceClass();SourceClass.InnerRecClass sourceInner = source.new InnerRecClass();Object sourceResult1 = sourceInner.methodWithVarargs("a", "b");Object sourceResult2 = sourceInner.methodWithVarargs(20);}}