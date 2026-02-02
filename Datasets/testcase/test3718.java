import java.util.ArrayList;import java.util.List;
public class SourceClass {private String outerField = "source_outer_field";
public static class FirstStaticNested {@RefactorMarkerpublic final List<String> recursiveMethod(TargetClass target, int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {return result;}
TargetClass.ChildTarget child = new TargetClass.ChildTarget(target);synchronized (target) {expressionStatement: {target.setData(SourceClass.this.outerField);}
List<String> targetDataList = target.getDataList();for (String data : targetDataList) {if (data.contains("skip")) {continue;}result.add(data);variableCall(target);}}
Runnable lambda = () -> {for (int i = 0; i < 3; i++) {target.addData("lambda_data_" + i);}};lambda.run();
result.addAll(recursiveMethod(target, depth - 1));return result;}
private void variableCall(TargetClass target) {target.updateTimestamp();}}
public static class SecondStaticNested {public strictfp List<String> callInDoWhile(TargetClass target, String... varargs) {List<String> result = new ArrayList<>();int count = 0;do {result.addAll(target.processVarargs(varargs));count++;} while (count < 2);return result;}}
static class TargetClass extends ParentTarget {private List<String> dataList = new ArrayList<>();private long timestamp;
public TargetClass() {super("parent_init_data");}
public void setData(String data) {new Runnable() {@Overridepublic void run() {dataList.add(data);}}.run();}
public List<String> getDataList() {return new ArrayList<>(dataList);}
public void addData(String data) {dataList.add(data);}
public void updateTimestamp() {this.timestamp = System.currentTimeMillis();}
public strictfp List<String> processVarargs(String... varargs) {List<String> result = new ArrayList<>();for (String arg : varargs) {result.add(arg + "_processed");}return result;}
static class ChildTarget extends TargetClass {public ChildTarget(TargetClass parent) {super();}
@Overridepublic void setData(String data) {}}}
static class ParentTarget {protected String parentField;
public ParentTarget(String parentField) {this.parentField = parentField;}
public void setData(String data) {this.parentField = data;}}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorMarker {}