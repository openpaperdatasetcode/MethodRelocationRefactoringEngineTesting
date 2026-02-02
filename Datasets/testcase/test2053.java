package test;
import java.io.IOException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SourceClass {class SourceInner {@MyAnnotationprivate int methodToMove(TargetClass<String> target) throws IOException {class LocalInnerInSource {}new LocalInnerInSource();
OtherClass.process(this);; // Empty statement
int result = target.instanceField;String str;
if (result > 0) {str = target.privateMethod();} else {str = target.privateMethod();}
return result + str.length();}}}
public class TargetClass<T> {int instanceField;
private String privateMethod() {return super.toString();}
void someMethod() {class LocalInnerInTarget {}new LocalInnerInTarget();}}
class OtherClass {static void process(Object obj) {}}