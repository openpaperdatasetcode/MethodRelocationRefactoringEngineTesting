import java.util.List;import java.util.ArrayList;
protected record SourceRecord(String outerPrivateField) {public void instanceMethod(List<String> dataList, TargetRecord target) {MemberInner inner = new MemberInner();inner.process(dataList, target);}
public class MemberInner {private String innerField;
protected int overridingMethod(TargetRecord target) {labeledBlock: {if (target.data() == null) {break labeledBlock;}this.innerField = target.data();; // EmptyStatement (modifier:private, target_feature:this.field[1])variableCall(target);}
try {this.innerField = SourceRecord.this.outerPrivateField; // access_outer_private + uses_outer_thisreturn Integer.parseInt(this.innerField);} catch (NumberFormatException e) {return 0; // requires_try_catch + no_new_exception}}
private void variableCall(TargetRecord target) {target.updateData(this.innerField); // depends_on_inner_class}
public void process(List<String> dataList, TargetRecord target) {if (dataList.isEmpty()) {return; // return statement}
int result = overridingMethod(target);dataList.add(String.valueOf(result));
new Runnable() { // source_class feature: anonymous inner class@Overridepublic void run() {System.out.println("Processed data: " + dataList);}}.run();}}
{new Runnable() { // source_class feature: anonymous inner class@Overridepublic void run() {instanceMethod(new ArrayList<>(), new TargetRecord("initial_data"));}}.run();}}
public record TargetRecord(String data) {public void updateData(String newData) {new Runnable() { // target_class feature: anonymous inner class@Overridepublic void run() {System.out.println("Target data updated to: " + newData);}}.run();}}