package test;
strictfp class TargetClass extends ParentClass {/**
Javadoc for TargetClass
*/
public TargetClass() {
class LocalInner {}
}
protected class TargetInnerRec {}
}
protected class SourceClass {static class StaticNested {}class MemberInner {}
@MyAnnotationpublic Object methodToMove(TargetClass.TargetInnerRec param) {super();int[] arr = new int[1];assert arr[0] == 0;Object var = new Object();synchronized (var) {for (Object obj : new Object[0]) {}MemberInner inner = new MemberInner();}return param;}}
class ParentClass {}@interface MyAnnotation {}