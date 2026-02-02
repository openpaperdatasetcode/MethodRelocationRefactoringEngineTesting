package test;
private class SourceClass {private TargetClass targetField = new TargetClass();
// Static nested class (source_class feature)static class SourceStaticNested {}
// Local inner class (source_class feature)void createLocalInner() {class SourceLocalInner {void invokeRecursive() {new SourceInner().recursiveMethod(3);}}new SourceLocalInner().invokeRecursive();}
class SourceInner {// Recursive method to be refactoredstrictfp TargetClass recursiveMethod(int depth) {if (depth <= 0) {return targetField;}
// Assert statementassert targetField != null;
// Synchronized statementsynchronized (this) {// For statementfor (int i = 0; i < depth; i++) {// Expression statement + Variable calltargetField.count += i;}}
// Recursionreturn recursiveMethod(depth - 1);}}}
/**
Javadoc for TargetClass
Contains static nested class and instance fields*/public class TargetClass {int count;
// Static nested class (target_class feature)static class TargetStaticNested {}
// Overloaded methods for call_method featureTargetClass increment() {count++;return this;}
TargetClass multiply(int factor) {count *= factor;return this;}
String getResult() {return String.valueOf(count);}}
class OthersClass {// call_method implementationstrictfp String callOverloaded(TargetClass target, int caseValue) {switch (caseValue) {case 1:return target.increment().getResult();case 2:return target.increment().multiply(2).getResult();default:return target.increment().multiply(3).getResult();}}}