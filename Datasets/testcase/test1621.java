package test;
interface Action {Object execute();}
interface TargetInterface {void perform();}
protected class SourceClass implements Action {public static class StaticNested {public static String process(String input) {return input.toUpperCase();}}
@Overridepublic Object execute() {// Anonymous inner classRunnable runner = new Runnable() {@Overridepublic void run() {System.out.println("Processing target inner rec");}};runner.run();
return null;}
// Overriding method (implicit override of Action.execute())public Object execute(TargetClass.Inner.Rec targetRec) {// Type declaration statementchar delimiter;
// For statementfor (int i = 0; i < targetRec.items.size(); i++) {if (targetRec.items.get(i).isEmpty()) {// Continue statementcontinue;}
// Protected CharacterLiteral (1 occurrence)protected char literal = ';';delimiter = literal;
// Variable call - access target inner rec's fieldtargetRec.items.set(i, targetRec.items.get(i) + delimiter);}
return targetRec.items;}}
public class TargetClass implements TargetInterface {public class Inner {public class Rec {public java.util.List<String> items = new java.util.ArrayList<>();}}
@Overridepublic void perform() {}}