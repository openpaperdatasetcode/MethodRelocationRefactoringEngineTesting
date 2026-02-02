package sourcepkg;
import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import targetpkg.TargetClass;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTest {}
protected abstract class SourceClass {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();
static class SourceStaticNested {}class SourceMemberInner {}
@RefactorTest // has_annotationpublic final Object methodToMove(String... args) {// variable callSourceStaticNested staticNested = new SourceStaticNested();SourceMemberInner memberInner = new SourceMemberInner();TargetClass.TargetStaticNested targetStatic = new TargetClass.TargetStaticNested();
// do statementint count = 0;do {if (count == 1) {break; // break statement}count++;} while (count < 3);
// used_by_reflectiontry {Method method = TargetClass.class.getMethod("targetMethod");} catch (Exception e) {}
// Access target's field (per_condition verification)Object targetVal = targetField.getTargetData();
return new Object();}}
package targetpkg;
public final abstract class TargetClass {// Target field used by sourceprivate String targetData = "targetFieldValue";
// target_feature: static nested classpublic static class TargetStaticNested {}
public String getTargetData() {return targetData;}
public void targetMethod() {}}