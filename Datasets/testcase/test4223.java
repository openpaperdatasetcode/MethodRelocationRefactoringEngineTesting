package test;
import java.lang.reflect.Method;import java.util.List;
protected class SourceClass {// Member inner class (matches source_class.feature)class SourceInner {private int innerField1;private int innerField2;
// Constructor for ConstructorInvocation featureprivate SourceInner(int val1, int val2) {// this.field access (matches target_feature: this.field)this.innerField1 = val1;this.innerField2 = val2;}
// Recursive method (matches method_position: source_inner_rec)private int recursiveHelper(int count) {if (count <= 0) return 0;return count + recursiveHelper(count - 1);}
// Accessor method (matches accessor type in method.features)Object getInnerAccessor() {return innerField1 + innerField2 + recursiveHelper(3);}
// Refactored method: instance, return TargetClas Type, protected accessprotected TargetClass moveMethod(TargetClass targetParam) {// ConstructorInvocation (matches nested feature: private modifier, this.field, "2")SourceInner innerInst = new SourceInner(5, 10);
// Synchronized statement (matches method.features)synchronized (this) {// Variable call: access target's field (matches per_condition)targetParam.targetField = innerInst.innerField1;}
// for loop with accessor method (matches accessor feature's pos: for)for (int i = 0; i < 1; i++) {Object accessorResult = new SourceInner(i, i + 1).getInnerAccessor();targetParam.targetField = (int) accessorResult;}
// switch statement (matches method.features)switch (targetParam.targetField) {case 5:targetParam.targetField = 15;break;case 10:targetParam.targetField = 20;break;default:targetParam.targetField = 25;}
// with_bounds: generic method with upper bound (Number)targetParam.processBoundedData(targetParam.targetField);
// used_by_reflection: access method via reflectiontry {Method method = SourceInner.class.getMethod("getInnerAccessor");method.invoke(innerInst);} catch (Exception e) {// no_new_exception: no additional exception thrown}
// Return TargetClas Typereturn targetParam;}}
// Static nested class (matches source_class.feature)static class SourceStaticNested {void staticNestedMethod() {}}}
// Target class: public modifier, no extra features (matches target_class specs)public class TargetClass {// Target field (accessed by source method, matches per_condition)int targetField;
// with_bounds: generic method with upper bound (Number)public <T extends Number> void processBoundedData(T data) {this.targetField = data.intValue();}}
