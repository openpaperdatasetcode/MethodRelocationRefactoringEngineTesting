package test;
import java.util.List;
/**
Source record class with inner components
/
sealed record SourceRecord<T>(T value) permits ExtendedSourceRecord {
public class MemberInner {
/*
Static method in source_inner with method javadoc
Processes target's inner class and returns TargetClass instance
@param targetInner target's member inner class instance
@return TargetClass instance*/private static TargetRecord process(TargetRecord.Inner targetInner) {
// Anonymous inner class
Runnable logger = new Runnable() {
@Override
public void run() {
System.out.println("Processing: " + targetInner.data());
}
};
// Constructor invocationTargetRecord target = new TargetRecord<>(null);
try {// Super keywordSystem.out.println(targetInner.superToString());
// Variable call - access target inner's fieldU processedData = targetInner.data();target = new TargetRecord<>(processedData);
// Access instance method of target innerList<String> details = targetInner.getDetails();
// Switch statementswitch (details.size()) {case 0:logger.run();break;default:details.forEach(System.out::println);}
// Depends on inner classtargetInner.update("processed: " + processedData);} catch (Exception e) {// no new exception}
return target;}}}
record ExtendedSourceRecord<T>(T value, String extra) extends SourceRecord<T>(value) {}
/**
Target record class with Javadoc
Extends a base class and contains member inner class
/
public record TargetRecord(U content) extends BaseRecord {
/*
Member inner class of TargetRecord*/public class Inner {private U data;
public Inner(U data) {this.data = data;}
public U data() {return data;}
public void update(U newData) {this.data = newData;}
public List<String> getDetails() {return List.of(data.toString());}
public String superToString() {return super.toString();}}}
class BaseRecord {}