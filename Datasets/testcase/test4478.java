package test;
interface SourceClass {static class SourceStaticNested {static int staticField = 5;}
default Object method(TargetClass targetParam) {try {class LocalInner {private Object varargsMethod(Object... args) {if (args.length < 1) {return null;}return TargetClass.TargetStaticNested.staticMethod(args[0]);}}
LocalInner local = new LocalInner();Object result;do {if (targetParam != null) {result = local.varargsMethod(targetParam);targetParam.instanceMethod();result = SourceStaticNested.staticField;} else {result = new Object();}} while (result == null);return result;} catch (Exception e) {return new Object();}}
default Object method(String strParam) {return strParam + SourceStaticNested.staticField;}}
protected interface TargetClass {static class TargetStaticNested {static Object staticMethod(Object obj) {return obj;}}
default void instanceMethod() {}}