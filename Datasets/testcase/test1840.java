package test;
import java.util.function.Supplier;
public class SourceClass<T extends Number> extends ParentSource {private String outerField = "source_data";
// Member inner classpublic class SourceMemberInner {T getValue() {return (T) Integer.valueOf(10);}}
// Overloading method 1@MyAnnotationpublic TargetClass overloadedMethod(TargetClass.InnerRec rec) {// Local inner classclass LocalProcessor {TargetClass process() {TargetClass target = new TargetClass();// Uses outer thistarget.field = SourceClass.this.outerField + rec.value();return target;}}TargetClass target = new LocalProcessor().process();
// Empty statement;
// Variable calltarget.innerField = rec.id() + new SourceMemberInner().getValue().intValue();
// With boundsclass BoundedHandler {
U handle(U r) {
return r;
}
}
new BoundedHandler<>().handle(rec);
// Try statementtry {// Call method in object initialization (overriding)TargetClass initTarget = new TargetClass() {@Overridestrictfp Object processRec(TargetClass.InnerRec r) {return super.processRec(r) + "_processed";}};target = (TargetClass) initTarget.processRec(rec);} catch (Exception e) {// No new exception}
return target;}
// Overloading method 2@MyAnnotationpublic TargetClass overloadedMethod(TargetClass.InnerRec rec, int multiplier) {TargetClass target = overloadedMethod(rec);target.innerField *= multiplier;return target;}}
class ParentSource {}
@interface MyAnnotation {}
protected class TargetClass {String field;int innerField;
// Local inner class in constructorpublic TargetClass() {class RecValidator {boolean isValid(InnerRec rec) {return rec.id() > 0;}}}
public record InnerRec(int id, String value) {}
strictfp Object processRec(InnerRec rec) {return rec.value() + "_base";}}