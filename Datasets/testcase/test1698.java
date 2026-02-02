package test;
interface SomeInterface {}
class SourceClass implements SomeInterface {{new Runnable() {};}
/**
Static method with array initialization and override violation*/strictfp static Object staticMethod() {class LocalInner {}
// Access target fieldTargetClass<String> target = new TargetClass<>();int fieldVal = target.targetField;
// Normal method in array initializationTargetClass<String>[] targets = new TargetClass[] {target.new InnerClass().finalMethod()};
variableCall();target.new InnerClass().instanceMethod();
// Override violation (final method in parent)class BadInner extends TargetClass<String>.InnerClass {@Overridefinal void finalMethod() {} // Compile error expected}
return new Object();}
private static void variableCall() {}}
protected class TargetClass<T> {int targetField;
{new Runnable() {};}
class InnerClass {final TargetClass<T> finalMethod() {return new TargetClass<>();}
void instanceMethod() {}}}