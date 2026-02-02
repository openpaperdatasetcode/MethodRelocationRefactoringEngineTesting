package com.source;
import com.target.TargetEnum;
sealed enum SourceEnum permits SourceSubEnum {INSTANCE;
// Anonymous inner classes (source_feature)private Runnable anon1 = new Runnable() {@Overridepublic void run() {}};private Runnable anon2 = new Runnable() {@Overridepublic void run() {}};
public class SourceInner {public class SourceInnerRec {// with_bounds: T extends Number & Comparable<T>final <T extends Number & Comparable<T>> int methodToMove(TargetEnum target, T value) {// Variable call + contains target parameter (per_condition)target.toString();TargetEnum.Inner.InnerRec rec = target.new Inner().new InnerRec();
// Expression statementint result = rec.getCount() + value.intValue();
// Access instance methodrec.incrementCount(result);
// Throw statement + no_new_exceptiontry {if (rec.getCount() < 0) {throw new IllegalArgumentException("Count cannot be negative");}} catch (IllegalArgumentException e) {// No new exception thrownresult = 0;}
return result;}}}}
non-sealed enum SourceSubEnum extends SourceEnum {}
package com.target;
private enum TargetEnum {TARGET_INSTANCE;
public class Inner {public class InnerRec {private int count = 0;
public int getCount() {return count;}
public void incrementCount(int value) {this.count += value;}
// Local inner class (target_feature)public void createLocalInner() {class TargetLocalInner {}new TargetLocalInner();}}}}