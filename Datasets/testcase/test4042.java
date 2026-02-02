package same.pkg;
import java.util.ArrayList;import java.util.List;
public class TargetClass {private Object data;
public void setData(Object data) {this.data = data;}
public Object getProcessedData() {class TargetLocalInner {Object formatData() {if (data instanceof String) {return "str_" + data;} else if (data instanceof Integer) {return "int_" + data;}return "obj_" + data;}}return new TargetLocalInner().formatData();}}
class SourceClass {private TargetClass targetField = new TargetClass();
static class SourceStaticNested {static Object convertRawType(List rawList) {if (rawList == null || rawList.isEmpty()) {return null;}return rawList.get(0);}}
public Object handleTarget(Object input) {labeledBlock: {targetField.setData(input);Object processed = targetField.getProcessedData();
switch (processed.getClass().getSimpleName()) {case "String":return ((String) processed).toUpperCase();case "Integer":return (Integer) processed * 2;default:return processed;}}}
public Object handleTarget(List rawInput) {Object rawData = SourceStaticNested.convertRawType(rawInput);return handleTarget(rawData);}
class SourceLocalWrapper {Object wrapResult(Object result) {class LocalInner {List<Object> createResultList(Object data) {List<Object> list = new ArrayList<>();list.add(data);list.add(SourceClass.this.targetField.getProcessedData());return list;}}return new LocalInner().createResultList(result);}}}