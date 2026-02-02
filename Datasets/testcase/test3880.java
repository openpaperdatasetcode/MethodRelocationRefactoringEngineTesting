package test;
class SourceClass {TargetClass<String> targetField = new TargetClass<>();
class SourceInner {public TargetClass<String> methodToMove() {int count = 0;while (count < 3) {targetField.superField = 1;;variableCall();count++;}
List rawList = new ArrayList();
class LocalInner {}new LocalInner();
return targetField;}
private void variableCall() {}}}
protected class TargetClass<T> extends ParentClass {TargetClass() {new Runnable() {@Overridepublic void run() {}};}}
class ParentClass {int superField;}