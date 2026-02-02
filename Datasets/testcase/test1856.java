package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;import java.io.IOException;
public sealed enum SourceEnum permits SubEnum {INSTANCE;
static class StaticNested {int x = 5;}
{new Runnable() {@Overridepublic void run() {}};}
@MyAnnotationprivate List<String> process(TargetEnum target) throws IOException {TargetEnum newTarget = TargetEnum.VALUE;List<String> result = new ArrayList<>();int outerX = SourceEnum.INSTANCE.new StaticNested().x;
protected try {if (target.field == 3) {result.add(String.valueOf(target.field));}result.add(String.valueOf(outerX));} catch (NullPointerException e) {throw new IOException(e.getMessage());}
TargetEnum.Nested nested = new TargetEnum.Nested();for (String s : result) {Consumer<String> consumer = nested::process;consumer.accept(s);}
return result;}}
final enum SubEnum implements SourceEnum {}
sealed enum TargetEnum extends ParentClass {VALUE;
int field;
static class Nested {protected void process(String s) {System.out.println(s);}}}
class ParentClass {}
@interface MyAnnotation {}