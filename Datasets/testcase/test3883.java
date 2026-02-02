package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
private abstract class SourceAbstractClass {class SourceInner {Object recursiveMethod(TargetAbstractClass.Inner targetParam, int depth) {if (depth <= 0) {return null;}
if (targetParam == null) {throw new NullPointerException();}
for (int i = 0; i < 1; i++) {List<String> data = targetParam.callTargetMethod(i);variableCall();}
TargetAbstractClass.StaticNested nestedInst = new TargetAbstractClass.StaticNested();
try {Method reflectMethod = TargetAbstractClass.Inner.class.getMethod("callTargetMethod", int.class);reflectMethod.invoke(targetParam, 1);} catch (Exception e) {}
return recursiveMethod(targetParam, depth - 1);}
private void variableCall() {}}}
public abstract class TargetAbstractClass {static class StaticNested {}
class Inner {public List<String> callTargetMethod(int num) {return new ArrayList<>();}}}