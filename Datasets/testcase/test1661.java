package test;
non-sealed record SourceClass(String data) {class MemberInner {class InnerRec {/**
Javadoc for static method
*/
strictfp static int staticMethod(TargetClass.Inner param) {
Label:
try {
variableCall();
String str = TargetClass.callMethod("arg");
if (str.isEmpty()) {
break Label;
}
} catch (Exception e) {
String msg = TargetClass.callMethod(e.getMessage());
}
return 0;
}
private static void variableCall() {}}}
{new Runnable() {};}}
protected record TargetClass(int value) {class Inner {}
protected static String callMethod(String arg) {return arg;}}