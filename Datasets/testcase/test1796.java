package test;
import java.util.ArrayList;import java.util.List;
public abstract class SourceClass {public static class StaticNested {protected class SourceInner {@MyAnnotationprotected List<String> varargsMethod(TargetClass... targets) {List<String> result = new ArrayList<>();if (targets == null) {throw new NullPointerException();}for (TargetClass target : targets) {if (target == null) break;result.add(target.innerClass.method());}return result;}
protected List<String> varargsMethod(String... strs) {return new ArrayList<>(List.of(strs));}}}
void localInnerMethod() {class LocalInner {void useSourceInner() {String[] array = {new StaticNested.SourceInner().new TargetInnerCaller().call()};}}new LocalInner().useSourceInner();}
private class TargetInnerCaller {String call() {return new TargetClass.MemberInner().abstractMethod();}}}
private abstract class TargetClass {protected MemberInner innerClass = new MemberInner();
protected abstract class MemberInner {abstract String abstractMethod();
String method() {return super.toString();}}}
@interface MyAnnotation {}