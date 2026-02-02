package test;
public class Target {public int status = 0;
public static class StaticNested {public class InnerRec {public String type;
public void setType(String type) {this.type = type;}}}}
protected class Source {private static void process(Target target) {Target.StaticNested nested = new Target.StaticNested();Target.StaticNested.InnerRec rec = nested.new InnerRec();
// Anonymous inner classRunnable anonTask = new Runnable() {@Overridepublic void run() {rec.setType("anonymous");}};anonTask.run();
// Local inner classclass LocalProcessor {void handle(Target.StaticNested.InnerRec r) {switch (r.type) {case "static":target.status = 1;break;case "anonymous":target.status = 2;break;default:target.status = 0;}}}
new LocalProcessor().handle(rec);}}