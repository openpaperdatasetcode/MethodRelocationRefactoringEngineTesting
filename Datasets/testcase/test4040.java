package same.pkg;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;
class ParentClass {protected String parentProtectedField = "parent_data";}
protected class SourceClass extends ParentClass {static class SourceStaticNested {static boolean isTargetValid(TargetClass target) {return target != null;}}
class SourceInner {public List<String> recursiveCollect(TargetClass targetParam, int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {result.add(targetParam.getLocalInnerData());return result;}
synchronized (targetParam) {do {try {Method targetMethod = TargetClass.class.getMethod("appendData", String.class);targetMethod.invoke(targetParam, parentProtectedField + "_depth-" + depth);result.add(targetParam.getCurrentData());depth--;} catch (Exception e) {e.printStackTrace();break;}} while (depth > 0);}
result.addAll(recursiveCollect(targetParam, depth));
class LocalInnerHelper {void addSourceInfo() {result.add("source_inner_helper");}}new LocalInnerHelper().addSourceInfo();
return result;}
public List<String> recursiveCollect(TargetClass targetParam) {return recursiveCollect(targetParam, 3);}}
public List<String> startCollection(TargetClass target) {return new SourceInner().recursiveCollect(target);}}
class TargetClass {private List<String> dataList = new ArrayList<>();
public void appendData(String data) {dataList.add(data);}
public String getCurrentData() {return dataList.isEmpty() ? "" : dataList.get(dataList.size() - 1);}
public String getLocalInnerData() {class TargetLocalInner {String getInnerData() {return "target_local_inner_" + dataList.size();}}return new TargetLocalInner().getInnerData();}}