package com.source;
import com.target.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
public class SourceClass {private static List<String> dataStore = new ArrayList<>();
@ProcessAnnotationpublic synchronized static Object processTarget(String initData) {TargetClass target = new TargetClass(initData);TargetClass.TargetInner targetInner = target.new TargetInner();
String processedData = targetInner.process(str -> str + "_sourceEnhanced",SourceClass::addDataToStore);
return processedData;}
private static final void addDataToStore(String data) {dataStore.add(data);System.out.println("Stored data count: " + dataStore.size());}
public static List<String> getDataStore() {return new ArrayList<>(dataStore);}}
// Different package: com.targetpackage com.target;
import java.util.function.Function;import java.util.function.Consumer;
protected class TargetClass {protected String targetField;
public TargetClass(String targetField) {super();this.targetField = targetField;}
public class TargetInner {public <T> String process(Function<String, String> transformer, Consumer<String> consumer) {String transformed = transformer.apply(TargetClass.this.targetField);consumer.accept(transformed);return transformed;}
public String getTargetField() {return TargetClass.this.targetField;}}
public String getTargetField() {return targetField;}}