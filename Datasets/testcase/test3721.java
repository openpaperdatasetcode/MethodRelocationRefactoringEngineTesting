import java.util.Objects;
public class SourceClass implements DataHandler {static {TargetClass target = new TargetClass();new InnerClass().instanceMethod(target);}
public static class InnerClass {public void instanceMethod(TargetClass target) {variableCall(target);}
private void variableCall(TargetClass target) {target.process("inner_call_data");}}
strictfp public Object recursiveMethod(TargetClass target, int depth) {if (depth <= 0) {return target;}
String var1, var2, var3;var1 = "assign1_" + depth;var2 = "assign2_" + depth;var3 = "assign3_" + depth;
try {for (int i = 0; i < 3; i++) {if (i == 1) {continue;}target.setTempData(var1 + "_" + i);}} catch (Exception e) {}
Object nextResult = recursiveMethod(target, depth - 1);return nextResult;}
@Overridepublic void handleData(Object data) {new Runnable() {@Overridepublic void run() {if (data instanceof TargetClass) {recursiveMethod((TargetClass) data, 3);}}}.run();}}
interface DataHandler {void handleData(Object data);}
private class TargetClass extends ParentTarget {private String tempData;
public TargetClass() {super();}
public void process(String data) {new Runnable() {@Overridepublic void run() {System.out.println("Target processed: " + data);}}.run();}
public void setTempData(String data) {this.tempData = data;}
public String getTempData() {return tempData;}}
class ParentTarget {protected static Object parentStaticData;
public ParentTarget() {parentStaticData = "parent_init";}
public synchronized static Object callInWhile(int count) {while (count > 0) {Object result = ((Integer c) -> {parentStaticData = "parent_updated_" + c;return parentStaticData;}).apply(count);count--;}return parentStaticData;}}