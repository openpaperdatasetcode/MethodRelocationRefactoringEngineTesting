package com.source;
import com.target.TargetClass;import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
non-sealed abstract class SourceClass {class SourceInner {class SourceInnerRec {@MyAnnotationprivate void method(TargetClass targetParam) {variableCall();TargetClass.Inner inner = new TargetClass.Inner();depends_on_inner_class(inner);
protected int condExp = (1 > 0) ? 1 : 0;
try {Object obj = SourceClass.createInstance(3);} catch (Exception e) {}
class LocalInner1 {}new LocalInner1();
class LocalInner2 {}new LocalInner2();}
private void variableCall() {}
private <T extends Number> void depends_on_inner_class(TargetClass.Inner inner) {}}}
public static Object createInstance(int count) {return new Object();}}
@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface MyAnnotation {}
package com.target;
protected abstract class TargetClass {/**
Javadoc for TargetClass
*/
class Inner {}
void methodWithLocal() {class TargetLocalInner {}new TargetLocalInner();}}