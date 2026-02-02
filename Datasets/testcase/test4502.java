package test;
import java.util.ArrayList;import java.util.Collection;
private @interface TargetAnnotation {default void targetMethod() {class LocalInner {int field = 1;}LocalInner inner = new LocalInner();assert inner.field == 1;}}
@interface SourceAnnotation {class SourceInner1 {TargetAnnotation targetField = null;
class SourceInnerRec {public int sourceMethod(TargetAnnotation param) {int count = 0;while (count < 3) {SourceInner1 outer = new SourceInner1();outer.targetField = param;
assert outer.targetField != null : "Target field is null";
if (count == 1) {break;}count++;}
Collection<String> coll = new ArrayList<>();try {String result = this.callMethod(coll);param.targetMethod();} catch (Exception e) {}
return param.hashCode();}
private final String callMethod(Collection<String> coll) {coll.add("test");return coll.iterator().next();}}}
class SourceInner2 {void invoke() {SourceInner1.Rec innerRec = new SourceInner1().new SourceInnerRec();try {innerRec.sourceMethod(null);} catch (Exception e) {}}}}