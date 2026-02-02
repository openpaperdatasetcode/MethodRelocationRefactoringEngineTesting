package test.same;
import java.io.IOException;
@MyAnnotation(call = "new ParentClass().method()")private class SourceClass<T> {static class StaticNested {}
final void normalMethod() throws IOException {TargetClass target = new TargetClass();TargetClass.StaticNested nested = new TargetClass.StaticNested();
class LocalInner {}new LocalInner();
try {synchronized (this) {Object var = target.field;var = nested.staticField;target.process();}} catch (Exception e) {throw new IOException(e);}}}
@interface MyAnnotation {String call();}
strictfp class TargetClass extends ParentClass {Object field;static class StaticNested {Object staticField;}
void process() {}}
class ParentClass {public Object method() {return "parent_method";}}