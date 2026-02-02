import java.util.ArrayList;import java.util.List;
interface DataCollector {List<String> collectData();}
class ParentTarget {protected String parentData = "parent_base_data";
protected void parentMethod () {super.methodName () }}
protected class TargetClass extends ParentTarget {
public class TargetInner {private String innerData;
public TargetInner (String data) {this.innerData = data;}
public String getInnerData () {return innerData;}
public void appendData (String suffix) {this.innerData += suffix;}}
public List<String> processWithLocalInner(List<String> dataList) {class LocalProcessor {List<String> process() {List<String> result = new ArrayList<>();for (String data : dataList) {result.add(data.toUpperCase());}return result;}}return new LocalProcessor().process();}
@Overrideprotected void parentMethod () {super.parentMethod (); 
 super.methodName ()}}
abstract class SourceClass implements DataCollector {
protected TargetClass sourceTargetField;
public class FirstInner {
 default void recursiveCollect (TargetClass.TargetInner inner, int depth, List<String> result) {if (depth <= 0) {return;}
sourceTargetField.parentMethod ();
for (int i = 0; i < depth; i++) {inner.appendData ("_iter" + i);result.add (inner.getInnerData ());}
recursiveCollect (inner, depth - 1, result);}}
public class SecondInner {
public List<String> processTargetInner() {List<String> result = new ArrayList<>();if (sourceTargetField == null) {
sourceTargetField = new TargetClass ();}
TargetClass.TargetInner targetInner = sourceTargetField.new TargetInner ("init_data");
targetInner.appendData ("_base");result.add (targetInner.getInnerData ());
FirstInner firstInner = new FirstInner ();firstInner.recursiveCollect (targetInner, 2, result);
result = sourceTargetField.processWithLocalInner (result);
return result; // no_new_exception}}
@Overridepublic abstract List<String> collectData();}
class ConcreteSource extends SourceClass {@Overridepublic List<String> collectData() {SecondInner secondInner = new SecondInner();return secondInner.processTargetInner();}}
public class SourceTest {public static void main (String [] args) {SourceClass source = new ConcreteSource ();List<String> dataList = source.collectData ();System.out.println ("Collected Data:" + dataList); }}