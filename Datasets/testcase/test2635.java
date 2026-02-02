package test.same;
class SourceClass {protected String outerProtectedField = "protectedValue";static class StaticNested {}
/**
Processes target instances and returns base type result
@param targets Varargs of sealed TargetClass instances
@return int result from target field access*/@Deprecatedprivate int varargsMethod(TargetClass... targets) {class LocalInner {int getProcessedValue() {return SourceClass.this.outerProtectedField.hashCode();}}LocalInner local = new LocalInner();
Object var = targets[0].instanceField;int result = (int) var + local.getProcessedValue();
return result;}}
sealed class TargetClass permits TargetSubclass {Object instanceField;
Runnable anon = new Runnable() {public void run() {instanceField = 100;}};}