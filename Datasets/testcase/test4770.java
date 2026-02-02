package test;
interface MyInterface {}
sealed class ParentClass permits SourceClass {}
protected class SourceClass extends ParentClass {public class SourceInner {@MyAnnotationpublic void recursiveMethod(TargetClass target, int depth) {if (depth <= 0) return;
class LocalType {void useMethod() {target.instanceMethod();}}new LocalType().useMethod();
SourceInner inner = new SourceInner();inner.toString();
recursiveMethod(target, depth - 1);}}}
non-sealed class TargetClass implements MyInterface {public void instanceMethod() {class TargetLocalInner {}}}
@interface MyAnnotation {}