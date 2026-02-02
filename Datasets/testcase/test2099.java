package test;
interface SourceInterface {}interface TargetInterface {}
protected class SourceClass<T> implements SourceInterface {static class StaticNested {}
class FirstInner {class InnerRecursive {Object methodToMove(TargetClass targetParam) {super();new SuperClass();
if (targetParam.instanceField == null) {throw new IllegalArgumentException();}
targetParam.variableCall();Object fieldVal = targetParam.instanceField;
return fieldVal;}
Object methodToMove(TargetClass targetParam, String arg) {return targetParam.instanceField;}}}}
class SuperClass {}
private class TargetClass implements TargetInterface {Object instanceField;
class MemberInner {}
void variableCall() {}}