package test;
import java.util.List;import java.util.ArrayList;
interface ParentInterface {void process(TargetClass target);}
class SourceClass implements ParentInterface {private String outerPrivateField = "sourcePrivateData";
public class SourceInner implements ParentInterface {public SourceInner() {super();}
@Overridepublic void process(TargetClass target) {try {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}
SourceInnerHelper helper = new SourceInnerHelper();List<String> dataList = new ArrayList<>();dataList.add(target.targetField);dataList.add(outerPrivateField);
for (String data : dataList) {if (data.contains("private")) {break;}variableCall(data);}} catch (IllegalArgumentException e) {e.printStackTrace();}}
private void variableCall(String value) {System.out.println("Processed: " + value);}}
private class SourceInnerHelper {public SourceInnerHelper() {}}
{new Runnable() {@Overridepublic void run() {new SourceInner().process(new TargetClass());}}.run();}
@Overridepublic void process(TargetClass target) {}}
private class TargetClass {String targetField = "targetPublicField";
public void targetMethod() {class TargetLocalInner {public void printField() {System.out.println(targetField);}}new TargetLocalInner().printField();}}