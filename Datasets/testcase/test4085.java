package test;
strictfp class SourceClass {class SourceInner {private int overloadingMethod(TargetClass.TargetInnerRec rec) {try {static int count = 1;count += ((ParentClass) rec).superField;
Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(rec.value());}};anon.run();
return count + rec.value();} catch (ClassCastException e) {return 0;}}
private int overloadingMethod(String str) {return str.length();}}
static class StaticNested {void useInner(SourceInner inner, TargetClass.TargetInnerRec rec) {inner.overloadingMethod(rec);}}}
public class TargetClass {public void createLocal() {class LocalInner {void processRec(TargetInnerRec rec) {System.out.println(rec.value());}}new LocalInner().processRec(new TargetInnerRec(5));}
record TargetInnerRec(int value) {}}
class ParentClass {int superField = 10;}