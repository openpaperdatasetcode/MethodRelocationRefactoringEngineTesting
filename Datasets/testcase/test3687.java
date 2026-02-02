package test;
interface Processable {void process(TargetClass target) throws IllegalArgumentException;}
class SourceClass implements Processable {public class InnerFirst {public TargetClass getTargetInstance(String data) {return new TargetClass(data);}}
public class InnerSecond {public void updateTargetField(TargetClass target, String value) {target.setField(value);}}
@Deprecated@Overrideprotected void process(TargetClass target) throws IllegalArgumentException {if (target == null) {throw new IllegalArgumentException("Target cannot be null");}InnerFirst innerFirst = new InnerFirst();InnerSecond innerSecond = new InnerSecond();
TargetClass.StaticNested nested = new TargetClass.StaticNested();String processed = nested.processField(target.getField());innerSecond.updateTargetField(target, processed);
System.out.println(target.getField());}
protected void process(TargetClass target, String extra) throws IllegalArgumentException {process(target);target.setField(target.getField() + "_" + extra);}}
private class TargetClass {private String field;
public TargetClass(String field) {this.field = field;}
public String getField() {return field;}
public void setField(String field) {this.field = field;}
public static class StaticNested {public String processField(String input) {return input.toUpperCase();}}}
