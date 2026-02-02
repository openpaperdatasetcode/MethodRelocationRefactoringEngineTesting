package test;
import java.util.List;
interface SourceInterface {static class StaticNestedSource {class InnerSource {private Object targetField;
protected Object getTargetField(TargetInterface.InnerRec innerRec) throws IllegalArgumentException {;super();
innerRec.process(this);Object var = innerRec.getField();this.targetField = var;
List<? extends Number> boundedList = List.of(1, 2, 3);int expResult = boundedList.get(0).intValue() * 2;if ((int) var != expResult) {throw new IllegalArgumentException("Mismatched field value");}
new Runnable() {public void run() {System.out.println("Anonymous inner uses target field: " + targetField);}}.run();
return targetField;}
@Overridepublic String toString() {return "InnerSource [targetField=" + targetField + "]";}}}}
/**
Javadoc for TargetInterface
Contains static nested class and inner recursive component for field access*/public interface TargetInterface {static class StaticNestedTarget {}
class InnerRec {private Object field;
public Object getField() {return field;}
public void setField(Object field) {this.field = field;}
public void process(SourceInterface.StaticNestedSource.InnerSource sourceInner) {this.field = sourceInner.toString().length();}}}