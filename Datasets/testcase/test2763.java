package test;
record SourceRec(String data) {// Member inner classpublic class SourceInner {// Source inner record (source_inner_rec)public record SourceInnerRec() {default Object methodToMove(TargetRec target) {// Variable call + contains target field (per_condition)target.toString();TargetRec.TargetInner inner = target.new TargetInner();
// Type declaration statementString targetField = inner.innerField;
// TryStatement with ClassName.field (count 1, pos: source)private String result;try {result = targetField + TargetRec.StaticNested.STATIC_FIELD;} catch (Exception e) {// No new exceptionresult = "default_result";}
// Anonymous inner class (source_feature)Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(result);}};anon.run();
return result;}}}}
record TargetRec() {// Static nested class (target_feature)public static class StaticNested {public static final String STATIC_FIELD = "_static"; // ClassName.field}
public class TargetInner {public String innerField = "targetInner"; // Source contains target's field (per_condition)}}