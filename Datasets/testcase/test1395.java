package sourcepkg;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import targetpkg.TargetClass;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
public class SourceClass {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();private int instanceField = 10;
@MethodAnno // has_annotationprivate int methodToMove() {// access_instance_fieldint fieldVal = this.instanceField;
// variable callTargetClass target = targetField;target.doAction();
// DoStatement (private, obj.field, 1, pos:source)int count = 0;do {target.targetField = 1; // obj.field, 1count++;} while (count < 2);
// overload_exist (overloaded method in target)target.calculate(1);target.calculate(1L);
// Access target's field (per_condition verification)Object targetData = target.targetField;
return 0; // base type}
// Overload for overload_exist verification (source side)private int methodToMove(String param) {return 1;}}
package targetpkg;
public class TargetClass {public int targetField; // Target field used by source
// target_feature: anonymous inner classRunnable anonymous = new Runnable() {@Overridepublic void run() {targetField = 2;}};
void doAction() {}
// overload_exist (target side overloaded methods)public int calculate(int num) {return num;}
public long calculate(long num) {return num;}}