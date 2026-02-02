package test;
public class SourceClass {private String outerPrivateField = "sourcePrivateField";private TargetClass targetField = new TargetClass() {};
static class StaticNestedClass {void useTarget(TargetClass target) {System.out.println(target);}}
public TargetClass instanceMethod() {class TypeDeclaration {TargetClass processTarget(TargetClass t) {return t;}}
TypeDeclaration typeDecl = new TypeDeclaration();TargetClass varCall = typeDecl.processTarget(targetField);
Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(outerPrivateField);}};anon.run();
while (varCall != null) {callParentMethod(varCall, "arg1", "arg2", "arg3");break;}
if (varCall.field == null) {public void throwMethod() {throw new IllegalArgumentException("obj.field is null");}throwMethod();}
return varCall;}
private void callParentMethod(TargetClass target, String arg1, String arg2, String arg3) {super.toString();}}
abstract class TargetClass {String field;}