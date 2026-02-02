package test.same;
/**
Final source class with static nested and local inner classes*/final class SourceClass {static class StaticNested {}
/**
Varargs method returning TargetClass type
@param targets array of TargetClass instances
@return first non-null TargetClass instance*/protected TargetClass varargsMethod(TargetClass... targets) {type DeclarationStatement: TargetClass result = null;
for (TargetClass target : targets) {new TargetClass() {@Overridevoid abstractMethod() {}};
switch (target.field) {case 1:result = new TargetClass.Child();break;case 2:private Object var = TargetClass.staticField;result = target.instanceMethod();break;default:break;}
if (result != null) break;}
return result;}}
/**
Abstract target class with javadoc and anonymous inner class*/abstract class TargetClass {static Object staticField = 1;Object field;
static class Child extends TargetClass {}
abstract void abstractMethod();
TargetClass instanceMethod() {return new TargetClass() {@Overridevoid abstractMethod() {}};}}