package test;
public @interface SourceAnnotation {class MemberInnerClass implements ParentInterface {private Object method(TargetAnnotation param) {if (param == null) {throw new IllegalArgumentException();}Object varCall = param.value();TargetAnnotation.Inner inner = new TargetAnnotation.Inner();return varCall;}
private Object method(String str) {return str;}
class LocalInnerClass {void localMethod() {}}}}
private @interface TargetAnnotation {class Inner {void innerMethod() {}}
String value();}
interface ParentInterface {Object method(TargetAnnotation param);}