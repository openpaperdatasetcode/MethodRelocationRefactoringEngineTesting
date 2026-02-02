package same;
import java.io.IOException;
abstract class SourceClass {static class StaticNested {}
class MemberInner extends TargetClass.MemberInner {@Overrideprivate TargetClass process (TargetClass target) throws IOException {if (target.dataField == null) {throw new IOException ("Target data field is null");}
TargetClass.NestedHandler handler = target.new NestedHandler ();handler.processData (target.dataField);
target.dataField = handler.getProcessedData ();return target;}}
public abstract void execute();}
package same;
abstract class TargetClass {protected String dataField;
class MemberInner {protected abstract TargetClass process(TargetClass target) throws IOException;}
class NestedHandler {private String processedData;
void processData(String input) {this.processedData = input.toUpperCase();}
String getProcessedData() {return processedData;}}
public abstract String getType();}