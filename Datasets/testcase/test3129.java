package test;
// Target enum class (default modifier + anonymous inner class + inner record)enum TargetEnum {INSTANCE;
public static String staticField = "target_static"; // For depends_on_static_field
public TargetEnum() {// Target feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
class TargetInner {record TargetInnerRec(String val) {} // target_inner_rec}}
// Source enum class (protected modifier + local inner class + member inner class)protected enum SourceEnum {INSTANCE;
// Source feature: member inner classclass SourceInner {record SourceInnerRec() {} // source_inner_rec
private Object methodToMove(TargetEnum.TargetInner.TargetInnerRec rec) {// Super keywords (call enum superclass method)super.toString();
// Variable callString targetVal = rec.val();TargetEnum target = TargetEnum.INSTANCE;TargetEnum.TargetInner targetInner = target.new TargetInner();
// Depends_on_static_field (target's static field)String staticVal = TargetEnum.staticField;
// Source feature: local inner classclass LocalInner {Object process() {return targetVal + "_" + staticVal;}}
// No new exception thrownreturn new LocalInner().process();}}
// Initialize inner class and invoke methodprivate final SourceInner inner = new SourceInner();
public Object execute(TargetEnum.TargetInner.TargetInnerRec rec) {return inner.methodToMove(rec);}}