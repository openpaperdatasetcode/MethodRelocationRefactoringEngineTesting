package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
protected class SourceClass {private String sourceField;private TargetClass target;
public SourceClass(TargetClass target, String sourceField) {super();this.target = target;this.sourceField = sourceField;
SourceStaticNested.populateSourceField(this, sourceField);
targetProcessing: {TargetClass.TargetInner targetInner = target.new TargetInner();List<String> combinedList = targetInner.collectData(sourceField);
for (int i = 0; i < 2; i++) {combinedList.add("Iteration-" + i + ":" + sourceField);}
if (combinedList.size() > 5) {break targetProcessing;}combinedList.add("Fallback-Data");}}
public class SourceLocalWrapper {public List<String> wrapTargetData() {TargetClass.TargetInner inner = target.new TargetInner();return inner.collectData(sourceField);}}
public static class SourceStaticNested {public static void populateSourceField(SourceClass source, String data) {source.sourceField = data + "_static";}
public static Function<TargetClass.TargetInner, List<String>> dataExtractor = TargetClass.TargetInner::collectData;}
public String getSourceField() {return sourceField;}}
/**
Target class with member inner class for data collection
Cooperates with SourceClass to process and combine data*/protected class TargetClass {private String targetField = "DefaultTargetField";
public TargetClass() {super();}
public class TargetInner {public List<String> collectData(String externalData) {List<String> dataList = new ArrayList<>();dataList.add(TargetClass.this.targetField);dataList.add(externalData);return dataList;}
public void updateTargetField(String newVal) {TargetClass.this.targetField = newVal;}}
public String getTargetField() {return targetField;}}