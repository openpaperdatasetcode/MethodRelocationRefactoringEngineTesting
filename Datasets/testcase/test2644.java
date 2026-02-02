package test.same;
private class SourceClass {protected int outerProtected = 100;
class MemberInner {record InnerRec(TargetClass target) {@MyAnnotationpublic int instanceMethod() {class LocalInner {void process() {private int var1 = target.superField1;private int var2 = target.superField2;private int var3 = target.superField3;
var1 = var2 + var3;var2 = SourceClass.this.outerProtected;}}LocalInner local = new LocalInner();local.process();
Object var = target.superField1;return (int) var;}}}}
@interface MyAnnotation {}
protected class TargetClass extends ParentClass {Runnable anon = new Runnable() {public void run() {}};}
class ParentClass {int superField1 = 10;int superField2 = 20;int superField3 = 30;}