package test;
interface BaseInterface {TargetClass overrideMethod(TargetClass param);}
private class SourceClass implements BaseInterface {@Override@Deprecatedprotected TargetClass overrideMethod(TargetClass targetParam) {class LocalInner1 {private Object varargsMethod(Object... args) {if (args.length >= 1) {return args[0];}return null;}}
class LocalInner2 {void process(TargetClass target) {LocalInner1 inner1 = new LocalInner1();for (Object arg : new Object[]{target.field}) {Object result = inner1.varargsMethod(arg);targetParam.field = (int) result;}}}
LocalInner2 inner2 = new LocalInner2();inner2.process(targetParam);return targetParam;}}
protected class TargetClass {int field = 5;
void sampleMethod() {class TargetLocalInner {}new TargetLocalInner();}}