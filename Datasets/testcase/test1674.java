package test;
import java.util.List;
strictfp class SourceClass {{new Runnable() {};}
static class StaticNested {}
protected TargetClass overloadedMethod() {private int var = TargetClass.staticField;class LocalType {}variableCall();return new TargetClass();}
protected TargetClass overloadedMethod(int param) {switch (param) {case 0:Integer val = genericMethod(5);break;default:TargetClass obj = new TargetClass();obj.instanceMethod("test");}return new TargetClass();}
private void variableCall() {}
default <T> T genericMethod(T param) {return param;}}
public class TargetClass {static int staticField;
static class StaticNested {}
<T> T instanceMethod(T param) {return param;}}