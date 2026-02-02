package test;
import java.util.ArrayList;import java.util.List;import java.io.IOException;
abstract class SourceClass {/**
Processes the target parameter and returns a list of strings.
@param targetParam the target parameter to process
@return list of processed strings
*/
private List<String> process(TargetClass targetParam) {
List<String> result = new ArrayList<>();
try {
String data = targetParam.getData();
result.add(data);
targetParam.execute();
} catch (IOException e) {
result.add(e.getMessage());
}
return result;
}
}
private class TargetClass extends ParentClass {private String data;
TargetClass(String data) {super();this.data = data;Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(data);}};anon.run();}
String getData() {return data;}
void execute() throws IOException {if (data == null) {throw new IOException("Data is null");}}}
class ParentClass {}