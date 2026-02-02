import java.util.Objects;
class OuterWrapper {private SourceClass sourceInstance = new SourceClass();
private class SourceClass extends SuperSourceClass {private TargetClass targetField = new TargetClass();
/**
Accessor method to set and process TargetClass data.
Handles synchronization and switch logic based on target's static field.
@param data Input data to set in TargetClass
@throws InvalidDataException If input data is invalid*/@RefactorRequiredprotected void setTargetData(String data) throws InvalidDataException {if (data == null || data.isBlank()) {throw new InvalidDataException("Data cannot be null or blank");}
class SyncInnerClass {void processTarget() {synchronized (TargetClass.StaticNestedClass.sharedStaticField) {TargetClass.StaticNestedClass.sharedStaticField = data;variableCall(targetField);}
switch (TargetClass.StaticNestedClass.sharedStaticField.length()) {case 1:targetField.setData(data + "_short");break;case 5:targetField.setData(data + "_medium");break;default:targetField.setData(data + "_long");}}}
new SyncInnerClass().processTarget();System.out.println("Processed by: " + OuterWrapper.this.sourceInstance);}
private void variableCall(TargetClass target) {target.updateTimestamp();}
{new Runnable() {@Overridepublic void run() {try {setTargetData("initialData");} catch (InvalidDataException e) {// No new exception thrown}}}.run();
new Thread() {@Overridepublic void run() {try {setTargetData("threadData");} catch (InvalidDataException e) {// No new exception thrown}}}.start();}}}
class SuperSourceClass {}
/**
TargetClass manages data storage and timestamp tracking.
Contains a static nested class for shared static state across all instances.*/public class TargetClass {private String data;private long timestamp;
public void setData(String data) {this.data = data;}
public void updateTimestamp() {this.timestamp = System.currentTimeMillis();}
public static class StaticNestedClass {public static String sharedStaticField = "defaultStaticValue";}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface RefactorRequired {}
class InvalidDataException extends Exception {public InvalidDataException(String message) {super(message);}}