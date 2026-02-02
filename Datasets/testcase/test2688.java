package com.source;
import com.target.TargetEnum;
private enum SourceEnum {INSTANCE;
public int methodToMove(TargetEnum... targets) {// Anonymous inner class (source_feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in enum");}};
// Local inner class (source_feature)class LocalInner {public void process(TargetEnum target) {target.getInner().innerMethod();}}
int count = 0;// While statementwhile (count < targets.length) {TargetEnum target = targets[count];// Constructor invocation (inner class)TargetEnum.TargetInner inner = target.new TargetInner();// Variable call + contains target parameter (per_condition)inner.toString();String targetField = target.field; // Source contains target's field
new LocalInner().process(target);count++;}
anonymous.run();return count;}}
package com.target;
sealed enum TargetEnum permits TargetSubEnum {INSTANCE("targetFieldValue");
public final String field; // Per_condition: source contains this field
TargetEnum(String field) {this.field = field;}
// Member inner class (target_feature)public class TargetInner {public void innerMethod() {}}
public TargetInner getInner() {return new TargetInner();}}
non-sealed enum TargetSubEnum extends TargetEnum {SUB_INSTANCE("subTargetField");
TargetSubEnum(String field) {super(field);}}