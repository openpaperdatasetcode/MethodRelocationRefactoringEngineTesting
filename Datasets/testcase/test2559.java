package same;
import java.util.ArrayList;import java.util.List;
public class SourceClass {protected String outerProtected = "base-data";static class StaticNested {}
{// Anonymous inner classRunnable initTask = new Runnable() {@Overridepublic void run() {System.out.println("Source initialized");}};initTask.run();}
@SuppressWarnings("unchecked")protected <T extends TargetClass> List<String> process(T target) {List<String> result = new ArrayList<>();int i = 0;
// Labeled statement + do statementlabeledLoop: do {// Expression statementtarget.appendData(outerProtected + "-" + i);
// If/else with target recursion callif (i < 2) {TargetClass.recursiveProcess(target, i);} else {result.add(target.getProcessedData());if (i == 3) break labeledLoop;}
i++;} while (i < 5);
// Access target instance methodresult.addAll(target.getStaticNestedData());return result;}}
package same;
import java.util.List;import java.util.ArrayList;
protected class TargetClass {private final List<String> dataList = new ArrayList<>();static class StaticNested {}
// Recursion method (instanceReference call)public static void recursiveProcess(TargetClass instance, int depth) {if (depth <= 0) return;instance.dataList.add("recursive-" + depth);recursiveProcess(instance, depth - 1);}
void appendData(String data) {dataList.add(data);}
String getProcessedData() {return dataList.get(dataList.size() - 1);}
List<String> getStaticNestedData() {return new ArrayList<>(List.of("static-nested-1", "static-nested-2"));}}