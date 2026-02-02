package test;
public class SourceClass {class MemberInner {class SourceInnerRec {public Object moveMethod(PublicTarget target, String... args) {PublicTarget.StaticNested nested = new PublicTarget.StaticNested();for (String arg : args) {nested.process(arg + SourceClass.this.getOuterData());}return nested;}}}
{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
private String getOuterData() {return "outer";}}
public class PublicTarget {public static class StaticNested {public void process(String data) {}}}