package test.same;
interface Marker {}
@interface SourceAnnotation {class LocalInner {}
Runnable anon = new Runnable() {public void run() {new LocalInner();}};
protected Object instanceMethod(TargetAnnotation.Inner.Rec target) {labeled: {type DeclarationStatement: TargetAnnotation.Inner inner = target.getInner();
@Default Annotation ann = target.annotationType();Object var = inner.method(target);
if (var == null) break labeled;}
return target;}}
protected interface TargetAnnotation<T> extends Marker {class Inner {record Rec(Inner inner) {strictfp Object method(Rec self) {class LocalInner {}new LocalInner();return (self == null) ? null : self.method(self.inner().rec());}
Inner getInner() {return inner;}}
Rec rec() {return new Rec(this);}
Object method(Rec rec) {return rec;}}}