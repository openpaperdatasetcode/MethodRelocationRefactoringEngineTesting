package test;
class SourceClass<T> {static String staticField = "static";
{new Object() {}; // Anonymous inner class}
void localInnerMethod() {class LocalInner {} // Local inner class}
protected void varargsMethod(TargetClass<String> targetParam, Integer... args) {// ReturnStatementif (args.length > 0) {return;}if (targetParam.field != null) {return;}if (3 > 0) {return;}
// do statementdo {int var = targetParam.field;} while (false);
// super constructor invocation (within anonymous class)new SourceClass<T>() {{super();}};
// otherObject.process(this);OtherClass.process(this);
// variable callInteger arg = args[0];
// depends_on_static_fieldString str = staticField;}}
class TargetClass {
int field;
{new Object() {}; // Anonymous inner class}}
class OtherClass {static <T> void process(SourceClass<T> source) {}}
