package test;
import java.util.ArrayList;import java.util.List;
strictfp enum SourceEnum {A, B;
// Static nested classpublic static class SourceStatic {public static String STATIC_FIELD = "source_static";}
// Anonymous inner classprivate Runnable initializer = new Runnable() {@Overridepublic void run() {processTarget(TargetEnum.X);}};
// Record-like inner structure (source_inner_rec position)public record ProcessRec(TargetEnum target) {public List<String> process() throws InvalidDataException {List<String> results = new ArrayList<>();
// Type declaration statementTargetEnum.MemberInner inner = target.new MemberInner();OtherHelper helper = new OtherHelper();
// Expression statementresults.add(target.name());results.add(inner.getData());
// Variable callresults.add(target.getDescription());
// Access instance fieldresults.add(String.valueOf(inner.count));
// With boundsclass BoundedHandler<T extends Number & Comparable<T>> {String format(T num) {return "Formatted: " + num;}}results.add(new BoundedHandler<Integer>().format(inner.count));
// For statementfor (int i = 0; i < inner.count; i++) {results.add("index: " + i);}
// Subclass method call in for loopTargetSubClass sub = new TargetSubClass();for (TargetEnum t : TargetEnum.values()) {results.add(sub.process(t));}
// EnhancedForStatement with ClassName.field (same package others)results.addAll(helper.processInner(inner));
return results;}}
// Instance method in source_inner_rec contextList<String> processTarget(TargetEnum target) {try {return new ProcessRec(target).process();} catch (InvalidDataException e) {return List.of("Error: " + e.getMessage());}}}
enum TargetEnum {X(5), Y(3);
private final int value;
TargetEnum(int value) {this.value = value;}
public String getDescription() {return name() + "_desc";}
// Member inner classpublic class MemberInner {public int count = value;private String data = TargetEnum.this.name() + "_inner";
public String getData() {return data;}}}
class TargetSubClass {// Subclass normal methodString process(TargetEnum target) {return "sub_" + target.name();}}
class OtherHelper {// Private EnhancedForStatement with 1 ClassName.field referenceList<String> processInner(TargetEnum.MemberInner inner) {List<String> list = new ArrayList<>();class InnerProcessor {private void process() {for (String s : List.of(inner.getData())) { // ClassName.field referencelist.add("processed: " + s);}}}new InnerProcessor().process();return list;}}
class InvalidDataException extends Exception {public InvalidDataException(String message) {super(message);}}