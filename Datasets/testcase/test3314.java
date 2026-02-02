package test;
public class SourceClass {{Runnable anon = new Runnable() {@Overridepublic void run() {}};}
private ProtectedTarget.StaticNested moveMethod(ProtectedTarget target) {class LocalInner {}LocalInner local = new LocalInner();
try {ProtectedTarget.StaticNested staticNested = new ProtectedTarget.StaticNested();staticNested.process(ProtectedTarget.STATIC_FIELD);target.useStaticNested(staticNested);return staticNested;} finally {}}}
protected class ProtectedTarget {public static final String STATIC_FIELD = "targetStaticData";
public static class StaticNested {public void process(String data) {}}
public void useStaticNested(StaticNested nested) {}}