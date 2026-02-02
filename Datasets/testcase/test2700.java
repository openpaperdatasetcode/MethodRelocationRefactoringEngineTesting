package com.source;
import com.target.TargetClass;
non-sealed class SourceClass permits SourceSubClass {// Static nested class (source_feature)public static class SourceStaticNested {}
// Member inner class (source_feature)public class SourceMemberInner {}
// Method type parameter: nonepublic final int methodToMove(TargetClass... targets) {super(); // Super keywordsint count = 0;
for (TargetClass target : targets) {// Variable call + contains target parameter (per_condition)target.toString();String targetField = target.targetField;
// Expression statementcount += targetField.length();}
return count;}}
class SourceSubClass extends SourceClass {// Override violation: reduces access modifier from final to public@Overridepublic int methodToMove(TargetClass... targets) {return super.methodToMove(targets) * 2;}}
package com.target;
protected class TargetClass {public String targetField = "targetValue"; // Source contains target's field (per_condition)
{// Anonymous inner class (target_feature)new Runnable() {@Overridepublic void run() {}};}}