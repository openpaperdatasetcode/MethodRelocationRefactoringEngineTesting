package same.pkg;
import java.util.ArrayList;import java.util.List;
interface ParentInterface {}
interface SourceInterface extends ParentInterface {class SourceMemberInner {private List<String> processTargets(TargetInterface... targets) {List<String> result = new ArrayList<>();
private EnhancedForProcessor processor = new EnhancedForProcessor();result.addAll(processor.process(targets));
return result;}
private class EnhancedForProcessor {List<String> process(TargetInterface... targets) {List<String> dataList = new ArrayList<>();for (TargetInterface target : targets) {TargetInterface.TargetInner inner = target.new TargetInner();dataList.add(inner.getFieldValue());}return dataList;}}}
protected default List<String> handleTargets(TargetInterface... targetParams) {SourceMemberInner innerHandler = new SourceMemberInner();List<String> result = innerHandler.processTargets(targetParams);
Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Processed " + result.size() + " items");}};anon.run();
return result;}}
protected interface TargetInterface {class TargetInner {private String field = "default";
public String getFieldValue() {return field;}
public void setFieldValue(String value) {this.field = value;}}
default TargetInner createInner() {return new TargetInner();}}