package test;
public class SourceClass<T extends CharSequence> {// Static nested class (source_feature)public static class SourceStaticNested {}
public class SourceInner {public class SourceInnerRec {/**
Method Javadoc for testing Move Method refactoring
@param target TargetClass instance with generic inner class
@return Processed result object*/strictfp public Object methodToMove(TargetClass<T> target) {// Local inner class (source_feature)class SourceLocalInner {public void validate(T value) {assert value != null : "Value must not be null";}}
Object result = null;// Try statementtry {// Variable call + contains target parameter (per_condition)target.toString();TargetClass<T>.TargetInner inner = target.new TargetInner();
// If statementif (inner != null) {// Access instance methodT targetField = inner.getInnerField();new SourceLocalInner().validate(targetField);
// Assert statementassert targetField.length() > 0 : "Field length must be positive";result = targetField;}} catch (Exception e) {// No new exceptionresult = "default";}
return result;}}}}
public class TargetClass extends ParentTargetClass {
// Type parameter (target_feature)
public class TargetInner {
private U innerField;
public U getInnerField() {return innerField;}
public void setInnerField(U value) {this.innerField = value;}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {}new TargetLocalInner();}}}
abstract class ParentTargetClass<V extends CharSequence> {}