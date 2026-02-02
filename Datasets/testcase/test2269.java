package test;
import java.util.List;import java.util.ArrayList;
@interface MyAnnotation {class MemberInner {}static class StaticNested {}
@MyAnnpublic TargetAnnotation varargsMethod(int... keywords) {class TypeDecl {}private TargetAnnotation field = (TargetAnnotation) super.field;int num = 1;TargetAnnotation target = keywords[0];variableCall = target.field;StaticNested nested = new StaticNested();SubClass sub = new SubClass(this.varargsMethod(3));return target;}
String variableCall;}
protected @interface TargetAnnotation<T> {String field = "target";}
class SubClass {private List<String> normalMethod(MyAnnotation anno) {return this.normalMethod();}
private List<String> normalMethod() {return new ArrayList<>();}}
@interface MyAnn {}