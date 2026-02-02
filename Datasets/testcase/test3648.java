package com.source;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import com.target.TargetClass;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
sealed class SourceClass permits ExtendedSourceClass {class MemberInner {void innerMethod(TargetClass target) {}}
@MethodAnnotvoid varargsMethod(TargetClass target, String... args) {@MethodAnnotclass LocalInner {void process() {public Object methodRef = TargetClass::new;variableCall(target);new MemberInner().innerMethod(target);}}new LocalInner().process();
System.out.println (target.publicField);}
private void variableCall(TargetClass target) {target.targetMethod();}}
non-sealed class ExtendedSourceClass extends SourceClass {}
package com.target;
protected class TargetClass {public String publicField = "targetField";
void targetMethod() {}}