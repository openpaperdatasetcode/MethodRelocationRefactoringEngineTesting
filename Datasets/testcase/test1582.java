package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessingAnnotation {}
abstract class SourceClass {protected String outerField = "outer_value";
public static class StaticNested {public int id;}
protected Object process(TargetClass... targets) {// Local inner class in sourceclass TargetProcessor {Object handle(TargetClass target) {// Uses outer thistarget.setData(SourceClass.this.outerField);return target.getData();}}TargetProcessor processor = new TargetProcessor();
// Constructor invocationTargetClass newTarget = new TargetClass("initial");
// Has annotation@ProcessingAnnotationint processedCount = 0;
for (TargetClass target : targets) {// Variable call and access instance fieldprocessedCount += target.counter;
// Overloaded method callstarget.setValue(processedCount);target.setValue("count: " + processedCount);
processor.handle(target);}
return newTarget.process();}}
/**
TargetClass with Javadoc
Handles data processing with local inner class*/final class TargetClass {public int counter;private String data;
public TargetClass(String data) {this.data = data;this.counter = 0;}
// Overloaded methodspublic void setValue(int val) {this.counter = val;}
public void setValue(String val) {this.data = val;}
public void setData(String data) {this.data = data;}
public String getData() {return data;}
public Object process() {// Local inner class in targetclass LocalProcessor {Object compute() {return data + "_processed";}}return new LocalProcessor().compute();}}