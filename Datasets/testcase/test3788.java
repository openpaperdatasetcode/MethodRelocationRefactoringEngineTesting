import java.lang.annotation.*;
// Annotation for method_feature: pos=the attribute values of annotations@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {String value() default "default_annotation_value";}
enum TargetEnum {VALUE1, VALUE2;
public class TargetInnerClass {private Object innerData;
TargetInnerClass (Object data) {this.innerData = data;}
public void setInnerData (Object data) {this.innerData = data;}
public Object getInnerData () {return this.innerData;}}
private TargetInnerClass targetField;
TargetEnum () {this.targetField = new TargetInnerClass (this.name () + "_init_data");}
public TargetInnerClass getTargetField () {return targetField;}}
enum SourceEnum {INSTANCE;
private abstract class SourceAbstractInner {
protected abstract Object processData1 (Object data);
protected abstract Object processData2 (Object data);
protected abstract Object processData3 (Object data);
}
@ProcessAnnotation (value = "process_target_enum") //method_feature: pos=the attribute values of annotationspublic void processTargets (TargetEnum... targets) {if (targets == null) {return;}
SourceAbstractInner abstractInner = new SourceAbstractInner () {
@Overrideprotected Object processData1 (Object data) {return data + "_processed1";}
@Overrideprotected Object processData2(Object data) {return data + "_processed2";}
@Overrideprotected Object processData3(Object data) {return data + "_processed3";}};
for (TargetEnum target : targets) {
String enumName = target.name ();
TargetEnum.TargetInnerClass targetInner = target.getTargetField ();Object data1 = abstractInner.processData1 (targetInner.getInnerData ());Object data2 = abstractInner.processData2 (data1);Object data3 = abstractInner.processData3 (data2);
targetInner.setInnerData (data3);
this.validateData (targetInner.getInnerData ());
assert targetInner.getInnerData ().toString ().contains ("processed3") : "Data process failed";}
private void validateData (Object data) {
if (data == null) {
throw new IllegalArgumentException ("Data cannot be null");
}
}
public static void main (String [] args) {SourceEnum.INSTANCE.processTargets (TargetEnum.VALUE1, TargetEnum.VALUE2);}}