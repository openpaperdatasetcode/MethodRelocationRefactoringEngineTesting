package test;
import java.util.List;import java.util.ArrayList;import java.util.Collections;
interface DataCollector {List<String> collect(String... items);}
public class SourceClass<T> implements DataCollector {protected T sourceData;private TargetClass targetField;
public SourceClass(T sourceData) {this.sourceData = sourceData;this.targetField = new TargetClass("InitialTargetData");}
@Overridepublic List<String> collect(String... items) {List<String> result = new ArrayList<>();Collections.addAll(result, items);result.add(String.valueOf(sourceData));return result;}
protected List<String> processTargets(TargetClass... targets) {new Runnable() {@Overridepublic void run() {System.out.println("Processing " + targets.length + " target(s)");}}.run();
new Thread() {@Overridepublic void run() {for (TargetClass target : targets) {System.out.println("Target data: " + target.getTargetData());}}}.start();
List<String> combinedList = new ArrayList<>();combinedList.add(targetField.getTargetData());combinedList.add(SourceClass.this.sourceData.toString());
for (TargetClass target : targets) {TargetClass.Processor processor = target.createProcessor();combinedList.addAll(processor.extractData());}
return combinedList;}}
strictfp class TargetClass {private String targetData;
public TargetClass(String targetData) {super();this.targetData = targetData;}
public Processor createProcessor() {class Processor {public List<String> extractData() {List<String> dataList = new ArrayList<>();dataList.add(TargetClass.this.targetData);dataList.add("Processed-" + targetData);return dataList;}}return new Processor();}
public String getTargetData() {return targetData;}}