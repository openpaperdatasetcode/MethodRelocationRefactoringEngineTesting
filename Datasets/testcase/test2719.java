package com.source;
import com.target.TargetClass;
non-sealed public class SourceClass<T extends CharSequence> permits SourceSubClass {// Static nested class (source_feature)public static class SourceStaticNested {public static final String STATIC_FIELD = "source_static";}
// Member inner class (source_feature)public class SourceInner {// Method types parameter: nonedefault Object methodToMove(TargetClass... targets) {super(); // Super keywords// Uses outer this referenceSourceClass<T> outerThis = SourceClass.this;Object result = new Object();
outerLoop: // LabeledStatementfor (TargetClass target : targets) {// Variable call + contains target parameter (per_condition)target.toString();TargetClass.Inner.InnerRec rec = target.new Inner().new InnerRec();
// LabeledStatement with super.field (count 1, pos: diff_package_others)private String superFieldVal;labeledBlock: {superFieldVal = target.superField;if (superFieldVal == null) break labeledBlock;}
// InstanceofExpression (numbers:2, modifier:private)private boolean isString = superFieldVal instanceof String;private boolean isTargetInner = rec instanceof TargetClass.Inner.InnerRec;
// If statementif (isString && isTargetInner) {// Depends on static fieldrec.setField(superFieldVal + SourceStaticNested.STATIC_FIELD);result = rec.getField();break outerLoop; // Break statement}
// NullPointerException handling (no_new_exception)try {if (rec.getField() == null) throw new NullPointerException();} catch (NullPointerException e) {rec.setField("default");}
// Instance code blocks with target static method call{TargetClass.synchronizedStaticMethod(target);}}
return result;}}}
class SourceSubClass extends SourceClass<String> {}
package com.target;
class TargetClass extends ParentTargetClass {public String superField = "target_super"; // Super.field (target_feature: extends)
// Member inner class (target_feature)public class Inner {public class InnerRec {private String field;
public String getField() {return field;}
public void setField(String field) {this.field = field;}}}
// Synchronized target static methodpublic static synchronized Object synchronizedStaticMethod(TargetClass target) {// Static + this.methodName(arguments)return target.new Inner().new InnerRec().getField();}}
class ParentTargetClass {}