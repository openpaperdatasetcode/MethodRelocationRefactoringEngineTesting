package test;
import java.util.ArrayList;import java.util.Collection;
@interface SourceAnnotation {class Inner {class InnerRec {protected int outerProtected;
strictfp int varargsMethod(TargetAnnotation target, String... args) {class LocalInner1 {void syncBlock() {synchronized (target) {target.field = 1;}}}new LocalInner1().syncBlock();
class LocalInner2 {}
super.toString();
TargetAnnotation.StaticNested.nestedStaticField = 5;
outerProtected = target.field;
InnerRec rec = new InnerRec();rec.toString();
return target.field;}}}
private static String callInCollection(TargetAnnotation target) {Collection<String> coll = new ArrayList<>();return SourceAnnotation.Inner.InnerRec.varargsMethod(target, coll.toArray(new String[0]));}}
strictfp @interface TargetAnnotation {int field() default 0;
class StaticNested {static int nestedStaticField;}}