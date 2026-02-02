package test;
sealed class SourceClass permits SourceSubclass {private TargetClass targetField;
public static class StaticNested {}
public class MemberInner {public class SourceInnerRec {private int instanceMethod() {TargetClass.StaticNested<String extends CharSequence> bounded = new TargetClass.StaticNested<>();targetField.innerRec.variableCall();bounded.toString();return targetField.value;}}}
public void switchCallMethod(int choice) {switch (choice) {case 1:String res1 = OtherClass.staticMethod("arg1");break;case 2:String res2 = OtherClass.staticMethod("arg2", 3);break;default:String res3 = OtherClass.staticMethod("default");}}}
final class SourceSubclass extends SourceClass {}
public class TargetClass {public int value;public TargetInnerRec innerRec = new TargetInnerRec();
public static class StaticNested<T> {}
public static class TargetInnerRec {public void variableCall() {}}}
final class OtherClass {public static String staticMethod(String arg) {return arg;}
public static String staticMethod(String arg1, int arg2) {return arg1 + arg2;}}