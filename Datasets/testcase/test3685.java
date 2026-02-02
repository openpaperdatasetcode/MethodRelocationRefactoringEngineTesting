package test;
import java.util.List;import java.util.function.Supplier;
public enum SourceEnum {INSTANCE;
public static class StaticNestedSource {public static final String STATIC_FIELD = "source_static";}
{new Runnable() {@Overridepublic void run() {Object result = instanceMethod(TargetEnum.VALUE1);System.out.println(result);}}.run();}
Object instanceMethod(TargetEnum target) {try {ParentClass parent = new ParentClass();Supplier<List<String>> supplier = parent::invokeTargetMethod;List<String> parentResult = supplier::get;
target.compute();String targetField = target.field;String combined = targetField + StaticNestedSource.STATIC_FIELD;
super.toString();return combined;} catch (IllegalStateException e) {throw new IllegalStateException("Processing failed", e);}}}
enum TargetEnum {VALUE1("target_field");
public String field;
TargetEnum(String field) {super();this.field = field;}
public void compute() {class LocalInner {void updateField() {field = field + "_updated";}}new LocalInner().updateField();}}
class ParentClass {public List<String> invokeTargetMethod() {return List.of(TargetEnum.VALUE1.field);}}