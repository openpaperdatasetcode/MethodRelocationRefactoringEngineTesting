package same;
import java.util.ArrayList;import java.util.List;
private enum SourceEnum {VALUE {/**
Overrides the process method to handle TargetEnum
@param target the target enum instance
@return list of processed strings*/@Overrideprotected List<String> process(TargetEnum target) {// Local inner classclass Processor {// VariableDeclarationStatement with target static field in inner classpublic int maxCount = TargetEnum.STATIC_LIMIT;
List<String> handle() {List<String> result = new ArrayList<>();try {for (int i = 0; i < maxCount; i++) {result.add(target.name() + "-" + i);}result.addAll(TargetEnum.StaticNested.transform(target.data));} catch (Exception e) {result.add("Error: " + e.getMessage());}return result;}}
// Anonymous inner classRunnable logTask = new Runnable() {@Overridepublic void run() {System.out.println("Processing " + target);}};logTask.run();
return new Processor().handle();}};
protected abstract List<String> process(TargetEnum target);}
package same;
import java.util.List;import java.util.Arrays;
abstract enum TargetEnum {TARGET_VALUE("data1"),ANOTHER_TARGET("data2");
static final int STATIC_LIMIT = 3;final String data;
TargetEnum(String data) {this.data = data;}
static class StaticNested {static List<String> transform(String input) {return Arrays.asList(input.toUpperCase());}}}