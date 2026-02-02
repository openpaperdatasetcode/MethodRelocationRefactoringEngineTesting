package test;
import java.util.List;import java.util.ArrayList;
abstract class SourceClass {private String outerField = "sourceOuterField";
static class StaticNestedClass {}
class MemberInnerClass {record SourceInnerRec(List<String> dataList) {@MyAnnotationprivate SourceInnerRec(TargetClass.TargetInner<String> targetInner) {this(new ArrayList<>());SourceClass.this.outerField = "updatedByRec";
TargetClass.TargetInner<String> varCall = targetInner;this.dataList.add(varCall.getInnerData());
TargetClass.TargetInner<String> newTargetInner = new TargetClass.TargetInner<>("newData");this.dataList.add(newTargetInner.getInnerData());}
public int instanceMethod() {return super.toString().length();}}
{SourceInnerRec rec = new SourceInnerRec(new TargetClass.TargetInner<>("initData"));int result = rec.instanceMethod();}}}
protected class TargetClass {class TargetInner<T> {private T innerData;
public TargetInner(T data) {this.innerData = data;}
public T getInnerData() {return this.innerData;}}}
@interface MyAnnotation {}