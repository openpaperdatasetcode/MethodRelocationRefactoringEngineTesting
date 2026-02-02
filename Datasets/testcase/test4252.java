package test;
import java.sql.SQLException;
final class SourceClass {// Member inner classclass SourceMemberInner {private SourceMemberInner() {}private SourceMemberInner(int num) {}private SourceMemberInner(String str) {}}
// Instance method to be refactoredpublic TargetClass getTargetInstance(TargetClass targetParam) throws SQLException {// Constructor invocation (3 private constructors of inner class)SourceMemberInner inner1 = new SourceMemberInner();SourceMemberInner inner2 = new SourceMemberInner(10);SourceMemberInner inner3 = new SourceMemberInner("test");
// Method reference: instanceReference::methodNameRunnable run = inner1::toString;
// Expression statement + Variable callint targetValue = targetParam.targetField;targetParam.doNothing();
// With bounds (generic bounds example)GenericClass<? extends Number> generic = new GenericClass<>(100);
// Exception throwing statement (matches "pos": "exception throwing statements")if (targetParam == null) {throw new SQLException("Target parameter cannot be null");}
return targetParam;}
// Local inner class (in outer method)public void createLocalInner() {class SourceLocalInner {void localMethod() {}}new SourceLocalInner().localMethod();}}
// Generic class for "with_bounds" featureclass GenericClass<T> {T value;public GenericClass(T value) {this.value = value;}}
final class TargetClass {int targetField;
// Target inner class (matches "target class": "target_inner")class TargetInner {void innerMethod() {}}
void doNothing() {}}