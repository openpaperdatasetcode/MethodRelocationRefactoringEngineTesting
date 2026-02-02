package test;
class SourceClass {private TargetClass targetInstance = new TargetClass();
final TargetClass varargsMethod(int... args) {label: {for (int arg : args) {Object result = new LocalInnerClass().overrideMethod();if (arg > 5) break label;}}try {Class<?> cls = Class.forName("test.SourceClass$MemberInnerClass");cls.getMethod("useTarget", TargetClass.class).invoke(null, targetInstance);} catch (Exception e) {}return targetInstance;}
class MemberInnerClass {static void useTarget(TargetClass target) {}}
class LocalInnerClass extends SuperClass {@OverrideObject overrideMethod() {return super.overrideMethod();}}}
private class TargetClass {}
class SuperClass {Object overrideMethod() {return null;}}