package test;
sealed abstract class SourceClass permits SourceSubClass {static class SourceStaticNested {}
class SourceInner {}
private int overloadedMethod(TargetClass.TargetStaticNested.TargetInner param) {int var = param.value;variableCall(var);
dependsOnInnerClass(new SourceStaticNested());
return var;}
private int overloadedMethod(String str) {return str.length();}
private void variableCall(int val) {}
private void dependsOnInnerClass(SourceStaticNested ssn) {}}
class SourceSubClass extends SourceClass {}
abstract class TargetClass {static class TargetStaticNested {static class TargetInner {int value;}}}