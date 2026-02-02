package test;
import java.lang.reflect.Method;
// Protected source class with anonymous inner and local inner classesprotected class SourceClass {class InnerClass {class InnerRec {// Overloading method 1 (position: source_inner_rec)public Object process(TargetClass target) {return process(target, "default_param");}
// Overloading method 2 (core method, position: source_inner_rec)public Object process(TargetClass target, String param) {// Type declaration statementclass LocalProcessor {}LocalProcessor processor = new LocalProcessor();
// Requires try-catchtry {// Anonymous inner class (source_class feature)Runnable task = new Runnable() {@Overridepublic void run() {variableCall(target);}};task.run();
// Used by reflectionMethod method = TargetClass.class.getDeclaredMethod("processLocal", String.class);method.invoke(target, param);
// Local inner class (source_class feature)class LocalHelper {Object getResult() {return target.getData() + "_" + param;}}return new LocalHelper().getResult();} catch (Exception e) {return "error: " + e.getMessage();}}
private void variableCall(TargetClass target) {target.doTask();}}}
// Trigger method to invoke overloading methodspublic Object triggerProcess(TargetClass target) {return new InnerClass().new InnerRec().process(target);}}
// Non-sealed target class with local inner class (target_feature)non-sealed class TargetClass {private String data = "target_data";
public void doTask() {}
public String getData() {return data;}
public void processLocal(String param) {// Local inner class (target_feature)class TargetLocalInner {void updateData() {data += "_" + param + "_local";}}new TargetLocalInner().updateData();}}