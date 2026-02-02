package com.source;
import com.target.TargetEnum;
enum SourceEnum<T extends CharSequence> {INSTANCE;
// Type parameter (source_feature)private T data;
// Static nested class (source_feature)public static class SourceStaticNested {}
public class SourceInner {strictfp public Object methodToMove(TargetEnum... targets) {Object result = new Object[targets.length];
for (int i = 0; i < targets.length; i++) {TargetEnum target = targets[i];// Variable call + contains target parameter (per_condition)target.toString();TargetEnum.MemberInner inner = target.new MemberInner();
// DoStatement with this.field (count 2, pos: diff_package_others)private String field1, field2;do {field1 = inner.getField1();field2 = inner.getField2();} while (field1 == null || field2 == null);
// Switch caseswitch (field1.length()) {case 0:result = "empty_field1";break;case 5:result = recursiveProcess(inner, 3); // Recursion callbreak;default:result = field1 + "_" + field2;}
// NullPointerException handling (no_new_exception)try {if (inner.getField1() == null) throw new NullPointerException();} catch (NullPointerException e) {result = "null_safe_result";}
// Local inner class (source_feature)class LocalProcessor {public void validate() {if (result == null) result = "validated";}}new LocalProcessor().validate();
((Object[]) result)[i] = result;}
return result;}
// Recursion method (3 levels, target recursion, this.methodName(arguments))private final int recursiveProcess(TargetEnum.MemberInner inner, int depth) {if (depth == 0) return inner.getField1().length();// This.methodName(arguments) recursionreturn depth + recursiveProcess(inner, depth - 1);}}}
package com.target;
abstract enum TargetEnum {TARGET_ONE, TARGET_TWO;
// Member inner class (target_feature)public class MemberInner {private String field1 = "field1"; // Source contains target's field (per_condition)private String field2 = "field2"; // Source contains target's field (per_condition)
// this.field accessorspublic String getField1() {return this.field1;}
public String getField2() {return this.field2;}}}