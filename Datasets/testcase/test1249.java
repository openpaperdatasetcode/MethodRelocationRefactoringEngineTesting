package test.pkg;
strictfp class SourceClass {// Static nested class (source_class feature)public static class StaticNestedClass {private String nestedField = "staticNested";}
// Member inner class (source_inner: method in source's member inner class)public class SourceInnerClass {private int count = 0;
// Constructor with this(arguments)public SourceInnerClass() {this(3); // this(arguments) feature}
public SourceInnerClass(int count) {this.count = count;}
@SuppressWarnings("rawtypes") // has_annotation featureprotected Object process(TargetClass targetParam) {// Type declaration statementStaticNestedClass staticNested = new StaticNestedClass();TargetClass.MemberInnerClass targetInner = targetParam.new MemberInnerClass();
// Raw type usageTargetClass rawTarget = new TargetClass();
// While loop containing accessor method callwhile (count > 0) {// Accessor method call (3: count, target: owner, accessor: type, this.methodName(arguments))Object accessorResult = targetInner.getInnerField(this.count);staticNested.nestedField = accessorResult.toString();count--;}
// Variable call: target's member inner class method + raw type calltargetInner.setInnerField(staticNested.nestedField);rawTarget.new MemberInnerClass().setInnerField("rawTypeValue");
// Return statementreturn staticNested.nestedField;}}}
class TargetClass {// Member inner class (target_feature)public class MemberInnerClass {private Object innerField;
// Accessor methods (getter/setter)public Object getInnerField(int count) {return "targetInner:" + count + ":" + innerField;}
public void setInnerField(Object value) {this.innerField = value;}}
// Method will be moved here:// @SuppressWarnings("rawtypes")// protected Object process(TargetClass targetParam) { ... }}