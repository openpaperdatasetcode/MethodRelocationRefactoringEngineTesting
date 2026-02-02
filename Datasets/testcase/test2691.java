package com.source;
import com.target.TargetInterface;import java.sql.SQLException;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
interface SourceInterface {// Overload exists (overload_exist feature)default TargetInterface<String> methodToMove(TargetInterface<String> target) {return target;}
@MethodAnnotdefault <T> TargetInterface<T> methodToMove(TargetInterface<T>... targets) {for (TargetInterface<T> target : targets) {// Type declaration statementTargetInterface.LocalInner inner = target.createLocalInner();
// ExpressionStatement with this.field (count 1, pos: inner class)private T fieldVal = target.getField();
// Variable call + contains target parameter (per_condition)target.toString();
// PrefixExpression (numbers:1, modifier:default)default int prefix = ++target.getCount();
// Access outer private (simulated via inner class method)inner.accessPrivate();
// SQLException handling (no_new_exception)try {if (fieldVal == null) throw new SQLException("Field is null");} catch (SQLException e) {// No rethrow to satisfy no_new_exception}
return target;}return null;}}
package com.target;
strictfp interface TargetInterface<T> {T getField();int getCount();
// Local inner class (target_feature)default LocalInner createLocalInner() {class LocalInner {private void accessPrivate() {}}return new LocalInner();}
class LocalInner {}}
class OthersClass {default void callMethod(TargetInterface target) {
// Static method call in do-while
do {
SourceInterface source = new SourceInterface() {};
source.methodToMove(target);
} while (target.getCount() < 1);
}
}