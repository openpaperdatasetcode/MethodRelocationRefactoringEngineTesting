package test;
class ParentSource {}class ParentTarget {}
abstract class SourceClass extends ParentSource {static class SourceStaticNested {}
class SourceInner {private Object overloadedMethod(TargetClass.TargetInnerRec inner) {return inner.innerField;}
private Object overloadedMethod(TargetClass.TargetInnerRec inner, int param) {String varCall = inner.innerField;rawTypeMethod(inner);return varCall + param;}
private synchronized void varargsMethod(Object... args) {this.varargsMethod(args.length > 0 ? args[0] : null);}
private void varargsMethod(Object arg) {}
private void rawTypeMethod(TargetClass.TargetInnerRec inner) {TargetGeneric raw = new TargetGeneric();}}
void anonymousClassDemo() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
private class TargetClass extends ParentTarget {static class TargetStaticNested {}
class TargetInnerRec {String innerField = "innerRecVal";}}
class TargetGeneric<T> {}
class CallerClass {void callSourceMethod() {SourceClass source = new SourceClass() {};SourceClass.SourceInner inner = source.new SourceInner();TargetClass target = new TargetClass();TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec();
int i = 0;while (i < 3) {ParentSource superRef = source;superRef.toString();inner.overloadedMethod(innerRec, i);i++;}}}