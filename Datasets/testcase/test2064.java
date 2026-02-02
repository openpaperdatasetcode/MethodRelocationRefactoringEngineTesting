package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
private class SourceClass {private List<String> methodToMove(TargetClass target) {// Anonymous inner classnew Object() {};
// Local inner classclass LocalInner {}new LocalInner();
// Constructor invocationTargetClass newTarget = new TargetClass();TargetClass.MemberInner inner = newTarget.new MemberInner();
// TryStatement with static modifier and target_feature: this.field (3)static {try {int field1 = (int) target.thisField1;String field2 = (String) target.thisField2;Object field3 = (Object) target.thisField3;} catch (Exception e) {}}
// 3 CastExpressions with abstract modifierabstract class AbstractCast {void castOps() {Object obj = new Object();int cast1 = (int) obj;String cast2 = (String) obj;List<String> cast3 = (List<String>) obj;}}
// Call method in if/else conditionsint result;if (target != null) {result = this::finalGenericMethod;} else {result = this::finalGenericMethod;}
return new ArrayList<>();}
// Generic method for call_method featurefinal <T> int finalGenericMethod(T param) {return 0;}}
class TargetClass {// Target features: member inner class and 3 this.fieldsint thisField1;String thisField2;Object thisField3;
class MemberInner {}}