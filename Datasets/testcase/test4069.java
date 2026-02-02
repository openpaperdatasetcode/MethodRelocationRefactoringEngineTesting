package test;
public class SourceClass {protected TargetClass targetField;private static int staticField = 5;
class MemberInner {void innerMethod() {}}
strictfp int instanceMethod() {if (targetField == null) {throw new IllegalArgumentException("Target field is null");}int var = TargetClass.staticNestedStaticField;super.toString();return var + staticField;}
final String callMethod() {return new SourceClass().new MemberInner().innerMethod() + targetField.overriddenMethod();}
{new Runnable() {@Overridepublic void run() {instanceMethod();}};}}
protected class TargetClass {static class StaticNested {static int staticNestedStaticField = 10;}
String overriddenMethod() {return "Original";}}
class SubTarget extends TargetClass {@OverrideString overriddenMethod() {return "Overridden";}}
