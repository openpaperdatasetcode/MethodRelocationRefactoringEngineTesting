package test;
import java.util.List;import java.util.ArrayList;import java.util.Collections;
interface DataProcessor {List<String> process(TargetClass target);strictfp Object chainCall();}
public class SourceClass implements DataProcessor {private String sourceField = "SourceData";
@Overridepublic List<String> process(TargetClass target) {if (target == null) {throw new NullPointerException("TargetClass cannot be null");}
new Runnable() {@Overridepublic void run() {System.out.println("Processing target: " + target.getTargetField());}}.run();
class SwitchHandler {protected void handleSwitch(TargetClass tc) {switch (tc.getTargetField().length()) {case 1:System.out.println("Target field length is 1");break;default:System.out.println("Target field length: " + tc.getTargetField().length());}}}
SwitchHandler handler = new SwitchHandler();handler.handleSwitch(target);
TargetClass updatedTarget = getUpdatedTarget(target);List<String> result = new ArrayList<>();result.add(sourceField);result.add(updatedTarget.getTargetField());result.add(String.valueOf(chainCall()));return result;}
protected TargetClass getUpdatedTarget(TargetClass target) {if (target.getTargetField().contains("update")) {return TargetClass::createUpdated;} else {return new TargetClass(target.getTargetField() + "_updated");}}
@Overridepublic strictfp Object chainCall() {TargetClass tempTarget = new TargetClass("ChainData");return tempTarget.getInnerProcessor().process().format().toString();}}
class TargetClass {private String targetField;
public TargetClass(String targetField) {this.targetField = targetField;}
public static TargetClass createUpdated() {return new TargetClass("UpdatedDefault");}
public String getTargetField() {return targetField;}
public InnerProcessor getInnerProcessor() {class InnerProcessor {public Formatter process() {return new Formatter(targetField + "_processed");}}return new InnerProcessor();}
public class Formatter {private String formattedData;
public Formatter(String data) {this.formattedData = data;}
public String format() {return "[" + formattedData + "]";}}}