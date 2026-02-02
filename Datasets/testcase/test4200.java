  }
package test;
// Source interface: default modifier (empty modifier), same package with target, has static nested + member inner classesinterface SourceInterface {// Member inner class (matches source_class.feature)class SourceInner {// Private field for access_outer_privateprivate String innerPrivateField = "sourceInnerPrivate";
// Recursive method (matches method_position: source_inner_rec)private int recursiveHelper(int count) {if (count <= 0) return 0;return count + recursiveHelper(count - 1);}
// Refactored method: static, return TargetClas Type, private accessprivate static TargetInterface.TargetInner moveMethod(TargetInterface.TargetInner targetParam) {// NullPointerException + throw statementif (targetParam == null) {throw new NullPointerException("TargetInner parameter cannot be null");}
// Constructor invocation: create SourceInner instance (for access_outer_private)SourceInner sourceInnerInst = new SourceInner();
// Variable call: access target's field/methodString targetData = targetParam.getTargetData();int recResult = sourceInnerInst.recursiveHelper(3);
// access_outer_private: access SourceInner's private fieldString combinedData = sourceInnerInst.innerPrivateField + targetData + recResult;targetParam.setTargetData(combinedData);
// Return statement: return TargetClas Type (TargetInner)return targetParam;}}
// Static nested class (matches source_class.feature)static class SourceStaticNested {void staticNestedMethod() {}}}
// Target interface: abstract modifier, has member inner class (target_feature)abstract interface TargetInterface {// Member inner class (matches target_class.target_feature, target class: target)class TargetInner {private String targetField;
// Constructor for constructor invocationpublic TargetInner() {}
// Getter for variable callpublic String getTargetData() {return targetField;}
// Setter for variable callpublic void setTargetData(String data) {this.targetField = data;}}}