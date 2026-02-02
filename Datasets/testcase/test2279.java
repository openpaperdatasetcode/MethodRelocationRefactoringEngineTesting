package test;
interface MyInterface {}
public class SourceClass<T> implements MyInterface {static class StaticNested {}
void methodWithLocal() {class LocalInner {}}
Object varargsMethod(TargetClass... targets) {private class PrivateType {void useField() {int val = targets[0].this.field;if (val != 2) {}}}
class TypeDecl {}assert targets.length > 0;variableCall = targets[0].field;targets[0].field = 5;
if (targets.length > 0) {int result = SourceClass.StaticNested.newInstance().staticMethod();}
return null;}
String variableCall;
static class StaticNested {protected static int staticMethod() {return 0;}
static StaticNested newInstance() {return new StaticNested();}}}
class TargetClass {int field;static class TargetStaticNested {}}