package test;
import java.io.IOException;import java.util.List;
public class SourceClass {private int outerPrivateField = 3;TargetClass target = new TargetClass();
static class SourceStaticNested {}
class SourceMemberInner {final TargetClass getTargetInstance(TargetClass param) {return param;}}
{TargetClass innerTarget = new SourceMemberInner().getTargetInstance(target);}
/**
Javadoc for method
@param param target class parameter
@return TargetClass instance
@throws IOException if error occurs*/@MyAnnotationprotected <T extends Number> TargetClass method(TargetClass param) throws IOException {class LocalInner {void createTarget() {new TargetClass.TargetStaticNested(param.field) {};}}new LocalInner().createTarget();
param.field = outerPrivateField;this(target);
return new TargetClass(param) {@Overridevoid doSomething() {}};}
private SourceClass(TargetClass param) {this.target = param;}}
@MyAnnotationprotected class TargetClass implements Runnable {int field;
static class TargetStaticNested {TargetStaticNested(int value) {}}
class TargetInner {}
TargetClass() {}
TargetClass(TargetClass other) {this.field = other.field;}
void doSomething() {}
@Overridepublic void run() {}}
@interface MyAnnotation {}