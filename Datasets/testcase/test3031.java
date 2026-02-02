package test;
interface SourceInterface extends ParentInterface {permits SourceImpl, AnotherImpl;
default Object instanceMethod(TargetInterface targetParam) {TargetInterface.NestedClass nested = new TargetInterface.NestedClass();TargetInterface result;
try {result = super.abstractMethod();} catch (Exception e) {result = null;}
PrivateHelper helper = new PrivateHelper();helper.usePrivateField();targetParam.doAction();
return result;}
private static class PrivateHelper {private String privateField = "privateValue";
void usePrivateField() {System.out.println(privateField);}}}
default interface TargetInterface {static class NestedClass {}
void doAction();}
interface ParentInterface {TargetInterface abstractMethod();}
class SourceImpl implements SourceInterface {}
class AnotherImpl implements SourceInterface {}
