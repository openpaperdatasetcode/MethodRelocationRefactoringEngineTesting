package test;
import java.util.function.Function;
abstract class SuperTarget {protected String superField1 = "base1";protected String superField2 = "base2";}
abstract class Source {protected Target process(Target target) {class LocalProcessor {Target handle(Target t) {private String field1 = t.superField1;private String field2 = t.superField2;return t;}}
Function<Target.InnerAnon, Target> func = LocalProcessor::handle;
Target.InnerAnon anon = target.new InnerAnon() {@Overridevoid execute() {System.out.println("Anonymous execution");}};
try {return func.apply(anon);} catch (Exception e) {return new Target();}}
private Target process(String input) {return new Target();}
{Runnable anon = new Runnable() {@Overridepublic void run() {process(new Target());}};anon.run();}}
protected class Target extends SuperTarget {class InnerAnon {void execute() {}}
{Runnable action = new Runnable() {@Overridepublic void run() {System.out.println(superField1);}};action.run();}}