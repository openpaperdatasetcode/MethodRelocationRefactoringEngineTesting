package test;
non-sealed public class SourceClass<T> permits SourceSubClass {// Member inner classes (source_feature)class Inner1 {}class Inner2 {}
@Overridedefault void methodToMove(TargetClass<T> target) {// Variable call + contains target parameter (per_condition)TargetClass<T>.TargetInner inner = target.new TargetInner();inner.toString();
// Access instance fieldT targetField = inner.innerField;}}
public class SourceSubClass<T> extends SourceClass<T> {@Overridepublic void methodToMove(TargetClass<T> target) {super.methodToMove(target);}}
public class TargetClass extends ParentTargetClass {
class TargetInner {
public U innerField; // Source contains target's field (per_condition)
public void createLocalInner() {// Local inner class (target_feature)class TargetLocalInner {}}}}
class ParentTargetClass {}